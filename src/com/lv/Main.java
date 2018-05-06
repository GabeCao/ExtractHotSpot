package com.lv;

import com.lv.Utils.Points;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        //创建 HotSpot
        HotSpot[][] hotSpots = createHotSpots();

        String inFileFolderPath = "C:\\E\\dataSet\\2018-05-04\\extracted-data\\2009-03-09";
        File inFileFolder = new File(inFileFolderPath);
        File[] inFiles = inFileFolder.listFiles();

        File file = inFiles[0];
        String line;
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //针对第一个特殊的Point
        //创建第一个Trajectory
        Trajectory t1 = new Trajectory(hotSpots);
        line = bufferedReader.readLine();
        //获取第一个Point
        String[] data1 = line.split(",");
        String date_string1 = data1[4] + " " + data1[5];
        Date date1 = simpleDateFormat.parse(date_string1);
        String x_axis_string1 = data1[6];
        String y_axis_string1 = data1[7];
        double x_axis1 = Double.parseDouble(x_axis_string1);
        double y_axis1 = Double.parseDouble(y_axis_string1);
        Point point1 = new Point(x_axis1,y_axis1,date1);
        //找到第一个轨迹点的附近的HotSpot
        ArrayList<HotSpot> belongedHotSpot = Points.getNearHotSpot(point1,hotSpots);
        point1.setBelongedHotSpots(belongedHotSpot);
        //第一个Trajectory
        for (HotSpot hotSpot : point1.getBelongedHotSpots() ) {
            t1.map.put(hotSpot, 1);
        }

        //记录上一个Point 和 Trajectory
        Point prePoint = point1;
        Trajectory preTrajectory = t1;
        //保存所有的Trajectory
        ArrayList<Trajectory> trajectories = new ArrayList<>();
        trajectories.add(preTrajectory);
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            String date_string = data[4] + " " + data[5];
            Date date = simpleDateFormat.parse(date_string);
            String x_axis_string = data[6];
            String y_axis_string = data[7];
            double x_axis = Double.parseDouble(x_axis_string);
            double y_axis = Double.parseDouble(y_axis_string);
            //创建一个Point
            Point point = new Point(x_axis,y_axis,date);

            //判断两个时间差是否小于五分钟
            long timeDiff = point.getDate().getTime() - prePoint.getDate().getTime();
            if (timeDiff < 300000) {
                //如果小于五分钟,找到当前point 属于的附近的HotSpot
                ArrayList<HotSpot> hotSpotArrayList = Points.getNearHotSpot(point,hotSpots);
                point.setBelongedHotSpots(hotSpotArrayList);
                //遍历所有的 HotSpot,如果当前的 hotspot 没有在上一个 轨迹点中，则认为这个hotspot 被访问一次
                for (HotSpot hotSpot : hotSpotArrayList) {
                    if (!prePoint.getBelongedHotSpots().contains(hotSpot)) {
                        preTrajectory.map.put(hotSpot,preTrajectory.map.get(hotSpot) + 1);
                    }
                }

                prePoint = point;
            } else {
                //如果时间大于五分钟，则 建立新的Trajectory，并添加到 Trajectorys 中
                Trajectory newTrajectory = new Trajectory(hotSpots);
                trajectories.add(newTrajectory);
                //找到 Point 附近的所有HotSpot
                ArrayList<HotSpot> hotSpotArrayList = Points.getNearHotSpot(point,hotSpots);
                point.setBelongedHotSpots(hotSpotArrayList);
                //对所有的 HotSpot Trajectory 都算访问一次
                for (HotSpot hotSpot : hotSpotArrayList) {
                    newTrajectory.map.put(hotSpot,1);
                }
                prePoint = point;
                preTrajectory = newTrajectory;
            }
        }
        System.out.println("获得所有的 Trajectory.........." + new Date());
        ArrayList<HotSpot> hotSpotArrayList = geedilySelectHotSpot(trajectories,hotSpots);
        for (HotSpot hotSpot : hotSpotArrayList) {
            System.out.println(hotSpot);
        }
    }

    public static HotSpot[][] createHotSpots() {
        Area area = new Area();
        double r = 10;
        double hotSpotDistance = Math.sqrt(3) * r;
        HotSpot[][] hotSpots = new HotSpot[74][95];
        int m = 0;
        for (double i = (hotSpotDistance / 2); i <= area.x; i += hotSpotDistance, m++) {
            int n = 0;
            for (double j = (hotSpotDistance / 2); j <= area.y; j +=hotSpotDistance, n++) {
                HotSpot hotSpot = new HotSpot(i, j, m, n);
                hotSpots[m][n] = hotSpot;
            }
        }
        return hotSpots;
    }

    private static ArrayList<HotSpot> geedilySelectHotSpot(ArrayList<Trajectory> trajectories, HotSpot[][] hotSpots) {
        for (int i = 0; i < hotSpots.length; i++) {
            for (int j = 0; j < hotSpots[i].length; j++) {
                HotSpot hotSpot = hotSpots[i][j];
                int times = 0;
                for (Trajectory trajectory : trajectories) {
                    if (trajectory.map.get(hotSpot) != 0) {
                        hotSpot.trajectories.add(trajectory);
                        times += trajectory.map.get(hotSpot);
                    }
                }
                hotSpot.setTimes(times);
            }
        }
        System.out.println("给所有的hotspot 添加 访问的次数 和 Trajectory............" + new Date());
        SelectedHotSpots selectedHotSpots = new SelectedHotSpots();
        ArrayList<HotSpot> hotSpotArrayList = new ArrayList<>();
        /*for (int i = 0; i < hotSpots.length; i++) {
            for (int j = 0; j < hotSpots[i].length; j++) {
                HotSpot hotSpot = hotSpots[i][j];
                hotSpotArrayList.add(hotSpot);
            }
        }*/

        for (HotSpot[] hotSpotsArray : hotSpots) {
            hotSpotArrayList.addAll(Arrays.asList(hotSpotsArray));
        }
        while (isTrajectoryLeft(trajectories)) {

            HotSpot maxHotSpot = hotSpotArrayList.get(0);
            int max = maxHotSpot.getTimes();
            for (HotSpot hotSpot : hotSpotArrayList) {
                ArrayList<Trajectory> hotSpotTrajectory = hotSpot.trajectories;
                int times = 0;
                for (Trajectory trajectory : hotSpotTrajectory) {
                    if (trajectory.isSelected == false) {
                        times++;
                    }
                }
                hotSpot.setTimes(times);
                if (hotSpot.getTimes() > max) {
                    max = hotSpot.getTimes();
                    maxHotSpot = hotSpot;
                }
            }
            if (!selectedHotSpots.hotSpots.contains(maxHotSpot)) {
                selectedHotSpots.hotSpots.add(maxHotSpot);
                for (Trajectory trajectory : maxHotSpot.trajectories) {
                    trajectory.isSelected = true;
                }
            }
            hotSpotArrayList.remove(maxHotSpot);
        }

        return selectedHotSpots.hotSpots;
    }
    private static boolean isTrajectoryLeft(ArrayList<Trajectory> trajectories) {
        int selectedTrajectories = 0;
        for (Trajectory trajectory: trajectories) {
            if (trajectory.isSelected) {
                selectedTrajectories ++;
            }
        }
        return selectedTrajectories != trajectories.size();
    }
}



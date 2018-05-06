package com.lv.Utils;

import com.lv.HotSpot;
import com.lv.Point;

import java.util.ArrayList;

public class Points {

    private static double getDistanceBetweenAndPontAndHotSpot(Point p1, HotSpot p2) {
        return Math.sqrt(Math.abs((p1.getX() - p2.getX())* (p1.getX() - p2.getX())+(p1.getY() - p2.getY())* (p1.getY() - p2.getY())));
    }

    public  static ArrayList<HotSpot> getNearHotSpot(Point point, HotSpot[][] hotSpots) {
        ArrayList<HotSpot> hotSpotArrayList = new ArrayList<>();
        for (int i = 0; i < hotSpots.length; i++) {
            for (int j = 0; j <hotSpots[i].length; j++) {
                if (getDistanceBetweenAndPontAndHotSpot(point,hotSpots[i][j]) < 10) {
                    hotSpotArrayList.add(hotSpots[i][j]);
                }
            }
        }
        return hotSpotArrayList;
    }
}

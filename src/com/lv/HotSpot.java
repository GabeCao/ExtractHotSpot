package com.lv;

import java.util.ArrayList;

public class HotSpot {
    //HotSpot的坐标
    private double x;
    private double y;
    //HotSpot 在二维数组中的位置
    private int m;
    private int n;
    //HotSpot 被 Trajectory 访问多少次，初始默认为0
    private int times = 0;
    //访问 HotSpot 的 Trajectory
    ArrayList<Trajectory> trajectories = new ArrayList<>();

    public HotSpot(double x, double y, int m, int n) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.n = n;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "HotSpot{" +
                "x=" + x +
                ", y=" + y +
                ", m=" + m +
                ", n=" + n +
                ", times=" + times +
                '}';
    }
}

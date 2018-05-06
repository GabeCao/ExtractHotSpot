package com.lv;

import java.util.ArrayList;
import java.util.Date;

public class Point {
    private double x;
    private double y;
    private ArrayList<HotSpot> belongedHotSpots;
    private Date date;

    public ArrayList<HotSpot> getBelongedHotSpots() {
        return belongedHotSpots;
    }

    public void setBelongedHotSpots(ArrayList<HotSpot> belongedHotSpots) {
        this.belongedHotSpots = belongedHotSpots;
    }

    public Point(double x, double y, Date date) {
        this.x = x;
        this.y = y;
        this.date = date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", date=" + date +
                '}';
    }
}

package com.lv;


import java.util.*;

public class TEst {
    public static void main(String[] args) {
        /*HotSpot[][] hotSpots = Main.createHotSpots();
        ArrayList<HotSpot> p1 = new ArrayList<>();
        p1.add(hotSpots[0][1]);
        p1.add(hotSpots[0][2]);
        Trajectory trajectory = new Trajectory(hotSpots);
        for (HotSpot hotSpot : p1) {
            trajectory.map.put(hotSpot,trajectory.map.get(hotSpot) + 1);
        }

        ArrayList<HotSpot> p2 = new ArrayList<>();
        p2.add(hotSpots[0][1]);
        p2.add(hotSpots[0][2]);

        for (HotSpot p2_hotSpot : p2) {
            if (!p1.contains(p2_hotSpot)) {
                trajectory.map.put(p2_hotSpot,trajectory.map.get(p2_hotSpot) + 1);
            }
        }
        for (Map.Entry<HotSpot,Integer> entry : trajectory.map.entrySet()) {
            System.out.println(entry.getKey() + "      " + entry.getValue());
        }*/
        Dog dog = new Dog("大黄");
        for (int i = 0; i < 3; i++) {
            dog = new Dog(i+"");
        }
        System.out.println(dog);
    }


}
class Dog{
    String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}

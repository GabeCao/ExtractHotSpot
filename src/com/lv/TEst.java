package com.lv;


import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class TEst {
    public static void main(String[] args) throws Exception{
        HotSpot[][] hotSpots = Main.createHotSpots();
        File outFile = new File("C:\\E\\dataSet\\2018-05-06\\HotSpots\\hotSpots.txt");
        if (!outFile.exists()) {
            outFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(outFile,true);
        for (int i = 0; i < hotSpots.length; i++) {
            for (int j = 0; j < hotSpots[i].length; j++) {
                fileWriter.write(hotSpots[i][j].getX() + "," + hotSpots[i][j].getY() + "\n");
            }
        }
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

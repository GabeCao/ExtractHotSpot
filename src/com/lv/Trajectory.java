package com.lv;

import java.util.HashMap;

public class Trajectory {
    HashMap<HotSpot,Integer> map = new HashMap<>();
    Boolean isSelected = false;

    public Trajectory(HotSpot[][] hotSpots) {
        for (int i = 0; i < hotSpots.length; i++) {
            for (int j = 0; j < hotSpots[i].length; j++) {
                map.put(hotSpots[i][j],0);
            }
        }
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}

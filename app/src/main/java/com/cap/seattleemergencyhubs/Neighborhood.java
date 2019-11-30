package com.cap.seattleemergencyhubs;

import android.util.EventLogTags;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Neighborhood {

    private String name;
    private String mapImagePath;
    private ArrayList<Hub> currentNeighborhoodHubs;

    public Neighborhood(String name, String mapImagePath, ArrayList<Hub> neighborhoodHubs){
        this.name = name;
        this.mapImagePath = mapImagePath;
        this.currentNeighborhoodHubs = neighborhoodHubs;
    }
    //do not remove this constructor - needed for Firebase database data read
    public Neighborhood(){

    }

    public String getName() {
        return name;
    }
    public String getMapImagePath(){
        return mapImagePath;
    }

    public ArrayList<Hub> getCurrentNeighborhoodHubs(){
        return currentNeighborhoodHubs;
    }
}

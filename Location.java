package com.company;

/**
 * Created by Alex Laquitara on 6/7/2017.
 */

public class Location {
    private String name;  //Name of the location

    //Room constructor.  Name identifies the name it will be given.
    public Location(String name){
        this.name = name;
    }

    //Returns the name of the room.
    public String getName(){
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }
}

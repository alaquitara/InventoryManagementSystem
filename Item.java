package com.company;

/**
 * Created by Alex Laquitara on 6/7/2017.
 */
public class Item {

   // private String id; //id reference to the item
    private int level;  //amount of item in inventory
    private String name; //name of item
    private Location location; //physical location of item

    //Constuctor for the Item class
    public Item(String name, int level, Location location){
        this.name = name;
        this.level =  level;
        this.location = location;
    }

    //method to return item name
    public String getName(){
        return this.name;
    }

    protected void setName(String name){
        this.name = name;
    }

    //method to return item level
    public int getLevel(){
        return this.level;
    }

    protected void setLevel(int level){
        this.level = level;
    }

    //method to return item location
    public Location getLocation(){
        return this.location;
    }

    protected void setLocation(Location location){
        this.location = location;
    }

    //removes x amount of items from inventory
    public void pickItem(int x ){
        this.level -=x;
    }

    //adds x amount of items from inventory
    public void restockItem(int x){
        this.level += x;
    }
}

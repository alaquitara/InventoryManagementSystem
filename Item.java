package com.company;

/**
 * Created by Alex Laquitara on 6/7/2017.
 */
public class Item {

    private int level;  //amount of item in inventory
    private String name; //name of item.  Also used as productId
    private String location; //physical location of item

    private Item(){}//prevent default

    //Constuctor for the Item class
    protected Item(String name, int level, String location){
        this.name = name;
        this.level =  level;
        this.location = location;
    }

    //method to return item name
    protected String getName(){
        return this.name;
    }

    //rename an item
    protected void setName(String name){
        this.name = name;
    }

    //method to return item level
    protected int getLevel(){
        return this.level;
    }

    //change level value of an item
    protected void setLevel(int level){
        this.level = level;
    }

    //method to return item location
    protected String getLocation(){
        return this.location;
    }

    //change location of an item
    protected void setLocation(String location){
        this.location = location;
    }

    //removes x amount of items from inventory
    protected void pickItem(int x ){
        this.level -=x;
    }

    //adds x amount of items from inventory
    protected void restockItem(int x){
        this.level += x;
    }
}

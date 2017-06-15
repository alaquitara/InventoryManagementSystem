package com.company;

/**
 * Created by Alex on 6/7/2017.
 */
public class PickingResult  {
    private Item item;

    protected PickingResult (Item item) {
        this.item = item;
    }

    protected Item getItem(){
        return this.item;
    }

    protected String getItemLoc(){return this.item.getLocation();}

    protected String getItemName(){return this.item.getName();}

    protected int getItemLevel(){return this.item.getLevel();}
}

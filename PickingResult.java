package com.company;

/**
 * Created by Alex on 6/7/2017.
 */
public class PickingResult  {
    private Item item;
    private Location location;


    public PickingResult (Item item) {
        this.item = item;
    }

    public Item getItem(){
        return this.item;
    }
}

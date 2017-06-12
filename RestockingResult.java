package com.company;

/**
 * Created by Alex on 6/7/2017.
 */
public class RestockingResult {
    private Item item;

    public RestockingResult(Item item){
        this.item = item;
    }

    public Item getItem(){
        return this.item;
    }
}

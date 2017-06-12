package com.company;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Alex Laquitara on 6/7/2017.
 */
public class Inventory implements InventoryManagementSystem {

    //After a lot of research I found ConcurrentHashMap to be an ideal data structure to maintain thread-safe properties
    private ConcurrentHashMap<String, Item> items;
    private ConcurrentHashMap<String, Location> locations;

    //constructor
    public Inventory(){
        this.items = new ConcurrentHashMap<String, Item>();
        this.locations = new ConcurrentHashMap<String, Location>();
    }

    /*adds an item to the items map.  Takes an item parameter and uses the item name as a key
    if there exists an item with the same name in inventory, the new item is added to the old.
    This ensures that all like items are in the same location and that levels are accurate.
    */
    public Inventory addItem(Item item){
        String name = item.getName();
        if(items.containsKey(name)) {
            item.setLocation(items.get(name).getLocation());
            int newLevel = item.getLevel();
            int oldLevel = items.get(name).getLevel();
            newLevel += oldLevel;
            item.setLevel(newLevel);
        }
        items.put(item.getName(), item);
        return this;
    }
    //adds an inventory location to the locations map.  Takes a location parameter and the location name is set as key
    public Inventory addLocation(Location location){
        locations.put(location.getName(), location);
        return this;
    }

    //returns items in the map
    public ConcurrentHashMap <String, Item> getItems(){
        return this.items;
    }

    //returns locations in the map
    public ConcurrentHashMap<String, Location> getLocation(){
        return this.locations;
    }

    //from the IMS interface.  It essentially removes an item from inventory and adjusts levels.
    @Override
    public PickingResult pickProduct(String productId, int amountToPick) {
        Item item = items.get(productId);
        item.pickItem(amountToPick); //item.level -= amountToPick
        PickingResult pickingResults = new PickingResult(item);
        return pickingResults;
    }

    //from the IMS interface.  This method will place an item back into inventory and adjust level
    @Override
    public RestockingResult restockProduct(String productId, int amountToRestock) {
        Item item = items.get(productId);
        item.restockItem(amountToRestock); //item.level += amountToRestock
        RestockingResult restockingResult = new RestockingResult(item);
        return restockingResult;
    }

    //returns information about an item using the parameter name as the key value in the items map.
    public void getItem(String name){
        Item item = this.items.get(name);
        System.out.println("Name: " + item.getName() + "     Level: " + item.getLevel() + "     Location: " + item.getLocation().getName());
    }

    //searches the item map for an item by name, returns true if item is in inventory.
    public boolean checkItem(String productId){
        boolean found = false;
        if (getItems().containsKey(productId)){
            found = true;
        }
        return found;
    }

    //prints out a list of all items by key (item.name)
    public void printItems() {
        System.out.println("Items contents");
        for (String key : items.keySet()) {
            System.out.println(key);
        }
    }
    //prints out a list of all locations by key (location.name)
    public void printLocations() {
        System.out.println("Locations contents");
        for (String key : locations.keySet()) {
            System.out.println(key);
        }
    }
}

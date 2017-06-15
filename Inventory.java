package com.company;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.security.InvalidParameterException;
/**
 * Created by Alex Laquitara on 6/7/2017.
 */
public class Inventory implements InventoryManagementSystem {

    /*After a lot of research I found ConcurrentHashMap to be an ideal data structure to maintain thread-safe properties
    when used with synchronized methods.  I still need to look further into optimistic vs pessimistic concurrency implementation  */
    private ConcurrentHashMap<String, Item> items;

    //constructor
    public Inventory() {
        this.items = new ConcurrentHashMap<String, Item>();
    }

    /*adds an item to the items map.  Takes an item parameter and uses the item name as a key
    if there exists an item with the same name in inventory, the new item is added to the old.
    This ensures that all like items are in the same location and that levels are accurate.
    */
    public synchronized Inventory addItem(Item item) {
        String name = item.getName();
        if (items.containsKey(name)) {
            item.setLocation(items.get(name).getLocation());
            int newLevel = item.getLevel();
            int oldLevel = items.get(name).getLevel();
            newLevel += oldLevel;
            item.setLevel(newLevel);
        }
        items.put(item.getName(), item);
        return this;
    }

    //returns items in the map
    public ConcurrentHashMap<String, Item> getItems() {
        return this.items;
    }

    //returns name of item in map
    public Item getItemName(String name){return this.items.get(name);}


    /*from the IMS interface.  It essentially removes an item from inventory and adjusts levels.
    https://stackoverflow.com/questions/14947723/is-concurrenthashmap-totally-safe */
    @Override
    public synchronized PickingResult pickProduct(String productId, int amountToPick) throws InvalidParameterException {
        Item item = items.get(productId);
        if (amountToPick < 0) {
            try {
                throw new InvalidParameterException();
            } catch (InvalidParameterException e) {
                System.out.println("cannot pick negative amount");
                item.pickItem(0); //leave the level where it was
                PickingResult pickingResults = new PickingResult(item);
                return pickingResults;
            }
        }
        else {
            item.pickItem(amountToPick); //item.level -= amountToPick
            PickingResult pickingResults = new PickingResult(item);
            return pickingResults;
        }
    }

    /*from the IMS interface.  This method will place an item back into inventory and adjust level
    https://stackoverflow.com/questions/14947723/is-concurrenthashmap-totally-safe */
    @Override
    public synchronized RestockingResult restockProduct(String productId, int amountToRestock)  throws InvalidParameterException {
        Item item = items.get(productId);
        if (amountToRestock < 0){
            try {
                throw new InvalidParameterException();
            } catch (InvalidParameterException e) {
                System.out.println("cannot restock negative amount");
                item.restockItem(0); //Leave the level where it was
                RestockingResult restockingResult = new RestockingResult(item);
                return restockingResult;
            }
        }
        else {
            item.restockItem(amountToRestock); //item.level += amountToRestock
            RestockingResult restockingResult = new RestockingResult(item);
            return restockingResult;
        }
    }

    //returns information about an item using the parameter name as the key value in the items map.
    public void getItem(String name) {
        Item item = this.items.get(name);
        System.out.println("Name: " + item.getName() + "     Level: " + item.getLevel() + "     Location: " + item.getLocation());
    }

    //searches the item map for an item by name, returns true if item is in inventory.
    public synchronized boolean checkItem(String productId) {
        boolean found = false;
        if (getItems().containsKey(productId)) {
            found = true;
        }
        return found;
    }

    //prints out a list of all items by key (item.name)
    public synchronized void printItems() {
        System.out.println("ITEMS CONTENTS:");
        for (String key : items.keySet()) {
            System.out.println(key);
        }
    }

    //Ties all picking methods together for ease of use... picks an item from inventory
    public void pick() {
        System.out.println("Enter the name of the item you wish to pick.");
        Scanner scan = new Scanner(System.in);
        String productId = scan.nextLine();
        boolean fd = checkItem(productId);
        if (!fd) {
            System.out.println("Item not found\n");
            return;
        }
        int amountToPick = 0;
        Item item = getItemName(productId);
        do {
            System.out.println("Enter the quantity you wish to pick.");
            Scanner sc = new Scanner(System.in);
            amountToPick = Integer.parseInt(sc.nextLine());
            if ((amountToPick > item.getLevel()) || (amountToPick < 0)) {
                System.out.println("Cannot complete request");
                System.out.println("Requested amount cannot be negative or exceed the amount on hand");
            }
        } while ((amountToPick > item.getLevel()) || (amountToPick < 0));
        PickingResult pickingResult = pickProduct(productId, amountToPick);
        System.out.println("The quantity of " + pickingResult.getItemName() + " is now  " +
                pickingResult.getItemLevel());
        System.out.println(pickingResult.getItemName() + " can be found in " +
                pickingResult.getItemLoc());
    }

    //Ties all restocking methods together for ease of use... restocks an item in inventory
    public void restock() {
        System.out.println("Enter the name of the item you wish to restock.");
        Scanner s = new Scanner(System.in);
        String productI = s.nextLine();
        boolean found = checkItem(productI);
        if (!found) {
            System.out.println("Item not found\n");
            return;
        }
        int amountToRestock = 0;
        do {
            System.out.println("Enter the quantity you wish to restock.");
            Scanner sc = new Scanner(System.in);
            amountToRestock = Integer.parseInt(sc.nextLine());
            if (amountToRestock < 0) {
                System.out.println("Cannot complete request");
                System.out.println("Cannot restock a negative amount");
            }
        }while(amountToRestock < 0);
        RestockingResult restockingResult = restockProduct(productI, amountToRestock);
        System.out.println("The quantity of " + restockingResult.getItemName()+ " is " +
                restockingResult.getItemLevel());
        System.out.println(restockingResult.getItemName() + " can be found in " +
                restockingResult.getItemLoc());
    }

    //checks if an item exists then displays information about it if it does.
    public  void viewItem(){
        System.out.println("Enter the name of the item you wish to view.");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        boolean fd = checkItem(name);
        if (fd) {
            getItem(name);
            System.out.println();
        }
        else{
            System.out.print("Item not found \n");
            System.out.println();
        }
    }
}


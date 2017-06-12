package com.company;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import static java.lang.System.exit;


public class Main {
    public static Inventory inventory = new Inventory();

    public static void main(String[] args) {
        initInventory(inventory);
        int choice = 0;
        do {
            choice = menu();
            switch (choice) {
                case 1: //pick an item
                    System.out.println("Enter the name of the item you wish to pick.");
                    Scanner scan = new Scanner(System.in);
                    String productId = scan.nextLine();
                    boolean fd = inventory.checkItem(productId);
                    if (!fd) {
                        System.out.println("Item not found\n");
                        break;
                    }
                    int amountToPick = 0;
                    Item item = inventory.getItems().get(productId);
                    do {
                        System.out.println("Enter the quantity you wish to pick.");
                        Scanner sc = new Scanner(System.in);
                        amountToPick = Integer.parseInt(sc.nextLine());
                        if (amountToPick > item.getLevel()) {
                            System.out.println("Cannot complete request");
                            System.out.println("Requested amount exceeds quantity on hand");
                        }
                    }while(amountToPick > item.getLevel());
                    PickingResult pickingResult = inventory.pickProduct(productId, amountToPick);
                    System.out.println("The quantity of " + pickingResult.getItem().getName() + " is now  " +
                            pickingResult.getItem().getLevel());
                    System.out.println(pickingResult.getItem().getName() + " can be found in " +
                            pickingResult.getItem().getLocation().getName());
                    break;
                case 2://restock an item
                    System.out.println("Enter the name of the item you wish to restock.");
                    Scanner s = new Scanner(System.in);
                    String productI = s.nextLine();
                    boolean found = inventory.checkItem(productI);
                    if (!found) {
                        System.out.println("Item not found\n");
                        break;
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
                    RestockingResult restockingResult = inventory.restockProduct(productI, amountToRestock);
                    System.out.println("The quantity of " + restockingResult.getItem().getName() + " is " +
                            restockingResult.getItem().getLevel());
                    System.out.println(restockingResult.getItem().getName() + " can be found in " +
                            restockingResult.getItem().getLocation().getName());
                    break;
                case 3: //get information about an item
                    viewItem();
                    break;
                case 4: //print all items
                    inventory.printItems();
                    break;
                case 5: //print all locations
                    inventory.printLocations();
                    break;
                case 6: //quit
                   exitProgram();
            }
        }while(choice != 6);
    }

    //will display information about an item if it exists in inventory.
    public static void viewItem(){
        System.out.println("Enter the name of the item you wish to view.");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        boolean fd = inventory.checkItem(name);
        if (fd) {
            inventory.getItem(name);
            System.out.println();
        }
        else{
            System.out.print("Item not found \n");
            System.out.println();
        }
    }

    //closes the program when prompted by user
    public static void exitProgram(){
        System.out.println("Closing Inventory Management System....\n");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Thank you for using our software");
        exit(0);
    }

    //provides menu options for a user to interact with the software via console
    public static int menu() {
        int choice = 0;
        System.out.println("Welcome to the Inventory Management System.  Please pick from the following:");
        while ((choice < 1) || (choice > 6)) {
            System.out.println("Enter corresponding number");
            System.out.println("1: Pick item");
            System.out.println("2: Restock item");
            System.out.println("3: View item details");
            System.out.println("4: List all Items");
            System.out.println("5: List all Locations");
            System.out.println("6: Exit program");
            Scanner sc = new Scanner(System.in);
            choice = Integer.parseInt(sc.nextLine());
        }
        return choice;
    }

    //initializes an Inventory class with several arbitrary items and locations for testing purposes.
    public static Inventory initInventory(Inventory inventory){
        Location loc1 = new Location("Location 1");
        Location loc2 = new Location("Location 2");
        Location loc3 = new Location("Location 3");
        Location loc4 = new Location("Location 4");
        Location loc5 = new Location("Location 5");

        Item item0 = new Item("Item0", 2, loc1);
        Item item1 = new Item("Item1", 4, loc2);
        Item item2 = new Item("Item2", 0, loc3);
        Item item3 = new Item("Item3", 1, loc4);
        Item item4 = new Item("Item4", 2, loc5);
        Item item5 = new Item("Item5", 3, loc1);
        Item item6 = new Item("Item6", 7, loc2);
        Item item7 = new Item("Item7", 6, loc3);
        Item item8 = new Item("Item8", 2, loc4);
        Item item9 = new Item("Item0", 1, loc5);

        inventory.addLocation(loc1);
        inventory.addLocation(loc2);
        inventory.addLocation(loc3);
        inventory.addLocation(loc4);
        inventory.addLocation(loc5);

        inventory.addItem(item0);
        inventory.addItem(item1);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);
        inventory.addItem(item5);
        inventory.addItem(item6);
        inventory.addItem(item7);
        inventory.addItem(item8);
        inventory.addItem(item9);

        return inventory;
    }
}

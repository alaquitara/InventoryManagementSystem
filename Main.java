package com.company;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import static java.lang.System.exit;

//where all the magic happens
public class Main {
    public static Inventory inventory = new Inventory();

    public static void main(String[] args) {
        initInventory(inventory);
        int choice;
        do {
            choice = menu();
            switch (choice) {
                case 1: //pick an item
                    inventory.pick();
                    break;
                case 2://restock an item
                    inventory.restock();
                    break;
                case 3: //get information about an item
                    inventory.viewItem();
                    break;
                case 4: //print all items
                    inventory.printItems();
                    break;
                case 5: //quit
                   exitProgram();
            }
        }while(choice != 5);
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
        while ((choice < 1) || (choice > 5)) {
            System.out.println("Enter corresponding number");
            System.out.println("1: Pick item");
            System.out.println("2: Restock item");
            System.out.println("3: View item details");
            System.out.println("4: List all Items");
            System.out.println("5: Exit program");
            Scanner sc = new Scanner(System.in);
            choice = Integer.parseInt(sc.nextLine());
        }
        return choice;
    }

    /*initializes an Inventory class with several arbitrary items for testing purposes.
    item 0 and item 9 have the same name so I wanted to be sure that levels and locations were adjusted accordingly
    */
    public static Inventory initInventory(Inventory inventory){

        Item item0 = new Item("Item0", 2, "loc1");
        Item item1 = new Item("Item1", 4, "loc2");
        Item item2 = new Item("Item2", 0, "loc3");
        Item item3 = new Item("Item3", 1, "loc4");
        Item item4 = new Item("Item4", 2, "loc5");
        Item item5 = new Item("Item5", 3, "loc1");
        Item item6 = new Item("Item6", 7, "loc2");
        Item item7 = new Item("Item7", 6, "loc3");
        Item item8 = new Item("Item8", 2, "loc4");
        Item item9 = new Item("Item0", 1, "loc5");

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

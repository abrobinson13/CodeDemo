package org.toolCo;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        RentalRecord customerRental = new RentalRecord();
        ArrayList<Tool> inventoryList = inventory.createSampleInventory();

        Checkout checkout = new Checkout(inventoryList);

        //User input
        customerRental = checkout.gatherUserInput(customerRental);

        //Calculations
        customerRental = checkout.doFinalCalculations(customerRental);

        //Print rental agreement
        customerRental.printRentalAgreement();

    }
}
package org.toolCo;

import java.util.ArrayList;

import static org.toolCo.ToolBrandConstants.*;

public class Main {

    public static void main(String[] args) {

        ToolType ladder = new ToolType("Ladder", 1.99, true, true, false);
        ToolType chainsaw = new ToolType("Chainsaw", 1.49, true, false, true);
        ToolType jackhammer = new ToolType("Jackhammer", 2.99, true, false, false);

        Tool theOnlyChainsaw = new Tool("CHNS", chainsaw, STIHL);
        Tool theOnlyLadder = new Tool("LADW", ladder, WERNER);
        Tool deWaltJackhammer = new Tool("JAKD", jackhammer, DEWALT);
        Tool ridgidJackhammer = new Tool("JAKR", jackhammer, RIDGID);

        ArrayList<Tool> inventoryList = new ArrayList<>();
        inventoryList.add(theOnlyChainsaw);
        inventoryList.add(theOnlyLadder);
        inventoryList.add(deWaltJackhammer);
        inventoryList.add(ridgidJackhammer);

        RentalRecord customerRental = new RentalRecord();

        Checkout checkout = new Checkout(inventoryList);

        //User input
        customerRental = checkout.gatherUserInput(customerRental);

        //Calculations
        customerRental = checkout.doFinalCalculations(customerRental);

        //Print rental agreement
        customerRental.printRentalAgreement();

    }
}
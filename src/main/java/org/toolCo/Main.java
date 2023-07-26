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

        rentalRecord customerRental = new rentalRecord();

        Checkout checkout = new Checkout(inventoryList);

        //User input
        customerRental.setToolBeingRented( checkout.selectTool() );
        customerRental.setRentalDurationDays( checkout.selectRentalDuration() );
        customerRental.setCheckoutDate( checkout.selectCheckoutDate() );
        customerRental.setFormattedCheckoutDate( checkout.formatCheckoutDate(customerRental.getCheckoutDate()) );
        customerRental.setDiscountPercent( checkout.selectDiscountPercent() );

        //Calculations
        customerRental.setReturnDate( checkout.calculateReturnDate(customerRental.getCheckoutDate(), customerRental.getRentalDurationDays()) );
        customerRental.setFormattedReturnDate( checkout.formatReturnDate(customerRental.getReturnDate()) );
        customerRental.setDailyRentalCharge( customerRental.getToolBeingRented().getType().getDailyCharge() );
        customerRental.setChargeDays( checkout.calculateChargeDays(customerRental.getCheckoutDate(),
                                                                    customerRental.getReturnDate(),
                                                                    customerRental.getRentalDurationDays(),
                                                                    customerRental.getToolBeingRented().getType().getWeekendCharge(),
                                                                    customerRental.getToolBeingRented().getType().getHolidayCharge()
                                                                    ) );
        double preDiscountChargeUnrounded = customerRental.getDailyRentalCharge() * customerRental.getChargeDays();
        customerRental.setPreDiscountCharge( Math.round(preDiscountChargeUnrounded * 100.0) / 100.0 );
        //ToDo remove print line debugging
        System.out.println("preDiscountCharge: " + customerRental.getPreDiscountCharge());
        double discountAmountUnrounded = (customerRental.getDiscountPercent()/100.00) * customerRental.getPreDiscountCharge();
        customerRental.setDiscountAmount( Math.round(discountAmountUnrounded * 100.0) / 100.0 );
        double finalChargeUnrounded = customerRental.getPreDiscountCharge() - customerRental.getDiscountAmount();
        customerRental.setFinalCharge( Math.round(finalChargeUnrounded * 100.0) / 100.0 );

        //Print rental agreement
        customerRental.printRentalAgreement();

    }
}
package org.toolRental;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static org.toolRental.ToolBrandConstants.*;

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

        //ToDo Move most of the following logic blocks to a UserInputClass and a DataCalculations class

        System.out.println("Welcome to ToolCo! Which tool is being rented?");
        Scanner input = new Scanner(System.in);
        int selection = 0;
        int toolNumber = 1;
        for (Tool tool : inventoryList){
            System.out.printf("%d - %s (%s %s)\n", toolNumber, tool.getCode(), tool.getBrand(), tool.getType().getTypeName());
            toolNumber++;
        }
        System.out.print("Choice: ");

        try{
            selection = Integer.parseInt(input.next());
            if (selection > inventoryList.size()){
                System.out.println("Invalid option, try again");
            }
        }
        catch (NumberFormatException e){
            System.out.println("Input not a number, try again.");
        }
        switch(selection){
            case 1:
                customerRental.setToolBeingRented(theOnlyChainsaw);
            case 2:
                customerRental.setToolBeingRented(theOnlyLadder);
            case 3:
                customerRental.setToolBeingRented(deWaltJackhammer);
            case 4:
                customerRental.setToolBeingRented(ridgidJackhammer);
        }
        System.out.print("For how many days? ");
        //ToDo validate user input is >= 1
        customerRental.setRentalDurationDays( Integer.parseInt(input.next()) );

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
        String yesNo;
        do {
            System.out.print("Is the checkout day today? y/n ");
            yesNo = input.next();
            if (Objects.equals(yesNo, "y")) {
                customerRental.setFormattedCheckoutDate( dtf.format(LocalDate.now()) );
                customerRental.setCheckoutDate(LocalDate.now());
            } else if (Objects.equals(yesNo, "n")) {
                //The following 4 lines are prone to exceptions as we're relying on proper user input.
                //The user input needs to be properly validated. Such a task is left as an exercise for the reader.
                System.out.println("Enter the checkout date (mm/dd/yy): ");
                String checkoutDate = input.next();
                customerRental.setFormattedCheckoutDate(checkoutDate);
                customerRental.setCheckoutDate(LocalDate.parse(checkoutDate, dtf));
            }
            else{
                System.out.println("Invalid input, enter either y or n");
            }
        }while(!Objects.equals(yesNo, "y") && !Objects.equals(yesNo, "n"));

        //ToDo remove print line debugging
        System.out.println("Checkout date: " + customerRental.getFormattedCheckoutDate());
        System.out.println("Checkout date: " + customerRental.getCheckoutDate());

        System.out.print("Enter discount percent (0-100): ");
        //ToDo Validate 0 <= userInput <= 100
        customerRental.setDiscountPercent(Double.parseDouble(input.next()));

        //ToDo remove print line debugging
        System.out.println("Discount percent: " + customerRental.getDiscountPercent());

        //Calculations
        customerRental.setReturnDate( customerRental.getCheckoutDate().plusDays(customerRental.getRentalDurationDays().longValue()) );
        customerRental.setDailyRentalCharge(customerRental.getToolBeingRented().getType().getDailyCharge());
        //ToDo calculate charge days - Count of chargeable days, from day after checkout through and including due
        // date, excluding “no charge” days as specified by the tool type
        // calculateChargeDays(checkoutDate, returnDate);
        double preDiscountChargeUnrounded = customerRental.getDailyRentalCharge() * customerRental.getChargeDays();
        customerRental.setPreDiscountCharge( Math.round(preDiscountChargeUnrounded * 100.0) / 100.0 );
        //ToDo remove print line debugging
        System.out.println("preDiscountCharge: " + customerRental.getPreDiscountCharge());
        double discountAmountUnrounded = (customerRental.getDiscountPercent()/100.00) * customerRental.getPreDiscountCharge();
        customerRental.setDiscountAmount( Math.round(discountAmountUnrounded * 100.0) / 100.0 );
        customerRental.setFinalCharge( customerRental.getPreDiscountCharge() - customerRental.getDiscountAmount() );

        customerRental.printRentalAgreement();

    }
}
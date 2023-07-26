package org.toolCo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Checkout {
    ArrayList<Tool> inventoryList;
    Scanner input = new Scanner(System.in);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");

    public Checkout(ArrayList<Tool> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public Tool selectTool(){
        System.out.println("Welcome to ToolCo! Which tool is being rented?");
        int selection;
        Tool selectedTool = null;
        for (int i=0; i<inventoryList.size(); i++){
            System.out.printf("%d - %s (%s %s)\n",
                    i+1,
                    inventoryList.get(i).getCode(),
                    inventoryList.get(i).getBrand(),
                    inventoryList.get(i).getType().getTypeName()
            );
        }

        do{
            System.out.print("Choice: ");
            try {
                selection = Integer.parseInt(input.next());
                if (selection<1 || selection>inventoryList.size()) {
                    System.out.println("Invalid option, try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
                selection = 0;
            }
        } while( selection<1 || selection>inventoryList.size() );

        for(int i=0; i< inventoryList.size(); i++){
            if (selection == i+1){
                selectedTool = inventoryList.get(i);
            }
        }
        return selectedTool;
    }

    public Integer selectRentalDuration(){
        Integer rentalDuration = null;
        do {
            System.out.print("How many days is the rental for? ");
            try {
                rentalDuration = Integer.parseInt(input.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
            }
            if(rentalDuration != null && rentalDuration < 1){
                throw new RuntimeException("Number of rental days is not 1 or greater!");
            }
        } while (rentalDuration == null);

        return rentalDuration;
    }

    public LocalDate selectCheckoutDate(){
        String yesNo;
        LocalDate checkoutDate = null;
        do {
            System.out.print("Is the checkout day today? y/n ");
            yesNo = input.next();
            if (Objects.equals(yesNo, "y")) {
                checkoutDate = LocalDate.now();
            } else if (Objects.equals(yesNo, "n")) {
                //The following 4 lines are prone to exceptions as we're relying on proper user input.
                //The user input needs to be properly validated. Such a task is left as an exercise for the reader.
                do {
                    String checkoutDateInput;
                    System.out.print("Enter the checkout date (mm/dd/yy): ");
                    checkoutDateInput = input.next();
                    try{
                        checkoutDate = LocalDate.parse(checkoutDateInput, dtf);
                    }
                    catch (DateTimeParseException e){
                        System.out.println("Invalid input, try again.");
                    }
                } while (checkoutDate == null);
            }
            else{
                System.out.println("Invalid input, enter either y or n");
            }
        }while(!Objects.equals(yesNo, "y") && !Objects.equals(yesNo, "n"));

        //ToDo remove print line debugging
        System.out.println("Checkout date: " + checkoutDate);
        return checkoutDate;
    }

    public String formatCheckoutDate(LocalDate checkoutDate){
        return dtf.format(checkoutDate);
    }

    public String formatReturnDate(LocalDate returnDate) {
        return dtf.format(returnDate);
    }

    public Double selectDiscountPercent() {
        Double discountPercent = null;
        do {
            System.out.print("Enter discount percent (0-100): ");
            try {
                discountPercent = Double.parseDouble(input.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
            }
            if(discountPercent != null && (discountPercent < 0 || discountPercent > 100)){
                throw new RuntimeException("Discount percent is not in the range 0-100.");
            }
        } while (discountPercent == null);
        return discountPercent;
    }

    public LocalDate calculateReturnDate(LocalDate checkoutDate, Integer rentalDurationDays) {
        return checkoutDate.plusDays(rentalDurationDays);
    }

    public Integer calculateChargeDays(LocalDate checkoutDate, LocalDate returnDate, Integer rentalDays,
                                       Boolean weekendCharge, Boolean holidayCharge) {
        Integer chargeDays = rentalDays;
        if(!weekendCharge){
            //This is not efficient, but it works and is sufficient for this exercise.
            //Iterate through the days of the rental, if weekend day, then subtract a day from chargeDays.
            for(int i = 0; !checkoutDate.plusDays(i).isEqual(returnDate); i++){
                if(checkoutDate.plusDays(i).getDayOfWeek() == DayOfWeek.SATURDAY ||
                        checkoutDate.plusDays(i).getDayOfWeek() == DayOfWeek.SUNDAY){
                    chargeDays--;
                }
            }

        }
        if(!holidayCharge) {
            //We're going to assume that if July 4th of the checkout year has passed, we're not going to encounter it.
            //i.e. the rental will never be > 184 days (Jan01 - July4)
            String checkoutYearYY = dtf.format(checkoutDate).substring(6);
            LocalDate julyFourthCheckoutYear = LocalDate.parse("07/04/" + checkoutYearYY, dtf);
            //ToDo remove print debugging
            System.out.println("julyFourthCheckoutYear: " + dtf.format(julyFourthCheckoutYear));
            LocalDate julyFourthObservanceCheckoutYear = julyFourthCheckoutYear;
            if (julyFourthCheckoutYear.getDayOfWeek() == DayOfWeek.SATURDAY) {
                julyFourthObservanceCheckoutYear = julyFourthCheckoutYear.minusDays(1);
            } else if (julyFourthCheckoutYear.getDayOfWeek() == DayOfWeek.SUNDAY) {
                julyFourthObservanceCheckoutYear = julyFourthCheckoutYear.plusDays(1);
            }

            //If July 4th hasn't occurred yet, let's check for it, otherwise ignore.
            if (!checkoutDate.isAfter(julyFourthObservanceCheckoutYear)) {
                //Once again, not efficient, but it works and is sufficient for this exercise.
                for(int i = 0; !checkoutDate.plusDays(i).isEqual(returnDate); i++){
                    if(checkoutDate.plusDays(i) == julyFourthObservanceCheckoutYear){
                        chargeDays--;
                    }
                }
            }
        }
        //ToDo remove print debugging
        System.out.println("chargeDays: " + chargeDays);
        return chargeDays;
    }
}

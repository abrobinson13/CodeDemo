package org.toolCo;


import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutTest {

    Inventory inventory = new Inventory();
    ArrayList<Tool> inventoryList = inventory.createSampleInventory();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");

    Checkout checkout = new Checkout(inventoryList);

    RentalRecord customerRental1 = new RentalRecord();
    RentalRecord customerRental2 = new RentalRecord();
    RentalRecord customerRental3= new RentalRecord();
    RentalRecord customerRental4 = new RentalRecord();
    RentalRecord customerRental5 = new RentalRecord();
    RentalRecord customerRental6 = new RentalRecord();

    void setupCustomerRentalTestData(RentalRecord customerRental, String checkoutDate, int rentalDays, double discountPercent){
        customerRental.setCheckoutDate(LocalDate.parse(checkoutDate, dtf));
        customerRental.setRentalDurationDays(rentalDays);
        customerRental.setDiscountPercent(discountPercent);
    }

    @Test
    public void doFinalCalculations_Test1() {
        //JAKR, Checkout 9/3/15, 5 days, 101% discount
        customerRental1.setToolBeingRented(inventoryList.get(3));
        setupCustomerRentalTestData(customerRental1, "09/03/15", 5, 101);
        boolean exceptionThrown = false;
        try {
            customerRental1 = checkout.doFinalCalculations(customerRental1);
            customerRental1.printRentalAgreement();
        }
        catch(RuntimeException ex){
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
    @Test
    public void doFinalCalculations_Test2() {
        //LADW, Checkout 7/2/20, 3 days, 10% discount
        customerRental2.setToolBeingRented(inventoryList.get(1));
        setupCustomerRentalTestData(customerRental2, "07/02/20", 3, 10);

        customerRental2 = checkout.doFinalCalculations(customerRental2);
        assertEquals("07/05/20", customerRental2.getFormattedReturnDate());
        assertEquals(Integer.valueOf(2), customerRental2.getChargeDays());
        assertEquals("$3.98", customerRental2.getFormattedPreDiscountCharge());
        assertEquals("10%", customerRental2.getFormattedDiscountPercent());
        assertEquals("$0.40", customerRental2.getFormattedDiscountAmount());
        assertEquals("$3.58", customerRental2.getFormattedFinalCharge());
    }
    @Test
    public void doFinalCalculations_Test3() {
        //CHNS, Checkout 7/2/15, 5 days, 25% discount
        customerRental3.setToolBeingRented(inventoryList.get(0));
        setupCustomerRentalTestData(customerRental3, "07/02/15", 5, 25);

        customerRental2 = checkout.doFinalCalculations(customerRental3);
        assertEquals("07/07/15", customerRental3.getFormattedReturnDate());
        assertEquals(Integer.valueOf(3), customerRental3.getChargeDays());
        assertEquals("$4.47", customerRental3.getFormattedPreDiscountCharge());
        assertEquals("25%", customerRental3.getFormattedDiscountPercent());
        assertEquals("$1.12", customerRental3.getFormattedDiscountAmount());
        assertEquals("$3.35", customerRental3.getFormattedFinalCharge());
    }
    @Test
    public void doFinalCalculations_Test4() {
        //JAKD, Checkout 9/3/15, 6 days, 0% discount
        customerRental4.setToolBeingRented(inventoryList.get(2));
        setupCustomerRentalTestData(customerRental4, "09/03/15", 6, 0);

        customerRental2 = checkout.doFinalCalculations(customerRental4);
        assertEquals("09/09/15", customerRental4.getFormattedReturnDate());
        assertEquals(Integer.valueOf(4), customerRental4.getChargeDays());
        assertEquals("$11.96", customerRental4.getFormattedPreDiscountCharge());
        assertEquals("0%", customerRental4.getFormattedDiscountPercent());
        assertEquals("$0.00", customerRental4.getFormattedDiscountAmount());
        assertEquals("$11.96", customerRental4.getFormattedFinalCharge());
    }
    @Test
    public void doFinalCalculations_Test5() {
        //JAKR, Checkout 7/2/15, 9 days, 0% discount
        customerRental5.setToolBeingRented(inventoryList.get(3));
        setupCustomerRentalTestData(customerRental5, "07/02/15", 9, 0);

        customerRental2 = checkout.doFinalCalculations(customerRental5);
        assertEquals("07/11/15", customerRental5.getFormattedReturnDate());
        assertEquals(Integer.valueOf(6), customerRental5.getChargeDays());
        assertEquals("$17.94", customerRental5.getFormattedPreDiscountCharge());
        assertEquals("0%", customerRental5.getFormattedDiscountPercent());
        assertEquals("$0.00", customerRental5.getFormattedDiscountAmount());
        assertEquals("$17.94", customerRental5.getFormattedFinalCharge());
    }
    @Test
    public void doFinalCalculations_Test6() {
        //JAKR, Checkout 7/2/20, 4 days, 50% discount
        customerRental6.setToolBeingRented(inventoryList.get(3));
        setupCustomerRentalTestData(customerRental6, "07/02/20", 4, 50);

        customerRental2 = checkout.doFinalCalculations(customerRental6);
        assertEquals("07/06/20", customerRental6.getFormattedReturnDate());
        assertEquals(Integer.valueOf(1), customerRental6.getChargeDays());
        assertEquals("$2.99", customerRental6.getFormattedPreDiscountCharge());
        assertEquals("50%", customerRental6.getFormattedDiscountPercent());
        assertEquals("$1.50", customerRental6.getFormattedDiscountAmount());
        assertEquals("$1.49", customerRental6.getFormattedFinalCharge());
    }
}
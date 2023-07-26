package org.toolRental;

import java.time.LocalDate;

// In an enterprise operation, this would be a db table.
public class rentalRecord {
    private Tool toolBeingRented;
    private Integer rentalDurationDays;
    private LocalDate checkoutDate;
    private String formattedCheckoutDate;
    private LocalDate returnDate;
    private Double dailyRentalCharge;
    private String formattedReturnDate;
    private Integer chargeDays;
    private Double preDiscountCharge;
    private Double discountPercent;
    private Double discountAmount;
    private Double finalCharge;

    public Tool getToolBeingRented() {
        return toolBeingRented;
    }

    public void setToolBeingRented(Tool toolBeingRented) {
        this.toolBeingRented = toolBeingRented;
    }

    public Integer getRentalDurationDays() {
        return rentalDurationDays;
    }

    public void setRentalDurationDays(Integer rentalDurationDays) {
        this.rentalDurationDays = rentalDurationDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getFormattedCheckoutDate() {
        return formattedCheckoutDate;
    }

    public void setFormattedCheckoutDate(String formattedCheckoutDate) {
        this.formattedCheckoutDate = formattedCheckoutDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Double getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(Double dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public String getFormattedReturnDate() {
        return formattedReturnDate;
    }

    public void setFormattedReturnDate(String formattedReturnDate) {
        this.formattedReturnDate = formattedReturnDate;
    }

    public Integer getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(Integer chargeDays) {
        this.chargeDays = chargeDays;
    }

    public Double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(Double preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(Double finalCharge) {
        this.finalCharge = finalCharge;
    }

    public void printRentalAgreement(){
        System.out.println(
                "Tool code: " + this.getToolBeingRented().getCode() + "\n" +
                "Tool type: " + this.getToolBeingRented().getType() + "\n" +
                "Tool brand: " + this.getToolBeingRented().getBrand() + "\n" +
                "Rental days: " + this.getRentalDurationDays() + "\n" +
                "Checkout date: " + this.getFormattedCheckoutDate() + "\n" +
                "Due date: " + this.getFormattedReturnDate() + "\n" +
                "Daily rental charge: " + this.getDailyRentalCharge() + "\n" +
                "Charge days: " + this.getChargeDays() + "\n" +
                "Pre-discount charge: " + this.getPreDiscountCharge() + "\n" +
                "Discount percent: " + this.getDiscountPercent() + "\n" +
                "Discount amount: " + this.getDiscountAmount() + "\n" +
                "Final charge: " + this.getFinalCharge()
        );
    }
}

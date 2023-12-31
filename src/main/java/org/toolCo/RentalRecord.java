package org.toolCo;

import java.time.LocalDate;

// In an enterprise operation, this would be a db table.
public class RentalRecord {
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

    public String getFormattedDailyRentalCharge() {
        return "$"+this.getDailyRentalCharge().toString();
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

    public String getFormattedPreDiscountCharge() {
        int decimalPlaces = this.getPreDiscountCharge().toString().substring(this.getPreDiscountCharge().toString().indexOf('.')+1).length();
        String formattedTwoDecimalPlaces = "$"+this.getPreDiscountCharge().toString();
        if(decimalPlaces < 2){
            formattedTwoDecimalPlaces = formattedTwoDecimalPlaces+"0";
        }
        return formattedTwoDecimalPlaces;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getFormattedDiscountPercent(){
        if(discountPercent != null && (discountPercent < 0 || discountPercent > 100)){
            throw new RuntimeException("Discount percent is not in the range 0-100.");
        }
        int decimalPointIndex = this.getDiscountPercent().toString().indexOf('.');
        return this.getDiscountPercent().toString().substring(0,decimalPointIndex)+"%";
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getFormattedDiscountAmount(){
        int decimalPlaces = this.getDiscountAmount().toString().substring(this.getDiscountAmount().toString().indexOf('.')+1).length();
        String formattedTwoDecimalPlaces = "$"+this.getDiscountAmount().toString();
        if(decimalPlaces < 2){
            formattedTwoDecimalPlaces = formattedTwoDecimalPlaces+"0";
        }
        return formattedTwoDecimalPlaces;
    }

    public Double getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(Double finalCharge) {
        this.finalCharge = finalCharge;
    }

    public String getFormattedFinalCharge(){
        int decimalPlaces = this.getFinalCharge().toString().substring(this.getFinalCharge().toString().indexOf('.')+1).length();
        String formattedTwoDecimalPlaces = "$"+this.getFinalCharge().toString();
        if(decimalPlaces < 2){
            formattedTwoDecimalPlaces = formattedTwoDecimalPlaces+"0";
        }
        return formattedTwoDecimalPlaces;
    }

    public void printRentalAgreement(){
        System.out.println(
                "\nRENTAL AGREEMENT: \n" +
                "Tool code: " + this.getToolBeingRented().getCode() + "\n" +
                "Tool type: " + this.getToolBeingRented().getType().getTypeName() + "\n" +
                "Tool brand: " + this.getToolBeingRented().getBrand() + "\n" +
                "Rental days: " + this.getRentalDurationDays() + "\n" +
                "Checkout date: " + this.getFormattedCheckoutDate() + "\n" +
                "Due date: " + this.getFormattedReturnDate() + "\n" +
                "Daily rental charge: " + this.getFormattedDailyRentalCharge() + "\n" +
                "Charge days: " + this.getChargeDays() + "\n" +
                "Pre-discount charge: " + this.getFormattedPreDiscountCharge() + "\n" +
                "Discount percent: " + this.getFormattedDiscountPercent() + "\n" +
                "Discount amount: " + this.getFormattedDiscountAmount() + "\n" +
                "Final charge: " + this.getFormattedFinalCharge()
        );
    }
}

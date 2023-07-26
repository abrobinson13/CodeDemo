package org.toolCo;

public class ToolType {
    private String typeName;
    private Double dailyCharge;
    private Boolean weekdayCharge;
    private Boolean weekendCharge;
    private Boolean holidayCharge;

    public ToolType(String typeName, Double dailyCharge, Boolean weekdayCharge, Boolean weekendCharge, Boolean holidayCharge) {
        this.typeName = typeName;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Double getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(Double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public Boolean getWeekdayCharge() {
        return weekdayCharge;
    }

    public void setWeekdayCharge(Boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public Boolean getWeekendCharge() {
        return weekendCharge;
    }

    public void setWeekendCharge(Boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public Boolean getHolidayCharge() {
        return holidayCharge;
    }

    public void setHolidayCharge(Boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

    @Override
    public String toString() {
        return "ToolType{" + "\n" +
                "typeName= " + typeName + "\n" +
                "dailyCharge=" + dailyCharge + "\n" +
                "weekdayCharge=" + weekdayCharge + "\n" +
                "weekendCharge=" + weekendCharge + "\n" +
                "holidayCharge=" + holidayCharge + "\n" +
                '}';
    }
}

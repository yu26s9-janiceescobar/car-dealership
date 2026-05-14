package com.pluralsight.business;
/*
Sales Tax Amount (5%)
• Recording Fee ($100)
• Processing fee ($295 for vehicles under $10,000 and $495 for all others
• Whether they want to finance (yes/no)
• Monthly payment (if financed) based on:
• All loans are at 4.25% for 48 months if the price is $10,000 or more
• Otherwise they are at 5.25% for 24 month
*/
public class SalesContract extends Contract{
    private boolean isFinance;
    private final double salesTax;
    private final double recordingFee;

    public SalesContract(boolean isFinance, String date, String name, String email, Vehicle vehicle){
        super(date, name, email, vehicle);
        this.isFinance = isFinance;
        salesTax = .5;
        recordingFee = 100;

    }

    @Override
    public double getMonthlyPayment(){
        return isFinance ? Math.pow(getTotalPrice() * getInterestRate(), getTotalMonths()) : 0; 
    }

    @Override
    public double getTotalPrice(){
        return (getVehicle().getPrice() * (salesTax + 1) + recordingFee + getProcessingFee());
    }
    public int getTotalMonths(){
        return getVehicle().getPrice() < 10000 ? 24 : 48;
    }
    public double getInterestRate(){
       return getVehicle().getPrice() < 10000 ? .0425 : .0525;
    }
    public double getProcessingFee(){
        return getVehicle().getPrice() < 10000 ? 295 : 495;
    }

    public void setFinance(boolean isFinance){
        this.isFinance = isFinance;
    }
    public boolean getFinance(){
        return isFinance;
    }
}

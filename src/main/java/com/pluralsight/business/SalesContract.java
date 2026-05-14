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
    private final double salesTaxRate;
    private final double recordingFee;

    public SalesContract(String date, String name, String email, Vehicle vehicle, boolean isFinance){
        super(date, name, email, vehicle);
        this.isFinance = isFinance;
        salesTaxRate = .5;
        recordingFee = 100;
    }

    @Override
    public double getMonthlyPayment(){
        double monthlyInterestRate = getAnnualInterestRate() / 12;
        double monthlyPayment = calculateSubtotalPrice() * (monthlyInterestRate * Math.pow(monthlyInterestRate + 1, getTotalMonths())) / (Math.pow(monthlyInterestRate + 1, getTotalMonths()) - 1); // MonthlyPayment = principal * (((monthlyRate)(monthlyRate + 1)^months) / (((1 + monthlyRate)^months) - 1))
        return isFinance ? monthlyPayment : 0;
    }

    @Override
    public double getTotalPrice(){
        return isFinance ? (getMonthlyPayment() * getTotalMonths()): calculateSubtotalPrice();
    }

    public double calculateSubtotalPrice(){
        return (getVehicle().getPrice() * (salesTaxRate + 1) + recordingFee + getProcessingFee());
    }

    public int getTotalMonths(){
        return getVehicle().getPrice() < 10000 ? 24 : 48;
    }
    public double getAnnualInterestRate(){
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

    public double getSalesTaxRate(){
        return salesTaxRate;
    }
    public double getRecordingFee(){
        return recordingFee;
    }
}

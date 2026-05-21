package com.pluralsight.business;

public class SalesContract extends Contract{
    private boolean isFinance;
    private final double salesTaxRate;
    private final double recordingFee;

    public SalesContract(String date, String name, String email, Vehicle vehicle, boolean isFinance){
        super(date, name, email, vehicle);
        this.isFinance = isFinance;
        salesTaxRate = .05;
        recordingFee = 100;
    }

    @Override
    public double getMonthlyPayment(){
        double monthlyInterestRate = getAnnualInterestRate() / 12;
        double feesPerMonth = (calculateSalesTax() + recordingFee + getProcessingFee()) / getTotalMonths();
        double monthlyPayment = getVehicle().getPrice() * (monthlyInterestRate * Math.pow(monthlyInterestRate + 1, getTotalMonths())) / (Math.pow(monthlyInterestRate + 1, getTotalMonths()) - 1); // MonthlyPayment = principal * (((monthlyRate)(monthlyRate + 1)^months) / (((1 + monthlyRate)^months) - 1))
        monthlyPayment += feesPerMonth; // Interest is not charged to fees
        return isFinance ? monthlyPayment : 0;
    }

    @Override
    public double getTotalPrice(){
        return isFinance ? (getMonthlyPayment() * getTotalMonths()): calculatePayInFullPrice();
    }

    public double calculatePayInFullPrice(){
        return ((getVehicle().getPrice() * (salesTaxRate + 1)) + recordingFee + getProcessingFee());
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
    public double calculateSalesTax(){
        return getVehicle().getPrice() * salesTaxRate;
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
    @Override
    public String toString(){
        return String.format("%.2f|%.2f|%.2f|%,.2f|%s|%.2f", calculateSalesTax(), recordingFee, getProcessingFee(), getTotalPrice(), isFinance ? "YES" : "NO", getMonthlyPayment());
    }
}

package com.pluralsight.business;

/*
A LeaseContract will include the following additional information:
• Expected Ending Value (50% of the original price)
• Lease Fee (7% of the original price)
• Monthly payment based on
• All leases are financed at 4.0% for 36 months
 */
public class LeaseContract extends Contract{
    private double expectedEndingVal;
    private double leaseFee;
    private double annualInterestRate;
    private int leasingTerm;

    public LeaseContract(String date, String name, String email, Vehicle vehicle){
        super(date, name, email, vehicle);
        this.expectedEndingVal = .5;
        this.leaseFee = .07;
        this.annualInterestRate = .04;
        this.leasingTerm = 36;
    }
    @Override
    public double getMonthlyPayment(){

    }
    @Override
    public double getTotalPrice(){

    }
    public void setExpectedEndingValue(double expectedEndingVal){
        this.expectedEndingVal = expectedEndingVal;
    }
    public double getExpectedEndingVal(){
        return expectedEndingVal;
    }
    public void setLeaseFee(double leaseFee){
        this.leaseFee = leaseFee;
    }
    public double getLeaseFee(){
        return leaseFee;
    }
    public void setAnnualInterestRate(double annualInterestRate){
        this.annualInterestRate = annualInterestRate;
    }
    public double getAnnualInterestRate(){
        return annualInterestRate;
    }
    public void setLeasingTerm(int leasingTerm){
        this.leasingTerm = leasingTerm;
    }
    public int getLeasingTerm(){
        return leasingTerm;
    }
}

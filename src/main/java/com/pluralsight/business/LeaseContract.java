package com.pluralsight.business;

/*
A LeaseContract will include the following additional information:
• Expected Ending Value (50% of the original price)
• Lease Fee (7% of the original price)
• Monthly payment based on
• All leases are financed at 4.0% for 36 months
 */
public class LeaseContract extends Contract{
    private double expectedEndingRate;
    private double leasingFeeRate;
    private double annualInterestRate;
    private int leasingTerm;

    public LeaseContract(String date, String name, String email, Vehicle vehicle){
        super(date, name, email, vehicle);
        expectedEndingRate = .5;
        leasingFeeRate = .07;
        annualInterestRate = .04;
        leasingTerm = 36;
    }
    @Override
    public double getMonthlyPayment(){
        return 
    }
    @Override
    public double getTotalPrice(){

    }
    public void expectedEndingRate(double expectedEndingRate){
        this.expectedEndingRate = expectedEndingRate;
    }
    public double getExpectedEndingRate(){
        return expectedEndingRate;
    }
    public void setLeasingFeeRate(double leasingFeeRate){
        this.leasingFeeRate = leasingFeeRate;
    }
    public double getLeasingFeeRate(){
        return leasingFeeRate;
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

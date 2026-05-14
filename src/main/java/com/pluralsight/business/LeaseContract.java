package com.pluralsight.business;

public class LeaseContract extends Contract{
    private double expectedEndingVal;
    private double leaseFee;
    private double annualInterestRate;
    private int leasingTerm;
    public LeaseContract(double expectedEndingVal, double leaseFee, double annualInterestRate, int leasingTerm,  String date, String name, String email, Vehicle vehicle){
        super(date, name, email, vehicle);
        this.expectedEndingVal = expectedEndingVal;
        this.leaseFee = leaseFee;
        this.annualInterestRate = annualInterestRate;
        this.leasingTerm = leasingTerm;
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

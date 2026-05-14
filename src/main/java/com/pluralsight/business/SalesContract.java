package com.pluralsight.business;

public class SalesContract extends Contract{
    private double salesTax;
    private double recordingFee;
    private double processingFee;
    private boolean isFinance;

    public SalesContract(double salesTax, double recordingFee, double processingFee, boolean isFinance, String date, String name, String email, Vehicle vehicle){
        super(date, name, email, vehicle);
        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.isFinance = isFinance;
    }

    public void setSalesTax(double salesTax){
        this.salesTax = salesTax;
    }
    public double getSalesTax(){
        return salesTax;
    }
    public void setRecordingFee(double recordingFee){
        this.recordingFee = recordingFee;
    }
    public double getRecordingFee(){
        return recordingFee;
    }
    public void setProcessingFee(double processingFee){
        this.processingFee = processingFee;
    }
    public double getProcessingFee(){
        return processingFee;
    }
    public void setFinance(boolean isFinance){
        this.isFinance = isFinance;
    }
    public boolean getFinance(){
        return isFinance;
    }
}

package com.pluralsight.business;


public class LeaseContract extends Contract{
    private final double expectedEndingRate;
    private final double leasingFeeRate;
    private final double annualInterestRate;
    private final int totalMonths;

    public LeaseContract(String date, String name, String email, Vehicle vehicle){
        super(date, name, email, vehicle);
        expectedEndingRate = .5;
        leasingFeeRate = .07;
        annualInterestRate = .04;
        totalMonths = 36;
    }
    // MonthlyPayment = principal * (((monthlyRate)(monthlyRate + 1)^months) / (((1 + monthlyRate)^months) - 1))
    @Override
    public double getMonthlyPayment(){
        double monthlyInterestRate = annualInterestRate / 12;
        double principal = getVehicle().getPrice() - calculateExpectedEndingValue();
        double feesPerMonth = calculateLeasingFee() / totalMonths;
        double monthlyPayment = principal * ((monthlyInterestRate) * (Math.pow(monthlyInterestRate + 1, totalMonths) / (Math.pow(1 + monthlyInterestRate, totalMonths) - 1)));
        monthlyPayment += feesPerMonth;
        return monthlyPayment;

    }
    @Override
    public double getTotalPrice(){
        return getMonthlyPayment() * totalMonths;
    }

    public double calculateExpectedEndingValue(){
        return getVehicle().getPrice() * expectedEndingRate;
    }

    public double calculateLeasingFee(){
        return leasingFeeRate * getVehicle().getPrice();
    }

    public double getExpectedEndingRate(){
        return expectedEndingRate;
    }

    public double getLeasingFeeRate(){
        return leasingFeeRate;
    }
    public double getAnnualInterestRate(){
        return annualInterestRate;
    }
    public int getLeasingTerm(){
        return totalMonths;
    }
    @Override
    public String toString(){
        return String.format("%.2f|%.2f|%.2f|%.2f",
                calculateExpectedEndingValue(),
                calculateLeasingFee(),
                getTotalPrice(),
                getMonthlyPayment());
    }
}

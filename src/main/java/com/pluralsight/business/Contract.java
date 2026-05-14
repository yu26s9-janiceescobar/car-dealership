package com.pluralsight.business;

public abstract class Contract {
    private String date;
    private String name;
    private String email;
    private Vehicle vehicle;

    public Contract(String date, String name, String email, Vehicle vehicle){
        this.date = date;
        this.name = name;
        this.email = email;
        this.vehicle = vehicle;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return date;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }
    public Vehicle getVehicle(){
        return vehicle;
    }
    public abstract double getTotalPrice(){

    }

}

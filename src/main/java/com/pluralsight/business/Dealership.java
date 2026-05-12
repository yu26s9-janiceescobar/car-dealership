package com.pluralsight.business;
import java.time.Year;
import java.util.ArrayList;


public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone){
        this.name = name;
        this.address = address;
        this.phone = phone;
        inventory = new ArrayList<>();
    }



    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return phone;
    }
    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max){
        ArrayList<Vehicle> vehicleByPrice = new ArrayList<>();
        for (Vehicle vehicle: inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                vehicleByPrice.add(vehicle);
            }
        }
        return vehicleByPrice;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){
        ArrayList<Vehicle> vehicleByMakeModel = new ArrayList<>();
        for (Vehicle vehicle: inventory){
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)){
                vehicleByMakeModel.add(vehicle);
            }
        }
        return vehicleByMakeModel;
    }
    public ArrayList<Vehicle> getVehiclesByYear(int min, int max){
        ArrayList<Vehicle> vehicleByYear = new ArrayList<>();
        for (Vehicle vehicle: inventory){
            if (vehicle.getYear() < min && vehicle.getYear() < max){
                vehicleByYear.add(vehicle);
            }
        }
        return vehicleByYear;
    }
    public ArrayList<Vehicle> getVehiclesByColor(String color){
        ArrayList<Vehicle> vehicleByColor= new ArrayList<>();
        for (Vehicle vehicle: inventory){
            if (vehicle.getColor().equalsIgnoreCase(color)){
                vehicleByColor.add(vehicle);
            }
        }
        return vehicleByColor;
    }
    public ArrayList<Vehicle> getVehicleByMileage(int min, int max){
        ArrayList<Vehicle> vehicleByMileage= new ArrayList<>();
        for (Vehicle vehicle: inventory){
            if (vehicle.getOdometer() <= min && vehicle.getOdometer() >= max){
                vehicleByMileage.add(vehicle);
            }
        }
        return vehicleByMileage;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        ArrayList<Vehicle> vehicleByMileage= new ArrayList<>();
        for (Vehicle vehicle: inventory){
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)){
                vehicleByMileage.add(vehicle);
            }
        }
        return vehicleByMileage;
    }
    public void removeVehicle(Vehicle vehicle){
        inventory.remove(vehicle);
    }
}

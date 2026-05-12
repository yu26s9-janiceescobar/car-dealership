package com.pluralsight.ui;

import com.pluralsight.business.Dealership;
import com.pluralsight.business.Vehicle;
import com.pluralsight.data.DealershipFileManager;
import java.util.ArrayList;

public class UserInterface {
    private Dealership dealership;

    private void init(){
        DealershipFileManager dealershipFileManager = new DealershipFileManager();

        dealership = dealershipFileManager.getDealership();

    }

    public void display(){
        try{
            int option;
            do {
                init();
                System.out.println("""
                Dealership Main Menu
                1 - Find vehicles within a price range
                2 - Find vehicles by make / model
                3 - Find vehicles by year range
                4 - Find vehicles by color
                5 - Find vehicles by mileage range
                6 - Find vehicles by type (car, truck, SUV, van)
                7 - List ALL vehicles
                8 - Add a vehicle
                9 - Remove a vehicle
                99 - Quit""");

                option = Console.promptForIntRange("> ","Invalid Input. Enter one of the following menu options.",1,9, 99);
                switch (option) {
                    case 1:
                        processGetByPriceRequest();
                        break;
                    case 2:
                        processGetByMakeModelRequest();
                        break;
                    case 3:
                        processGetByYearRequest();
                        break;
                    case 4:
                        processGetByColorRequest();
                        break;
                    case 5:
                        processGetByMileageRequest();
                        break;
                    case 6:
                        processGetByVehicleRequest();
                        break;
                    case 7:
                        processGetAllVehiclesRequest();
                        break;
                    case 8:
                        processAddByRequest();
                        break;
                    case 9:
                        processRemoveVehicleRequest();
                        break;
                    case 99:
                        System.out.println("Exiting Application...");
                        break;
                }
            }while (option != 99);
        }
        catch(RuntimeException e){
            System.out.println("There was an error reading from the file: " + e.getMessage());
        }

    }
    private void processGetAllVehiclesRequest(){
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        displayListOfVehicles(vehicles);
    }
    private void processGetByPriceRequest(){
        double parseMin = Console.promptForCurrency("Enter Minimum Amount: ");
        double parseMax = Console.promptForCurrency("Enter Maximum Amount: ");
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByPrice(parseMin, parseMax);
        displayListOfVehicles(vehicles);
    }
    private void processGetByMakeModelRequest(){
        String make = Console.promptForString("Enter Make: ");
        String model = Console.promptForString("Enter Model: ");
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayListOfVehicles(vehicles);
    }

    private void processGetByYearRequest(){
        int minYear = Console.promptForIntRange("Enter Minimum Year: ","Must be between 1980 and 2026", 1980, 2026);
        int maxYear = Console.promptForIntRange("Enter Maximum Year: ","Must be between " + minYear + " and 2026", minYear, 2026);
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayListOfVehicles(vehicles);

    }
    private void processGetByColorRequest(){
        String colorInput = Console.promptForString("Enter Color: ");
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByColor(colorInput);
        displayListOfVehicles(vehicles);
    }
    private void processGetByMileageRequest(){
        int minMileage = Console.promptForIntRange("Enter Minimum Mileage: ","Must be between 0 and 300,000.", 0, 300000);
        int maxMileage = Console.promptForIntRange("Enter Maximum Mileage: ", "Must be between " + minMileage + " and 300,000." ,minMileage, 300000);
        ArrayList<Vehicle> vehicles = dealership.getVehicleByMileage(minMileage, maxMileage);
        displayListOfVehicles(vehicles);
    }
    private void processGetByVehicleRequest(){
        String vehicleType = Console.promptForString("Enter Vehicle Type: ");
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayListOfVehicles(vehicles);
    }
    private void displayListOfVehicles(ArrayList<Vehicle> vehicles){
        for (Vehicle v: vehicles){
            System.out.println(v);
        }
    }
    private void processAddByRequest(){
        int vin = Console.promptForInt("Enter Vin Number: ");
        int year = Console.promptForIntRange("Enter Year: ", "Year must be between 1980 and 2026", 1980, 2026);
        String make = Console.promptForString("Enter Make: ");
        String model = Console.promptForString("Enter Model: ");
        String vehicleType = Console.promptForString("Enter Vehicle Type: ");
        String color = Console.promptForString("Enter Color: ");
        int odometer = Console.promptForInt("Enter Odometer: ");
        double price = Console.promptForCurrency("Enter Price: ");
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);
    }
    private void processRemoveVehicleRequest(){
        int vin = Console.promptForInt("Enter Vin Number: ");
        for (Vehicle v: dealership.getAllVehicles() ){
            if (v.getVin() == vin){
                dealership.removeVehicle(v);
            }
        }
    }

}

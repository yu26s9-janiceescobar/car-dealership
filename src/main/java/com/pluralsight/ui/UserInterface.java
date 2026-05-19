package com.pluralsight.ui;
import com.pluralsight.business.*;
import com.pluralsight.data.ContractFileManager;
import com.pluralsight.data.DealershipFileManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UserInterface {
    private Dealership dealership;
    private DealershipFileManager dealershipFileManager;
    private ContractFileManager contractFileManager;
    private ArrayList<Contract> contracts;

    private void init(){
        dealershipFileManager = new DealershipFileManager();
        dealership = dealershipFileManager.getDealership();
        contractFileManager = new ContractFileManager();
        contracts = contractFileManager.getContracts();
    }

    public void display(){
        try{
            init();
            int option;
            do {

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
                10 - Sell / Lease vehicle
                99 - Quit""");

                option = Console.promptForIntRange("> ",1,10, 99);
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
                    case 10:
                        SaleLeaseDisplay();
                    case 99:
                        System.out.println("Exiting Application...");
                        break;
                }
            }while (option != 99);
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
        }

    }
    private void processGetAllVehiclesRequest(){
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        displayListOfVehicles(vehicles);
    }

    private void processGetByPriceRequest(){
        double parseMin = Console.promptForCurrencyRange("Enter Minimum Amount: ", Dealership.MIN_VEHICLE_PRICE, Dealership.MAX_VEHICLE_PRICE, true);
        double parseMax = Console.promptForCurrencyRange("Enter Maximum Amount: ", parseMin, Dealership.MAX_VEHICLE_PRICE, true); // Maximum price range cannot be lower than minimum price range entered by user.
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
        int minYearInput = Console.promptForIntRange("Enter Minimum Year: ", Dealership.MIN_VEHICLE_YEAR, Dealership.MAX_VEHICLE_YEAR);
        int maxYearInput = Console.promptForIntRange("Enter Maximum Year: ", minYearInput, Dealership.MAX_VEHICLE_YEAR);
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByYear(minYearInput, maxYearInput);
        displayListOfVehicles(vehicles);

    }
    private void processGetByColorRequest(){
        String colorInput = Console.promptForString("Enter Color: ");
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByColor(colorInput);
        displayListOfVehicles(vehicles);
    }
    private void processGetByMileageRequest(){
        int minMileage = Console.promptForIntRange("Enter Minimum Mileage: ", 0, Dealership.MAX_MILEAGE);
        int maxMileage = Console.promptForIntRange("Enter Maximum Mileage: ", minMileage, Dealership.MAX_MILEAGE);
        ArrayList<Vehicle> vehicles = dealership.getVehicleByMileage(minMileage, maxMileage);
        displayListOfVehicles(vehicles);
    }
    private void processGetByVehicleRequest(){
        String vehicleType = Console.promptForString("Enter Vehicle Type: ");
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayListOfVehicles(vehicles);
    }

    private void displayListOfVehicles(ArrayList<Vehicle> vehicles){
        if (vehicles.isEmpty()){
            System.out.println("No Vehicles Found.");
        }
        else {
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }

    private void processAddByRequest(){
        int vin = Console.promptForInt("Enter Vin Number: ");
        int year = Console.promptForIntRange("Enter Year: ",  Dealership.MIN_VEHICLE_YEAR, Dealership.MAX_VEHICLE_YEAR);
        String make = Console.promptForString("Enter Make: ");
        String model = Console.promptForString("Enter Model: ");
        String vehicleType = Console.promptForString("Enter Vehicle Type: ");
        String color = Console.promptForString("Enter Color: ");
        int odometer = Console.promptForIntRange("Enter Odometer: ",0, Dealership.MAX_MILEAGE);
        double price = Console.promptForCurrencyRange("Enter Price: ", Dealership.MIN_VEHICLE_PRICE, Dealership.MAX_VEHICLE_PRICE, true);
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);


        dealership.addVehicle(vehicle);
        dealershipFileManager.saveDealership(dealership);
    }

    private void processRemoveVehicleRequest(){
        int vin = Console.promptForInt("Enter Vin Number: ");
        if (dealership.removeVehicle(vin)) {
            dealershipFileManager.saveDealership(dealership);
        }else{
            System.out.println("Vehicle not found.");
        }
    }
    private void SaleLeaseDisplay(){
        String option;
        do {
            System.out.println("[S] Sale [L] Lease [X] Main Menu");
            option = Console.promptForCharacterOptions("> ", "s", "l", "x");
            boolean isSale = option.equalsIgnoreCase("s");
            processSaleLease(isSale);

        }while(!option.equalsIgnoreCase( "x"));
    }
    private void processSaleLease(boolean isSale){
        Vehicle vehicle;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = LocalDate.now().format(fmt);
        String name = Console.promptForString("Enter Name: ");
        String email = Console.promptForString("Enter Email: ");
        do {
            int vin = Console.promptForInt("Enter Vin Number of Vehicle: ");
            vehicle = dealership.getVehicleByVin(vin);
            if (vehicle == null){
                System.out.println("Vehicle not Found. Please Try Again.");
            }
        }while (vehicle == null);

        if (isSale){
            boolean isFinance = Console.promptForYesNoInput("Are you financing your vehicle? [Y] Yes [N] No \n> ");
            SalesContract salesContract = new SalesContract(date, name, email, vehicle, isFinance);
            contracts.add(salesContract);
        }else{
            LeaseContract leaseContract = new LeaseContract(date, name, email,vehicle);
            contracts.add(leaseContract);
        }
        contractFileManager.saveContracts(contracts);
    }


}

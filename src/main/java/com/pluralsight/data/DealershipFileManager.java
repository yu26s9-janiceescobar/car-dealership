package com.pluralsight.data;

import com.pluralsight.business.Dealership;
import com.pluralsight.business.Vehicle;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {
    private static final String fileName = "data/inventory.csv";

    public static Dealership getDealership() {
        String[] dealershipInfo = readDealershipInfo();

        Dealership dealership = new Dealership(dealershipInfo[0], dealershipInfo[1], dealershipInfo[2]);
        ArrayList<Vehicle> inventory = readInventory();

        for (Vehicle v: inventory){
            dealership.addVehicle(v);
        }
        return dealership;
    }
    public static void saveDealership(Dealership dealership){
        ArrayList<Vehicle> inventory = dealership.getAllVehicles();
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.format("%s|%s|%s",dealership.getName(),dealership.getAddress(),dealership.getPhone()));

            for (Vehicle v : inventory) {
                bufferedWriter.write(String.format("%s|%s|%s|%s|%s|%s|%,.2f", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getOdometer(), v.getPrice()));
            }

            bufferedWriter.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    private static String[] readDealershipInfo(){
        String[] dealershipInfo = new String[3];

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line = bufReader.readLine();
            if ( line != null){
                String[] dealerInfo = line.split("\\|");
                dealershipInfo[0] = dealerInfo[0];
                dealershipInfo[1] = dealerInfo[1];
                dealershipInfo[2] = dealerInfo[2];
            }
            bufReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return dealershipInfo;

    }
    public static ArrayList<Vehicle> readInventory(){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufReader = new BufferedReader(fileReader);

            bufReader.readLine();
            String line;
            while ((line = bufReader.readLine()) != null) {
                String[] fileLine = line.split("\\|");

                int vin = Integer.parseInt(fileLine[0]);
                int year = Integer.parseInt(fileLine[1]);
                String make = fileLine[2];
                String model = fileLine[3];
                String vehicleType = fileLine[4];
                String color = fileLine[5];
                int odometer = Integer.parseInt(fileLine[6]);
                double price = Double.parseDouble(fileLine[7]);
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                vehicles.add(vehicle);
            }
            bufReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return vehicles;
    }


}

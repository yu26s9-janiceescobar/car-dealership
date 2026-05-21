package com.pluralsight.data;
import com.pluralsight.business.Dealership;
import com.pluralsight.business.Vehicle;
import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {
    private final String fileName;

    public DealershipFileManager(){
        fileName = "data/inventory.csv";
    } // File containing preloaded information with Dealership information and existing vehicles in the dealership.

    /**
     * Creates a Dealership from preloaded file with vehicle information.
     * @return Dealership with vehicles.
     */
    public Dealership getDealership(){
        Dealership d;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bufReader = new BufferedReader(fr);

            String firstLine = bufReader.readLine();
            d = makeDealershipFromEncodedString(firstLine);

            String line;
            while((line = bufReader.readLine()) != null){
                if (!line.isBlank()) {
                    Vehicle v = makeVehicleFromEncodedString(line);
                    d.addVehicle(v);
                }
            }


            bufReader.close();
            return d;
        }
        catch( IOException e){
            throw new RuntimeException("Cannot read dealership from file.");
        }

    }

    /**
     * Saves Dealership containing vehicle information to CSV file.
     * @param dealership containing vehicle information.
     */
    public void saveDealership(Dealership dealership){

        try{
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String firstLine = String.format("%s|%s|%s%n", dealership.getName(),dealership.getAddress(),dealership.getPhone());
            bufferedWriter.write(firstLine);

            ArrayList<Vehicle> vehicles = dealership.getAllVehicles();

            for (Vehicle v: vehicles){
                String vehicleInfo = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f%n",
                        v.getVin(),
                        v.getYear(),
                        v.getMake() ,
                        v.getModel(),
                        v.getVehicleType(),
                        v.getColor(),
                        v.getOdometer(),
                        v.getPrice());

                bufferedWriter.write(vehicleInfo);
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot save new dealership file.");
        }
    }

    /**
     * Gets Dealership information from a String containing the name, address, and phone number of dealership.
     * @param s String containing Dealership information.
     * @return Dealership with dealership information.
     */
    private Dealership makeDealershipFromEncodedString(String s){
        String[] dealerInfo = s.split("\\|");
         return new Dealership(dealerInfo[0], dealerInfo[1], dealerInfo[2]);
    }

    /**
     * Gets Vehicle information from a String containing the VIN, year, make, model, vehicle type, color, odometer, and price of vehicle.
     * @param s the string containing vehicle information.
     * @return Vehicle with vehicle information.
     */
    private Vehicle makeVehicleFromEncodedString(String s){
        String[] fileLine = s.split("\\|");

        int vin = Integer.parseInt(fileLine[0]);
        int year = Integer.parseInt(fileLine[1]);
        String make = fileLine[2];
        String model = fileLine[3];
        String vehicleType = fileLine[4];
        String color = fileLine[5];
        int odometer = Integer.parseInt(fileLine[6]);
        double price = Double.parseDouble(fileLine[7]);

        return new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
    }



}

package com.pluralsight.data;
import com.pluralsight.business.Dealership;
import com.pluralsight.business.Vehicle;
import java.io.*;

public class DealershipFileManager {
    private final String fileName;

    public DealershipFileManager(){
        this.fileName = "data/inventory.csv";
    }

    public Dealership getDealership(){
        Dealership d;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader bufReader = new BufferedReader(fr);

            String firstLine = bufReader.readLine();
            d = makeDealershipFromEncodedString(firstLine);

            String line;
            while((line = bufReader.readLine()) != null){
                Vehicle v = makeVehicleFromEncodedString(line);
                d.addVehicle(v);
            }


            bufReader.close();
            return d;
        }
        catch( IOException e){
            throw new RuntimeException("Cannot read dealership from file");
        }

    }


    private Dealership makeDealershipFromEncodedString(String s){
        String[] dealerInfo = s.split("\\|");

         return new Dealership(dealerInfo[0], dealerInfo[1], dealerInfo[2]);
    }

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

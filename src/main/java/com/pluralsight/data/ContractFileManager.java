package com.pluralsight.data;

import com.pluralsight.business.Contract;
import com.pluralsight.business.LeaseContract;
import com.pluralsight.business.SalesContract;
import com.pluralsight.business.Vehicle;
import java.io.*;
import java.util.ArrayList;

public class ContractFileManager {
    private final String fileName;

    public ContractFileManager(){
        fileName = "data/contracts.csv";
    }

    public ArrayList<Contract> getContracts(){
        ArrayList<Contract> contracts = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null){
                if (!line.isBlank()) {
                    Contract contract = makeContractFromEncodedString(line);
                    contracts.add(contract);
                }
            }
            bufferedReader.close();
        }catch(IOException e){
            throw new RuntimeException("Error reading from file.");
        }
        return contracts;
    }

    private Contract makeContractFromEncodedString(String line){

        Vehicle vehicle = makeVehicleFromEncodedString(line);

        String[] fileLine = line.split("\\|");
        String date = fileLine[1];
        String name = fileLine[2];
        String email = fileLine[3];

        if (fileLine[0].equalsIgnoreCase("sale")) {

            boolean isFinance = fileLine[16].equalsIgnoreCase("yes");
            return new SalesContract(date, name, email, vehicle, isFinance);

        }
        else{
            return new LeaseContract(date, name, email, vehicle);
        }

    }
    private Vehicle makeVehicleFromEncodedString(String line){
        String[] fileLine = line.split("\\|");

        int vin = Integer.parseInt(fileLine[4]);
        int year = Integer.parseInt(fileLine[5]);
        String make = fileLine[6];
        String model = fileLine[7];
        String vehicleType = fileLine[8];
        String color = fileLine[9];
        int odometer = Integer.parseInt(fileLine[10]);
        double price = Double.parseDouble(fileLine[11]);


        return new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
    }
    public String makeFileLine(Contract contract){
        Vehicle vehicle = contract.getVehicle();
        String type = contract instanceof SalesContract ? "SALE" : "LEASE";
        return String.format("%s|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%s%n",
                type,
                contract.getDate(),
                contract.getName(),
                contract.getEmail(),
                vehicle.getVin(),
                vehicle.getYear(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getVehicleType(),
                vehicle.getColor(),
                vehicle.getOdometer(),
                vehicle.getPrice(),
                contract
                );

    }
    public void saveContracts(ArrayList<Contract> contracts){
        try{
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Contract contract : contracts){
                String fileLine = makeFileLine(contract);
                System.out.println(fileLine);
                bufferedWriter.write(fileLine);
            }
            bufferedWriter.close();
        }catch(IOException e){
            throw new RuntimeException("Error saving to file.");
        }
    }
}

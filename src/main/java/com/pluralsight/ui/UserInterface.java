package com.pluralsight.ui;

import com.pluralsight.business.Dealership;
import com.pluralsight.data.DealershipFileManager;

public class UserInterface {
    private Dealership dealership;

    private void init(){
        DealershipFileManager dealershipFileManager = new DealershipFileManager();

        dealership = dealershipFileManager.getDealership();

    }
    public void display(){
        try{

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


        }
        catch(RuntimeException e){
            System.out.println("There was an error reading from the file: " + e.getMessage());
        }




    }

    // processGetByPriceRequest()
    // processGetByMakeModelRequest()
    // processGetByYearRequest()
    // processGetByColorRequest()
    // processGetByMileageRequest()
    // processGetByVehicleRequest()
    // processGetAllVehiclesRequest()
    // processAddByRequest()
    // processRemoveVehicleRequest()

}

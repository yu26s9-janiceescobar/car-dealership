package com.pluralsight.ui;
import java.time.Year;
import java.util.*;
public class Console {
    public final static Scanner scanner = new Scanner(System.in);

    public static String promptForString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine().strip().toLowerCase();
    }
    public static double promptForDouble(String prompt){
        while (true) {
            try {
                String input = promptForString(prompt);
                return parseDouble(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }
    public static double promptForCurrency(String prompt){
        while (true){
            try {
                String input = promptForString(prompt);
                return parseCurrency(input);
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
    private static double parseCurrency(String input){
        try{
            double parseDouble = parseDouble(input);
            if (parseDouble < 0){
                throw new IllegalArgumentException("Amount cannot be less than 0.");
            }
            if (input.contains(".")){
                String[] decimal = input.split("\\.");
                if (decimal[1].length() > 2){
                    throw new IllegalArgumentException("Cannot be more than two decimal places.");
                }
            }
            return parseDouble;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private static double parseDouble(String input){
        try{
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Input: Please Try Again.");
        }
    }
    private static int parseInt(String input){
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: Please Try Again.");
        }
    }

    public static int promptForInt(String prompt){
        try{
            String input = promptForString(prompt);
            return parseInt(input);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    public static int promptForIntRange(String prompt, String errorMessage, int min, int max, int... options) {
        while (true){
            try {
                String input = promptForString(prompt);
                int userChoice = parseInt(input);
                for (int num : options){
                    if (num == userChoice){
                        return userChoice;
                    }
                }
                if (userChoice >= min && userChoice <= max){
                    return userChoice;
                }
                throw new IllegalArgumentException(errorMessage);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }


}

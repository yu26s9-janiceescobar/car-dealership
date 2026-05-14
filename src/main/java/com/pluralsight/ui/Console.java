package com.pluralsight.ui;
import java.util.*;
public class Console {
    public final static Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to enter a string.
     * @param prompt the message displayed to the user.
     * @return String entered by user.
     */
    public static String promptForString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine().strip().toLowerCase();
    }

//    public static double promptForDouble(String prompt){
//        while (true) {
//            try {
//                String input = promptForString(prompt);
//                return parseDouble(input);
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }

//    public static double promptForCurrency(String prompt){
//        while (true){
//            try {
//                String input = promptForString(prompt);
//                return parseCurrency(parseDouble(input));
//            }catch(IllegalArgumentException e){
//                System.out.println(e.getMessage());
//            }
//        }
//    }

    /**
     * Prompts the user to enter a valid currency amount within a given range.
     * @param prompt the message displayed to the user.
     * @param min the minimum amount a user is allowed to enter.
     * @param max the maximum amount a user it allowed to enter.
     * @param showOptions will display message regarding options to user if true.
     * @return double entered by user and accepted as a currency amount.
     */
    public static double promptForCurrencyRange(String prompt, double min, double max, boolean showOptions){ // Do I need a boolean if options is always shown?
        while (true){
           if (showOptions){
               System.out.printf("Must be between $%,.2f and $%,.2f %n", min, max);
           }
           String input = promptForString(prompt);
            try {
                
                double parseCurrency = parseCurrency(parseDouble(input));

                if(!(min > max) && !(parseCurrency < min) && !(parseCurrency > max)){
                    return parseCurrency;
                }

            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Takes a double and checks if it is a valid currency amount.
     * @param parseDouble the double entered.
     * @return double accepted as a valid currency amount.
     */
    private static double parseCurrency(double parseDouble){
        if (parseDouble < 0){
            throw new IllegalArgumentException("Amount cannot be less than 0.");
        }
        String parseString = String.valueOf(parseDouble);
        if (parseString.contains(".")){
            String[] decimal = parseString.split("\\.");
            if (decimal[1].length() > 2){
                throw new IllegalArgumentException("Cannot be more than two decimal places.");
            }
        }
        return parseDouble;
    }

    /**
     * Takes a string and converts it into a double.
     * @param input the String entered.
     * @return the double entered.
     */
    private static double parseDouble(String input){
        try{
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Input. Please Try Again.");
        }
    }

    /**
     * Takes a string and converts it into an integer.
     * @param input the string entered.
     * @return the integer entered.
     */
    private static int parseInt(String input){
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Input. Please Try Again.");
        }
    }

    /**
     * Prompts the user to enter an integer.
     * @param prompt the message displayed to the user.
     * @return the integer entered by the user.
     */
    public static int promptForInt(String prompt){
        while(true) {
            try {
                String input = promptForString(prompt);
                return parseInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Prompts user to enter an integer within a given range of numbers.
     * @param prompt the message displayed to the user.
     * @param min the minimum integer allowed to be entered by user.
     * @param max the maximum integer allowed to be entered by user.
     * @param options additional integers allowed to be entered, if needed.
     * @return the integer entered by user.
     */
    public static int promptForIntRange(String prompt, int min, int max, int... options) {
        while (true){
            String input = promptForString(prompt);
            if (options.length == 0){
                System.out.printf("Must be between %d and %d", min, max);
            }

            try {
                int userChoice = parseInt(input);

                if (userChoice >= min && userChoice <= max){
                   return userChoice;
                }
                for (int option : options) {
                    if (userChoice == option) {
                        return userChoice;
                    }
                }
                System.out.println("Invalid Option. Please Try Again.");
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }


}

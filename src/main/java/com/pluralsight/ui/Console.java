package com.pluralsight.ui;
import java.util.*;
public class Console {
    public final static Scanner scanner = new Scanner(System.in);

    public static String promptForString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }
    public static void promptForIntOptions(String prompt, int min, int max, int... ){
        int userChoice = Integer.parseInt(promptForString(prompt));
    }
    public static void promptForIntOptions(String prompt, int... options){

    }
}

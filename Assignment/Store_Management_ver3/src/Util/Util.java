
package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author toden
 */
public class Util {

    public static int inputInt(String str, int i, int n) {
        Scanner sc = new Scanner(System.in);
        System.out.print(str + " (" + i + "-" + n + "): ");
        String regex = generateIntegerRangeRegex(i, n);
        String choice = sc.nextLine();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(choice);
        if (!matcher.matches()) {
            System.out.println("out of range");
            return inputInt(str, i, n);
        }
        return Integer.parseInt(choice);
    }

    public static double inputDouble(String str, double i, double n) {
        Scanner sc = new Scanner(System.in);
        System.out.print(str + " (" + i + "-" + n + "): ");
        String choice = sc.nextLine();
        try {
            if (!isDoubleInRange(choice, i, n)) {
                System.out.println("out of range");
                return inputDouble(str, i, n);
            }
        } catch (Exception ex) {
            System.out.println("wrong fotmat");
            return inputDouble(str, i, n);
        }

        return Double.parseDouble(choice);
    }
    
    // Function to check if a string is a double and in a specified range
    public static boolean isDoubleInRange(String inputStr, double minRange, double maxRange) {
        try {
            // Attempt to parse the input string to a double
            double value = Double.parseDouble(inputStr);
            
            // Check if the value is within the specified range
            if (value >= minRange && value <= maxRange) {
                return true;
            }
        } catch (NumberFormatException e) {
            // Parsing failed, input is not a double
        }
        return false;
    }

    public static String generateIntegerRangeRegex(int a, int b) {
        // Ensure that 'a' is less than or equal to 'b'
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        // Create the regex pattern for the specified range
        StringBuilder regex = new StringBuilder("^(");
        for (int i = a; i <= b; i++) {
            regex.append(i);
            if (i < b) {
                regex.append("|");
            }
        }
        regex.append(")$");

        return regex.toString();
    }

    

    public static String inputString(String content) {
        Scanner sc = new Scanner(System.in);
        System.out.print(content + ": ");
        String str = sc.nextLine();
        if (str.trim().isEmpty()) {
            System.out.println("input is invalid");
            return inputString(content);
        }
        return str;
    }

    public static boolean inputYN(String content) {
        Scanner sc = new Scanner(System.in);
        System.out.print(content + ": ");
        String str = sc.nextLine();
        if (!str.matches("^[YyNn]$")) {
            System.out.println("input is invalid");
            return inputYN(content);
        }
        return str.matches("^[Yy]$");
    }

    public static Date dateParse(String strDate) {
        // Define the date format
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            // Parse the string into a Date object
            Date date = dateFormat.parse(strDate);

            // You can now work with the Date object as needed
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Date inputDate(String content) {
        String dateStr = inputString(content);
        // Define the regex pattern for "dd-MM-yyyy" format
        String regexPattern = "\\d{2}-\\d{2}-\\d{4}";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regexPattern);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(dateStr);

        // Check if the input string matches the pattern
        if (!matcher.matches()) {
            System.err.println("invalid date");
            return inputDate(content);
        }
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
        } catch (ParseException ex) {
            System.err.println("invalid date");
            return inputDate(content);
        }
    }

    public static String formatDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

}

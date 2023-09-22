/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author lvhho
 */
public class DateTime {

    private static Scanner sc = new Scanner(System.in);

    //<1> Get Manufature Date of product
    public static String getManuFactureDate() {
        String result = null;
        Date date = null;
        do {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            try {
                date = df.parse(sc.nextLine());
                result = df.format(date);
                return result;
            } catch (Exception e) {
                System.err.println("Invalid value! try again <dd-MM-yyyy>");
            }
        } while (true);
    }

    //<2> Get Exprition Date of prodeuct
    public static String getExpritionDate() {
        String result = null;
        Date date = null;
        do {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            try {
                date = df.parse(sc.nextLine());
                result = df.format(date);
                return result;
            } catch (Exception e) {
                System.err.print("Invalid value! try again <dd-MM-yyyy> :");
            }
        } while (true);
    }

    //<3> Get time current
    public static String getTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("hh:mm | dd-MM-yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    //<3.2> Get day Current
    public static String getDay() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    //<4> Check expired: kiem tra han su dung
    public static boolean isExpired(String day) {
        try {
            Date today = null;
            Date exDate = null;
            // Get Date of now, today
            String stringToday = DateTime.getDay();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            today = df.parse(stringToday);
            // Get Date of exprition day
            exDate = df.parse(day);
            if (today.after(exDate)) {
                return true;
            } else {
                return false;
            }

        } catch (ParseException ex) {
            System.err.println("ERROR");
        }
        return false;
    }

    // <5> Check exprition date (exprition after manufacture)
    public static String checkExpritionDate(String manufacture) {
        String result;
        Date manDate = null;
        Date exDate = null;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        do {
            try {
                // Get Date of manDate
                manDate = df.parse(manufacture);
                // input exDate
                result = DateTime.getExpritionDate();
                // get Date of exDate
                exDate = df.parse(result);

                // Compare exDate after manDate
                if (exDate.after(manDate)) {
                    return result;
                }
                System.err.println("Exprition date must after manufacture date!!!");
                System.out.print("Enter again <dd-MM-yyyy>: ");
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        } while (true);
    }
}

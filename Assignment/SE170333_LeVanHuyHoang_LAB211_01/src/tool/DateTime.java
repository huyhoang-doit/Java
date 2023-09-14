/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

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
        boolean check = false;
        do {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            try {
                date = df.parse(sc.nextLine());
                result = date.toString();
                check = true;
            } catch (Exception e) {
                System.out.print("Invalid value, try again <dd-MM-yyyy> :");
            }
        } while (check = false);
        return result;
    }

    //<2> Get Exprition Date of prodeuct
    public static String getExpritionDate() {
        String result = null;
        Date date = null;
        boolean check = false;
        do {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            try {
                date = df.parse(sc.nextLine());
                result = date.toString();
                check = true;
            } catch (Exception e) {
                System.out.print("Invalid value, try again <dd-MM-yyyy> :");
            }
        } while (check = false);
        return result;
    }

    //<3> Get time current
    public static String getTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

}

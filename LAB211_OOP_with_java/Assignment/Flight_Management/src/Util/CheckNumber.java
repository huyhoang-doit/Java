/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author lvhho
 */
public class CheckNumber {

    private static Scanner sc = new Scanner(System.in);

    // <1> Get Integer
    public static int getAnInteger() {
        do {
            try {
                int soNguyen = Integer.parseInt(sc.nextLine());
                return soNguyen;
            } catch (Exception e) {
                System.err.println("Invalid value, try again!");
            }
        } while (true);
    }

    // <2> Get Integer with request
    public static int getAnInteger(String messRequest, String messError, int lower, int upper) {
        if (upper < lower) {
            int tmp = upper;
            upper = lower;
            lower = tmp;
        }
        int n;
        do {
            try {
                System.out.print(messRequest);
                n = Integer.parseInt(sc.nextLine());
                while (n > upper || n < lower) {
                    System.err.println("Value out of range!");
                    System.out.print("Try again: ");
                    n = Integer.parseInt(sc.nextLine());
                }
                return n;
            } catch (Exception e) {
                System.err.println(messError);
            }

        } while (true);
    }

    // <3> Get Double
    public static Double getADouble() {
        do {
            try {
                double soThuc = Double.parseDouble(sc.nextLine());
                return soThuc;
            } catch (Exception e) {
                System.err.println("Invalid value, try again!");
            }
        } while (true);
    }

    //<4> getValue of quantity seats on flight
    public static int getQuantitySeats() {
        int value;
        do {
            value = CheckNumber.getAnInteger();
            if (value >= 9) {
                System.err.println("Quantity is not according to regulations");
                System.out.print("Please re-enter another quantity < 9: ");

            }
            if(value == 0) {
                break;
            }
        } while (value >= 9);
        return value;
    }

   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Enum.LocationItem;
import Enum.MenuItem;
import Model.Crew;
import Model.Customer;
import Model.Flight;
import Model.Staff;
import Views.Menu;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author lvhho
 */
public class CheckRule {

    private static Scanner sc = new Scanner(System.in);

    //<1> Get String Day
    public static String getDay() {
        String result;
        Date date = null;
        do {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            try {
                date = df.parse(sc.nextLine());
                result = df.format(date);
                return result;
            } catch (Exception e) {
                System.err.println("Invalid value, try again <dd-MM-yyyy>");
            }
        } while (true);
    }

    // ... getTime
    public static String getTime() {
        String result = null;
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm");

        while (true) {
            try {
                String input = sc.nextLine();
                if (input.matches("^\\d{2}:\\d{2}$")) {
                    LocalTime time = LocalTime.parse(input);
                    result = tf.format(time);
                    return result;
                } else {
                    System.err.println("Invalid value, try again <hh:mm>");
                }
            } catch (Exception e) {
                System.err.println("Invalid value, try again <hh:mm>");
            }
        }
    }

    // <2> Get Location 
    public static String getDeparture() {
        LocationItem.printListLocations();
        int choice = CheckNumber.getAnInteger("Departure: ", "ERROR", 1, LocationItem.size());
        return LocationItem.getByOrder(choice);
    }

    // <3> Get destination
    public static String getDestination(int choice) {
        LocationItem.locationWithOut(choice);
        String regex = getRegex(choice);
        Pattern p = Pattern.compile(regex);
        Integer intDes;
        do {
            System.out.print("Destination:");
            intDes = CheckNumber.getAnInteger();
            String sDes = intDes.toString();
            if (p.matcher(sDes).find()) {
                return LocationItem.getByOrder(intDes);
            } else {
                System.err.println("Invalid destination!");
                System.out.print("Try again: ");
            }
        } while (true);
    }

    // ... generate regex for choice destination
    public static String getRegex(int choice) {
        StringBuilder regex = new StringBuilder("^(");
        for (int i = 1; i <= LocationItem.size(); i++) {
            if (i != choice) {
                regex.append(i);
                if (i < LocationItem.size()) {
                    regex.append("|");
                }
            }
        }
        regex.append(")$");
        return regex.toString();
    }

    // <4> Get quantity seats valid
    public static int validQuantity(int remain) {
        int sl;
        do {
            sl = CheckNumber.getAnInteger();
            if (sl > remain) {
                System.err.println("The number of seats is not enough!");
                System.out.print("Select quantity again ( < " + remain + " ): ");
            }
        } while (sl > remain);
        return sl;
    }

    // <5> Create seat map on flight
    public static ArrayList<String> listSeatsBusiness() {
        ArrayList<String> ds = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            for (char j = 'A'; j <= 'C'; j++) {
                String result = i + "" + j;
                ds.add(result);
            }
        }
        return ds;
    }

    public static ArrayList<String> listSeatEco() {
        ArrayList<String> ds = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            for (char j = 'D'; j <= 'O'; j++) {
                String result = i + "" + j;
                ds.add(result);
            }
        }
        return ds;
    }

    //<6> Input Id
    public static String inputID(String type) {
        String result;
        String regex = "^(\\b" + type + "[a-zA-S0-9]{4})$";
        Pattern p = Pattern.compile(regex);
        do {
            result = sc.nextLine();
            if (p.matcher(result).find()) {
                return result;
            } else {
                System.err.println("Invalid ID");
                System.out.print("Please re-enter ID: ");
            }
        } while (true);
    }
    // <7> getPhone number => 11 int

    public static String getPhone() {
        String value;
        Pattern p = Pattern.compile("^([0-9]{10,11})$");
        do {
            value = sc.nextLine().trim();
            if (p.matcher(value).find()) {
                return value;
            }
            System.err.println("Invalid value, The phone number consists of 10 - 11 integers");
            System.out.print("Please re-enter another phone number: ");
        } while (true);

    }

    // <8> getPersonID  => 12 int
    public static String getPersonID(ArrayList<String> listPerIDs, String name, HashMap<String, Customer> cus) {
        String value;
        Pattern p = Pattern.compile("^([0-9]{12})$");
        do {
            value = sc.nextLine().trim();
            if (p.matcher(value).find()) {
                boolean check = true;
                // check value exist or not
                for (Customer cu : cus.values()) {
                    if (cu.getPersonID().equals(value)) {
                        if (cu.getName().equals(name) == false) {
                            System.out.println("Invalid value, PersonID already exist with another person");
                            check = false;
                            break;
                        }
                        if (cu.getName().equals(name)) {
                            System.out.println("You are " + name + "?");
                            System.out.println("Your Person ID is " + cu.getPersonID());
                            MenuItem confirm = Menu.confirm(Menu.menuConfirm, ">> Confirm");
                            switch (confirm) {
                                case CONFIRM: {
                                    return cu.getPersonID();
                                }
                                case BACK: {
                                    System.out.println("Invalid value, PersonID already exist with another person");
                                    check = false;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (check == true) {
                    return value;
                } else {

                    System.out.print("Please re-enter your person ID: ");
                }
            } else {
                System.err.println("Invalid value, The person ID consists of 12 integers");
                System.out.print("Please re-enter your person ID: ");
            }
        } while (true);

    }

    // <9> getEmail
    public static String getEmail() {
        String value;
        Pattern p;
        p = Pattern.compile("^([0-9a-zA-Z]+@[a-z]+.[a-z]{2,4})$");
        do {
            value = sc.nextLine().trim();
            if (p.matcher(value).find()) {
                return value;
            }
            System.err.println("Invalid value, The Email wrong ");
            System.out.print("Please re-enter your email: ");
        } while (true);

    }

    public static String idAuto(int count, String type) {
        String result;
        String fm = String.format("%04d", count);
        result = type + fm;
        return result;
    }

    // <10> Map seats
    public static void mapSeats(String type, Flight fl) {
        ArrayList<String> listEco = fl.listEcoSeats;
        ArrayList<String> listBus = fl.listBusinessSeats;

        if (type.equals("Economy Class")) {
            // D1 -> O6
            System.out.println(">> SEATING CHART - ECONOMY CLASS");
            for (char t = 'D'; t < 'P'; t++) {
                for (int i = 1; i <= 6; i++) {
                    String seat = i + "" + t;
                    if (listEco.contains(seat)) {
                        System.out.print("| " + seat + " | ");
                    } else {
                        System.out.print("| -- | ");
                    }
                    if (i == 3) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
        } else {
            // A1 -> C6
            System.out.println(">> SEATING CHART - BUSINESS CLASS");
            for (char t = 'A'; t < 'D'; t++) {
                for (int i = 1; i <= 6; i++) {
                    String seat = i + "" + t;
                    if (listBus.contains(seat)) {
                        System.out.print("| " + seat + " | ");
                    } else {
                        System.out.print("| -- | ");
                    }
                    if (i == 3) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
        }
    }

}

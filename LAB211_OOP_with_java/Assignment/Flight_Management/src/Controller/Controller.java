/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Enum.*;
import Model.*;
import Util.*;
import Views.Menu;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author lvhho
 */
public class Controller {

    // variable
    private static int countFli = loadFlightDefault().size() + 1;
    private static int countEmp = loadStaffDefault().size() + 1;
    private static int countCre = loadFlightDefault().size() + 1;
    private static int countCus = 1;
    private static int countBks = 1;
    private static ArrayList<String> listPerIDs = new ArrayList<>();

    public static Scanner sc = new Scanner(System.in);

    // <0> -- DATA MANAGEMENT --
    //... load file default to demo
    public static SystemManager loadData(SystemManager sm) {
        sm.setFlights(loadFlightDefault());
        sm.setStaffs(loadStaffDefault());
        sm.setCrews(listCrewsDefault(loadFlightDefault(), loadStaffDefault(), sm));
        return sm;
    }

    public static HashMap<String, Flight> loadFlightDefault() {
        HashMap<String, Flight> list = new HashMap<>();
        try {
            FileReader fr = new FileReader("src//Default//Flights1.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                // Split toString of flight to get components of flight
                String[] components = line.split(",");
                String fNumber = components[0];
                String crewID = components[1];
                String departure = components[2];
                String destination = components[3];
                String date = components[4];
                String time = components[5];
                String duration = components[6];
                int seatBooked = Integer.parseInt(components[7]);
                int seatEmpty = Integer.parseInt(components[8]);
                int numberOfSeats = Integer.parseInt(components[9]);
                Double price = Double.parseDouble(components[10]);
                // add flight 
                Flight fl = new Flight(fNumber, crewID, departure, destination, date, time, duration, price);
                list.put(fNumber, fl);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static HashMap<String, Staff> loadStaffDefault() {
        HashMap<String, Staff> list = new HashMap<>();
        try {
            FileReader fr = new FileReader("src//Default//Employees1.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                // Split toString of Staff to get components of Staff
                String[] components = line.split(", ");
                String staffID = components[0];
                String name = components[1];
                String role = components[2];

                // add Staff
                Staff e = new Staff(staffID, name, role);
                list.put(staffID, e);
            }
        } catch (Exception e) {
        }
        return list;
    }

    // create default crew for flight 
    public static HashMap<String, Crew> listCrewsDefault(HashMap<String, Flight> listFls, HashMap<String, Staff> listEs, SystemManager sm) {
        HashMap<String, Crew> listCs = new HashMap<>();
        ArrayList<Staff> listBusy = new ArrayList<>();
        int pilots = 2;
        int technicians = 2;
        int stewardess = 4;
        for (Flight fl : listFls.values()) {
            String crID = fl.getCrewID();
            String flID = fl.getfNumber();
            ArrayList<Staff> listStaff = new ArrayList<>();
            int countP = 0;
            int countT = 0;
            int countS = 0;
            for (Staff e : listEs.values()) {
                if (e.getRole().equals("Pilot") && listBusy.contains(e) == false && countP < pilots) {
                    listStaff.add(e);
                    listBusy.add(e);
                    countP++;
                }
                if (e.getRole().equals("Technician") && listBusy.contains(e) == false && countT < technicians) {
                    listStaff.add(e);
                    listBusy.add(e);
                    countT++;
                }
                if (e.getRole().equals("Stewardess") && listBusy.contains(e) == false && countS < stewardess) {
                    listStaff.add(e);
                    listBusy.add(e);
                    countS++;
                }
                if (countP == pilots && countT == technicians && countS == stewardess) {
                    break;
                }
            }
            Crew newCr = new Crew(crID, flID, listStaff);
            listCs.put(crID, newCr);
        }
        return listCs;
    }

    //... save data
    public static void saveData(SystemManager sm) {
        try {
            // new FileWriter and BufferWriter
            FileWriter fileF = new FileWriter("src//Data//Flights.dat");
            FileWriter fileCus = new FileWriter("src//Data//Customers.dat");
            FileWriter fileB = new FileWriter("src//Data//Bookings.dat");
            FileWriter fileE = new FileWriter("src//Data//Employees.dat");
            FileWriter fileCr = new FileWriter("src//Data//Crews.dat");

            BufferedWriter wF = new BufferedWriter(fileF);
            BufferedWriter wCus = new BufferedWriter(fileCus);
            BufferedWriter wB = new BufferedWriter(fileB);
            BufferedWriter wE = new BufferedWriter(fileE);
            BufferedWriter wCr = new BufferedWriter(fileCr);

            // write value to file
            for (Flight value : sm.getFlights().values()) {
                wF.write(value.toString());
                wF.newLine();
            }

            for (Customer value : sm.getCustomers().values()) {
                wCus.write(value.toString());
                wCus.newLine();
            }

            for (Booking value : sm.getBookings().values()) {
                wB.write(value.toString());
                wB.newLine();
            }

            for (Staff value : sm.getStaffs().values()) {
                wE.write(value.toString());
                wE.newLine();
            }

            for (Crew value : sm.getCrews().values()) {
                wCr.write(value.toString());
                wCr.newLine();
            }

            // close FileWriter and BufferWriter
            wF.close();
            wCus.close();
            wB.close();
            wE.close();
            wCr.close();

            fileF.close();
            fileCus.close();
            fileB.close();
            fileE.close();
            fileCr.close();
        } catch (IOException e) {
        }
    }

    // <1> -- BOOKING --  
    public static SystemManager createBooking(SystemManager sm) {
        // Request from user
        System.out.println(">> Choose your departure location ");
        LocationItem.printListLocations();
        int lo1 = CheckNumber.getAnInteger("Departure: ", "ERROR", 1, LocationItem.size());
        String dep = LocationItem.getByOrder(lo1);
        System.out.println(">> Choose your destination location ");
        String des = CheckRule.getDestination(lo1);
        System.out.print(">> Departure day: ");
        String day = CheckRule.getDay();
        System.out.println(">> Choose seat class ");
        System.out.println("** Business class seat prices are 20% higher");
        String type = Menu.seatClass(Menu.menuSeatClass);
        System.out.print(">> Quantity:  ");
        int sl = CheckNumber.getQuantitySeats();
        if (sl == 0) {
            System.err.println("Invalid quantity! ");
            System.out.println("Thank you. See you later");
            return sm;
        }

        // Result flights from the request of user
        HashMap<String, Flight> listFls = new HashMap<>();
        for (Flight fl : sm.getFlights().values()) {
            if (fl.getDeparture().equals(dep) && fl.getDestination().equals(des)
                    && fl.getDate().equals(day) && fl.getEmptySeats() > sl) {
                listFls.put(fl.getfNumber(), fl);
            }
        }
        if (listFls.isEmpty()) {
            System.out.println("No suitable flight found :((");
        } else {
            String flID = chooseFlight(listFls);
            Flight fl = sm.getFlights().get(flID);
            MenuItem confirm = Menu.confirm(Menu.menuConfirm, ">> Start booking tickets ");
            switch (confirm) {
                case CONFIRM: {
                    // cung cap thong tin ca nhan
                    Customer cus = addCustomer(sl, sm);
                    listPerIDs.add(cus.getPersonID());
                    double price = getPrice(type, fl, sl);
                    MenuItem confirm1 = Menu.confirm(Menu.menuConfirm, ">> Confirm payment of " + price + ":");
                    switch (confirm1) {
                        case CONFIRM: {
                            sm.getCustomers().put(cus.getCusID(), cus);
                            HashMap<String, Booking> listTickets = createTickets(sl, cus.getCusID(), type, flID, sm);
                            sm.getBookings().putAll(listTickets);
                            System.out.println("Payment success");
                            showIDBooking(listTickets, sm);
                            System.out.println("Thank you. See you later");
                            countCus++;
                            break;
                        }
                        case BACK: {
                            System.out.println("Thank you. See you later");
                            break;
                        }
                    }
                    break;
                }
                case BACK: {
                    System.out.println("Thank you. See you later");
                    break;
                }
            }
        }

        return sm;
    }

    // ... chooseFlight method 
    public static String chooseFlight(HashMap<String, Flight> listFls) {
        System.out.println(">> SUITABLE FLIGHTS");

        System.out.println("+-------------------------------------------------"
                + "-------------------------------+");
        System.out.printf("|%5s|%-15s|%-15s|%-10s|%5s|%8s|%5s|%-10s|\n",
                "Fl-ID", "Departure", "Destination", "Date", "Time", "Duration", "Empty", "Price");
        System.out.println("+-----+---------------+---------------+----------+"
                + "-----+--------+-----+----------+");
        for (Flight fl : listFls.values()) {
            fl.printInfoFlight();
            System.out.println("+-----+---------------+---------------+----------+"
                    + "-----+--------+-----+----------+");
        }
        System.out.print(">> Choose flight - Enter Fl-ID: ");
        do {
            String flID = CheckRule.inputID("F");
            for (String id : listFls.keySet()) {
                if (flID.equals(id)) {
                    System.out.println("Flight " + id + " was selected");
                    return id;
                }
            }
            System.err.println("Flight code does not exist");
            System.out.print("Try again: ");
        } while (true);

    }

    // AddCustomer Information
    public static Customer addCustomer(int sl, SystemManager sm) {
        String cusID = CheckRule.idAuto(countCus, "C");
        System.out.print("Name: ");
        String name = sc.nextLine().toUpperCase().trim();
        System.out.print("PersonID - CCCD: ");
        String perID = CheckRule.getPersonID(listPerIDs, name, sm.getCustomers());
        System.out.print("Phone: ");
        String phone = CheckRule.getPhone();
        System.out.print("Email: ");
        String email = CheckRule.getEmail();
        Customer cus = new Customer(cusID, name, phone, email, perID, sl);
        return cus;
    }

    //... create tickets from information booking of customer
    public static HashMap<String, Booking> createTickets(int sl, String cusID, String typeSeat, String flID, SystemManager sm) {
        HashMap<String, Booking> list = new HashMap<>();
        for (int i = 1; i <= sl; i++) {
            String bookId = CheckRule.idAuto(countBks, "B");
            countBks++;
            String seat = "--";
            if (typeSeat.equals("Economy Class")) {
                do {
                    //map seat
                    CheckRule.mapSeats(typeSeat, sm.getFlights().get(flID));
                    System.out.println("Enter the seat number for  " + cusID + "  < ex: 2F > :");
                    String choice = sc.nextLine();
                    ArrayList<String> listSeatOnFlight = sm.getFlights().get(flID).listEcoSeats;
                    if (listSeatOnFlight.contains(choice)) {
                        for (int j = 0; j < listSeatOnFlight.size(); j++) {
                            if (listSeatOnFlight.get(j).equals(choice)) {
                                seat = sm.getFlights().get(flID).listEcoSeats.remove(j);
                                break;
                            }
                        }
                    } else {
                        System.err.println("This seat number has been registered or does not exist");
                    }
                } while (seat.equals("--"));
            } else {

                do {
                    // map seat
                    CheckRule.mapSeats(typeSeat, sm.getFlights().get(flID));
                    System.out.println("Enter the seat number number for  " + cusID + "  < ex: 1A > :");
                    String choice = sc.nextLine();
                    ArrayList<String> listSeatOnFlight = sm.getFlights().get(flID).listBusinessSeats;
                    if (listSeatOnFlight.contains(choice)) {
                        for (int j = 0; j < listSeatOnFlight.size(); j++) {
                            if (listSeatOnFlight.get(j).equals(choice)) {
                                seat = sm.getFlights().get(flID).listBusinessSeats.remove(j);
                            }
                        }
                    } else {
                        System.err.println("This seat number has been registered or does not exist");
                    }

                } while (seat.equals("--"));
            }

            Booking bk = new Booking(bookId, flID, cusID, typeSeat, seat);
            list.put(bookId, bk);
        }
        int seatBooked = sm.getFlights().get(flID).getSeatsBooked();
        sm.getFlights().get(flID).setSeatsBooked(seatBooked + sl);
        return list;
    }

    // ...show ID booking
    public static void showIDBooking(HashMap<String, Booking> listbook, SystemManager sm) {
        System.out.println("This is your booking code ");
        System.out.println("======================");
        for (Booking bk : listbook.values()) {
            System.out.println("|       " + bk.getBookingID() + "        |");
        }
        System.out.println("======================");
        System.out.println("Please keep this code to check in and receive your flight ticket");

    }

    // ...get Price
    public static double getPrice(String type, Flight fl, int sl) {
        if (type.equals("Business Class")) {
            return (fl.getPrice() * fl.getTax() + fl.getPrice()) * sl;
        } else {
            return fl.getPrice() * sl;
        }
    }

    // <2> -- CHECK IN ONLINE --
    public static SystemManager checkInOnline(SystemManager sm) {
        MenuItem by = Menu.confirm(Menu.menuCheckIn, ">> Check in by ...");
        switch (by) {
            case BY_BOOKED_ID: {
                System.out.print("Enter booking ID: ");
                String bID = CheckRule.inputID("B");
                Booking bk = checkBkID(bID, sm);
                if (bk == null || true == bk.isStatus()) {
                    System.out.println("Information " + bID + " does not exist in system");
                } else {
                    showTicket(bk, sm);
                    MenuItem confirm = Menu.confirm(Menu.menuConfirm, ">> Issue the ticket");
                    switch (confirm) {
                        case CONFIRM: {
                            bk.setStatus(true);
                            System.out.println("Ticket issued successfully");
                            System.out.println("Thank you. See you later");
                            break;
                        }
                        case BACK: {
                            System.out.println("Thank you. See you later");
                            break;
                        }
                    }
                }

                break;
            }
            case BY_PERSON_ID: {
                System.out.print("Enter the person ID you used to booking: ");
                String pID = sc.nextLine();
                ArrayList<Booking> bks = checkPersonID(pID, sm);
                if (bks.isEmpty()) {
                    System.out.println("Information " + pID + " does not exist in system");
                } else {
                    for (Booking bk : bks) {
                        if (bk.isStatus() == true) {
                            System.out.println("Information " + pID + " does not exist in system");
                        } else {
                            showTicket(bk, sm);
                            MenuItem confirm = Menu.confirm(Menu.menuConfirm, ">> Issue the ticket");
                            switch (confirm) {
                                case CONFIRM: {
                                    bk.setStatus(true);
                                    System.out.println("Ticket issued successfully");
                                    break;
                                }
                                case BACK: {
                                    System.out.println("Thank you. See you later");
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
            }
        }
        return sm;
    }

    //... check booking ID 
    public static Booking checkBkID(String id, SystemManager sm) {
        if (sm.getBookings().containsKey(id)) {
            return sm.getBookings().get(id);
        } else {
            return null;
        }
    }

    //... check booking person ID
    public static ArrayList<Booking> checkPersonID(String pID, SystemManager sm) {
        ArrayList<Booking> listBkOfThisPerson = new ArrayList<>();
        String cusID = null;
        for (Customer cus : sm.getCustomers().values()) {
            if (cus.getPersonID().equals(pID)) {
                cusID = cus.getCusID();
            }
        }
        if (cusID == null) {
            return null;
        } else {
            for (Booking bk : sm.getBookings().values()) {
                if (bk.getCusID().equals(cusID)) {
                    listBkOfThisPerson.add(bk);
                }
            }
        }
        if (listBkOfThisPerson.isEmpty()) {
            return null;
        } else {
            return listBkOfThisPerson;
        }
    }

    // ... show tickets
    public static void showTicket(Booking bk, SystemManager sm) {
        Customer cus = sm.getCustomers().get(bk.getCusID());
        Flight fl = sm.getFlights().get(bk.getfNumber());
        System.out.println("============== AIR TICKET ===============");
        System.out.printf("|%39s|\n", "");
        System.out.printf("|%-7s|%22s|%-8s|\n", bk.getBookingID(), fl.getDate(), fl.getTime());
        System.out.printf("|%-15s ->     %-15s |\n", fl.getDeparture(), fl.getDestination());
        System.out.printf("|%5s %21s|%5s %-5s|\n", "Name:", cus.getName(), "Seat:", bk.getNumberOfSeat());
        System.out.printf("|%19s%5s%15s|\n", "", "Type:", bk.getTypeSeat());
        System.out.printf("|%39s|\n", "");
        System.out.println("=========================================");
    }

    // <3> -- LOG IN --
    public static SystemManager adminField(SystemManager sm) {
        boolean pass = checkPassWord();
        MenuItem choice;
        if (pass) {
            do {
                choice = Menu.Display(Menu.subMenuForAdmin);
                switch (choice) {
                    case FLIGHT_MANAGEMENT: {
                        flightManagement(sm);
                        break;
                    }
                    case STAFF_MANAGEMENT: {
                        staffManagement(sm);
                        break;
                    }
                    case CUSTOMER_MANAGEMENT: {
                        customerManagement(sm);
                        break;
                    }
                    case BOOKING_MANAGEMENT: {
                        bookingManagement(sm);
                        break;
                    }
                    case SAVE: {
                        saveData(sm);
                        System.out.println("Data has been saved successfully");
                        break;
                    }
                    case BACK: {
                        break;
                    }
                }
            } while (choice != MenuItem.BACK);
        } else {
            System.err.println("login unsuccessful!");
        }

        return sm;
    }

    // ... check password for login by Admin
    public static boolean checkPassWord() {
        String password = "staff";
        String passInput;
        do {
            System.out.println(">> You need to enter a password to log into the system");
            System.out.print("Password: ");
            passInput = sc.nextLine();
            if (passInput.equals(password)) {
                return true;
            } else {
                System.err.println("Login failed!");
                MenuItem confirm = Menu.confirm(Menu.menuConfirm, ">> Please try logging in again");
                switch (confirm) {
                    case CONFIRM: {
                        break;
                    }
                    case BACK: {
                        return false;
                    }
                }

            }
        } while (!passInput.equals(password));
        return false;
    }
    // FLIGHT MANAGEMENT 

    public static SystemManager flightManagement(SystemManager sm) {
        MenuItem choice;
        do {
            choice = Menu.Display(Menu.menuFlight);
            switch (choice) {
                case CREATE_NEW_FLIGHT: {
                    createNewFlight(sm);
                    break;
                }
                case UPDATE_FLIGHT: {
                    updateFlight(sm);
                    break;
                }
                case DELETE_FLIGHT: {
                    cancelFlight(sm);
                    break;
                }
                case SHOW_FLIGHT: {
                    listOfFlights(sm);
                    break;
                }
                case BACK: {
                    break;
                }
            }
        } while (choice != MenuItem.BACK);
        return sm;
    }

    public static SystemManager createNewFlight(SystemManager sm) {
        String fNumber = CheckRule.idAuto(countFli, "F");
        countFli++;
        System.out.println("Enter flight information >=)->");
        LocationItem.printListLocations();
        int choice = CheckNumber.getAnInteger("Departure: ", "ERROR", 1, LocationItem.size());
        String dep = LocationItem.getByOrder(choice);
        String des = CheckRule.getDestination(choice);
        System.out.print("Date <dd-MM-yyyy> :");
        String day = CheckRule.getDay();
        System.out.print("Time <hh:mm>: ");
        String time = CheckRule.getTime();
        System.out.print("Duration <mins>: ");
        Integer durInt = CheckNumber.getAnInteger();
        String durStr = durInt.toString();
        System.out.println("Create Crew for this flight:");
        Crew cr = createCrew(fNumber, day, sm);
        String crID = cr.getCrewID();
        System.out.print("Price: ");
        Double price = CheckNumber.getADouble();

        // add new flight
        Flight newFL = new Flight(fNumber, crID, dep, des, day, time, durStr, price);

        sm.getFlights().put(fNumber, newFL);
        System.out.println("Flight " + fNumber + " has been created successfully");
        return sm;
    }

    public static SystemManager updateFlight(SystemManager sm) {
        ArrayList<String> listFNumber = new ArrayList<>();
        for (String fl : sm.getFlights().keySet()) {
            listFNumber.add(fl);
        }
        //
        System.out.println("Enter Fl-ID you want to update:");
        String fID = CheckRule.inputID("F");
        if (listFNumber.contains(fID)) {
            System.out.println("Flight " + fID + " is ready to be updated");
            Flight flUpdate = sm.getFlights().get(fID);
            System.out.print("Update crew: ");
            Crew cr = createCrew(fID, flUpdate.getDate(), sm);
            String crID = cr.getCrewID();
            System.out.print("Date: ");
            String day = CheckRule.getDay();
            System.out.print("Time: ");
            String time = CheckRule.getTime();
            System.out.print("Price:");
            Double price = CheckNumber.getADouble();
            //
            flUpdate.setCrewID(crID);
            flUpdate.setDate(day);
            flUpdate.setPrice(price);
            flUpdate.setTime(time);

            sm.getFlights().put(fID, flUpdate);

        } else {
            System.err.println("There are no suitable flights");
        }
        return sm;
    }

    public static SystemManager cancelFlight(SystemManager sm) {
        ArrayList<String> listFNumber = new ArrayList<>();
        for (String fl : sm.getFlights().keySet()) {
            listFNumber.add(fl);
        }
        //
        System.out.println("Enter Fl-ID you want to CANCEL:");
        String fID = CheckRule.inputID("F");
        if (listFNumber.contains(fID)) {
            if (sm.getFlights().get(fID).getSeatsBooked() == 0) {
                MenuItem confirm = Menu.confirm(Menu.menuConfirm, "<!> Are you sure you want "
                        + "to CANCEL your flight? This operation is dangerous and can lead to many consequences");
                switch (confirm) {
                    case CONFIRM: {
                        sm.getFlights().remove(fID);
                        System.out.println("The flight was cancelled");
                        break;
                    }
                    case BACK: {
                        break;
                    }
                }
            } else {
                System.out.println("The flight has already been booked.");
                System.out.println("If there are issues, please consider using the UPDATE FEATURE for this flight");
            }
        } else {
            System.err.println("There are no suitable flights");
        }

        return sm;
    }

    public static void listOfFlights(SystemManager sm) {
        System.out.println("+-------------------------------------------------"
                + "-------------------------------+");
        System.out.printf("|%5s|%-15s|%-15s|%-10s|%5s|%8s|%5s|%-10s|\n",
                "Fl-ID", "Departure", "Destination", "Date", "Time", "Duration", "Empty", "Price");
        System.out.println("+-----+---------------+---------------+----------+"
                + "-----+--------+-----+----------+");
        for (Flight fl : sm.getFlights().values()) {
            fl.printInfoFlight();
            System.out.println("+-----+---------------+---------------+----------+"
                    + "-----+--------+-----+----------+");
        }
    }

    // STAFF MANAGEMENT  
    public static SystemManager staffManagement(SystemManager sm) {
        MenuItem choice;
        do {
            choice = Menu.Display(Menu.menuStaff);
            switch (choice) {
                case EDIT_INFO_STAFF: {
                    editInfoStaff(sm);
                    break;
                }
                case SHOW_CREW: {
                    listOfCrews(sm);
                    break;
                }
                case BACK: {
                    break;
                }
            }
        } while (choice != MenuItem.BACK);
        return sm;
    }

    // ... edit information of staff
    public static SystemManager editInfoStaff(SystemManager sm) {
        MenuItem choice;
        do {
            choice = Menu.Display(Menu.subMenuStaff1);
            switch (choice) {
                case ADD_STAFF: {
                    addStaff(sm);
                    break;
                }
                case UPDATE_STAFF: {
                    updateStaff(sm);
                    break;
                }
                case DELETE_STAFF: {
                    deleteStaff(sm);
                    break;
                }
                case SHOW_STAFF: {
                    listOfStaffs(sm);
                    break;
                }
                case BACK: {
                    break;
                }
            }
        } while (choice != MenuItem.BACK);
        return sm;
    }

    //
    public static SystemManager addStaff(SystemManager sm) {
        System.out.println("Enter staff information ");
        String staffID = CheckRule.idAuto(countEmp, "E");
        System.out.print("Name:");
        String name = sc.nextLine();
        System.out.println("Choose role of staff:");
        String role = Menu.roleOfStaff(Menu.menuRole);

        // add new staff
        Staff newE = new Staff(staffID, name, role);
        sm.getStaffs().put(staffID, newE);

        System.out.println("Staff " + staffID + " has been created successfully");

        return sm;
    }

    public static SystemManager updateStaff(SystemManager sm) {
        ArrayList<String> listId = new ArrayList<>();
        for (String id : sm.getStaffs().keySet()) {
            listId.add(id);
        }
        System.out.println("Enter StaffID you want to update");
        String staffID = CheckRule.inputID("E");
        if (listId.contains(staffID)) {
            Staff staffUpdate = sm.getStaffs().get(staffID);
            System.out.println("Staff " + staffID + " is ready to be updated");
            System.out.print("Name:");
            String name = sc.nextLine();
            System.out.println("Choose role of staff");
            String role = Menu.roleOfStaff(Menu.menuRole);

            staffUpdate.setName(name);
            staffUpdate.setRole(role);

            System.out.println("Staff " + staffID + " has been updated successfully");
        } else {
            System.err.println("There is no suitable staff");
        }

        return sm;
    }

    // doi lam crew....kiem tra xem co busy hay khong
    public static SystemManager deleteStaff(SystemManager sm) {
        ArrayList<String> listId = new ArrayList<>();
        for (String id : sm.getStaffs().keySet()) {
            listId.add(id);
        }
        System.out.println("Enter StaffID you want to delete");
        String staffID = CheckRule.inputID("E");
        if (listId.contains(staffID)) {
            Staff staffD = sm.getStaffs().get(staffID);
            MenuItem confirm = Menu.confirm(Menu.menuConfirm, "<!> Are you sure you want"
                    + " to DELETE THIS STAFF? ");
            switch (confirm) {
                case CONFIRM: {
                    System.out.println("Staff " + staffID + " has been delete successfully");
                    break;
                }
                case BACK: {
                    break;
                }
            }
        } else {
            System.err.println("There is no suitable staff");
        }

        return sm;
    }

    public static void listOfStaffs(SystemManager sm) {
        System.out.println("+--------------------------------------+");
        System.out.printf("|%5s|%-20s|%-11s|\n", "EmpID", "Name", "Role");
        System.out.println("+-----+--------------------+-----------+");
        for (Staff e : sm.getStaffs().values()) {
            e.printInfoStaff();
            System.out.println("+-----+--------------------+-----------+");
        }
    }

    // ... edit information of crew
//    public static SystemManager editCrew(SystemManager sm) {
//        MenuItem choice;
//        do {
//            choice = Menu.Display(Menu.subMenuStaff2);
//            switch (choice) {
//                case SHOW_CREW: {
//                    listOfCrews(sm);
//                    break;
//                }
//                case BACK: {
//                    break;
//                }
//
//            }
//        } while (choice != MenuItem.BACK);
//        return sm;
//    }
    public static Crew createCrew(String flID, String day, SystemManager sm) {
        String crID = CheckRule.idAuto(countCre, "T");
        ArrayList<Staff> listStaffs = new ArrayList<>();
        ArrayList<Staff> listPilots = new ArrayList<>();
        ArrayList<Staff> listTechs = new ArrayList<>();
        ArrayList<Staff> listStewars = new ArrayList<>();
        int countP = 0;
        int countT = 0;
        int countS = 0;
        for (Staff e : sm.getStaffs().values()) {
            if (isBusy(e, day, sm) == false) {
                switch (e.getRole()) {
                    case "Pilot":
                        listPilots.add(e);
                        countP++;
                        break;
                    case "Stewardess":
                        listStewars.add(e);
                        countS++;
                        break;
                    default:
                        listTechs.add(e);
                        countT++;
                        break;
                }
            }
            if (countP == 5 && countT == 5 && countS == 10) {
                break;
            }
        }
        System.out.println("Choose 2 members to be pilots: ");
        printTitleRole();
        for (Staff Pilot : listPilots) {
            Pilot.printInfoStaff();
        }
        for (int i = 0; i < 2; i++) {
            System.out.print("ID_Pilot: ");
            String id = CheckRule.inputID("E");
            listStaffs.add(sm.getStaffs().get(id));
        }
        System.out.println("Choose 2 members to be technicians: ");
        printTitleRole();
        for (Staff tech : listTechs) {
            tech.printInfoStaff();
        }
        for (int i = 0; i < 2; i++) {
            System.out.print("ID_Technician: ");
            String id = CheckRule.inputID("E");
            listStaffs.add(sm.getStaffs().get(id));
        }

        System.out.println("Choose 4 members to be stewardess: ");
        printTitleRole();
        for (Staff ste : listStewars) {
            ste.printInfoStaff();
        }
        for (int i = 0; i < 4; i++) {
            System.out.print("ID_Stewardess: ");
            String id = CheckRule.inputID("E");
            listStaffs.add(sm.getStaffs().get(id));
        }

        Crew cr = new Crew(crID, flID, listStaffs);
        sm.getCrews().put(crID, cr);

        return cr;
    }

    //... print title role
    public static void printTitleRole() {
        System.out.println("+--------------------------------------+");
        System.out.printf("|%5s|%-20s|%-11s|\n", "EmpID", "Name", "Role");
        System.out.println("+-----+--------------------+-----------+");
    }

    //...is Staff busy
    public static boolean isBusy(Staff e, String day, SystemManager sm) {
        // List flight on day
        ArrayList<Flight> listFlOnDay = new ArrayList<>();
        for (Flight fl : sm.getFlights().values()) {
            if (fl.getDate().equals(day)) {
                listFlOnDay.add(fl);
            }
        }
        // Check crew on day
        for (Flight fl : listFlOnDay) {
            String crID = fl.getCrewID();
            Crew cr = sm.getCrews().get(crID);
            for (Staff staff : cr.getListStaff()) {
                if (staff.getStaffID().equals(e.getStaffID())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static SystemManager updateCrew(SystemManager sm) {
        return sm;
    }

    public static SystemManager deleteCrew(SystemManager sm) {
        return sm;
    }

    public static void listOfCrews(SystemManager sm) {
        for (Crew e : sm.getCrews().values()) {
            System.out.println(e.toString());
        }
    }

    // BOOKING MANAGEMENT
    public static SystemManager bookingManagement(SystemManager sm) {
        MenuItem choice;
        do {
            choice = Menu.Display(Menu.menuBooking);
            switch (choice) {

                case ADD_BOOKING: {
                    createBooking(sm);
                    break;
                }
                case UPDATE_BOOKING: {
                    updateBooking(sm);
                    break;
                }

                case DELETE_BOOKING: {
                    cancelBooking(sm);
                    break;
                }

                case SHOW_BOOKING: {
                    listOfBookings(sm);
                    break;
                }
                case BACK: {
                    break;
                }
            }
        } while (choice != MenuItem.BACK);
        return sm;
    }

    public static SystemManager updateBooking(SystemManager sm) {
        ArrayList<String> listId = new ArrayList<>();
        for (String id : sm.getBookings().keySet()) {
            listId.add(id);
        }
        System.out.println("Enter BookID you want to update");
        String bkID = CheckRule.inputID("B");
        if (listId.contains(bkID)) {
            Booking bkUpdate = sm.getBookings().get(bkID);
            String flOfBk = bkUpdate.getfNumber();
            Flight flOfBooking = sm.getFlights().get(flOfBk);
            ArrayList<String> listSeat = new ArrayList<>();
            String seat = bkUpdate.getNumberOfSeat();
            if (bkUpdate.getTypeSeat().equals(MenuItem.ECONOMY.getLabel())) {
                listSeat = flOfBooking.listEcoSeats;
                String oldSeat = seat;
                CheckRule.mapSeats(bkUpdate.getTypeSeat(), flOfBooking);
                System.out.println("Enter the seat number for  " + bkUpdate.getCusID() + "  < ex: 2F > :");
                String choice = sc.nextLine();
                if (listSeat.contains(choice)) {
                    for (int j = 0; j < listSeat.size(); j++) {
                        if (listSeat.get(j).equals(choice)) {
                            seat = flOfBooking.listEcoSeats.remove(j);
                            flOfBooking.listEcoSeats.add(oldSeat);

                            bkUpdate.setNumberOfSeat(seat);

                            System.out.println("Booking " + bkID + " has been updated successfully");
                            return sm;
                        }
                    }
                } else {
                    System.err.println("This seat number has been registered or does not exist");
                }
            } else {
                listSeat = flOfBooking.listBusinessSeats;
                String oldSeat = seat;
                CheckRule.mapSeats(bkUpdate.getTypeSeat(), flOfBooking);
                System.out.println("Enter the seat number for  " + bkUpdate.getCusID() + "  < ex: 1A > :");
                String choice = sc.nextLine();
                if (listSeat.contains(choice)) {
                    for (int j = 0; j < listSeat.size(); j++) {
                        if (listSeat.get(j).equals(choice)) {
                            seat = flOfBooking.listBusinessSeats.remove(j);
                            flOfBooking.listBusinessSeats.add(oldSeat);

                            bkUpdate.setNumberOfSeat(seat);

                            System.out.println("Booking " + bkID + " has been updated successfully");
                            return sm;
                        }
                    }
                } else {
                    System.err.println("This seat number has been registered or does not exist");
                }

            }
        } else {
            System.err.println("There is no suitable booked");
        }
        return sm;
    }

    public static SystemManager cancelBooking(SystemManager sm) {
        ArrayList<String> listId = new ArrayList<>();
        for (String id : sm.getBookings().keySet()) {
            listId.add(id);
        }
        System.out.println("Enter BookID you want to CANCEL");
        String bkID = CheckRule.inputID("B");
        if (listId.contains(bkID)) {
            MenuItem confirm = Menu.confirm(Menu.menuConfirm, "<!> Are you sure you want "
                    + "to CANCEL this BOOKING?");
            switch (confirm) {
                case CONFIRM: {
                    Booking bkD = sm.getBookings().get(bkID);
                    String flOfBk = bkD.getfNumber();
                    Flight flOfBooking = sm.getFlights().get(flOfBk);
                    ArrayList<String> listSeat = new ArrayList<>();
                    String seat = bkD.getNumberOfSeat();
                    if (bkD.getTypeSeat().equals(MenuItem.ECONOMY)) {
                        listSeat = flOfBooking.listEcoSeats;
                        listSeat.add(seat);

                        sm.getBookings().remove(bkID);
                        System.out.println("Booking has been DELETE successfully");

                    } else {
                        listSeat = flOfBooking.listBusinessSeats;
                        listSeat.add(seat);

                        sm.getBookings().remove(bkID);
                        System.out.println("Booking has been DELETE successfully");

                    }
                    flOfBooking.setSeatsBooked(flOfBooking.getSeatsBooked() - 1);
                    break;
                }
                case BACK: {
                    break;
                }
            }
        } else {
            System.err.println("There is no suitable booked");
        }

        return sm;
    }

    public static void listOfBookings(SystemManager sm) {
        System.out.println(">> List information booked___");
        for (Booking bk : sm.getBookings().values()) {
            System.out.println(bk.toString());
        }
    }

    // CUSTOMER MANAGEMENT
    public static SystemManager customerManagement(SystemManager sm) {
        MenuItem choice;
        do {
            choice = Menu.Display(Menu.menuCustomer);
            switch (choice) {
                case UPDATE_CUSTOMER: {
                    updateCustomer(sm);
                    break;
                }
                case DELETE_CUSTOMER: {
                    deleteCustomer(sm);
                    break;
                }
                case SHOW_CUSTOMER: {
                    listOfCustomer(sm);
                    break;
                }
                case BACK: {
                    break;
                }
            }
        } while (choice != MenuItem.BACK);
        return sm;
    }

//    public static SystemManager addCustomer(SystemManager sm) {
//        return sm;
//    }
    public static SystemManager updateCustomer(SystemManager sm) {
        ArrayList<String> listId = new ArrayList<>();
        for (String id : sm.getCustomers().keySet()) {
            listId.add(id);
        }
        System.out.println("Enter CustomerID you want to update");
        String cusID = CheckRule.inputID("C");
        if (listId.contains(cusID)) {
            Customer cusUpdate = sm.getCustomers().get(cusID);
            System.out.println("Customer " + cusID + " is ready to be updated");
            System.out.print("Phone: ");
            String phone = CheckRule.getPhone();
            System.out.print("Email: ");
            String email = CheckRule.getEmail();

            cusUpdate.setPhone(phone);
            cusUpdate.setEmail(email);
            sm.getCustomers().put(cusID, cusUpdate);
            System.out.println("Customer " + cusID + " has been updated successfully");
        } else {
            System.err.println("There is no suitable customer");
        }
        return sm;
    }

    public static SystemManager deleteCustomer(SystemManager sm) {
        ArrayList<String> listId = new ArrayList<>();
        for (String id : sm.getCustomers().keySet()) {
            listId.add(id);
        }
        System.out.println("Enter CustomerID you want to Delete");
        String cusID = CheckRule.inputID("C");
        if (listId.contains(cusID)) {
            MenuItem confirm = Menu.confirm(Menu.menuConfirm, "<!> Are you sure you want "
                    + "to DELETE INFORMATION OF THIS CUSTOMER?");
            switch (confirm) {
                case CONFIRM: {
                    // Remove booking have cusID  
                    HashMap<String, Booking> listbk = sm.getBookings();

                    for (Booking bk : listbk.values()) {
                        String bkID = bk.getBookingID();
                        Flight fl = sm.getFlights().get(bk.getfNumber());
                        if (bk.getCusID().equals(cusID)) {
                            listbk.remove(bkID);

                            fl.setSeatsBooked(fl.getSeatsBooked() - 1);
                        }
                    }
                    sm.setBookings(listbk);

                    // Remove customer 
                    sm.getCustomers().remove(cusID);
                    System.out.println("Customer " + cusID + " has been updated successfully");
                    break;
                }
                case BACK: {
                    break;
                }
            }

        } else {
            System.err.println("There is no suitable customer");
        }
        return sm;
    }

    public static SystemManager listOfCustomer(SystemManager sm) {
        System.out.println("+----------------------------------------------------------------+");
        System.out.printf("|%5s|%-20s|%11s|%-20s|%4s|\n", "CusID", "Name", "PhoneNumber", "Email", "SL");
        System.out.println("+-----+--------------------+-----------+--------------------+----+");
        for (Customer cus : sm.getCustomers().values()) {
            cus.printInfoCustomer();
            System.out.println("+-----+--------------------+-----------+--------------------+----+");
        }
        return sm;
    }

    // BACK AND SAVE DATA
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Enum.MenuItem;
import Util.CheckNumber;

/**
 *
 * @author lvhho
 */
public class Menu {

    public static final MenuItem[] mainMenu = {
        MenuItem.MAIN_MENU,
        MenuItem.BOOKING,
        MenuItem.CHECK_IN,
        MenuItem.LOG_IN,
        MenuItem.EXIT,};
    // -----------------------------------------------
    public static final MenuItem[] subMenuForAdmin = {
        MenuItem.SYSTEM_MANAGEMENT,
        MenuItem.FLIGHT_MANAGEMENT,
        MenuItem.STAFF_MANAGEMENT,
        MenuItem.CUSTOMER_MANAGEMENT,
        MenuItem.BOOKING_MANAGEMENT,
        MenuItem.SAVE,
        MenuItem.BACK,};

    public static final MenuItem[] menuFlight = {
        MenuItem.FLIGHT_MANAGEMENT,
        MenuItem.CREATE_NEW_FLIGHT,
        MenuItem.UPDATE_FLIGHT,
        MenuItem.DELETE_FLIGHT,
        MenuItem.SHOW_FLIGHT,
        MenuItem.BACK,};

    public static final MenuItem[] menuStaff = {
        MenuItem.STAFF_MANAGEMENT,
        MenuItem.EDIT_INFO_STAFF,
        MenuItem.SHOW_CREW,
        MenuItem.BACK,};

    public static final MenuItem[] subMenuStaff1 = {
        MenuItem.EDIT_INFO_STAFF,
        MenuItem.ADD_STAFF,
        MenuItem.UPDATE_STAFF,
        MenuItem.DELETE_STAFF,
        MenuItem.SHOW_STAFF,
        MenuItem.BACK,};
    // tam thoi bo qua crew
    public static final MenuItem[] subMenuStaff2 = {
        MenuItem.EDIT_CREW,
        MenuItem.BACK,};

    public static final MenuItem[] menuBooking = {
        MenuItem.BOOKING_MANAGEMENT,
        MenuItem.ADD_BOOKING,
        MenuItem.UPDATE_BOOKING,
        MenuItem.DELETE_BOOKING,
        MenuItem.SHOW_BOOKING,
        MenuItem.BACK,};

    public static final MenuItem[] menuCustomer = {
        MenuItem.CUSTOMER_MANAGEMENT,
        MenuItem.UPDATE_CUSTOMER,
        MenuItem.DELETE_CUSTOMER,
        MenuItem.SHOW_CUSTOMER,
        MenuItem.BACK,};

    public static final MenuItem[] menuConfirm = {
        MenuItem.CONFIRM,
        MenuItem.BACK,};

    public static final MenuItem[] menuSeatClass = {
        MenuItem.ECONOMY,
        MenuItem.BUSINESS,};

    public static final MenuItem[] menuCheckIn = {
        MenuItem.BY_BOOKED_ID,
        MenuItem.BY_PERSON_ID,};

    public static final MenuItem[] menuRole = {
        MenuItem.PILOT,
        MenuItem.STEWARDESS,
        MenuItem.TECHNICIAN};

    // 
    public static MenuItem Display(MenuItem[] Menu) {
        System.out.println(">>>>>" + Menu[0].getLabel());
        for (int i = 1; i < Menu.length; i++) {
            System.out.println((i) + ". " + Menu[i].getLabel());
        }
        System.out.println("===============================");

        int choice = CheckNumber.getAnInteger("Enter your choice:", "ERROR", 1, Menu.length - 1);

        return Menu[choice];
    }

    public static MenuItem confirm(MenuItem[] Menu, String str) {
        System.out.println(str);
        for (int i = 0; i < Menu.length; i++) {
            System.out.println((i + 1) + ". " + Menu[i].getLabel());
        }
        System.out.println("===============================");
        int choice = CheckNumber.getAnInteger("Enter your choice:", "ERROR", 1, Menu.length);
        return Menu[choice - 1];
    }

    public static String seatClass(MenuItem[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ". " + choices[i].getLabel());
        }
        System.out.println("===============================");

        int choice = CheckNumber.getAnInteger("choice: ", "ERROR", 1, choices.length);
        return choices[choice - 1].getLabel();
    }

    public static String roleOfStaff(MenuItem[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ". " + choices[i].getLabel());
        }
        System.out.println("===============================");

        int choice = CheckNumber.getAnInteger("Role: ", "ERROR", 1, choices.length);
        return choices[choice - 1].getLabel();
    }
}

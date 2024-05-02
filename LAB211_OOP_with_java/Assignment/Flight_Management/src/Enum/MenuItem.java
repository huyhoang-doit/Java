/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

/**
 *
 * @author lvhho
 */
public enum MenuItem {
    EXIT("Exit"),
    BACK("Back"),
    CONFIRM("Confirm"),
    CONTINUE("Continue"),
    // BOOKING
    FIND_FLIGHT("Find flight"),
    // CHECK IN ONLINE
    BY_BOOKED_ID("Check in using booking ID "),
    BY_PERSON_ID("Check in using person ID "),
    
    
    // MAIN MENU
    MAIN_MENU("TRAVELOKA"),
    CHECK_IN("CHECK IN ONLINE"),
    BOOKING("BOOK TICKETS"),
    LOG_IN("LOG IN"),
    
    // SUBMENU FOR ADMINISTRATOR
    SYSTEM_MANAGEMENT("SYSTEM MANAGEMENT"),
    FLIGHT_MANAGEMENT("Flight Management"),
    STAFF_MANAGEMENT("Staff management"),
    CUSTOMER_MANAGEMENT("Customer Management"),
    BOOKING_MANAGEMENT("Booking Management"),
    SAVE("Save data"), 
    
    // MANAGER CUSTOMERS
    ADD_CUSTOMER("Add customer"),
    UPDATE_CUSTOMER("Update information of customer"),
    DELETE_CUSTOMER("Delete customer"),
    SHOW_CUSTOMER("Show list of customers"),
    
    
    // MANAGER EMPOYEES
    EDIT_INFO_STAFF("Edit employee information"),
    ADD_STAFF("Add new employee"),
    UPDATE_STAFF("Update information of employee"),
    DELETE_STAFF("Delete employee"),
    SHOW_STAFF("Show list of employees"),
    
    // MANAGER FLIGHTS
    CREATE_NEW_FLIGHT("Create new flight"),
    UPDATE_FLIGHT("Update information of flight"),
    DELETE_FLIGHT("Cancel the  flight"),
    SHOW_FLIGHT("Show list of flights"),
    // MANAGEMENT BOOKING
    ADD_BOOKING("Create new booking"),
    UPDATE_BOOKING("Update information of booking"),
    DELETE_BOOKING("Cancel booking"),
    SHOW_BOOKING("Show list of bookings"),
    
    // MANAGEMENT CREW
    EDIT_CREW("Edit crew information for flights"),
    CREATE_NEW_CREW("Create new crew "),
    UPDATE_CREW("Update information of crew"),
    DELETE_CREW("Delete crew"),
    SHOW_CREW("Show list of crews"),
    
    
    // SEAT CLASS
    ECONOMY("Economy Class"),
    BUSINESS("Business Class"),
    
    // ROLE OF STAFF
    PILOT("Pilot"),
    STEWARDESS("Stewardess"),
    TECHNICIAN("Technician");
    
    

    private final String label;

    public String getLabel() {
        return label;
    }


    private MenuItem(String label){
        this.label = label;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashMap;

/**
 *
 * @author lvhho
 */
public class SystemManager {
    private HashMap<String,Flight> Flights;
    private HashMap<String,Customer> Customers;
    private HashMap<String,Booking> Bookings;
    private HashMap<String,Staff> Staffs;
    private HashMap<String,Crew> Crews;
    
    // Constructor

    public SystemManager() {
        this.Flights = new HashMap<>();
        this.Customers = new HashMap<>();
        this.Bookings = new HashMap<>();
        this.Staffs = new HashMap<>();
        this.Crews = new HashMap<>();
    }

    public SystemManager(HashMap<String, Flight> Flights, HashMap<String, Customer> Customers, HashMap<String, Booking> Bookings, HashMap<String, Staff> Staffs, HashMap<String, Crew> Crews) {
        this.Flights = Flights;
        this.Customers = Customers;
        this.Bookings = Bookings;
        this.Staffs = Staffs;
        this.Crews = Crews;
    }
    
    // Getter && Setter

    public HashMap<String, Flight> getFlights() {
        return Flights;
    }

    public HashMap<String, Customer> getCustomers() {
        return Customers;
    }

    public HashMap<String, Booking> getBookings() {
        return Bookings;
    }

    public HashMap<String, Staff> getStaffs() {
        return Staffs;
    }

    public HashMap<String, Crew> getCrews() {
        return Crews;
    }
    //-----

    public void setFlights(HashMap<String, Flight> Flights) {
        this.Flights = Flights;
    }

    public void setCustomers(HashMap<String, Customer> Customers) {
        this.Customers = Customers;
    }

    public void setBookings(HashMap<String, Booking> Bookings) {
        this.Bookings = Bookings;
    }

    public void setStaffs(HashMap<String, Staff> Staffs) {
        this.Staffs = Staffs;
    }

    public void setCrews(HashMap<String, Crew> Crews) {
        this.Crews = Crews;
    }
    
    
    
}

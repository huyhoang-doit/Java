/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.CheckRule;
import java.util.ArrayList;

/**
 *
 * @author lvhho
 */
public class Flight {
    private Double tax = 0.2;
    private String fNumber;
    private String crewID;
    private String departure;
    private String destination;
    private String date;
    private String time;
    private String duration;
    private int seatsBooked = 0;
    private int emptySeats;
    private final int businessSeats = 18;
    private final int numberOfSeats = 90;
    private Double price;
    public ArrayList<String> listBusinessSeats = CheckRule.listSeatsBusiness(); // 18 seats
    public ArrayList<String> listEcoSeats = CheckRule.listSeatEco(); // 62 seats
    

    // Constructor
    public Flight() {
    }

    public Flight(String fNumber, String crewID, String departure, String destination, String date, String time, String duration, Double price) {
        this.fNumber = fNumber;
        this.crewID = crewID;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        
    }

    

    

    // Getter & Setter
    public String getfNumber() {
        return fNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDuration() {
        return duration;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public int getEmptySeats() {
        return numberOfSeats - seatsBooked;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public Double getPrice() {
        return price;
    }

    public String getCrewID() {
        return crewID;
    }

    public int getBusinessSeats() {
        return businessSeats;
    }

    public Double getTax() {
        return tax;
    }
    
    
    
    //---

    public void setfNumber(String fNumber) {
        this.fNumber = fNumber;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setSeatsBooked(int seatsBooked) {
        if (seatsBooked > numberOfSeats) {
            System.err.println("Invalid value!");
        } else {
            this.seatsBooked = seatsBooked;
        }

    }

    public void setEmptySeats(int emptySeats) {
        if (emptySeats > numberOfSeats) {
            System.err.println("Invalid value!");
        } else {
            this.emptySeats = emptySeats;
        }
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCrewID(String crewID) {
        this.crewID = crewID;
    }
    
    
    
    
    //toString and other methods

    @Override
    public String toString() {
        return fNumber + "," + crewID + ","+ departure + "," + destination + ","+ date + "," + time + "," 
        + duration + "," + seatsBooked + "," + emptySeats + "," + numberOfSeats + "," + price;
                //F0001,T001,Ho Chi Minh,Ha Noi,03-10-2023,15:30,120,60,30,90,1.200.000

    }
    
    public void printInfoFlight(){
        System.out.printf("|%5s|%-15s|%-15s|%10s|%5s|%8s|%5d|%-10.1f|\n",
                fNumber,departure,destination,date,time,duration,numberOfSeats - seatsBooked,price);
    }
    
    

}

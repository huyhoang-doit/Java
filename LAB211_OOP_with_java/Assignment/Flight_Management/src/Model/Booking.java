/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author lvhho
 */
public class Booking {
    private String bookingID;
    private String fNumber;
    private String cusID;
    private String typeSeat;
    private String numberOfSeat;
    private boolean status;
    
    // Constructor

    public Booking() {
    }

    public Booking(String bookingID, String fNumber, String cusID, String typeSeat, String numberOfSeat) {
        this.bookingID = bookingID;
        this.fNumber = fNumber;
        this.cusID = cusID;
        this.typeSeat = typeSeat;
        this.numberOfSeat = numberOfSeat;
        this.status = false;
    }

    
    // Getter & Setter

    public String getfNumber() {
        return fNumber;
    }

    public String getCusID() {
        return cusID;
    }

    public String getNumberOfSeat() {
        return numberOfSeat;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getTypeSeat() {
        return typeSeat;
    }

    public boolean isStatus() {
        return status;
    }
    
    
    
    
    //----

    public void setfNumber(String fNumber) {
        this.fNumber = fNumber;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public void setNumberOfSeat(String numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public void setTypeSeat(String typeSeat) {
        this.typeSeat = typeSeat;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
    //toString and other methods

    @Override
    public String toString() {
        return  bookingID + ","+ fNumber + "," + cusID + "," + typeSeat + "," + numberOfSeat+"," + status;
    }
    
}

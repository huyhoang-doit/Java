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
public class Customer {
    private String cusID;
    private String name;
    private String phone;
    private String email;
    private String personID;
    private int quantitySeats;
    
    // Constructor

    public Customer() {
    }

    public Customer(String cusID, String name, String phone, String email, String personID, int quantitySeats) {
        this.cusID = cusID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.personID = personID;
        this.quantitySeats = quantitySeats;
    }

    
    
    //Getter & Setter

    public String getCusID() {
        return cusID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getQuantitySeats() {
        return quantitySeats;
    }

    public String getPersonID() {
        return personID;
    }
    
    
    
    //----

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQuantitySeats(int quantitySeats) {
        this.quantitySeats = quantitySeats;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
    
    
    //toString and other Methods

    @Override
    public String toString() {
        return  cusID + "," + name + ","+ personID + "," + phone + "," + email + "," + quantitySeats;
        // C0001,Le Van Huy Hoang,011100110012,09123456789,huyhoang@gmail.com,2
    }
    
    public void printInfoCustomer() {
        System.out.printf("|%5s|%-20s|%11s|%-20s|%4d|\n", cusID, name, phone, email, quantitySeats);
    }
    
}

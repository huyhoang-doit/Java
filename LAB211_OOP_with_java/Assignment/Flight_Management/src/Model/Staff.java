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
public class Staff {
    private String staffID;
    private String name;
    private String role;
    
    // Constructor

    public Staff(String staffID, String name, String role) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
    }
    // Getter & Setter

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    // toString

    @Override
    public String toString() {
        return  staffID + "," + name + "," + role;
        // E0001,JohnSon,Pilot
    }
    
    public void printInfoStaff() {
        System.out.printf("|%5s|%-20s|%-11s|\n", staffID,name,role);
    }
}

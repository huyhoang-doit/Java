/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author lvhho
 */
public class Crew {
    private String crewID;
    private String fNumber;
    private ArrayList<Staff> listStaff;
    
    // Constructor

    public Crew() {
    }

    public Crew(String crewID, String fNumber, ArrayList<Staff> listStaff) {
        this.crewID = crewID;
        this.fNumber = fNumber;
        this.listStaff = listStaff;
    }
    

    
    
    // Getter & Setter

    public String getCrewID() {
        return crewID;
    }

    public ArrayList<Staff> getListStaff() {
        return listStaff;
    }

    public String getfNumber() {
        return fNumber;
    }
    
    
    //----

    public void setCrewID(String crewID) {
        this.crewID = crewID;
    }

    public void setListStaff(ArrayList<Staff> listStaff) {
        this.listStaff = listStaff;
    }

    public void setfNumber(String fNumber) {
        this.fNumber = fNumber;
    }
    
    
    
    //toString and other methods

    @Override
    public String toString() {
        String result = crewID + ","+ fNumber;
        for (Staff staff : listStaff) {
            result += "," + staff.getStaffID();
        }
        return result;
    }
    
    
    

    
    
    
}

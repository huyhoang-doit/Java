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
public enum StaffItem {
    PILOT(1, "Pilot"),
    STEWARDESS(2, "Stewardess"),
    TECHNICIAN(3, "Technician");

    private final int choice;
    private final String role;

    private StaffItem(int choice, String role) {
        this.choice = choice;
        this.role = role;
    }

    public int getChoice() {
        return choice;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return  role;
    }
    

    public static StaffItem getByChoice(int choice) {
        for (StaffItem staff : values()) {
            if (staff.choice == choice) {
                return staff;
            }
        }
        throw new IllegalArgumentException("Invalid choice: " + choice);
    }

}

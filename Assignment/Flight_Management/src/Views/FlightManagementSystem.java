/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.Controller;
import Enum.MenuItem;
import Model.SystemManager;
import java.util.Scanner;

/**
 *
 * @author lvhho
 */
public class FlightManagementSystem {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        SystemManager sm = new SystemManager();
        sm = Controller.loadData(sm);
       
        
        while(true) {
            MenuItem mainChoice = Menu.Display(Menu.mainMenu);
            switch(mainChoice) {
                case BOOKING: {
                    Controller.createBooking(sm);
                    break;
                }
                case CHECK_IN: {
                    Controller.checkInOnline(sm);
                    break;
                }
                case LOG_IN: {
                    Controller.adminField(sm);
                    break;
                }
                case EXIT: {
                    System.out.println("Thank you for using our service >.<");
                    System.out.println(">> HAVE A NICE DAY ");
                    System.exit(0);
                    break;
                }
            }
        }
    }
    
}

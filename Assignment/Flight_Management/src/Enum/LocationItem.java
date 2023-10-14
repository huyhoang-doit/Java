/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

import java.util.ArrayList;

/**
 *
 * @author lvhho
 */
public enum LocationItem {
    HO_CHI_MINH(1, "Ho Chi Minh"),
    HANOI(2, "Hanoi"),
    DA_NANG(3, "Da Nang"),
    NHA_TRANG(4, "Nha Trang"),
    HUE(5, "Hue"),  
    VINH(6, "Vinh"),  
    CAN_THO(7, "Can Tho"),  
    PHU_QUOC(8, "Phu Quoc"),  
    SINGAPORE(9, "Singapore"),
    BANGKOK(10, "Bangkok"),
    TOKYO(11, "Tokyo"),
    OSAKA(12, "Osaka");

    private final int order;
    private final String name;

    private LocationItem(int order, String name) {
        this.order = order;
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }
    
    public static int size() {
        int size = 0;
        for (LocationItem o : values()) {
            size++;
        }
        return size;
    }

    @Override
    public String toString() {
        return name;
    }

    public static String getByOrder(int order) {
        for (LocationItem locationItem : values()) {
            if (locationItem.order == order) {
                return locationItem.getName();
            }
        }
        throw new IllegalArgumentException("Invalid order: " + order);
    }
    public static void printListLocations (){
        for (LocationItem location : values()) {
            System.out.println(location.getOrder() + " - " + location.getName());
        }
    }
    
    public static void locationWithOut(int out) {
        for (LocationItem location : values()) {
            if(location.getOrder() != out){
            System.out.println(location.getOrder() + " - " + location.getName());
            }
        }
        
    }
}



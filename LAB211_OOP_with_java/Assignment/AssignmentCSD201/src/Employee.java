/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lvhho
 */
public class Employee {
    private String id;
    private String name;
    private int age;

    // Constructor

    public Employee() {
    }
    
    
    public Employee(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    
    // Getter & Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}

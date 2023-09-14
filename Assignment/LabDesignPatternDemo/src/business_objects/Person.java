/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_objects;

/**
 *
 * @author lvhho
 */
public abstract class Person {
    protected String name;
    protected int id;
    public Person() {
    }
    // Getter
    public String getName () {
        return name;
    }

    public int getId() {
        return id;
    }
    
    // Setter

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}

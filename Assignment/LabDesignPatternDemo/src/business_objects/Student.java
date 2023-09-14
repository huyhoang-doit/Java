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
public class Student extends Person{
    private String email;
    public Student (int id, String name, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }
    // Getter and Setter

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" + "email=" + email + '}';
    }
    
}

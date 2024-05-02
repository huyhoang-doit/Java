/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lvhho
 */
public class EmployeeManagement {

    private class Node {

        Employee nv;
        Node bf, at;

        public Node() {
        }

        public Node(Employee nv) {
            this.nv = nv;
            this.bf = null;
            this.at = null;
        }
    }

    Node head, tail;

    public EmployeeManagement() {
        this.head = null;
        this.tail = null;
    }

    public boolean insertE(Employee e) {
        Node newNode = new Node(e);
        if (isEmpty()) {
            head = tail = newNode;
            return true;
        }
        newNode.at = tail.at;
        newNode.bf = tail.bf;
        tail.at = newNode;
        return true;
    }

    public boolean isEmpty() {
        return (head == null);
    }
    
    public void show() {
        if(isEmpty()) {
            System.out.println("List employees is empty!");
            return;
        }
        Node cur = head;
        int count = 1;
        while(cur.at != null) {
            System.out.println(count + " :" + cur.nv.getId() + "-" + cur.nv.getName());
            cur = cur.at;
            count++;
        }
    }
    public static void main(String[] args) {
        Employee e1 = new Employee("E1", "Le Van Huy Hoang", 20);
        Employee e2 = new Employee("E2", "Le Van A", 21);
        Employee e3 = new Employee("E3", "Le Van B", 22);
        Employee e4 = new Employee("E4", "Le Van C", 23);
        Employee e5 = new Employee("E5", "Le Van D", 24);
        Employee e6 = new Employee("E6", "Le Van E", 25);
        
        // insert
         EmployeeManagement em = new EmployeeManagement();
         
         em.insertE(e1);
         em.insertE(e2);
         em.insertE(e3);
         em.insertE(e4);
         em.insertE(e5);
         em.insertE(e6);
         
         // show list
         em.show();
         
            
    }
}

package vn.hdi.spring.javapojo.entity;

public class Student {
    private int id;
    private String hoVaTen;
    private int tuoi;
    private String nganhHoc;
    private double gpa;

    // Contructor
    public Student() {
    }

    public Student(int id, String hoVaTen, int tuoi, String nganhHoc, double gpa) {
        this.id = id;
        this.hoVaTen = hoVaTen;
        this.tuoi = tuoi;
        this.nganhHoc = nganhHoc;
        this.gpa = gpa;
    }

    // Getter & Setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getNganhHoc() {
        return nganhHoc;
    }

    public void setNganhHoc(String nganhHoc) {
        this.nganhHoc = nganhHoc;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}

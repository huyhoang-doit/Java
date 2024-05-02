package vn.hdi.spring.javapojo.rest;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hdi.spring.javapojo.entity.ErrorResponse;
import vn.hdi.spring.javapojo.entity.Student;
import vn.hdi.spring.javapojo.exeption.StudentExeption;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sinhvien")
public class StudentController {
    public List<Student> danhSach;

    @PostConstruct
    public void createDanhSach() {
        danhSach = new ArrayList<Student>();
        danhSach.add(new Student(1, "Le Hoang", 21, "CNTT", 9));
        danhSach.add(new Student(2, "Le Hai", 17, "CNTT", 10));
        danhSach.add(new Student(3, "Nguyen Hoan", 21, "CNTT", 10));
        danhSach.add(new Student(4, "Ngo Ngan", 20, "CNTT", 9));
        danhSach.add(new Student(5, "Quynh Anh", 21, "Su Pham", 9));
    }

    @GetMapping("/")
    public List<Student> listStudent(){
        return danhSach;
    }

    @GetMapping("/{id}")
    public Student getSinhVienById(@PathVariable int id) {
        Student result = null;
        for(Student sv : danhSach) {
            if(sv.getId() == id) {
                result = sv;
            }
        }
        if(result == null) {
            throw new StudentExeption("Khong tim thay sinh vien voi id la: " + id);
        }
        return result;
    }

    @GetMapping("/index/{index}")
    public Student getSinhVienByIndex(@PathVariable int index) {
        Student result = null;
        for(int i = 0; i < danhSach.size(); i++) {
            if(i+1 == index) {
                result = danhSach.get(i);
            }
        }
        if(result == null) {
            throw new StudentExeption("Khong tim thay sinh vien voi index la: " + index);
        }
        return result;
    }



}

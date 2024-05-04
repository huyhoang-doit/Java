package vn.hdi.spring.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.hdi.spring.springmvc.entity.Student;
import vn.hdi.spring.springmvc.service.StudentService;

import java.util.List;


@Controller
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public String listAll(Model model) {
        List<Student> listStudents = studentService.getAllStudents();
        model.addAttribute("students", listStudents);
        return "student/students";
    }

}

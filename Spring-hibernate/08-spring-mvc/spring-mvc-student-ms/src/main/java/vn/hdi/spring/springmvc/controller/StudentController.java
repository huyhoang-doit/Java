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

    @GetMapping("/create")
    public String createForm(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/add-student";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("student") Student student){
        studentService.updateStudent(student);
        return "redirect:/students/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Integer id, Model model){
         Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student/add-student";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id, Model model){
         studentService.deleteStudent(id);
        return "redirect:/students/list";
    }
}

package com.example.student.controller;

import java.util.Map;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.student.service.FacultyService;
import com.example.student.service.StudentService;
import com.example.student.entity.StudentEntity;
import com.example.student.entity.FacultyEntity; // นำเข้า FacultyEntity

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;  // แก้ไขชื่อให้ตรงกัน

    @Autowired
    private FacultyService facultyService;

    @GetMapping({"","/"})
    public String getAll(ModelMap model) {
        System.out.println("----- StudentController getAll() -----");

        // List<StudentEntity> students = studentService.getStudentAll();  // แก้ไข method ให้ถูกต้องตาม service
        // System.out.println("---- getAll() Result ----");
        // System.out.println("Size: " + students.size());

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        return "student/index";
    }

    @GetMapping("/{student-id}")
    public String getById(
        ModelMap model,
        @PathVariable(name = "student-id") Integer studentId
    ) {
        System.out.println("----- StudentController getById -----");
        System.out.println("studentId: " + studentId);

        // StudentEntity entity = studentService.getStudentById(studentId);  // ใช้ StudentEntity แทน FacultyEntity
        // System.out.println("----- StudentController getById Result -----");
        // System.out.println("Student First Name: " + entity.getStudentFirstName());
        // System.out.println("Student Last Name: " + entity.getStudentLastName());

        StudentEntity entity = studentService.getStudentById(studentId);
        model.addAttribute("student", entity);

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        return "student/index";
    }

    @GetMapping("/delete/{student-id}")
    public String getDeleteById(
        ModelMap model,
        @PathVariable(name = "student-id") Integer studentId
    ) {
        System.out.println("----- StudentController getDeleteById() -----");
        System.out.println("studentId: " + studentId);

        System.out.println("---- StudentController getDeleteById() Result ----");
        studentService.deleteStudentById(studentId);  // ใช้ service ที่ถูกต้องสำหรับการลบ student

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);
        return "student/index";
    } 

    @PostMapping("/")
    public String postInsertAndUpdate(
        ModelMap model,
        @RequestParam() Map<String, String> param
    ) {
        System.out.println("----- StudentController postInsertAndUpdate() -----");
        // System.out.println("student-code : " + param.get("student-code"));
        // System.out.println("student-fname : " + param.get("student-fname"));
        // System.out.println("student-lname : " + param.get("student-lname"));
        // System.out.println("Faculty - ID : " + param.get("faculty-id"));

        System.out.println("----- StudentController postInsertAndUpdate() Result -----");
        Integer facultyId = Integer.parseInt(param.get("faculty-id"));
        FacultyEntity facultyEntity = facultyService.getFacultyById(facultyId);
        // System.out.println(facultyEntity.getFacultyId());

        StudentEntity entity = new StudentEntity();
        if (null != param.get("student-id")) {
            entity.setStudentId(Integer.parseInt(param.get("student-id")));
        }
        entity.setStudentCode(param.get("student-code"));
        entity.setStudentFirstName(param.get("student-fname"));
        entity.setStudentLastName(param.get("student-lname"));
        entity.setFaculty(facultyEntity);

        StudentEntity result = studentService.saveStudent(entity);
        // System.out.println("Student ID: " + result.getStudentId());
        // System.out.println("Student First Name: " + result.getStudentFirstName());
        // System.out.println("Student Last Name: " + result.getStudentLastName());

        List<FacultyEntity> faculties = facultyService.getFacultyAll();
        model.addAttribute("faculties", faculties);

        List<StudentEntity> students = studentService.getStudentAll();
        model.addAttribute("students", students);

        return "student/index";
    }
}
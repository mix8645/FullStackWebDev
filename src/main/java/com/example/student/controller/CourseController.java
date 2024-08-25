package com.example.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.student.entity.CourseEntity;
import com.example.student.entity.FacultyEntity;
import com.example.student.service.CourseService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping({"","/"})
    public String getAll(ModelMap model) {
        System.out.println("----- CourseController getAll() -----");

        // List<CourseEntity> courses = courseService.getCourseAll();
        // System.out.println("----- CourseController getAll() Result-----");
        // System.out.println("Size: " + courses.size());

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        return "course/index";
    }

	@GetMapping("/{course-id}")
	public String getById(
        ModelMap model,
		@PathVariable(name = "course-id") Integer courseId
	) {
		// System.out.println("----- CourseController getTById -----");
		// System.out.println("courseId: " + courseId);

        CourseEntity entity = courseService.getCourseById(courseId);
        // System.out.println("----- CourseController getTById Result-----");
		// System.out.println("Course Name: " + entity.getCourseName());

        model.addAttribute("course", entity);
        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        return "course/index";
	}

	@GetMapping("/delete/{course-id}")
    public String getDeleteById(
        ModelMap model,
        @PathVariable(name = "course-id") Integer courseId
    ) {
        // System.out.println("----- CourseController getDeleteById() -----");
        // System.out.println("courseId: " + courseId);

        // System.out.println("----- CourseController getDeleteById() Result-----");
        
		courseService.deleteCourseById(courseId);

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);
        return "course/index";
    } 

    @PostMapping("/")
    public String postInsertAndUpdate(
        ModelMap model,
        @RequestParam() Map<String, String> param
    ) {
        // System.out.println("----- CourseController postInsertAndUpdate() -----");
        // System.out.println("course-id: " + param.get("course-id"));
        // System.out.println("course-name: " + param.get("course-name"));
        // System.out.println("course-desc: " + param.get("course-desc"));
        
        // System.out.println("----- CourseController postInsertAndUpdate() Result-----");
        CourseEntity entity = new CourseEntity();
        if(null != param.get("course-id")) {
            entity.setCourseId(Integer.parseInt(param.get("course-id")));
        }
        entity.setCourseName(param.get("course-name"));
        entity.setCourseDescription(param.get("course-desc"));
        CourseEntity result = courseService.saveCourse(entity);
        // System.out.println("Course ID: " + result.getCourseId());
        // System.out.println("Course Name: " + result.getCourseName());

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);

        return "course/index";
    }
    
}
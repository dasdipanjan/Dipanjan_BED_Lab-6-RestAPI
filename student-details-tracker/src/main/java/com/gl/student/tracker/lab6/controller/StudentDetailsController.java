package com.gl.student.tracker.lab6.controller;

import com.gl.student.tracker.lab6.dto.StudentInformation;
import com.gl.student.tracker.lab6.services.StudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

/**
 * This class is the controller class of student details tracker application. This class is
 * responsible to accept all request from UI base on the mapping path.
 *
 * @author DIPANJAN DAS(212431882)
 * @version 1.0
 * @since 02-May-2023
 */
@Controller
@RequestMapping("/student")
public class StudentDetailsController {
    private final StudentDetailsService studentDetailsService;

    @Autowired
    public StudentDetailsController(StudentDetailsService service) {
        this.studentDetailsService = service;
    }

    /**
     * This method is responsible to accept request student add request and map to request to
     * corresponding UI and load the same in browser.
     *
     * @param model Object of {@link Model} class.
     * @return the student create page name.
     */
    @GetMapping("/create")
    public String createStudent(Model model) {
        model.addAttribute("studentInfo", new StudentInformation());
        return "create-student";
    }

    /**
     * This method is responsible to accept Student information save request along with student information
     * like firstName, lastName, course, country.
     *
     * @param model       Object of {@link Model} class.
     * @param information Object of {@link StudentInformation}
     * @return page name of student list along with proper path.
     */
    @PostMapping("/savestudent")
    public String saveStudentInformation(Model model, @ModelAttribute("studentInfo") StudentInformation information) {
        studentDetailsService.saveStudentDetails(information);
        model.addAttribute("studentList", studentDetailsService.retrieveAllStudents());
        return "redirect:/student/list";
    }

    /**
     * This method is responsible to show all student information as a list in a table.
     *
     * @param model Object of {@link Model}
     * @return Student list page name.
     */
    @RequestMapping("/list")
    public String displayAllStudents(Model model) {
        model.addAttribute("studentList", studentDetailsService.retrieveAllStudents());
        return "student-list";
    }

    /**
     * This method is responsible to accept student information delete request based on the student ID.
     *
     * @param model Object of Model class.
     * @param id    Student Id.
     * @return Web page with list of students
     */
    @GetMapping("/delete/{studentId}")
    public String deleteStudentById(Model model, @PathVariable("studentId") int id) {
        studentDetailsService.deleteStudent(id);
        model.addAttribute("studentList", studentDetailsService.retrieveAllStudents());
        return "redirect:/student/list";
    }

    /**
     * This method is responsible accept student information update request from the UI.
     *
     * @param model     Object of {@link Model} class.
     * @param studentId Integer value with Student Id.
     * @return Web page name which displays students in table format.
     */
    @GetMapping("/update/{studentId}")
    public String editStudentDetails(Model model, @PathVariable("studentId") int studentId) {
        Optional<StudentInformation> optional = studentDetailsService.retrieveStudentById(studentId);
        if (optional.isPresent()) {
            model.addAttribute("studentInformation", optional.get());
            return "update-student";
        } else {
            return "redirect:/student/list";
        }
    }

    /**
     * This method is responsible to accept student update request along with the latest student information
     * to update in the database.
     *
     * @param model       Object of Model class.
     * @param studentId   Integer value with Student Id.
     * @param information Object of StudentInformation
     * @return Web page name which displays students in table format.
     */
    @PostMapping("/apply/update/{studentId}")
    public String applyStudentUpdate(Model model,
                                     @PathVariable("studentId") int studentId,
                                     @ModelAttribute("studentInfo") StudentInformation information) {
        information.setStudentId(studentId);
        studentDetailsService.saveStudentDetails(information);
        model.addAttribute("studentList", studentDetailsService.retrieveAllStudents());
        return "redirect:/student/list";
    }

    /**
     * This method is responsible to accept error page request when any kind of error occurs in the application.
     *
     * @param user Object of {@link Principal} class.
     * @return Object of {@link ModelAndView}
     */
    @RequestMapping(value = "/error")
    public ModelAndView accesssDenied(Principal user) {
        ModelAndView model = new ModelAndView();
        if (user != null) {
            model.addObject("errmessage", "Hi " + user.getName() + ", you do not have permission to access this page!");
        } else {
            model.addObject("errmessage", "You do not have permission to access this page!");
        }
        model.setViewName("error-student");
        return model;
    }


}

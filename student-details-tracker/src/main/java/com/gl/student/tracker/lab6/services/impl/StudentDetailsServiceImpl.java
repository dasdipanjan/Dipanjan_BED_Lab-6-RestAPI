package com.gl.student.tracker.lab6.services.impl;

import com.gl.student.tracker.lab6.dto.StudentInformation;
import com.gl.student.tracker.lab6.model.StudentDetails;
import com.gl.student.tracker.lab6.repository.StudentDetailsRepository;
import com.gl.student.tracker.lab6.services.StudentDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is an implementation class of {@link StudentDetailsService} interface. This class has implemented
 * all CURD operations to update, retrieve, delete or create student information in database with help of
 * {@link org.springframework.stereotype.Repository} layer classes.
 *
 * @author DIPANJAN DAS(212431882)
 * @version 1.0
 * @since 02-May-2023
 */
@Service
@Slf4j
public class StudentDetailsServiceImpl implements StudentDetailsService {
    private final StudentDetailsRepository studentDetailsRepository;

    @Autowired
    public StudentDetailsServiceImpl(StudentDetailsRepository repository) {
        this.studentDetailsRepository = repository;
    }

    /**
     * This method is responsible to save {@link StudentInformation} in database.
     *
     * @param information Object of {@link StudentInformation} class.
     */
    @Override
    public void saveStudentDetails(StudentInformation information) {
        if (information == null) {
            log.info("StudentInformation object received as NULL");
            return;
        }
        Optional<StudentDetails> optional = studentDetailsRepository.findById(information.getStudentId());
        if (optional.isPresent()) {
            int studentId = optional.get().getStudentId();
            BeanUtils.copyProperties(information, optional.get());
            optional.get().setStudentId(studentId);
            StudentDetails savedStudentDetails = studentDetailsRepository.saveAndFlush(optional.get());
            log.info("Student information have been updated for student with studentId := {}", savedStudentDetails.getStudentId());
        } else {
            StudentDetails studentDetails = new StudentDetails();
            BeanUtils.copyProperties(information, studentDetails);
            StudentDetails savedStudentDetails = studentDetailsRepository.saveAndFlush(studentDetails);
            log.info("New Student record has been created with studentId := {}", savedStudentDetails.getStudentId());
        }
    }

    /**
     * This method is responsible to delete {@link StudentInformation} based on provided Student ID as part of argument
     * of this function.
     *
     * @param studentId as {@link Integer} value.
     */
    @Override
    public void deleteStudent(int studentId) {
        Optional<StudentDetails> optional = studentDetailsRepository.findById(studentId);
        optional.ifPresentOrElse(studentDetails -> {
            studentDetailsRepository.delete(studentDetails);
            log.info("Requested Student id is present in database and successfully deleted from database.");
        }, () -> {
            log.info("Provided student id is not present in database.");
        });
    }

    /**
     * This method is responsible to retrieve all students records from database and provide the list of {@link StudentInformation}
     *
     * @return List of {@link StudentInformation}
     */
    @Override
    public List<StudentInformation> retrieveAllStudents() {
        List<StudentInformation> studentInformationList = new ArrayList<>();
        List<StudentDetails> studentDetailsList = studentDetailsRepository.findAll();
        if (!studentDetailsList.isEmpty()) {
            studentDetailsList.forEach(studentDetails -> {
                StudentInformation information = new StudentInformation();
                BeanUtils.copyProperties(studentDetails, information);
                studentInformationList.add(information);
            });
        }
        log.info("Student details list size := {}", studentDetailsList.size());
        return studentInformationList;
    }

    /**
     * This method is responsible to retrieve a particular {@link StudentInformation} by using studentId.
     *
     * @param studentId {@link Integer} value.
     * @return Optional object of {@link StudentInformation} type.
     */
    @Override
    public Optional<StudentInformation> retrieveStudentById(int studentId) {
        StudentInformation information = new StudentInformation();
        Optional<StudentDetails> optional = studentDetailsRepository.findById(studentId);
        if (optional.isPresent()) {
            BeanUtils.copyProperties(optional.get(), information);
            return Optional.of(information);
        }
        return Optional.empty();
    }
}

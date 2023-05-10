package com.gl.student.tracker.lab6.services;

import com.gl.student.tracker.lab6.dto.StudentInformation;

import java.util.List;
import java.util.Optional;

/**
 * This interface is holding all contract of student details tracker application.
 *
 * @author DIPANJAN DAS(212431882)
 * @version 1.0
 * @since 02-May-2023
 */
public interface StudentDetailsService {
    void saveStudentDetails(StudentInformation information);

    void deleteStudent(int studentId);

    List<StudentInformation> retrieveAllStudents();

    Optional<StudentInformation> retrieveStudentById(int studentId);
}

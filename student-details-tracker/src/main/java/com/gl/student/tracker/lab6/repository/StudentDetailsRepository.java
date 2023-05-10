package com.gl.student.tracker.lab6.repository;

import com.gl.student.tracker.lab6.model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the repository interface which is responsible to perform all CURD operation with database
 * for student details information.
 *
 * @author DIPANJAN DAS(212431882)
 * @version 1.0
 * @since 02-May-2023
 */
@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Integer> {

}

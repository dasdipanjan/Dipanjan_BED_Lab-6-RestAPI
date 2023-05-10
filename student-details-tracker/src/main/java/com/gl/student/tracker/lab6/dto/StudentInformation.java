package com.gl.student.tracker.lab6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a plain java class which is responsible to carry student information from controller to service.
 *
 * @author DIPANJAN DAS(212431882)
 * @version 1.0
 * @since 02-May-2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInformation {
    private int studentId;
    private String firstName;
    private String lastName;
    private String courseName;
    private String country;
}

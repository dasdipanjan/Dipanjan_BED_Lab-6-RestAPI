package com.gl.student.tracker.lab6.model;

import javax.persistence.*;

/**
 * This class is responsible to represent student details information table in database.
 *
 * @author DIPANJAN DAS(212431882)
 * @version 1.0
 * @since 02-May-2023
 */
@Entity
@Table(
        name = "TBL_STUDENT_DETAILS",
        schema = "db_student_details",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "studentId_unique",
                        columnNames = "student_id"
                )
        })
public class StudentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_generator")
    @SequenceGenerator(
            name = "student_id_generator",
            sequenceName = "student_id_sequence_table",
            allocationSize = 10
    )
    @Column(name = "student_id", nullable = false)
    private int studentId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "course_name", nullable = false)
    private String courseName;
    @Column(name = "country", nullable = false)
    private String country;

    public StudentDetails() {

    }

    public StudentDetails(int studentId, String firstName, String lastName, String courseName, String country) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseName = courseName;
        this.country = country;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

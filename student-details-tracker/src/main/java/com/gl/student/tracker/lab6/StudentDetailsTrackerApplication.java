package com.gl.student.tracker.lab6;

import com.gl.student.tracker.lab6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the starter class of this application. This class is having main method which is the entry point of this
 * application.
 *
 * @author DIPANJAN DAS(212431882)
 * @version 1.0
 * @since 02-May-2023
 */
@SpringBootApplication
public class StudentDetailsTrackerApplication {
    @Autowired
    private UserRepository mUserRepository;

    /**
     * This method is invoked by main thread of this JVM.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(StudentDetailsTrackerApplication.class, args);
    }

}

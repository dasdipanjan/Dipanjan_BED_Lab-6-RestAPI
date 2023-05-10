package com.gl.student.tracker.lab6.model;

import lombok.Data;

import javax.persistence.*;

/**
 * This is a plain java class which is representing User's role information table in database.
 *
 * @author DIPANJAN DAS(212431882)
 * @version 1.0
 * @since 02-May-2023
 */
@Entity
@Table(name = "TBL_ROLES")
@Data
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}

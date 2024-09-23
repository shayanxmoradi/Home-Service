package org.example.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
@Table(name = BaseUser.TABLE_NAME)
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseUser extends BaseEntity<Long> {
    public static final String TABLE_NAME = "base_user";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PASSWORD = "password";


    @Column(nullable = false,name = FIRST_NAME)
    private String firstName;
    @Column(nullable = false,name = LAST_NAME)
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column
    private Date registrationDate;

    @Column
    private Time registrationTime;


    @Column(nullable = false,name = PASSWORD)
    private String password;
}

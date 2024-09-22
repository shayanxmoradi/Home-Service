package org.example.entites;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = Customer.TABLE_NAME)
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer  extends BaseEntity<Long>{
    public static final String TABLE_NAME = "customer";
    private static final String FIRST_NAME = "firstName";
    private static final String PASSWORD = "password";

    @Column(nullable = false,name = FIRST_NAME)
    private String firstName;


    @Column(nullable = false,name = PASSWORD)
    private String password;

}


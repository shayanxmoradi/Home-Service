package org.example.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Table(name = Customer.TABLE_NAME)
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends BaseUser {
    public static final String TABLE_NAME = "customer";

    @OneToMany
    private List<Order> orders;

    public void addOrder(Order order) {
        orders.add(order);
    }


}


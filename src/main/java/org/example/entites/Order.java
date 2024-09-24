package org.example.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.Data;
import org.example.entites.enums.OrderStatus;

import java.sql.Date;
import java.sql.Time;

@Table(name = Order.TABLE_NAME)
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Order extends BaseEntity<Long> {
    public static final String TABLE_NAME = "orders";

    @PrimaryKeyJoinColumn

    @OneToOne
    private Service choosenService;

    @Column(nullable = false)
    private String orderDescription;

    @Column
    private Double offeredPrice;

    @Column
    @Future
    private Date serviceDate;

    @Column
    private Time serviceTime;


    @OneToOne(cascade = CascadeType.ALL)  // Cascade the persist operation
    @PrimaryKeyJoinColumn

    private Address address;

    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus status=OrderStatus.WAITING_FOR_SPECIALISTS_OFFERS;


}

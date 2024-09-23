package org.example.entites;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Table(name = Specialist.TABLE_NAME)
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Specialist extends BaseUser {
    public static final String TABLE_NAME = "specialist";

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SpecialistStatus specialistStatus;

    @Column
    private Double rate=0.0;

    @OneToMany
    List<Service> workServices;


    @Lob
    @Column(name = "image_data",length = 300000)
    private byte[] personalImage;

}

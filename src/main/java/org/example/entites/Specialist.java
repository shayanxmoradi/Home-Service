package org.example.entites;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = Specialist.TABLE_NAME)
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Specialist extends BaseUser {
    public static final String TABLE_NAME = "specialist";

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SpecialistStatus specialistStatus;

    @Lob
    @Column(name = "image_data",length = 300000)
    private byte[] personalImage;

}

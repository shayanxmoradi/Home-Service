package org.example.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = Service.TABLE_NAME)
@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class Service extends BaseEntity<Long> {
    public static final String TABLE_NAME = "service";
    private static final String PARENT_SERVICE_ID = "parent_service_id";
    private static final String SERVICE_NAME = "service_name";


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PARENT_SERVICE_ID)
    private Service parentService;

    @OneToMany(mappedBy = "parentService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> subServices= new ArrayList<>();;

    @Column(name = SERVICE_NAME)
    private String name;

    public void addSubService(Service subService) {
        subServices.add(subService);
        subService.setParent(this);
    }

    public void removeSubService(Service subService) {
        subServices.remove(subService);
        subService.setParent(null);
    }

    public void setParent(Service parent) {
        this.parentService = parent;
    }

    public Service(String name) {
        this.name = name;
    }
    public void setParentServiceById(EntityManager entityManager, Long parentId) {
        if (parentId != null) {
            this.parentService = entityManager.getReference(Service.class, parentId);
        }
    }
}

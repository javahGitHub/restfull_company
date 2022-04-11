package pdp.uz.restfull_company.entity;

import lombok.Data;
import org.hibernate.annotations.Cache;

import javax.persistence.*;

@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Company company;
}

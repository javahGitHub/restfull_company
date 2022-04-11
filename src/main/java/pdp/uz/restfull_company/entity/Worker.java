package pdp.uz.restfull_company.entity;

import lombok.Data;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Data
@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @ManyToOne
    private Department department;

    @OneToOne
    private Address address;

}

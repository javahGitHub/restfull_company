package pdp.uz.restfull_company.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String corpName;
    @Column(nullable = false)
    private String directorName;
    @OneToOne
    private Address address;

}

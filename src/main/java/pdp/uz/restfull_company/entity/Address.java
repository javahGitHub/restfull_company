package pdp.uz.restfull_company.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Street can't be empty")
    @Column(nullable = false)
    private String street;

    @NotNull(message = "Street can't be empty")
    @Column(nullable = false)
    private String homeNumber;


}
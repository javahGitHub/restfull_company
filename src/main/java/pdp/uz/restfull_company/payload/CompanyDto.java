package pdp.uz.restfull_company.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import pdp.uz.restfull_company.entity.Address;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data

public class CompanyDto {

    @NotNull(message = "Company name can't be empty!")
    private String corpName;
    @NotNull(message = "Company Director name can't be empty!")
    private String directorName;
    @NotNull(message = "Company address can't be empty!")
    private int addressId;

}

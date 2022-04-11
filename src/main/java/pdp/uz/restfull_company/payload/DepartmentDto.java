package pdp.uz.restfull_company.payload;

import lombok.Data;
import pdp.uz.restfull_company.entity.Company;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {


    @NotNull(message = "Department should have name")
    private String name;

    @NotNull(message = "You should enter Department Company name")
    private int companyId;
}

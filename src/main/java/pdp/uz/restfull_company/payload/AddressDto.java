package pdp.uz.restfull_company.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NotNull
public class AddressDto {
    @NotNull(message = "Street can't be empty")
    private String street;

    @NotNull(message = "Street can't be empty")
    private String homeNumber;


}

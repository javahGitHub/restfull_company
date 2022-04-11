package pdp.uz.restfull_company.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {

    @NotNull(message = "Name of worker can't be empty")
    private String name;
    @NotNull(message = "Phone number of worker can't be empty")
    private String phoneNumber;
    @NotNull(message = "Department can't be empty")
    private int departmentId;
    @NotNull(message = "Address can't be empty")
    private int addressId;

}

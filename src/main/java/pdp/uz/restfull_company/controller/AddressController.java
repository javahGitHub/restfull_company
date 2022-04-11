package pdp.uz.restfull_company.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pdp.uz.restfull_company.entity.Address;
import pdp.uz.restfull_company.payload.AddressDto;
import pdp.uz.restfull_company.payload.ApiResponse;
import pdp.uz.restfull_company.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;


    /**
     * Add Address
     *
     * @param addressDto
     * @return Class{String message ,boolean success}
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addAddressController(@Valid @RequestBody AddressDto addressDto) {
        return addressService.addAddress(addressDto);
    }


    /**
     * Get one Address by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAddressController(@PathVariable int id) {
        return addressService.getAddressService(id);
    }


    /**
     * Get All Address using Page param
     *
     * @param page
     * @return page
     */
    @GetMapping
    public ResponseEntity<Page> getAllAddressController(@RequestParam int page) {
        return addressService.getAllAddressService(page);
    }


    /**
     * Update Address by id
     *
     * @param id
     * @param addressDto
     * @return Class { String message,boolean success }
     */
    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAddressController(@PathVariable int id, @RequestBody AddressDto addressDto) {
        return addressService.updateAddressService(id, addressDto);
    }


    /**
     * Delete Address by id
     *
     * @param id
     * @return Class{ String message, boolean success }
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAddressController(@PathVariable int id) {
        return addressService.deleteAddressService(id);
    }




    /**
     * Handle exception
     *
     * @param ex
     * @return http message
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}

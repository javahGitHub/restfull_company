package pdp.uz.restfull_company.service;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pdp.uz.restfull_company.entity.Address;
import pdp.uz.restfull_company.payload.AddressDto;
import pdp.uz.restfull_company.payload.ApiResponse;
import pdp.uz.restfull_company.repository.AddressRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


    /**
     * Add Address
     *
     * @param addressDto
     * @return Class{String message ,boolean success}
     */
    public ResponseEntity<ApiResponse> addAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Address saved successfully", true));
    }


    /**
     * Get one Address by id
     *
     * @param id
     * @return
     */
    public ResponseEntity<Object> getAddressService(int id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address id not found");
        return ResponseEntity.ok(optionalAddress.get());
    }


    /**
     * Get All Address using Page param
     *
     * @param page
     * @return page
     */
    public ResponseEntity<Page> getAllAddressService(int page) {

        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok(addressRepository.findAll(pageable));
    }


    /**
     * Update Address using id
     * @param id
     * @param addressDto
     * @return Class{ String message , boolean success }
     */
    public ResponseEntity<ApiResponse> updateAddressService(int id, AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Address not found by id=" + id, false));
        Address address=optionalAddress.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(address.getHomeNumber());
        addressRepository.save(address);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Address updates successfully",true));

    }


    /**
     * Delete Address by id
     * @param id
     * @return Class{ String message, boolean success }
     */
    public ResponseEntity<ApiResponse> deleteAddressService(int id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Address not found by id=" + id, false));

        addressRepository.deleteById(id);
        return ResponseEntity.status(204).body(new ApiResponse("Address deleted successfully",true));

    }



}

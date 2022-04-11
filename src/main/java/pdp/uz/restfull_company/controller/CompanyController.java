package pdp.uz.restfull_company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pdp.uz.restfull_company.entity.Address;
import pdp.uz.restfull_company.entity.Company;
import pdp.uz.restfull_company.payload.ApiResponse;
import pdp.uz.restfull_company.payload.CompanyDto;
import pdp.uz.restfull_company.service.CompanyService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;




    /**
     * Add Company
     * @param companyDto
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addCompanyController(@Valid @RequestBody CompanyDto companyDto){
        return companyService.addCompanyService(companyDto);
    }



    /**
     * Get all Company
     * @param page
     * @return Page 1 blocks of 10
     */
    @GetMapping
    public ResponseEntity<Page> getAllCompanyController(@RequestParam int page) {

        return companyService.getAllCompanyService(page);
    }



    /**
     * Get one Company
     * @param id
     * @return Object true=Company ,false=ApiResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCompanyService(@PathVariable Integer id) {
        return companyService.getCompanyService(id);
    }






    /**
     * Update exist Company
     * @param companyDto
     * @param id
     * @return ApiResponse
     */
    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCompanyController(@RequestBody CompanyDto companyDto,@PathVariable int id) {
      return companyService.updateCompanyService(companyDto,id);
    }





    /**
     * Delete Company
     * @param id
     * @return ApiResponse
     */
        @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCompanyService(@PathVariable int id) {
         return companyService.deleteCompanyService(id);
    }






    /*
    * To Get Exception and send to Client
    * */
        @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
         ex.getBindingResult().getAllErrors().forEach( error->{
          errors.put(((FieldError) error).getField(),error.getDefaultMessage()) ;
        });
         return errors;
    }

}

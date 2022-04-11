package pdp.uz.restfull_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.restfull_company.entity.Address;
import pdp.uz.restfull_company.entity.Company;
import pdp.uz.restfull_company.payload.ApiResponse;
import pdp.uz.restfull_company.payload.CompanyDto;
import pdp.uz.restfull_company.repository.AddressRepository;
import pdp.uz.restfull_company.repository.CompanyRepository;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;


    /**
     * Add Company
     * @param companyDto
     * @return
     */
    public ResponseEntity<ApiResponse> addCompanyService(CompanyDto companyDto) {

        //Check Address id from repository
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Address id not found in repository", false));

        //Check CorName in repository and return message,false
        if (companyRepository.existsByCorpName(companyDto.getCorpName()))
            return ResponseEntity.status(HttpStatus.SEE_OTHER).body(new ApiResponse("You can't enter exist Company name in repository", false));

        Company company = new Company();
        company.setAddress(optionalAddress.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return ResponseEntity.ok(new ApiResponse("Company saved successfully", true));
    }


    /**
     * Get all Company
     * @param page
     * @return Page 1 blocks of 10
     */
    public ResponseEntity<Page> getAllCompanyService(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok(companyRepository.findAll(pageable));
    }

    /**
     * Get one Company
     * @param id
     * @return Object true=Company ,false=ApiResponse
     */
    public ResponseEntity<Object> getCompanyService(int id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Company not found", false));
        return ResponseEntity.ok(optionalCompany.get());
    }


    /**
     * Update exist Company
     * @param companyDto
     * @param id
     * @return ApiResponse
     */
    public ResponseEntity<ApiResponse> updateCompanyService(CompanyDto companyDto, int id) {
        //Check Company id from repository
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Company id not found in repository", false));

        //Check Address id from repository
        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Address id not found in repository", false));

        //Check CorName in repository and return message,false
        if (companyRepository.existsByCorpNameAndIdNot(companyDto.getCorpName(), id))
            return ResponseEntity.status(HttpStatus.SEE_OTHER).body(new ApiResponse("You can't enter exist Company name in repository", false));

        Company company = optionalCompany.get();
        company.setAddress(optionalAddress.get());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return ResponseEntity.ok(new ApiResponse("Company updated successfully", true));
    }


    /**
     * Delete Company
     * @param id
     * @return ApiResponse
     */
    public ResponseEntity<ApiResponse> deleteCompanyService(int id) {
        if (!companyRepository.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Company not found", false));
        companyRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Company deleted successfully", true));
    }




}









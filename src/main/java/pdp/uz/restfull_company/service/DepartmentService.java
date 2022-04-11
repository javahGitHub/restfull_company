package pdp.uz.restfull_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pdp.uz.restfull_company.entity.Company;
import pdp.uz.restfull_company.entity.Department;
import pdp.uz.restfull_company.payload.ApiResponse;
import pdp.uz.restfull_company.payload.CompanyDto;
import pdp.uz.restfull_company.payload.DepartmentDto;
import pdp.uz.restfull_company.repository.CompanyRepository;
import pdp.uz.restfull_company.repository.DepartmentRepository;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;


    /**
     * Save Department
     *
     * @param departmentDto
     * @return ApiResponse
     */
    public ResponseEntity<ApiResponse> addDepartment(@RequestBody DepartmentDto departmentDto) {
        //Check Company id from repository
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Company id not found in repository", false));

        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return ResponseEntity.ok(new ApiResponse("Company saved successfully", true));
    }


    /**
     * Get All Departments
     *
     * @param page
     * @return Page of all Departments under 10 sheets
     */
    public ResponseEntity<Page> getDepartments(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok(departmentRepository.findAll(pageable));
    }


    /**
     * Get Departments by Company id
     *
     * @param id
     * @param page
     * @return true Page of departments false warning message
     */
    public ResponseEntity<Object> getDepartmentsByCopId(int id, int page) {
        boolean b = companyRepository.existsById(id);
        if (b) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You entered id not found");
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok(departmentRepository.findAllByCompanyId(id, pageable));
    }


    /**
     * Get one Department by id
     *
     * @param id
     * @return Object true Department class false error message
     */
    public ResponseEntity<Object> getDepartment(int id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Enter valid id of department");
        return ResponseEntity.ok(optionalDepartment.get());
    }


    /**
     * Update Department
     *
     * @param id
     * @param departmentDto
     * @return ApiResponse always
     */
    public ResponseEntity<ApiResponse> updateDepartment(int id, DepartmentDto departmentDto) {


        //Check Department id in repository
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Please enter valid id of Department", false));

        //Check Company id in repository
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Please enter valid id of Company", false));
        //Check Department name to be unique
        if (departmentRepository.existsByNameAndIdNot(departmentDto.getName(), id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("The name of Department You want change to is already exist ", false));

        Department department = optionalDepartment.get();
        department.setCompany(optionalCompany.get());
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return ResponseEntity.ok(new ApiResponse("Department updated successfully", true));

    }


    /**
     * Delete Department
     * @param id
     * @return ApiResponse
     */
    public ResponseEntity<ApiResponse> deleteDepartment(int id) {
        //Check Department id in repository
        if (!departmentRepository.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Please enter valid id of Department", false));

        departmentRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("Department deleted successfully",true));
    }
}

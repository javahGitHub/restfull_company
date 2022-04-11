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
import pdp.uz.restfull_company.entity.Company;
import pdp.uz.restfull_company.entity.Department;
import pdp.uz.restfull_company.payload.ApiResponse;
import pdp.uz.restfull_company.payload.DepartmentDto;
import pdp.uz.restfull_company.service.DepartmentService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;


    /**
     * Save Department
     *
     * @param departmentDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addDepartment(@RequestBody DepartmentDto departmentDto) {
        return departmentService.addDepartment(departmentDto);

    }


    /**
     * Get All Departments
     *
     * @param page
     * @return Page of all Departments under 10 sheets
     */
    @GetMapping
    public ResponseEntity<Page> getDepartmentsController(@RequestParam int page) {

        return departmentService.getDepartments(page);
    }



    /**
     * Get Departments by Company id
     * @param id
     * @param page
     * @return true Page of departments false warning message
     */
    public ResponseEntity<Object> getDepartmentsByCopId(int id,int page){
        return departmentService.getDepartmentsByCopId(id,page);
    }


    /**
     * Get one Department by id
     * @param id
     * @return Object true Department class false error message
     */
    public ResponseEntity<Object> getDepartment(int id){
        return departmentService.getDepartment(id);
    }


    /**
     * Update Department
     * @param id
     * @param departmentDto
     * @return ApiResponse always
     */
    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDepartmentController(@PathVariable  int id,@RequestBody DepartmentDto departmentDto){
       return departmentService.updateDepartment(id,departmentDto);
    }



    /**
     * Delete Department
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable int id) {
        return departmentService.deleteDepartment(id);
    }






    /*
     * To Get Exception and send to Client
     * */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });
        return errors;
    }
}

package pdp.uz.restfull_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pdp.uz.restfull_company.entity.Address;
import pdp.uz.restfull_company.entity.Department;
import pdp.uz.restfull_company.entity.Worker;
import pdp.uz.restfull_company.payload.ApiResponse;
import pdp.uz.restfull_company.payload.WorkerDto;
import pdp.uz.restfull_company.repository.AddressRepository;
import pdp.uz.restfull_company.repository.CompanyRepository;
import pdp.uz.restfull_company.repository.DepartmentRepository;
import pdp.uz.restfull_company.repository.WorkerRepository;

import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;




    /**
     *Add Worker
     * @param workerDto
     * @return ApiResponse
     */
    public ResponseEntity<ApiResponse> addWorker(WorkerDto workerDto){
        //Check Department from repository
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if(!optionalDepartment.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Department not found",false));
        //Check Address from repository
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if(!optionalAddress.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Address not found",false));
        //Check Phone Number from repository
        if(workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Phone number already exist ",false));

        Worker worker=new Worker();
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return ResponseEntity.ok(new ApiResponse("Worker added successfully",true));

    }




    /**
     * Get all Workers as Page
     * @param page
     * @return only Page
     */
    public ResponseEntity<Page> getAllWorkers(int page){
        Pageable pageable= PageRequest.of(page,10);
        return ResponseEntity.ok(workerRepository.findAll(pageable));
    }


    /**
     * Get all Workers by Department id as Page
     * @param page
     * @param id
     * @return exist ? Page : ApiResponse
     */
    public ResponseEntity<Object> getWorkersByDepId(int page,int id){

        if(!departmentRepository.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Department not found",false));

        Pageable pageable=PageRequest.of(page,10);
        return ResponseEntity.ok(workerRepository.findAllByDepartmentId(id,pageable));
    }


    /**
     * Get one Worker only
     * @param id
     * @return exist ? Worker : ApiResponse
     */
    public ResponseEntity<Object> getWorker(int id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if(optionalWorker.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Worker not found ",false));
        return ResponseEntity.ok(optionalWorker.get());
    }




    /**
     * Update Worker
     * @param id
     * @param workerDto
     * @return ApiResponse
     */
    public ResponseEntity<ApiResponse> updateWorker(int id,WorkerDto workerDto){

        //Check Department from repository
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if(!optionalDepartment.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Department not found",false));

        //Check Address from repository
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if(!optionalAddress.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Address not found",false));

        //Check Worker from repository
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if(!optionalWorker.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Worker not found",false));

        //Check Phone Number from repository
        if(workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(),id))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Phone number already exist ",false));


        Worker worker=optionalWorker.get();
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setDepartment(optionalDepartment.get());
        worker.setName(worker.getName());
        worker.setAddress(optionalAddress.get());
        workerRepository.save(worker);
        return ResponseEntity.ok(new  ApiResponse("Worker updated successfully",true));
    }




    /**
     * Delete Worker
     * @param id
     * @return ApiResponse
     */
    public ResponseEntity<ApiResponse> deleteWorker(int id) {
        //Check Worker from repository
        if (workerRepository.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Worker not found", false));
        workerRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("Worker deleted successfully", true));

    }

}

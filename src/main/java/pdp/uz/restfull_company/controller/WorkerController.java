package pdp.uz.restfull_company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.restfull_company.entity.Address;
import pdp.uz.restfull_company.entity.Department;
import pdp.uz.restfull_company.entity.Worker;
import pdp.uz.restfull_company.payload.ApiResponse;
import pdp.uz.restfull_company.payload.WorkerDto;
import pdp.uz.restfull_company.service.WorkerService;

import java.util.Optional;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    
    @Autowired
    WorkerService workerService;



    /**
     *Add Worker
     * @param workerDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addWorker(@RequestBody WorkerDto workerDto){

        return workerService.addWorker(workerDto);
    }


    /**
     * Get all Workers as Page
     * @param page
     * @return only Page
     */
    @GetMapping
    public ResponseEntity<Page> getAllWorkers(@RequestParam int page){
       return workerService.getAllWorkers(page);
    }



    /**
     * Get all Workers by Department id as Page
     * @param page
     * @param id
     * @return exist ? Page : ApiResponse
     */
    @GetMapping("/getByDepId/{id}")
    public ResponseEntity<Object> getWorkersByDepId(int page,@PathVariable int id){
        return workerService.getWorkersByDepId(page,id);
    }


    /**
     * Get one Worker only
     * @param id
     * @return exist ? Worker : ApiResponse
     */
    @GetMapping("/getByWorkerId/{id}")
    public ResponseEntity<Object> getWorker(@PathVariable int id){
        return workerService.getWorker(id);
    }



    /**
     * Update Worker
     * @param id
     * @param workerDto
     * @return ApiResponse
     */
    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> updateWorker(@PathVariable int id,@RequestBody WorkerDto workerDto){

        return workerService.updateWorker(id,workerDto);
    }

    /**
     * Delete Worker
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteWorker(@PathVariable int id) {
      return workerService.deleteWorker(id);
    }



}

package pdp.uz.restfull_company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.restfull_company.entity.Department;
import pdp.uz.restfull_company.entity.Worker;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
    Page findAllByDepartmentId(int id, Pageable pageable);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber,int id);

}

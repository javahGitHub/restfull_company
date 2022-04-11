package pdp.uz.restfull_company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.restfull_company.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Page findAllByCompanyId(int id, Pageable pageable);
    boolean existsByNameAndIdNot(String name,int id);
}

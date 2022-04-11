package pdp.uz.restfull_company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.restfull_company.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}

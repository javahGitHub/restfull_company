package pdp.uz.restfull_company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pdp.uz.restfull_company.entity.Company;

public interface CompanyRepository  extends JpaRepository<Company,Integer> {
    @Query("select (count(c) > 0) from Company c where c.corpName = ?1")
    boolean existsByCorpName(String corName);

    @Query("select (count(c) > 0) from Company c where c.corpName = ?1 and c.id <> ?2")
    boolean existsByCorpNameAndIdNot(String corName, int id);
}

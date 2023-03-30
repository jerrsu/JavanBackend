package demo.test.app.repository;

import demo.test.app.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author js
 */
@Repository
public interface TaxRepository extends JpaRepository<Tax,Integer> {
	List<Tax> findByStatus(String status);
}

package demo.test.app.repository;

import demo.test.app.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author js
 */
@Repository
public interface LogRepository extends JpaRepository<Log,Integer> {
}

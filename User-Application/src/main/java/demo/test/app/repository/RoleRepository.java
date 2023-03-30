package demo.test.app.repository;

import demo.test.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author js
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}

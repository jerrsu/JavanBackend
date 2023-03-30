package demo.test.app.repository;

import demo.test.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author js
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	User findByUsername(String userName);
}

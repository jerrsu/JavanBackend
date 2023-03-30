package demo.test.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author js
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Where(clause = "is_delete=0")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id ;
	
	@Column(name = "username", unique = true)
	private String username ;
	
	@Column(name = "email", unique = true)
	private String email ;

	@Column(name = "pass", nullable = false)
	private String password;

	@Column(name = "role_id")
	private Integer roleId;
	
	@Column(name = "is_delete")
	private Boolean isDelete;
}

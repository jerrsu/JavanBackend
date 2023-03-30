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
@Table(name = "role")
@Where(clause = "is_delete=0")
public class Role {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false, unique = true)
     private Integer id ;

     @Column(name = "name")
     private String name ;
     
     @Column(name = "is_delete")
     private Boolean isDelete ;
}

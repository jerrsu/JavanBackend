package demo.test.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author js
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tax")
public class Tax {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id ;
	
	@Column(name = "resi")
	private String resi ;
	
	@CreationTimestamp
	@Column(name = "date")
	private LocalDateTime tanggal ;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@UpdateTimestamp
	@Column(name = "date_update")
	private LocalDateTime tanggalUpdate ;
	
}

package demo.test.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author js
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxDto {
	private Integer id ;
	private String resi ;
	private String status;
	private String createdBy;
}

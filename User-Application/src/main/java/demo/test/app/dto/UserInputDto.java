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
public class UserInputDto {
    private Integer id;
    private String username;
    private String email ;
    private String password;
    private Integer roleId;
}

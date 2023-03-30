package demo.test.app.dto.mapper;

import demo.test.app.dto.UserDto;
import demo.test.app.dto.UserInputDto;
import demo.test.app.model.User;
import org.mapstruct.Mapper;

/**
 * @author js
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDto toDto(User entity);
	
	User toEntity(UserInputDto dto);
	
	UserInputDto toInputDto(User entity);
}

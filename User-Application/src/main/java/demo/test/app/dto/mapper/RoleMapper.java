package demo.test.app.dto.mapper;

import demo.test.app.dto.RoleDto;
import demo.test.app.model.Role;
import org.mapstruct.Mapper;

/**
 * @author js
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {
	RoleDto toDto(Role entity);
	
	Role toEntity(RoleDto dto);
}


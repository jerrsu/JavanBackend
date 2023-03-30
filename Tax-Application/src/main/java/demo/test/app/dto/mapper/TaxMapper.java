package demo.test.app.dto.mapper;

import demo.test.app.dto.TaxDto;
import demo.test.app.model.Tax;
import org.mapstruct.Mapper;

/**
 * @author js
 */
@Mapper(componentModel = "spring")
public interface TaxMapper {
	TaxDto toDto(Tax entity);
	
	Tax toEntity(TaxDto dto);
}

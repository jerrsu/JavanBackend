package demo.test.app.service;

import demo.test.app.dto.RoleDto;
import demo.test.app.dto.mapper.RoleMapper;
import demo.test.app.model.Role;
import demo.test.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author js
 */
@Service
public class RoleService {
	@Autowired
	RoleRepository repository;
	
	@Autowired
	RoleMapper mapper;
	
	public RoleDto create(RoleDto input){
		Role role = mapper.toEntity(input);
		role.setIsDelete(Boolean.FALSE);
		repository.save(role);
		return mapper.toDto(role);
	}
	
	public List<RoleDto> list(){
		return repository.findAll(Sort.by(Sort.Direction.ASC,"name")).stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	public RoleDto findById(Integer id){
		return repository.findById(id).map(mapper::toDto).orElse(null);
	}
	
	public RoleDto update(RoleDto input,Integer id){
		Role old = mapper.toEntity(this.findById(id));
		old.setName(input.getName());
		repository.save(old);
		return mapper.toDto(old);
	}
	
	public void softDelete(Integer id){
		Role old = mapper.toEntity(this.findById(id));
		old.setIsDelete(Boolean.TRUE);
		repository.save(old);
	}
}

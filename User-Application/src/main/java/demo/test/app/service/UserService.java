package demo.test.app.service;

import demo.test.app.dto.UserDto;
import demo.test.app.dto.UserInputDto;
import demo.test.app.dto.mapper.UserMapper;
import demo.test.app.model.Role;
import demo.test.app.model.User;
import demo.test.app.repository.RoleRepository;
import demo.test.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author js
 */
@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRepository repository;
	
	@Autowired
	UserMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		Role role = roleRepository.findById(user.getRoleId()).get();
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList(role.getName()));
	}
	
	public UserDto create(UserInputDto input){
		User user = mapper.toEntity(input);
		user.setIsDelete(Boolean.FALSE);
		user.setPassword(encoder.encode(input.getPassword()));
		repository.save(user);
		return mapper.toDto(user);
	}
	
	public List<UserDto> list(){
		return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	public UserDto findById(Integer id){
		return repository.findById(id).map(mapper::toDto).orElse(null);
	}
	
	public UserDto update(UserInputDto input,Integer id){
		User dataLama = repository.findById(id).orElseThrow();
		User dataBaru = mapper.toEntity(input);
		dataBaru = this.mapData(dataLama,dataBaru);
		dataBaru = repository.save(dataBaru);
		return mapper.toDto(dataBaru);
	}
	
	private User mapData(User dataLama,User dataBaru){
		User dataProses = dataLama;
		if (dataBaru.getEmail() != null){
			dataProses.setEmail(dataBaru.getEmail());
		}
		if (dataBaru.getRoleId() != null){
			dataProses.setRoleId(dataBaru.getRoleId());
		}
		if (dataBaru.getUsername() != null){
			dataProses.setUsername(dataBaru.getUsername());
		}
		return dataProses;
	}
	
	public void softDelete(Integer id){
		User old = repository.findById(id).orElseThrow();
		old.setIsDelete(Boolean.TRUE);
		repository.save(old);
	}
}

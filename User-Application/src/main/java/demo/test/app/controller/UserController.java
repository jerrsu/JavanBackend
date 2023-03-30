package demo.test.app.controller;

import demo.test.app.dto.UserDto;
import demo.test.app.dto.UserInputDto;
import demo.test.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author js
 */

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    @Autowired
	UserService service;
	
	@GetMapping()
	public ResponseEntity<?> list(){
		List<UserDto> list = service.list();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody UserInputDto inputDto){
		try {
			UserDto userInputDto = service.create(inputDto);
			return new ResponseEntity<>(userInputDto,HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>("Failed",HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody UserInputDto inputDto,@PathVariable Integer id){
		UserDto userDto = service.findById(id);
		if (userDto != null){
			try {
				UserDto dto = service.update(inputDto,id);
				return new ResponseEntity<>(dto,HttpStatus.OK);
			}catch (Exception e){
				return new ResponseEntity<>("Failed",HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}return new ResponseEntity<>("Data Not Found",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		UserDto userDto = service.findById(id);
		if (userDto != null){
			try {
				service.softDelete(id);
				return new ResponseEntity<>("Success delete",HttpStatus.OK);
			}catch (Exception e){
				return new ResponseEntity<>("Failed",HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}return new ResponseEntity<>("Data Not Found",HttpStatus.NOT_FOUND);
	}
}

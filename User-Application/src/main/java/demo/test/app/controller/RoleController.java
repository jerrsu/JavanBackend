package demo.test.app.controller;

import demo.test.app.dto.RoleDto;
import demo.test.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author js
 */
@RestController
@RequestMapping("/role")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class RoleController {
	@Autowired
	RoleService service;
	
	@GetMapping()
	public ResponseEntity<?> list(Principal principal){
		System.out.println(principal.getName());
		List<RoleDto> list = service.list();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
//	@PostMapping
//	public ResponseEntity<?> create(@RequestBody RoleDto inputDto){
//		try {
//			RoleDto userInputDto = service.create(inputDto);
//			return new ResponseEntity<>(userInputDto,HttpStatus.OK);
//		}catch (Exception e){
//			return new ResponseEntity<>("Failed",HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody RoleDto inputDto,@PathVariable Integer id){
		RoleDto userDto = service.findById(id);
		if (userDto != null){
			try {
				RoleDto dto = service.update(inputDto,id);
				return new ResponseEntity<>(dto,HttpStatus.OK);
			}catch (Exception e){
				return new ResponseEntity<>("Failed",HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}return new ResponseEntity<>("Data Not Found",HttpStatus.NOT_FOUND);
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> delete(@PathVariable Integer id){
//		RoleDto userDto = service.findById(id);
//		if (userDto != null){
//			try {
//				service.softDelete(id);
//				return new ResponseEntity<>("Success delete",HttpStatus.OK);
//			}catch (Exception e){
//				return new ResponseEntity<>("Failed",HttpStatus.UNPROCESSABLE_ENTITY);
//			}
//		}return new ResponseEntity<>("Data Not Found",HttpStatus.NOT_FOUND);
//	}
}

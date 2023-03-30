package demo.test.app.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import demo.test.app.dto.TaxDto;
import demo.test.app.service.TaxService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
@RequestMapping("/tax")
public class TaxController {
	@Autowired
	TaxService service;
	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('MAKER')")
	public ResponseEntity<?> create(Principal principal){
		try {
			TaxDto dto = service.create(principal);
			Gson gson = new Gson();
			template.convertAndSend("LOG_REPORT",gson.toJson(dto));
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>("UNPROCESSABLE_ENTITY", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('CHECKER')")
	public ResponseEntity<?> list(){
		List<TaxDto> dtos = service.listTax(null);
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/approve")
	@PreAuthorize("hasAnyAuthority('APPROVER')")
	public ResponseEntity<?> listApprove(){
		List<TaxDto> dtos = service.listTax("APPROVE");
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@PutMapping("/approve/{id}")
	@PreAuthorize("hasAnyAuthority('CHECKER')")
	public ResponseEntity<?> approve(@PathVariable Integer id,Principal principal){
		TaxDto dtos = service.findById(id);
		if (dtos != null){
			TaxDto approve = service.approveOrReject(id,"APPROVE",principal);
			Gson gson = new Gson();
			template.convertAndSend("LOG_REPORT",gson.toJson(approve));
			return new ResponseEntity<>(approve, HttpStatus.OK);
		}
		return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/reject/{id}")
	@PreAuthorize("hasAnyAuthority('CHECKER')")
	public ResponseEntity<?> reject(@PathVariable Integer id,Principal principal){
		TaxDto dtos = service.findById(id);
		if (dtos != null){
			TaxDto reject = service.approveOrReject(id,"REJECT",principal);
			Gson gson = new Gson();
			template.convertAndSend("LOG_REPORT",gson.toJson(reject));
			return new ResponseEntity<>(reject, HttpStatus.OK);
		}
		return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
	}
}

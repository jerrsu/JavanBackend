package demo.test.app.service;

import demo.test.app.dto.TaxDto;
import demo.test.app.dto.mapper.TaxMapper;
import demo.test.app.model.Tax;
import demo.test.app.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author js
 */
@Service
public class TaxService {
	@Autowired
	TaxRepository repository;
	
	@Autowired
	TaxMapper mapper;
	
	public TaxDto create(Principal principal){
		Tax tax = new Tax();
		tax.setResi("Tax-"+formatTime());
		tax.setStatus("CHECKER");
		tax.setCreatedBy(principal.getName());
		repository.save(tax);
		return mapper.toDto(tax);
	}
	
	public TaxDto approveOrReject(Integer id,String status,Principal principal){
		Tax tax = repository.findById(id).orElse(null);
		tax.setStatus(status);
		tax.setCreatedBy(principal.getName());
		repository.save(tax);
		return mapper.toDto(tax);
	}
	
	public TaxDto findById(Integer id){
		return repository.findById(id).map(mapper::toDto).orElse(null);
	}
	
	public List<TaxDto> listTax(String status){
		if (status != null){
			return repository.findByStatus(status).stream().map(mapper::toDto).collect(Collectors.toList());
		}
		return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	private String formatTime(){
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String formats = now.format(format);
		return formats;
	}
}

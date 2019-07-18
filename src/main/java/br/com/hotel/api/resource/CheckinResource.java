package br.com.hotel.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.api.dto.ResumoCheckinDTO;
import br.com.hotel.api.model.Checkin;
import br.com.hotel.api.service.CheckinService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/checkins")
public class CheckinResource {
	
	@Autowired
	private CheckinService service;
	
	@GetMapping
	public List<Checkin> listar(){
		return service.listar();
	}
	
	@PostMapping
	public ResponseEntity<Checkin> salvar(@RequestBody Checkin checkin, HttpServletResponse response){
		Checkin checkinSalvo = service.salvar(checkin);
		return ResponseEntity.status(HttpStatus.CREATED).body(checkinSalvo);
	}

	@GetMapping("/noHotel")
	public List<ResumoCheckinDTO> listarPessoasAindaNoHotel(){		
		return service.listarPessoasNoHotel();
	}
	
	@GetMapping("/foraHotel")
	public List<ResumoCheckinDTO> listarPessoasJaSairamHotel(){
		return service.listarPessoasJaSairamHotel();
	}
}

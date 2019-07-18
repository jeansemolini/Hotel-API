package br.com.hotel.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.api.model.Pessoa;
import br.com.hotel.api.service.PessoaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService service;
	
	@GetMapping
	public List<Pessoa> listar() {	
		return service.listar();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> salvar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		Pessoa pessoaSalva = service.salvar(pessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}

}

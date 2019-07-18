package br.com.hotel.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotel.api.model.Pessoa;
import br.com.hotel.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repository;
	
	public List<Pessoa> listar(){
		return repository.findAll();
	}
	
	public Pessoa salvar(Pessoa pessoa) {
		Pessoa pessoaSalva = repository.save(pessoa);
		return pessoaSalva;
	}

}

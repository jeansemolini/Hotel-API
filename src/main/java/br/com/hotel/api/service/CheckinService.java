package br.com.hotel.api.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotel.api.dto.ResumoCheckinDTO;
import br.com.hotel.api.model.Checkin;
import br.com.hotel.api.repository.CheckinRepository;

@Service
public class CheckinService {

	@Autowired
	private CheckinRepository repository;

	public List<Checkin> listar() {
		return repository.findAll();
	}

	public Checkin salvar(Checkin checkin) {
		Checkin chekinSalvo = repository.save(checkin);
		return chekinSalvo;
	}

	public List<ResumoCheckinDTO> listarPessoasNoHotel() {
		List<Checkin> list = repository.listaPessoasAindaNoHotel();

		List<ResumoCheckinDTO> listResumo = new ArrayList<>();

		list.forEach(c -> {
			ResumoCheckinDTO resumo = new ResumoCheckinDTO(c.getPessoa().getNome(), c.getPessoa().getDocumento(), 0.00);
			listResumo.add(resumo);
		});

		return listResumo;
	}

	public List<ResumoCheckinDTO> listarPessoasJaSairamHotel() {
		List<Checkin> list = repository.listaPessoasJaSairamHotel();

		List<ResumoCheckinDTO> listResumo = new ArrayList<>();

		list.forEach(c -> {
			
			Long qteDias = (ChronoUnit.DAYS.between(c.getDataEntrada(), c.getDataSaida()));

			double valor = 0;
						
			for (int i = 0; i < qteDias; i++) {
				LocalDateTime ldt = (c.getDataEntrada().plusDays(i));

				if (ldt.getDayOfWeek().getValue() == 6 || ldt.getDayOfWeek().getValue() == 7) {
					valor += 150.00;
					if (c.isAdicionalVeiculo()) {
						valor += 20.00;
					}
				} else {
					valor += 120.00;
					if (c.isAdicionalVeiculo()) {
						valor += 15.00;
					}
				}
			}
			
			if (c.getDataSaida().getHour() > 16) {
				if (c.getDataSaida().getDayOfWeek().getValue() == 6 || c.getDataSaida().getDayOfWeek().getValue() == 7) {
					valor += 150.00;                        
				} else {
					valor += 120.00;
				}
			} else if (c.getDataSaida().getHour() >= 16 && c.getDataSaida().getMinute() > 30) {
				if (c.getDataSaida().getDayOfWeek().getValue() == 6 || c.getDataSaida().getDayOfWeek().getValue() == 7) {
					valor += 150.00;                        
				} else {
					valor += 120.00;
				}
			}
			
			ResumoCheckinDTO resumo = new ResumoCheckinDTO(c.getPessoa().getNome(), c.getPessoa().getDocumento(), valor); 
			listResumo.add(resumo);
		});		

		return listResumo;
	}
}

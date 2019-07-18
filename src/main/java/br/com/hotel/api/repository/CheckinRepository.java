package br.com.hotel.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.api.model.Checkin;

@RestController
public interface CheckinRepository extends JpaRepository<Checkin, Long> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM CHECKIN where DATA_SAIDA is null", nativeQuery = true)
	List<Checkin> listaPessoasAindaNoHotel();

	
	 @Transactional(readOnly = true)	 
	 @Query(value = "SELECT * FROM CHECKIN where DATA_SAIDA is not null", nativeQuery = true) 
	 List<Checkin> listaPessoasJaSairamHotel();
}

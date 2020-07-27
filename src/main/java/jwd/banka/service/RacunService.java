package jwd.banka.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.banka.model.Racun;

public interface RacunService {

	Racun findOne(Long id);

	Page<Racun> findAll(int pageNum);

	Racun save(Racun racun);

	Racun delete(Long id);

	Page<Racun> search(
			@Param("bankaId") Long bankaId, 
			@Param("jmbg") String jmbg, 
			int pageNum);

	Racun findByBrojRacuna(String brojRacuna);

	void prenosSredstava(Racun racunSa, Racun racunNa, Double iznos);

}

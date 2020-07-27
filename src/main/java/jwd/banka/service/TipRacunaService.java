package jwd.banka.service;

import org.springframework.data.domain.Page;

import jwd.banka.model.TipRacuna;

public interface TipRacunaService {

	TipRacuna findOne(Long id);

	Page<TipRacuna> findAll(int pageNum);

	TipRacuna save(TipRacuna tipRacuna);

	TipRacuna delete(Long id);

}

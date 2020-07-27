package jwd.banka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.banka.model.TipRacuna;
import jwd.banka.repository.TipRacunaRepository;
import jwd.banka.service.TipRacunaService;

@Service
public class JpaTipRacunaService implements TipRacunaService {

	@Autowired
	private TipRacunaRepository tipRacunaRepository;

	@Override
	public TipRacuna findOne(Long id) {
		return tipRacunaRepository.findOne(id);
	}

	@Override
	public Page<TipRacuna> findAll(int pageNum) {
		return tipRacunaRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public TipRacuna save(TipRacuna tipRacuna) {
		return tipRacunaRepository.save(tipRacuna);
	}

	@Override
	public TipRacuna delete(Long id) {
		TipRacuna tipRacuna = tipRacunaRepository.findOne(id);
		if (tipRacuna == null) {
			throw new IllegalArgumentException("Tried to delete" + "non-existant poruka");
		}
		tipRacunaRepository.delete(tipRacuna);
		return tipRacuna;
	}

}

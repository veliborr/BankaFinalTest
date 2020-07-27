package jwd.banka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.banka.model.Racun;
import jwd.banka.repository.RacunRepository;
import jwd.banka.service.RacunService;

@Service
public class JpaRacunService implements RacunService {

	@Autowired
	private RacunRepository racunRepository;

	@Override
	public Racun findOne(Long id) {
		return racunRepository.findOne(id);
	}

	@Override
	public Page<Racun> findAll(int pageNum) {
		return racunRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Racun save(Racun racun) {
		return racunRepository.save(racun);
	}

	@Override
	public Racun delete(Long id) {
		Racun racun = racunRepository.findOne(id);
		if (racun == null) {
			throw new IllegalArgumentException("Tried to delete" + "non-existant racun");
		}
		racunRepository.delete(racun);
		return racun;
	}

	@Override
	public Page<Racun> search(Long bankaId, String jmbg, int pageNum) {
		if (jmbg != null) {
			jmbg = '%' + jmbg + '%';
		}

		return racunRepository.search(bankaId, jmbg, new PageRequest(pageNum, 5));
	}

	@Override
	public Racun findByBrojRacuna(String brojRacuna) {
		return racunRepository.findByBrojRacuna(brojRacuna);
	}

	@Override
	public void prenosSredstava(Racun racunSa, Racun racunNa, Double iznos) {

		Double provizija = (iznos * racunSa.getTipRacuna().getProvizija() / 100);

		if (racunSa.getStanjeNaRacunu() >= iznos + provizija) {
			racunSa.setStanjeNaRacunu(racunSa.getStanjeNaRacunu() - iznos);
			racunNa.setStanjeNaRacunu(racunNa.getStanjeNaRacunu() + iznos);

			if (provizija <= 1000) {
				racunSa.getBanka().setSredstvaBanke(racunSa.getBanka().getSredstvaBanke() + iznos * racunSa.getTipRacuna().getProvizija() / 100);
				racunSa.setStanjeNaRacunu(racunSa.getStanjeNaRacunu() - iznos * racunSa.getTipRacuna().getProvizija() / 100);
			}

			racunRepository.save(racunSa);
			racunRepository.save(racunNa);

		} else {
			throw new IllegalArgumentException("Prenos odbijen");
		}

	}

}

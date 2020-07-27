package jwd.banka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.banka.model.Banka;
import jwd.banka.model.Racun;
import jwd.banka.model.TipRacuna;
import jwd.banka.service.BankaService;
import jwd.banka.service.RacunService;
import jwd.banka.service.TipRacunaService;
import jwd.banka.web.dto.RacunDTO;

@Component
public class RacunDTOToRacun implements Converter< RacunDTO, Racun> {

	@Autowired
	private TipRacunaService tipRacunaService;

	@Autowired
	private BankaService bankaService;
	
	@Autowired
	private RacunService racunService;

	@Override
	public Racun convert(RacunDTO dto) {

		Banka banka = bankaService.findOne(dto.getBankaId());
		TipRacuna tipRacuna = tipRacunaService.findOne(dto.getTipRacunaId());

		if (banka != null) {

			Racun racun = null;

			if (dto.getId() != null) {
				racun = racunService.findOne(dto.getId());
			} else {
				racun = new Racun();
			}

			racun.setId(dto.getId());
			racun.setImeIPrezime(dto.getImeIPrezime());
			racun.setJMBG(dto.getJMBG());
			racun.setStanjeNaRacunu(dto.getStanjeNaRacunu());
			racun.setBrojRacuna(dto.getBrojRacuna());
			racun.setBanka(banka);
			racun.setTipRacuna(tipRacuna);

			return racun;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Racun> convert(List<RacunDTO> racunDTOs) {
		List<Racun> ret = new ArrayList<>();

		for (RacunDTO racunDTO : racunDTOs) {
			ret.add(convert(racunDTO));
		}

		return ret;
	}
}

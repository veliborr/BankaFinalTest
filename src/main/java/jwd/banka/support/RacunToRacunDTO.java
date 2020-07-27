package jwd.banka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.banka.model.Racun;
import jwd.banka.web.dto.RacunDTO;

@Component
public class RacunToRacunDTO implements Converter<Racun, RacunDTO> {

	@Override
	public RacunDTO convert(Racun racun) {

		RacunDTO dto = new RacunDTO();
		dto.setId(racun.getId());
		dto.setImeIPrezime(racun.getImeIPrezime());
		dto.setJMBG(racun.getJMBG());
		dto.setBrojRacuna(racun.getBrojRacuna());
		dto.setStanjeNaRacunu(racun.getStanjeNaRacunu());
		dto.setTipRacunaId(racun.getTipRacuna().getId());
		dto.setTipRacunaNaziv(racun.getTipRacuna().getNaziv());
		dto.setBankaId(racun.getBanka().getId());
		dto.setBankaNaziv(racun.getBanka().getNaziv());

		return dto;
	}

	public List<RacunDTO> convert(List<Racun> racuni) {
		List<RacunDTO> ret = new ArrayList<>();

		for (Racun racun : racuni) {
			ret.add(convert(racun));
		}

		return ret;
	}

}

package jwd.banka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.banka.model.TipRacuna;
import jwd.banka.web.dto.TipRacunaDTO;

@Component
public class TipRacunaToTipRacunaDTO implements Converter<TipRacuna, TipRacunaDTO> {

	@Override
	public TipRacunaDTO convert(TipRacuna tipRacuna) {

		TipRacunaDTO dto = new TipRacunaDTO();
		dto.setId(tipRacuna.getId());
		dto.setNaziv(tipRacuna.getNaziv());
		dto.setProvizija(tipRacuna.getProvizija());
		dto.setBankaId(tipRacuna.getBanka().getId());
		dto.setBankaNaziv(tipRacuna.getBanka().getNaziv());

		return dto;
	}

	public List<TipRacunaDTO> convert(List<TipRacuna> tipoviRacuna) {
		List<TipRacunaDTO> ret = new ArrayList<>();

		for (TipRacuna tipRacuna : tipoviRacuna) {
			ret.add(convert(tipRacuna));
		}

		return ret;
	}

}

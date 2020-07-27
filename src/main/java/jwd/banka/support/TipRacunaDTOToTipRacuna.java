package jwd.banka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.banka.model.TipRacuna;
import jwd.banka.model.Banka;
import jwd.banka.service.TipRacunaService;
import jwd.banka.service.BankaService;
import jwd.banka.web.dto.TipRacunaDTO;

@Component
public class TipRacunaDTOToTipRacuna implements Converter<TipRacunaDTO, TipRacuna> {

	@Autowired
	private TipRacunaService tipRacunaService;

	@Autowired
	private BankaService bankaService;

	@Override
	public TipRacuna convert(TipRacunaDTO dto) {

		Banka banka = bankaService.findOne(dto.getBankaId());

		if (banka != null) {

			TipRacuna tipRacuna = null;

			if (dto.getId() != null) {
				tipRacuna = tipRacunaService.findOne(dto.getId());
			} else {
				tipRacuna = new TipRacuna();
			}

			tipRacuna.setId(dto.getId());
			tipRacuna.setNaziv(dto.getNaziv());
			tipRacuna.setProvizija(dto.getProvizija());
			tipRacuna.setBanka(banka);

			return tipRacuna;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<TipRacuna> convert(List<TipRacunaDTO> tipRacunaDTOs) {
		List<TipRacuna> ret = new ArrayList<>();

		for (TipRacunaDTO tipRacunaDTO : tipRacunaDTOs) {
			ret.add(convert(tipRacunaDTO));
		}

		return ret;
	}
}

package jwd.banka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.banka.model.Banka;
import jwd.banka.service.BankaService;
import jwd.banka.web.dto.BankaDTO;

@Component
public class BankaDTOToBanka implements Converter<BankaDTO, Banka> {

	@Autowired
	BankaService bankaService;

	@Override
	public Banka convert(BankaDTO dto) {
		Banka banka = null;

		if (dto.getId() != null) {
			banka = bankaService.findOne(dto.getId());

			if (banka == null) {
				throw new IllegalStateException("Tried to " + "modify a non-existant banka");
			}
		} else {
			banka = new Banka();
		}

		banka.setId(dto.getId());
		banka.setNaziv(dto.getNaziv());
		banka.setSredstvaBanke(dto.getSredstvaBanke());

		return banka;
	}

	public List<Banka> convert(List<BankaDTO> dtoBanke) {
		List<Banka> banke = new ArrayList<>();

		for (BankaDTO dto : dtoBanke) {
			banke.add(convert(dto));
		}

		return banke;
	}

}

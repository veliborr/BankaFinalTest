package jwd.banka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.banka.model.Banka;
import jwd.banka.web.dto.BankaDTO;

@Component
public class BankaToBankaDTO implements Converter<Banka, BankaDTO> {

	@Override
	public BankaDTO convert(Banka banka) {
		if (banka == null) {
			return null;
		}

		BankaDTO dto = new BankaDTO();

		dto.setId(banka.getId());
		dto.setNaziv(banka.getNaziv());
		dto.setSredstvaBanke(banka.getSredstvaBanke());

		return dto;
	}

	public List<BankaDTO> convert(List<Banka> banke) {
		List<BankaDTO> ret = new ArrayList<>();

		for (Banka b : banke) {
			ret.add(convert(b));
		}

		return ret;
	}

}

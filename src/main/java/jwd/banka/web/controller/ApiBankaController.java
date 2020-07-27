package jwd.banka.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.banka.model.Banka;
import jwd.banka.model.TipRacuna;
import jwd.banka.service.BankaService;
import jwd.banka.support.BankaToBankaDTO;
import jwd.banka.support.TipRacunaToTipRacunaDTO;
import jwd.banka.web.dto.BankaDTO;
import jwd.banka.web.dto.TipRacunaDTO;

@RestController
@RequestMapping(value = "/api/banke")
public class ApiBankaController {
	@Autowired
	private BankaService bankaService;

	@Autowired
	private BankaToBankaDTO toDTO;


	@Autowired
	private TipRacunaToTipRacunaDTO toTipRacuna;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BankaDTO>> getBanke() {
		List<Banka> banke = bankaService.findAll();
		
		if (banke == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(banke), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/tipovi-racuna")
	public ResponseEntity<List<TipRacunaDTO>> getTipoviRacuna(@PathVariable Long id) {

		Banka banka = bankaService.findOne(id);

		if (banka == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {

			List<TipRacuna> tipoviRacuna = banka.getTipoviRacuna();

			return new ResponseEntity<>(toTipRacuna.convert(tipoviRacuna), HttpStatus.OK);
		}

	}

}

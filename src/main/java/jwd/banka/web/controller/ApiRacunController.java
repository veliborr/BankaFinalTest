package jwd.banka.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.banka.model.Racun;
import jwd.banka.service.RacunService;
import jwd.banka.support.RacunDTOToRacun;
import jwd.banka.support.RacunToRacunDTO;
import jwd.banka.web.dto.PrenosDTO;
import jwd.banka.web.dto.RacunDTO;

@RestController
@RequestMapping(value = "/api/racuni")
public class ApiRacunController {
	@Autowired
	private RacunService racunService;

	@Autowired
	private RacunToRacunDTO toDTO;

	@Autowired
	private RacunDTOToRacun toRacun;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<RacunDTO>> getRacuni(@RequestParam(required = false) Long bankaId,
			@RequestParam(required = false) String jmbg,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {

		Page<Racun> racuniPage = null;

		if (bankaId != null || jmbg != null) {
			racuniPage = racunService.search(bankaId, jmbg, pageNum);
		} else {
			racuniPage = racunService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(racuniPage.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(racuniPage.getContent()), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<RacunDTO> getRacun(@PathVariable Long id) {
		Racun racun = racunService.findOne(id);
		if (racun == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(racun), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<RacunDTO> delete(@PathVariable Long id) {

		Racun toDelete = racunService.findOne(id);
		if (toDelete == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (toDelete.getStanjeNaRacunu() != 0) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		Racun deleted = racunService.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RacunDTO> add(@Validated @RequestBody RacunDTO newRacunDTO) {

		Racun savedRacun = racunService.save(toRacun.convert(newRacunDTO));

		return new ResponseEntity<>(toDTO.convert(savedRacun), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<RacunDTO> edit(@Validated @RequestBody RacunDTO racunDTO, @PathVariable Long id) {

		if (!id.equals(racunDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Racun persisted = racunService.save(toRacun.convert(racunDTO));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/prenos")
	public ResponseEntity<Void> prenosiSredstva(@RequestBody PrenosDTO prenosDTO) {

		Racun racunSa = racunService.findByBrojRacuna(prenosDTO.getSaRacun());
		Racun racunNa = racunService.findByBrojRacuna(prenosDTO.getNaRacun());
		Double iznos = prenosDTO.getIznos();

		if (racunSa != null && racunNa != null && iznos != null) {
			racunService.prenosSredstava(racunSa, racunNa, iznos);
			return new ResponseEntity<>( HttpStatus.OK);
		}

		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}

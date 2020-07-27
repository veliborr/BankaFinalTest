package jwd.banka.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.banka.model.TipRacuna;
import jwd.banka.service.TipRacunaService;
import jwd.banka.support.TipRacunaToTipRacunaDTO;
import jwd.banka.web.dto.TipRacunaDTO;

@RestController
@RequestMapping(value = "/api/tipoviracuna")
public class ApiTipRacunaController {
	@Autowired
	private TipRacunaService tipRacunaService;

	@Autowired
	private TipRacunaToTipRacunaDTO toDTO;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<TipRacunaDTO>> getTipoviRacuna(

			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {

		Page<TipRacuna> tipoviRacuna = null;

		tipoviRacuna = tipRacunaService.findAll(pageNum);

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(tipoviRacuna.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(tipoviRacuna.getContent()), headers, HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}

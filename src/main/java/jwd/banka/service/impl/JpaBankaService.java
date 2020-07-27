package jwd.banka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.banka.model.Banka;
import jwd.banka.repository.BankaRepository;
import jwd.banka.service.BankaService;

@Service
public class JpaBankaService implements BankaService {

	@Autowired
	private BankaRepository bankaRepository;

	@Override
	public Banka findOne(Long id) {
		return bankaRepository.findOne(id);
	}

	@Override
	public List<Banka> findAll() {
		return bankaRepository.findAll();
	}

	@Override
	public Banka save(Banka banka) {
		return bankaRepository.save(banka);
	}

	
}

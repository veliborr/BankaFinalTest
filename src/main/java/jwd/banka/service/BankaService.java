package jwd.banka.service;

import java.util.List;

import jwd.banka.model.Banka;

public interface BankaService {

	Banka findOne(Long id);

	List<Banka> findAll();

	Banka save(Banka banka);

}

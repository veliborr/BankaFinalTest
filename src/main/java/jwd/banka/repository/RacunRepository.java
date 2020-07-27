package jwd.banka.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.banka.model.Racun;

@Repository
public interface RacunRepository extends JpaRepository<Racun, Long> {

	@Query("SELECT r FROM Racun r WHERE " + 
	"(:bankaId IS NULL OR r.banka.id = :bankaId) AND " 
	+ "(:jmbg IS NULL or r.JMBG like :jmbg)")
	Page<Racun> search(

			@Param("bankaId") Long bankaId,

			@Param("jmbg") String jmbg,

			Pageable pageRequest);

	Racun findByBrojRacuna(String brojRacuna);

	

}

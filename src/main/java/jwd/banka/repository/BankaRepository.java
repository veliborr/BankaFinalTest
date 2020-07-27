package jwd.banka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.banka.model.Banka;

@Repository
public interface BankaRepository extends JpaRepository<Banka, Long> {


}

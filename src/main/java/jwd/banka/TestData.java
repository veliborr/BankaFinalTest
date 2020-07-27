package jwd.banka;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.banka.model.Banka;
import jwd.banka.model.Racun;
import jwd.banka.model.TipRacuna;
import jwd.banka.service.BankaService;
import jwd.banka.service.RacunService;
import jwd.banka.service.TipRacunaService;

@Component
public class TestData {

	@Autowired
	private BankaService bankaService;

	@Autowired
	private TipRacunaService tipRacunaService;

	@Autowired
	private RacunService racunService;

	@PostConstruct
	public void init() {

		Banka b1 = bankaService.save(new Banka("Mobi Banka", 5000000.00));
		Banka b2 = bankaService.save(new Banka("Banka Intesa", 7000000.00));

		TipRacuna tr1 = tipRacunaService.save(new TipRacuna("standard", 0.05, b1));
		TipRacuna tr2 = tipRacunaService.save(new TipRacuna("premium", 0.03, b2));

		b1.addTipRacuna(tr1);
		b2.addTipRacuna(tr2);

		bankaService.save(b1);
		bankaService.save(b2);

		Racun r1 = new Racun("Marko Markovic", "0412986870004", "908 – 11501 – 07", 30000.00);
		r1.setBanka(b1);
		r1.setTipRacuna(tr1);
		racunService.save(r1);

		Racun r2 = new Racun("Tamara Tamic", "1506986870004", "908 – 11502 – 07", 22000.00);
		r2.setBanka(b1);
		r2.setTipRacuna(tr1);
		racunService.save(r2);

		Racun r3 = new Racun("Ivana Ivanovic", "0203986870004", "908 – 11503 – 07", 33300.00);
		r3.setBanka(b1);
		r3.setTipRacuna(tr1);
		racunService.save(r3);

		Racun r4 = new Racun("Milos Jankov", "1912986870004", "908 – 16001 – 87", 30444.00);
		r4.setBanka(b2);
		r4.setTipRacuna(tr2);
		racunService.save(r4);

		Racun r5 = new Racun("Jelena Janic", "1410986870004", "908 – 16002 – 87", 55000.00);
		r5.setBanka(b2);
		r5.setTipRacuna(tr2);
		racunService.save(r5);

		Racun r6 = new Racun("Velibor Rajin", "1010986870004", "908 – 16003 – 87", 0.00);
		r6.setBanka(b2);
		r6.setTipRacuna(tr2);
		racunService.save(r6);
		

	}
}

package jwd.banka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Racun {
	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(nullable = false)
	private String imeIPrezime;

	@Column
	private String JMBG;

	@Column
	private String brojRacuna;

	@Column
	private Double stanjeNaRacunu;

	@ManyToOne(fetch = FetchType.EAGER)
	private TipRacuna tipRacuna;

	@ManyToOne(fetch = FetchType.EAGER)
	private Banka banka;

	public Racun() {
		super();
	}

	public Racun(String imeIPrezime, String JMBG, String brojRacuna, Double stanjeNaRacunu) {
		super();
		this.imeIPrezime = imeIPrezime;
		this.JMBG = JMBG;
		this.brojRacuna = brojRacuna;
		this.stanjeNaRacunu = stanjeNaRacunu;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImeIPrezime() {
		return imeIPrezime;
	}

	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String JMBG) {
		this.JMBG = JMBG;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public Double getStanjeNaRacunu() {
		return stanjeNaRacunu;
	}

	public void setStanjeNaRacunu(Double stanjeNaRacunu) {
		this.stanjeNaRacunu = stanjeNaRacunu;
	}

	public TipRacuna getTipRacuna() {
		return tipRacuna;
	}

	public void setTipRacuna(TipRacuna tipRacuna) {
		this.tipRacuna = tipRacuna;
		if (!tipRacuna.getRacuni().contains(this)) {
			tipRacuna.getRacuni().add(this);
		}
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
		if (!banka.getRacuni().contains(this)) {
			banka.getRacuni().add(this);
		}
	}

}

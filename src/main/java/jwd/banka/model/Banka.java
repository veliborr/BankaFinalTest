package jwd.banka.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Banka {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(nullable = false, unique = true)
	private String naziv;

	@Column
	private Double sredstvaBanke;

	@OneToMany(mappedBy = "banka", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TipRacuna> tipoviRacuna = new ArrayList<>();

	@OneToMany(mappedBy = "banka", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Racun> racuni = new ArrayList<>();

	public Banka() {
		super();
	}

	public Banka(String naziv, Double sredstvaBanke) {
		super();
		this.naziv = naziv;
		this.sredstvaBanke = sredstvaBanke;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getSredstvaBanke() {
		return sredstvaBanke;
	}

	public void setSredstvaBanke(Double sredstvaBanke) {
		this.sredstvaBanke = sredstvaBanke;
	}

	public List<TipRacuna> getTipoviRacuna() {
		return tipoviRacuna;
	}

	public void setTipoviRacuna(List<TipRacuna> tipoviRacuna) {
		this.tipoviRacuna = tipoviRacuna;
	}

	public List<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(List<Racun> racuni) {
		this.racuni = racuni;
	}

	public void addTipRacuna(TipRacuna tipRacuna) {
		this.tipoviRacuna.add(tipRacuna);
		if (!this.equals(tipRacuna.getBanka())) {
			tipRacuna.setBanka(this);
		}
	}

	public void addRacun(Racun racun) {
		this.racuni.add(racun);
		if (!this.equals(racun.getBanka())) {
			racun.setBanka(this);
		}
	}

}

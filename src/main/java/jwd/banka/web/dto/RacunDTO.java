package jwd.banka.web.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class RacunDTO {

	private Long id;

	private String imeIPrezime;
	
	@Size(min = 13, max = 13)
	private String JMBG;
	
	@NotBlank
	private String brojRacuna;

	private Double stanjeNaRacunu;

	private Long tipRacunaId;
	private String tipRacunaNaziv;

	private Long bankaId;
	private String bankaNaziv;

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

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
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

	public Long getTipRacunaId() {
		return tipRacunaId;
	}

	public void setTipRacunaId(Long tipRacunaId) {
		this.tipRacunaId = tipRacunaId;
	}

	public String getTipRacunaNaziv() {
		return tipRacunaNaziv;
	}

	public void setTipRacunaNaziv(String tipRacunaNaziv) {
		this.tipRacunaNaziv = tipRacunaNaziv;
	}

	public Long getBankaId() {
		return bankaId;
	}

	public void setBankaId(Long bankaId) {
		this.bankaId = bankaId;
	}

	public String getBankaNaziv() {
		return bankaNaziv;
	}

	public void setBankaNaziv(String bankaNaziv) {
		this.bankaNaziv = bankaNaziv;
	}

}

package jwd.banka.web.dto;

public class BankaDTO {

	private Long id;

	private String naziv;

	private Double sredstvaBanke;

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

}

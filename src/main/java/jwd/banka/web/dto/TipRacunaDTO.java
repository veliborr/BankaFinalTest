package jwd.banka.web.dto;

public class TipRacunaDTO {

	private Long id;

	private String naziv;

	private Double provizija;

	private Long bankaId;

	private String bankaNaziv;

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

	public Double getProvizija() {
		return provizija;
	}

	public void setProvizija(Double provizija) {
		this.provizija = provizija;
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

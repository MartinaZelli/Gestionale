package model;

public class Ruolo {

	private int idRuolo;
	private String Descrizione;

	public int getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(int idRuolo) {
		this.idRuolo = idRuolo;
	}

	public String getDescrizione() {
		return Descrizione;
	}

	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Ruolo{" + "idRuolo=" + idRuolo + ", Descrizione='" + Descrizione + '\'' + '}' + '\n';
	}
}

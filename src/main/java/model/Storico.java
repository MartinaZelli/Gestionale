package model;

public class Storico {

	private int idStorico;
	private int matricola;
	private int idRuolo;
	private int annoInizio;
	private int annoFine;
	
	

	public Storico(int idStorico, int matricola, int idRuolo) {
		super();
		this.idStorico = idStorico;
		this.matricola = matricola;
		this.idRuolo = idRuolo;
	}

	public int getIdStorico() {
		return idStorico;
	}

	public void setIdStorico(int idStorico) {
		this.idStorico = idStorico;
	}

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public int getAnnoInizio() {
		return annoInizio;
	}

	public void setAnnoInizio(int dataInizio) {
		this.annoInizio = dataInizio;
	}

	public int getAnnoFine() {
		return annoFine;
	}

	public int getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(int idRuolo) {
		this.idRuolo = idRuolo;
	}

	public void setAnnoFine(int dataFine) {
		this.annoFine = dataFine;
	}

	@Override
	public String toString() {
		return "Storico{" + "idStorico=" + idStorico + ", matricola=" + matricola + ", idRuolo=" + idRuolo
				+ ", annoInizio=" + annoInizio + ", annoFine=" + annoFine + '}' + '\n';
	}
}

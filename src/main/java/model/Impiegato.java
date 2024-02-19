package model;

public class Impiegato {
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private int matricola;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	@Override
	public String toString() {
		return "Impiegato{" + "nome='" + nome + '\'' + ", cognome='" + cognome + '\'' + ", codiceFiscale='"
				+ codiceFiscale + '\'' + ", matricola=" + matricola + '\n' + '}';
	}
}

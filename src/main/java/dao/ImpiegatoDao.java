package dao;

import java.sql.SQLException;
import java.util.List;

import model.Impiegato;

public interface ImpiegatoDao {

	public Impiegato ricercaPerMatricola(int matricola) throws SQLException;

	public void inserisciImpiegato(Impiegato impiegatoDaAggiungere) throws SQLException;

	public boolean aggiornaImpiegato(Impiegato impiegatoDaAggiornare) throws SQLException;

	public boolean eliminaImpiegato(String codiceFiscaleDaEliminare) throws SQLException;
	public List<Impiegato> ricercaTuttiImpiegati ()throws SQLException;

	public static void main(String[] args) {
		System.out.println("a");
	}

}

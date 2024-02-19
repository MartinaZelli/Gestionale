package dao;

import java.sql.SQLException;
import java.util.List;

import model.Storico;

public interface StoricoDao {

	public Storico ricercaPerIdStorico(int storicoID) throws SQLException;

	public Storico inserisciStorico(Storico storicoDaAggiungere) throws SQLException;

	public boolean aggiornaStorico(Storico storicoDaAggiornare) throws SQLException;

	public boolean eliminaImpiegato(int idStoricoDaEliminare) throws SQLException;
	public List<Storico> listaStorico()throws SQLException;
}

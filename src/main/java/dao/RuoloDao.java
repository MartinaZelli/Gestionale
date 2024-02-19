package dao;

import java.sql.SQLException;
import java.util.List;

import model.Ruolo;

public interface RuoloDao {

	public Ruolo ricercaPerIdRuolo(int ruoloID) throws SQLException;

	public List<Ruolo> ricercaLista() throws SQLException;

	public Ruolo inserisciRuolo(Ruolo ruoloDaAggiungere) throws SQLException;

	public boolean aggiornaRuolo(Ruolo ruoloDaAggiornare) throws SQLException;

	public boolean eliminaRuolo(int idRuolooDaEliminare) throws SQLException;

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcConnection;
import model.Ruolo;

public class RuoloDaoImpl implements RuoloDao {

	private ResultSet ricercaInDatabaseIdRuolo(int ruoloID) throws SQLException {
		Connection connessione = JdbcConnection.connection();
		String queryRicerca = "SELECT *\n" + "FROM ruolo\n" + "WHERE id_ruolo_id = " + ruoloID
				+ " ;";
		Statement comando = connessione.createStatement();
		return comando.executeQuery(queryRicerca);
	}

	public Ruolo ricercaPerIdRuolo(int ruoloID) throws SQLException {
		ResultSet risultatoDiRicerca = ricercaInDatabaseIdRuolo(ruoloID);
		if (!risultatoDiRicerca.next()) {
			return null;
		}
		Ruolo ruoloTrovato = new Ruolo();
		ruoloTrovato.setDescrizione(risultatoDiRicerca.getString("descrizione"));
		ruoloTrovato.setIdRuolo(risultatoDiRicerca.getInt("id_ruolo_id"));
		return ruoloTrovato;
	}

	public Ruolo inserisciRuolo(Ruolo ruoloDaAggiungere) throws SQLException {
		ResultSet risultatoDiRicerca = ricercaInDatabaseIdRuolo(ruoloDaAggiungere.getIdRuolo());
		if (risultatoDiRicerca.next()) {
			return null; // Il ruolo esiste già nel database
		}

		Integer idRuoloCorretto = ruoloDaAggiungere.getIdRuolo();
		// if (ruoloDaAggiungere.getIdRuolo() == 0) {
		// idRuoloCorretto = null;
		// }

		String queryDiAggiunta = "INSERT INTO ruolo(id_ruolo_id, descrizione)\r\n"+" VALUES (?, ?)";
		Connection connessione = JdbcConnection.connection();
		PreparedStatement comando = connessione.prepareStatement(queryDiAggiunta,
				Statement.RETURN_GENERATED_KEYS);
		comando.setInt(1, idRuoloCorretto);
		comando.setString(2, ruoloDaAggiungere.getDescrizione());
		Ruolo ruoloInserito = new Ruolo();
		int righeAggiunte = comando.executeUpdate();
		if (righeAggiunte == 1) {
			ResultSet risultatoAggiunta = comando.getGeneratedKeys();
			if (risultatoAggiunta.next()) {
				ruoloInserito.setIdRuolo(risultatoAggiunta.getInt(1));
				ruoloInserito.setDescrizione(ruoloDaAggiungere.getDescrizione());
				System.out.println("ruolo inserito ruolo dao impl: "+ruoloInserito);
			}
			return ruoloInserito;
		} else
			return null;
	}

	public boolean aggiornaRuolo(Ruolo ruoloDaAggiornare) throws SQLException {
		if (ruoloDaAggiornare.getIdRuolo() == 0) {
			return false;
		}
		ResultSet cercaRuolo = ricercaInDatabaseIdRuolo(ruoloDaAggiornare.getIdRuolo());
		if (!cercaRuolo.next()) {
			return false;
		}
		Connection connessione = JdbcConnection.connection();
		String queryDiUpdate = "UPDATE ruolo\n" + "SET descrizione = \""
				+ ruoloDaAggiornare.getDescrizione() + "\"\n" + "WHERE id_ruolo_id = "
				+ ruoloDaAggiornare.getIdRuolo() + ";";
		Statement update = connessione.createStatement();
		update.executeUpdate(queryDiUpdate);
		return true;
	}

	public boolean eliminaRuolo(int idRuolooDaEliminare) throws SQLException {
		if (!ricercaInDatabaseIdRuolo(idRuolooDaEliminare).next()) {
			return false;
		}
		Connection connection = JdbcConnection.connection();
		Statement eliminaCondannato = connection.createStatement();
		String terminazione = "DELETE FROM ruolo\n" + "WHERE id_ruolo_id = " + idRuolooDaEliminare
				+ ";";
		eliminaCondannato.executeUpdate(terminazione);
		return true;
	}

	@Override
	public List<Ruolo> ricercaLista() throws SQLException {
		List<Ruolo> lista = new ArrayList<>();
		Connection connection = JdbcConnection.connection();
		String query = "SELECT *\n" + "FROM ruolo;";
		PreparedStatement comando = connection.prepareStatement(query);
		ResultSet risultato = comando.executeQuery();
		boolean risultatoTrovato = risultato.next();
		while (risultatoTrovato) {
			Ruolo ruolo = new Ruolo();
			ruolo.setDescrizione(risultato.getString("descrizione"));
			ruolo.setIdRuolo(risultato.getInt("id_ruolo_id"));
			lista.add(ruolo);
			risultatoTrovato = risultato.next();

		}
		return lista;
	}

	
	  public static void main(String[] args) throws SQLException { RuoloDao
	  sticazzi = new RuoloDaoImpl(); Ruolo a = new Ruolo(); 
	  //a.setIdRuolo(10);
	  a.setDescrizione("contafrancobolli");
	  sticazzi.eliminaRuolo(10);
	  //System.out.println(sticazzi.eliminaRuolo(1));
	  System.out.println(sticazzi.inserisciRuolo(a));
	  
	  }
	 
}

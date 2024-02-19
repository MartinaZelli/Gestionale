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
import model.Storico;

public class StoricoDaoImpl implements StoricoDao {

	private ResultSet ricercainDatabaseIdStorico(Integer storicoId) throws SQLException {
		Connection connessione = JdbcConnection.connection();
		String ricercaIdStorico = "SELECT *\n" + "FROM storico\n" + "WHERE id_storico_id = ?;";
		PreparedStatement comando = connessione.prepareStatement(ricercaIdStorico);
		comando.setInt(1, storicoId);
		comando.executeQuery();
		return comando.executeQuery();
	}

	public Storico ricercaPerIdStorico(int storicoID) throws SQLException {
		ResultSet risultatoDatabase = ricercainDatabaseIdStorico(storicoID);
		if (!risultatoDatabase.next()) {
			return null;
		}
		Storico risultatoDiRicerca = new Storico(storicoID, risultatoDatabase.getInt("matricola"),risultatoDatabase.getInt("ruolo_id"));
		risultatoDiRicerca.setAnnoInizio(risultatoDatabase.getInt("data_inizio"));
		risultatoDiRicerca.setAnnoFine(risultatoDatabase.getInt("data_fine"));
		
		return risultatoDiRicerca;
	}

	public Storico inserisciStorico(Storico storicoDaAggiungere) throws SQLException {
		if (ricercainDatabaseIdStorico(storicoDaAggiungere.getIdStorico()).next()) {
			return null;
		}
		Connection connessione = JdbcConnection.connection();
		Integer idStorico = storicoDaAggiungere.getIdStorico();
		if (storicoDaAggiungere.getIdStorico() == 0) {
			idStorico = null;
		}
		;
		String queryDiAggiunta = "INSERT INTO storico(id_storico_id, matricola, DATA_inizio, Data_fine, ruolo_id)\n"
				+ "VALUES (?,?,?,?,?);";
		PreparedStatement comando = connessione.prepareStatement(queryDiAggiunta, Statement.RETURN_GENERATED_KEYS);
		comando.setInt(1, storicoDaAggiungere.getIdStorico());
		comando.setInt(2, storicoDaAggiungere.getMatricola());
		comando.setInt(3, storicoDaAggiungere.getAnnoInizio());
		comando.setInt(4, storicoDaAggiungere.getAnnoFine());
		comando.setInt(5, storicoDaAggiungere.getIdRuolo());
		Storico storicoInserito = new Storico(0,0,0);
		int righeAggiunte = comando.executeUpdate();
		if(righeAggiunte ==1) {
			ResultSet risultatoAggiunta = comando.getGeneratedKeys();
			if(risultatoAggiunta.next()) {
				storicoInserito.setIdStorico(risultatoAggiunta.getInt(1));
				storicoInserito.setMatricola(storicoDaAggiungere.getMatricola());
				storicoInserito.setAnnoInizio(storicoDaAggiungere.getAnnoInizio());
				storicoInserito.setAnnoFine(storicoDaAggiungere.getAnnoFine());
				storicoInserito.setIdRuolo(storicoDaAggiungere.getIdRuolo());
				System.out.println("ruolo inserito ruolo dao impl: "+storicoInserito);
			}
			return storicoInserito;
		}
		return null;	
	}

	/*public boolean aggiornaStorico(Storico storicoDaAggiornare) throws SQLException {
		ResultSet risultatoDiricerca = ricercainDatabaseIdStorico(storicoDaAggiornare.getIdStorico());
		if (!risultatoDiricerca.next()) {
			return false;
		}
		Connection connessione = JdbcConnection.connection();
		Statement comando = connessione.createStatement();
		String aggiornamento = "UPDATE storico\n" + "SET(\n" + "matricola = " + storicoDaAggiornare.getMatricola()
				+ ",\n" + "DATA_inizio = \"" + storicoDaAggiornare.getAnnoInizio() + "\",\n" + "Data_fine = \" "
				+ storicoDaAggiornare.getAnnoFine() + "\",\n" + "ruolo_id = " + storicoDaAggiornare.getIdRuolo() + ")\n"
				+ "WHERE id_storico_id = " + storicoDaAggiornare.getIdStorico() + ";\n";
		comando.executeUpdate(aggiornamento);
		return true;
		// todo: controlla chiavi esterne che potrebbero dare errori
	}*/
	
	public boolean aggiornaStorico(Storico storicoDaAggiornare) throws SQLException {
		ResultSet risultatoDiricerca = ricercainDatabaseIdStorico(storicoDaAggiornare.getIdStorico());
		if (!risultatoDiricerca.next()) {
			return false;
		}
		Connection connessione = JdbcConnection.connection();
		String aggiornamento = 
				"UPDATE storico\r\n"
				+ "SET matricola = ?, Data_inizio = ?,Data_fine = ?,ruolo_id = ?\r\n"
				+ "WHERE id_storico_id = ? \r\n"
				+ ";";
		PreparedStatement comando = connessione.prepareStatement(aggiornamento);
		comando.setInt(1, storicoDaAggiornare.getMatricola());
		comando.setInt(2, storicoDaAggiornare.getAnnoInizio());
		comando.setInt(3, storicoDaAggiornare.getAnnoFine());
		comando.setInt(4, storicoDaAggiornare.getIdRuolo());
		comando.setInt(5, storicoDaAggiornare.getIdStorico());
		comando.executeUpdate();
		return true;
		// todo: controlla chiavi esterne che potrebbero dare errori
	}

	public boolean eliminaImpiegato(int idStoricoDaEliminare) throws SQLException {
		ResultSet risultatoDiRicerca = ricercainDatabaseIdStorico(idStoricoDaEliminare);
		if (!risultatoDiRicerca.next()) {
			return false;
		}
		Connection connessione = JdbcConnection.connection();
		Statement comando = connessione.createStatement();
		String queryDiTerminazione = "DELETE FROM storico\n" + "WHERE \n" + "id_storico_id = " + idStoricoDaEliminare
				+ ";\n";
		comando.executeUpdate(queryDiTerminazione);
		return true;
	}
	
	@Override
	public List<Storico> listaStorico() throws SQLException {
		List<Storico> lista = new ArrayList<>();
		Connection connection = JdbcConnection.connection();
		String query = "SELECT *\n" + "FROM storico;";
		PreparedStatement comando = connection.prepareStatement(query);
		ResultSet risultato = comando.executeQuery();
		boolean risultatoTrovato = risultato.next();
		while(risultatoTrovato) {
			Storico storico= new Storico(0,0,0);
			storico.setAnnoFine(risultato.getInt("data_fine"));
			storico.setAnnoInizio(risultato.getInt("data_inizio"));
			storico.setIdRuolo(risultato.getInt("ruolo_id"));
			storico.setIdStorico(risultato.getInt("id_storico_id"));
			storico.setMatricola(risultato.getInt("matricola"));
			lista.add(storico);
			risultatoTrovato= risultato.next();
		}
		return lista;
	}
	public static void main(String[] args) throws SQLException {
    	StoricoDao storicoDao = new StoricoDaoImpl();
    	Storico storico = new Storico(6,2,32);
    	storico.setAnnoFine(1995);
    	storico.setAnnoInizio(1994);
    	storicoDao.aggiornaStorico(storico);
    	
    	}
}

    

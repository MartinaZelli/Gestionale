package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcConnection;
import model.Impiegato;

public class ImpiegatoDaoImpl implements ImpiegatoDao {

	public Impiegato ricercaPerMatricola(int matricola) throws SQLException {
		String query = "SELECT *\n" + "FROM impiegato\n" + "WHERE id_matricola=\"" + matricola + "\"\n" + ";";

		Connection connection = JdbcConnection.connection();
		
		PreparedStatement comando = connection.prepareStatement(query);

		ResultSet risultato = comando.executeQuery();

		boolean risultatoTrovato = risultato.next();

		if (risultatoTrovato) {

			Impiegato impiegatoTrovato = new Impiegato();
			String codiceFiscaleTrovato = risultato.getString("codice_fiscale");
			String nomeTrovato = risultato.getString("nome");
			String cognomeTrovato = risultato.getString("cognome");
			impiegatoTrovato.setCognome(cognomeTrovato);
			impiegatoTrovato.setCodiceFiscale(codiceFiscaleTrovato);
			impiegatoTrovato.setNome(nomeTrovato);
			impiegatoTrovato.setMatricola(matricola);

			return impiegatoTrovato;
		}
		return null;
	}

	public void inserisciImpiegato(Impiegato impiegatoDaAggiungere) throws SQLException {
		Connection connection = JdbcConnection.connection();

		if (impiegatoDaAggiungere.getMatricola() == 0) {
			String query = "INSERT INTO impiegato\n" + "VALUES (" + null + "," + "\""
					+ impiegatoDaAggiungere.getCodiceFiscale() + "\"" + "," + "\"" + impiegatoDaAggiungere.getNome()
					+ "\"" + "," + "\"" + impiegatoDaAggiungere.getCognome() + "\")";

			Statement comando = connection.createStatement();
			comando.executeUpdate(query);
			return;
		}
		String querydiverifica = "SELECT *\n" + "FROM impiegato\n" + "WHERE id_matricola="
				+ impiegatoDaAggiungere.getMatricola() + "\n" + ";";

		PreparedStatement comandoDiVerifica = connection.prepareStatement(querydiverifica);

		ResultSet risultatoDiVerifica = comandoDiVerifica.executeQuery();
		boolean risultatodiControllo = risultatoDiVerifica.next();
		if (risultatodiControllo) {
			return;
		}

		String queryConMatricola = "INSERT INTO impiegato\n" + "VALUES (" + impiegatoDaAggiungere.getMatricola() + ","
				+ "\"" + impiegatoDaAggiungere.getCodiceFiscale() + "\"" + "," + "\"" + impiegatoDaAggiungere.getNome()
				+ "\"" + "," + "\"" + impiegatoDaAggiungere.getCognome() + "\")";

		Statement comando = connection.createStatement();
		comando.executeUpdate(queryConMatricola);
	}

	private boolean comandoRicercaDaMatricola(int matricolaDaCercare) throws SQLException {
		Connection connection = JdbcConnection.connection();
		String query = "SELECT *\n" + "FROM impiegato\n" + "WHERE id_matricola=" + matricolaDaCercare + "\n" +

				";";
		PreparedStatement comandoDiVerifica = connection.prepareStatement(query);
		ResultSet risultatoDiVerifica = comandoDiVerifica.executeQuery();
		return risultatoDiVerifica.next();
	}

	private boolean comandoRicercaDaCodiceFiscale(String codiceFiscale) throws SQLException {
		Connection connection = JdbcConnection.connection();
		String query = "SELECT *\n" + "FROM impiegato\n" + "WHERE codice_fiscale=\"" + codiceFiscale + "\"\n" + ";";
		PreparedStatement comandoDiVerificaCodiceFiscale = connection.prepareStatement(query);
		ResultSet risultatoDiVerificaCodiceFiscale = comandoDiVerificaCodiceFiscale.executeQuery();
		return risultatoDiVerificaCodiceFiscale.next();
	}

	public boolean aggiornaImpiegato(Impiegato impiegatoDaAggiornare) throws SQLException {
		Connection connection = JdbcConnection.connection();
		// se impiegato da aggiornare ha una matricola
		if (impiegatoDaAggiornare.getMatricola() != 0) {
			// se la matricola è presente nel database
			if (!comandoRicercaDaMatricola(impiegatoDaAggiornare.getMatricola())) {
				return false;
			}
			String queryConMatricola = "UPDATE impiegato\n" + "SET\n " + "codice_fiscale = \""
					+ impiegatoDaAggiornare.getCodiceFiscale() + "\"," + "nome = \"" + impiegatoDaAggiornare.getNome()
					+ "\"," + "cognome = \"" + impiegatoDaAggiornare.getCognome() + "\"" + "WHERE  id_matricola ="
					+ impiegatoDaAggiornare.getMatricola();

			Statement comando = connection.createStatement();
			comando.executeUpdate(queryConMatricola);
			return true;
		}
		// se l'impiegato non ha matricola allora si passa al cod. fiscale:
		if (impiegatoDaAggiornare.getCodiceFiscale() != null) {
			if (!comandoRicercaDaCodiceFiscale(impiegatoDaAggiornare.getCodiceFiscale())) {
				return false;
			}
			String querydiricercaCodiceFiscale = "SELECT *\n" + "FROM impiegato\n" + "WHERE codice_fiscale=\""
					+ impiegatoDaAggiornare.getCodiceFiscale() + "\"\n" + ";";
			PreparedStatement comandoDiVerificaCodiceFiscale = connection.prepareStatement(querydiricercaCodiceFiscale);
			ResultSet risultatoDiVerificaCodiceFiscale = comandoDiVerificaCodiceFiscale.executeQuery();
			boolean risultatoDiControlloCodiceFiscale = risultatoDiVerificaCodiceFiscale.next();
			while (risultatoDiControlloCodiceFiscale) {
				String queryConCodiceFiscale = "UPDATE impiegato\n" + "SET\n " + "nome = \""
						+ impiegatoDaAggiornare.getNome() + "\"," + "cognome = \"" + impiegatoDaAggiornare.getCognome()
						+ "\"" + "WHERE  codice_fiscale =\"" + impiegatoDaAggiornare.getCodiceFiscale() + "\"";

				Statement comando = connection.createStatement();
				comando.executeUpdate(queryConCodiceFiscale);
				risultatoDiControlloCodiceFiscale = risultatoDiVerificaCodiceFiscale.next();
			}
			return true;
		}
		return false;
	}

	public boolean eliminaImpiegato(String codiceFiscaleDaEliminare) throws SQLException {
		if (!comandoRicercaDaCodiceFiscale(codiceFiscaleDaEliminare)) {
			return false;
		}
		Connection connection = JdbcConnection.connection();
		Statement comando = connection.createStatement();
		String queryDiEliminazione = "DELETE FROM impiegato\n" + "WHERE codice_fiscale = \"" + codiceFiscaleDaEliminare
				+ "\"";
		comando.executeUpdate(queryDiEliminazione);
		return true;
	}

	@Override
	public List<Impiegato> ricercaTuttiImpiegati() throws SQLException {
		List<Impiegato> lista = new ArrayList<>();
		Connection connection = JdbcConnection.connection();
		String query = "SELECT *\n" + "FROM impiegato;";
		PreparedStatement comando = connection.prepareStatement(query);
		ResultSet risultato = comando.executeQuery();
		boolean risultatoTrovato = risultato.next();
		while(risultatoTrovato) {
			Impiegato impiegato = new Impiegato();
			impiegato.setCodiceFiscale(risultato.getString("codice_fiscale"));
			impiegato.setCognome(risultato.getString("cognome"));
			impiegato.setNome(risultato.getString("nome"));
			impiegato.setMatricola(risultato.getInt("id_matricola"));
			lista.add(impiegato);
			risultatoTrovato = risultato.next();
			
		}
		return lista;
	}

	/*public static void main(String[] args) throws SQLException {
		ImpiegatoDao sticazzi = new ImpiegatoDaoImpl();
		// System.out.println(sticazzi.ricercaPerCodiceFiscale("qwerty"));
		//Impiegato a = new Impiegato();
		// a.setMatricola(2);
		//a.setCognome("oro");
		//a.setNome("all");
		//a.setCodiceFiscale("kkk789");
		// sticazzi.inserisciImpiegato(a);
		System.out.println(sticazzi.ricercaTuttiImpiegati());
		// sticazzi.aggiornaImpiegato(a);
		// sticazzi.eliminaImpiegato("kkk789");
		

	}*/
}

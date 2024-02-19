package ctr;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Impiegato;
import dao.ImpiegatoDao;
import dao.ImpiegatoDaoImpl;

/**
 * Servlet implementation class ImpiegatoSrv
 */
public class ImpiegatoSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImpiegatoSrv() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImpiegatoDao impiegatoDao = new ImpiegatoDaoImpl();
		String linkTo= "/ImpiegatoJsp/ImpiegatoHome.jsp";
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String codiceFiscale = request.getParameter("codiceFiscale");
		String matricola = request.getParameter("matricola");
		if (matricola == null || Objects.equals("", matricola)) {
			matricola = "0";
		}
		;
		int matricolaInt = Integer.parseInt(matricola);
		Impiegato impiegato = new Impiegato();
		impiegato.setNome(nome);
		impiegato.setCognome(cognome);
		impiegato.setCodiceFiscale(codiceFiscale);
		impiegato.setMatricola(matricolaInt);

		String operazione = request.getParameter("operazione");
		if (operazione == null) {
			System.out.println("operazione non avviata");
		}
		if (Objects.equals("inserimento", operazione)) {
			try {
				impiegatoDao.inserisciImpiegato(impiegato);
				System.out.println("inserito" + impiegato);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (Objects.equals("eliminazione", operazione)) {
			try {
				impiegatoDao.eliminaImpiegato(codiceFiscale);
				System.out.println("eliminato l'impiegato con codice fiscale: " + codiceFiscale);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (Objects.equals("aggiornamento", operazione)) {
			try {
				impiegatoDao.aggiornaImpiegato(impiegato);
				System.out.println("aggiornato" + impiegato);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (Objects.equals("ricerca", operazione)) {
			Impiegato impiegatoTrovato = new Impiegato();
			try {
				impiegatoTrovato = impiegatoDao.ricercaPerMatricola(matricolaInt);
				System.out.println("trovato" + impiegatoTrovato);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("impiegato", impiegatoTrovato);
			System.out.println("Sono in fondo a ricerca impiegato");
		}
		if (Objects.equals("lista", operazione)) {
			System.out.println("Sono in lista");
			List<Impiegato> lista = new ArrayList<>();
			;
			try {
				lista = impiegatoDao.ricercaTuttiImpiegati();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.getSession().setAttribute("listaImpiegati", lista);
			System.out.println("Sono in 2° step lista");
		}
		if(operazione != null) {
			linkTo = "/ImpiegatoJsp/ListaImpiegati.jsp";
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(linkTo);
		dispatcher.forward(request, response);
		// TODO Auto-generated method stub
		System.out.println("Sono in fondo senza aver fatto nulla");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

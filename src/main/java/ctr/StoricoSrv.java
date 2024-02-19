package ctr;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dao.*;

/**
 * Servlet implementation class StoricoSrv
 */
public class StoricoSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StoricoSrv() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String linkTo = null;
		StoricoDao storicoDao = new StoricoDaoImpl();
		String idStorico = request.getParameter("idStorico");
		String matricola = request.getParameter("matricola");
		String idRuolo = request.getParameter("idRuolo");
		String annoInizio = request.getParameter("annoInizio");
		String annoFine = request.getParameter("annoFine");

		// da String a int
		if (idStorico == null) {
			idStorico = "0";
		}
		if (matricola == null) {
			matricola = "0";
		}
		if (idRuolo == null) {
			idRuolo = "0";
		}
		if (annoInizio == null) {
			annoInizio = "0";
		}
		if (annoFine == null) {
			annoFine = "0";
		}

		int idStoricoInt = Integer.parseInt(idStorico);
		int matricolaInt = Integer.parseInt(matricola);
		int idRuoloInt = Integer.parseInt(idRuolo);
		int annoInizioInt = Integer.parseInt(annoInizio);
		int annoFineInt = Integer.parseInt(annoFine);

		Storico storico = new Storico(idStoricoInt, matricolaInt, idRuoloInt);
		storico.setAnnoInizio(annoInizioInt);
		storico.setAnnoFine(annoFineInt);
		boolean risultato = false;

		String operazione = request.getParameter("operazione");
		
		if (operazione == null) {
			RuoloDao ruoloDao = new RuoloDaoImpl();
			ImpiegatoDao impiegatoDao = new ImpiegatoDaoImpl();
			
			List<Ruolo> listaRuoli = new ArrayList<>();
			try {
				listaRuoli = ruoloDao.ricercaLista();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Impiegato> listaImpiegati = new ArrayList<>();
			try {
				listaImpiegati = impiegatoDao.ricercaTuttiImpiegati();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("listaRuoli", listaRuoli);
			request.getSession().setAttribute("listaImpiegati", listaImpiegati);
			linkTo = "/StoricoJsp/StoricoHome.jsp";
		}
		
		
		
		if (Objects.equals("inserimento", operazione)) {
			linkTo = "/StoricoJsp/StoricoAggiunta.jsp";
			Storico storicoInserito = new Storico(0,0,0);
			try {
				storicoInserito = storicoDao.inserisciStorico(storico);
				System.out.println("inserito" + storico);
				risultato = true;
				if (storicoInserito == null) {
					risultato = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("storicoInserito", storicoInserito);
		}
		if (Objects.equals("eliminazione", operazione)) {
			linkTo = "/StoricoJsp/StoricoElimina.jsp";
			try {
				risultato = storicoDao.eliminaImpiegato(idStoricoInt);
				System.out.println("eliminato lo storico con id: " + idStorico);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (Objects.equals("aggiornamento", operazione)) {
			linkTo = "/StoricoJsp/StoricoAggiorna.jsp";
			try {
				risultato = storicoDao.aggiornaStorico(storico);
				System.out.println("aggiornato" + storico);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (Objects.equals("ricerca", operazione)) {
			linkTo = "/StoricoJsp/StoricoRicerca.jsp";
			Storico storicoRicerca = new Storico(0, 0, 0);
			try {
				risultato = false;
				storicoRicerca = storicoDao.ricercaPerIdStorico(idStoricoInt);
				System.out.println("trovato" + storicoRicerca);
				if (storicoRicerca != null) {
					risultato = true;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(risultato);
			request.getSession().setAttribute("storicoRicerca", storicoRicerca);
		}
		if (Objects.equals("lista", operazione)) {
			linkTo = "/StoricoJsp/StoricoLista.jsp";
			List<Storico> lista = new ArrayList<>();
			try {
				lista = storicoDao.listaStorico();
				System.out.println("sono in Lista");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("listaStorici", lista);
		}
		request.getSession().setAttribute("risultato", risultato);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(linkTo);
		dispatcher.forward(request, response);
	System.out.println("Sono dopo dispatcher");
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

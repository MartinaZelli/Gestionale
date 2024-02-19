package ctr;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ruolo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dao.RuoloDao;
import dao.RuoloDaoImpl;

/**
 * Servlet implementation class RuoloSrv
 */
public class RuoloSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RuoloSrv() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String linkTo = "/RuoloJsp/RuoloHome.jsp";
		RuoloDao ruoloDao = new RuoloDaoImpl();
		String descrizione = request.getParameter("descrizione");
		String idRuolo = request.getParameter("idRuolo");
		if (idRuolo == null || Objects.equals("", idRuolo)) {
			idRuolo = "0";
		}
		;
		int idRuoloInt = Integer.parseInt(idRuolo);
		Ruolo ruolo = new Ruolo();
		ruolo.setDescrizione(descrizione);
		ruolo.setIdRuolo(idRuoloInt);
		boolean risultato = false;

		String operazione = request.getParameter("operazione");
		if (operazione != null) {
			System.out.println("operazione non avviata");
			linkTo = "/RuoloJsp/RuoloRisposte.jsp";
		}

		if (Objects.equals("inserimento", operazione)) {
			Ruolo ruoloInserito = new Ruolo();
			try {
				ruoloInserito = ruoloDao.inserisciRuolo(ruolo);
				risultato = true;
				if (ruoloInserito== null) {
					risultato = false;
				}
				System.out.println("inserito: " + ruoloInserito);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("ruoloInserito", ruoloInserito);
		}
		if (Objects.equals("eliminazione", operazione)) {
			try {
				risultato = ruoloDao.eliminaRuolo(idRuoloInt);
				System.out.println("eliminato il ruolo con id: " + idRuoloInt);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (Objects.equals("aggiornamento", operazione)) {
			try {
				risultato = ruoloDao.aggiornaRuolo(ruolo);
				System.out.println("aggiornato" + ruolo);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (Objects.equals("ricerca", operazione)) {
			Ruolo ruoloRicerca = new Ruolo();
			try {
				ruoloRicerca = ruoloDao.ricercaPerIdRuolo(idRuoloInt);
				if (ruoloRicerca != null) {
					risultato = true;
				}
				System.out.println("trovato" + ruoloRicerca);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("ruoloRicerca", ruoloRicerca);
		}

		if (Objects.equals("lista", operazione)) {
			System.out.println("Sono in lista");
			List<Ruolo> lista = new ArrayList<>();
			;
			try {
				lista = ruoloDao.ricercaLista();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.getSession().setAttribute("listaRuoli", lista);
			System.out.println("Sono in 2° step lista");
		}
		request.getSession().setAttribute("risultato", risultato);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(linkTo);
		dispatcher.forward(request, response);
		// TODO Auto-generated method stub
		System.out.println("Sono in fondo");
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

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="ctr.ImpiegatoSrv"%>
<%@page import="ctr.RuoloSrv"%>
<%@page import="model.Impiegato"%>
<%@page import="model.Ruolo"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Impiegato</title>
</head>
<body>
<a href="/Gestionale/Home.jsp">torna alla Home delle home</a><br/>
	<h2>Storico Home</h2>

	<form action="/Gestionale/StoricoSrv" method="get">
		<label for="operazione">Seleziona un'azione:</label> <select
			id="operazione" name="operazione">
			<option value="inserimento">Inserisci</option>
			<option value="eliminazione">Elimina</option>
			<option value="aggiornamento">Aggiorna</option>
			<option value="ricerca">Ricerca</option>
			<option value="lista" selected>Vedi lista</option>
		</select> <br/>
		<label>Anno Inizio:</label> 
		<input type="number" name="annoInizio" value="0"/><br/>
		<label>Anno Fine:</label> 
		<input type="number" name="annoFine" value="0"/><br/>
		 <label>idStorico:</label>
		<input type="number" name="idStorico" value="0"/><br/>
		 <label>impiegato:</label> 
		 <select
			id="matricola" name="matricola">
			<c:forEach var="impiegato" items="${sessionScope.listaImpiegati}">
				<option value=${impiegato.matricola}>${impiegato.nome} ${impiegato.cognome}</option><br/>
			</c:forEach>
		</select> <br/>
		<label>idRuolo:</label> 
		<select id="idRuolo" name="idRuolo">
			<c:forEach var="ruolo" items="${sessionScope.listaRuoli}">
				<option value=${ruolo.idRuolo}>${ruolo.descrizione}</option><br/>
			</c:forEach>
		</select> 
		<br> 
		<input type="submit" value="Invia">
	</form>

</body>
</html>

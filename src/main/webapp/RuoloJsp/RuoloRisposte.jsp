<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.Ruolo"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ruoli</title>
</head>
<body>
	<a href="/Gestionale/RuoloJsp/RuoloHome.jsp">torna alla Home Ruolo</a>
	<br />
	<c:if test="${param.operazione == 'lista'}">
		<table border="1" width="100%">
			<c:forEach var="ruolo" items="${sessionScope.listaRuoli}">
				<tr>
					<td>${ruolo.idRuolo}</td>
					<td>${ruolo.descrizione}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if
		test="${(param.operazione == 'aggiornamento' 
		|| param.operazione =='eliminazione') 
		&& sessionScope.risultato == true}">
        L'operazione eseguita è: ${param.operazione}<br />
        il Ruolo è: <br />
        idRuolo: ${param.idRuolo}<br />
        descrizione: ${param.descrizione}<br />
	</c:if>
	<c:if test="${param.operazione == 'ricerca' && sessionScope.risultato == true}">
        L'operazione eseguita è: ${param.operazione}<br />
        il Ruolo è: <br />
        idRuolo: ${ruoloRicerca.idRuolo}<br />
        descrizione: ${ruoloRicerca.descrizione}<br />
	</c:if>
	<c:if
		test="${param.operazione == 'inserimento' && sessionScope.risultato == true}">
        L'operazione eseguita è: ${param.operazione}<br />
        il Ruolo è:  <br />
        idRuolo: ${ruoloInserito.idRuolo}<br />
        descrizione: ${ruoloInserito.descrizione}<br />
	</c:if>
	<c:if test="${param.operazione != 'lista' && sessionScope.risultato == false}">
        L'operazione richiesta non è stata eseguita<br/>
    </c:if>
</body>
</html>

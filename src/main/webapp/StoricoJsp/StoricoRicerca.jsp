<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Storico"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ricerca per Id Storico</title>
</head>
<body>
    <a href="/Gestionale/StoricoSrv">torna alla Home Storico</a><br/>
    <c:if test="${risultato == false }">
        Non è stato possibile trovare nessuno Storico con tale Id.
    </c:if>
     <c:if test="${risultato == true}">
        L'operazione eseguita è: ${param.operazione}<br/>
        lo Storico è: <br/>
        IdStorico: ${storicoRicerca.idStorico}<br/>
        Matricola: ${storicoRicerca.matricola}<br/>
        Id Ruolo: ${storicoRicerca.idRuolo}<br/>
        Anno Inizio / Anno Fine: ${storicoRicerca.annoInizio}/${storicoRicerca.annoFine}<br/>
    </c:if>
</body>
</html>

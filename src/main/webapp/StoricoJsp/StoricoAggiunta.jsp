<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Storico"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiunta Storico</title>
</head>
<body>
    <a href="/Gestionale/StoricoSrv">torna alla Home Storico</a><br/>
    <c:if test="${risultato == false}">
    Non è stato possibile inserire lo storico richiesto, riprova, stronzo.
    </c:if>
     <c:if test="${risultato == true}">
        L'operazione eseguita è: ${param.operazione}<br/>
       	Lo Storico è: <br/>
        IdStorico: ${storicoInserito.idStorico}<br/>
        Matricola: ${storicoInserito.matricola}<br/>
        Id Ruolo: ${storicoInserito.idRuolo}<br/>
        Anno Inizio / Anno Fine: ${storicoInserito.annoInizio}/${storicoInserito.annoFine}<br/>
    </c:if>
</body>
</html>

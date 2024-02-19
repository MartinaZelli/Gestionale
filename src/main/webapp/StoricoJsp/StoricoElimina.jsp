<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista Impiegati</title>
</head>
<body>
    <a href="/Gestionale/StoricoSrv">torna alla Home Storico</a><br/>
<c:if test="${risultato == false }">
        Non è stato trovato nessuno storico da eliminare
    </c:if>
     <c:if test="${risultato == true}">
        Lo storico richiesto è stato eliminato.        
    </c:if>
</body>
</html>

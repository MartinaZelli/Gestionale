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

     <c:if test="${risultato == false}">
        L'aggiornamento non è stato eseguito; <br/>
        mettere un id Storico già presente sul database, coglione.<br/>
    </c:if>
    <c:if test="${risultato == true}">
        L'aggiornamento richiesto è stato eseguito.
    </c:if>
</body>
</html>

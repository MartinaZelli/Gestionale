<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Storico"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista Impiegati</title>
</head>
<body>
    <a href="/Gestionale/StoricoSrv">torna alla Home Storico</a><br/>
        <table border="1" width="100%">
        <tr><th>Id Storico</th>
        <th>Matricola</th>
        <th>Id Ruolo</th>
        <th>Anno Inizio</th>
        <th>Anno Fine</th></tr>
            <c:forEach var="storico" items="${sessionScope.listaStorici}">
                <tr>
                    <td>${storico.idStorico}</td>
                    <td>${storico.matricola}</td>
                    <td>${storico.idRuolo}</td>
                    <td>${storico.annoInizio}</td>
                    <td>${storico.annoFine}</td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>

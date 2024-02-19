<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Impiegato"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista Impiegati</title>
</head>
<body>
    <a href="/Gestionale/ImpiegatoJsp/ImpiegatoHome.jsp">torna alla Home Impiegato</a><br/>
    <c:if test="${param.operazione == 'lista'}">
        <table border="1" width="100%">
            <c:forEach var="impiegato" items="${sessionScope.listaImpiegati}">
                <tr>
                    <td>${impiegato.matricola}</td>
                    <td>${impiegato.codiceFiscale}</td>
                    <td>${impiegato.nome}</td>
                    <td>${impiegato.cognome}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${param.operazione != 'lista' && param.operazione !='ricerca'}">
        L'operazione eseguita è: ${param.operazione}<br/>
        L'impiegato è: <br/>
        Nome: ${param.nome}<br/>
        Cognome: ${param.cognome}<br/>
        Codice Fiscale: ${param.codiceFiscale}<br/>
        Matricola: ${param.matricola}<br/>
    </c:if>
     <c:if test="${param.operazione == 'ricerca'}">
        L'operazione eseguita è: ${param.operazione}<br/>
        L'impiegato è: <br/>
        Nome: ${impiegato.nome}<br/>
        Cognome: ${impiegato.cognome}<br/>
        Codice Fiscale: ${impiegato.codiceFiscale}<br/>
        Matricola: ${impiegato.matricola}<br/>
    </c:if>
</body>
</html>

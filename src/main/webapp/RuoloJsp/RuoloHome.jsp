<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Impiegato</title>
</head>
<body>

<a href="/Gestionale/Home.jsp">torna alla Home delle home</a><br/>

<h2>Ruolo Home</h2>

<form action="/Gestionale/RuoloSrv" method="get">
  <label for="operazione">Seleziona un'azione:</label>
  <select id="operazione" name="operazione">
    <option value="inserimento">Inserisci</option>
    <option value="eliminazione">Elimina</option>
    <option value="aggiornamento">Aggiorna</option>
    <option value="ricerca">Ricerca</option>
    <option value="lista" selected>Vedi lista</option>
  </select>
  <label>Id Ruolo:</label>
  <input type= "text" name="idRuolo"/>
  <label>descrizione:</label>
  <input type= "text" name="descrizione"/>
  <br><br>
  <input type="submit" value="Invia">
</form>

</body>
</html>
    
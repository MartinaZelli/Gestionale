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
<h2>Impiegato Home</h2>



<form action="/Gestionale/ImpiegatoSrv" method="get">
  <label for="operazione">Seleziona un'azione:</label>
  <select id="operazione" name="operazione">
    <option value="inserimento">Inserisci</option>
    <option value="eliminazione">Elimina</option>
    <option value="aggiornamento">Aggiorna</option>
    <option value="ricerca">Ricerca</option>
    <option value="lista" selected>Vedi lista</option>
  </select>
  <label>nome:</label>
  <input type= "text" name="nome"/>
  <label>cognome:</label>
  <input type= "text" name="cognome"/>
  <label>codice fiscale:</label>
  <input type= "text" name="codiceFiscale"/>
  <label>matricola:</label>
  <input type= "number" name="matricola"/>
  <br><br>
  <input type="submit" value="Invia">
</form>

</body>
</html>
    
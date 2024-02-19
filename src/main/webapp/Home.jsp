<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Gestionale</title>
</head>
<body>

<h2>HOME DELLE HOME</h2>
<br/>Quale database desideri modificare?<br/>

 <form>
        <select id="comboBox" onchange="redirect()">
            <option value="">Seleziona un link</option>
            <option value="http://localhost:8080/Gestionale/StoricoSrv">storico</option>
            <option value="http://localhost:8080/Gestionale/RuoloSrv">ruolo</option>
            <option value="http://localhost:8080/Gestionale/ImpiegatoSrv">impiegato</option>
        </select>
  
</form>
<script>
        function redirect() {
            var selectedLink = document.getElementById("comboBox").value;
            if (selectedLink) {
                window.location.href = selectedLink;
            }
        }
    </script>
</body>
</html>
    
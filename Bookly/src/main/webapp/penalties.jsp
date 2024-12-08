<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.Penalty" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Penalty Status</title>
</head>
<body>
    <h1>Client Penalty Status</h1>

    <form action="penaltyServlet" method="post">
        <label for="clientId">Client ID:</label>
        <input type="text" id="clientId" name="clientId" required>
        <button type="submit">Check Penalty</button>
    </form>

    <% 
        // Assuming 'penalty' is passed as a request attribute
        Penalty penalty = (Penalty) request.getAttribute("penalty");
        if (penalty != null) {
    %>
        <p>Client ID: <%= penalty.getClientId() %></p>
        <p>Late Count: <%= penalty.getLateCount() %></p>
        <p>Penalty Status: <%= penalty.isPenalty() ? "Applied" : "No Penalty" %></p>
    <% 
        }
    %>

    <a href="index.jsp">Back to Home</a>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Location" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Locations</title>
</head>
<body>
    <h1>Available Locations</h1>

    <table border="1">
        <thead>
            <tr>
                <th>City</th>
                <th>Address</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Assuming 'locations' is a List<Location> object passed to the JSP
                List<Location> locations = (List<Location>) request.getAttribute("locations");
                for (Location location : locations) {
            %>
                <tr>
                    <td><%= location.getCity() %></td>
                    <td><%= location.getAddress() %></td>
                </tr>
            <% 
                }
            %>
        </tbody>
    </table>

    <a href="index.jsp">Back to Home</a>
</body>
</html>

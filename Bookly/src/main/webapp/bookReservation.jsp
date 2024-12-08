<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %> <!-- Assuming you have a Book model -->
<%@ page import="model.Location" %> <!-- Assuming you have a Location model -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reserve a Book</title>
</head>
<body>
    <h1>Reserve a Book</h1>

    <form action="bookServlet" method="post">
        <label for="bookId">Select Book:</label>
        <select id="bookId" name="bookId">
            <% 
                // Assuming 'books' is a List<Book> object passed to the JSP
                List<Book> books = (List<Book>) request.getAttribute("books");
                for (Book book : books) {
            %>
                <option value="<%= book.getId() %>"><%= book.getTitle() %> by <%= book.getAuthor() %></option>
            <% 
                }
            %>
        </select>

        <label for="locationId">Select Location:</label>
        <select id="locationId" name="locationId">
            <% 
                // Assuming 'locations' is a List<Location> object passed to the JSP
                List<Location> locations = (List<Location>) request.getAttribute("locations");
                for (Location location : locations) {
            %>
                <option value="<%= location.getId() %>"><%= location.getCity() %></option>
            <% 
                }
            %>
        </select>

        <button type="submit">Reserve</button>
    </form>

    <a href="index.jsp">Back to Home</a>
</body>
</html>

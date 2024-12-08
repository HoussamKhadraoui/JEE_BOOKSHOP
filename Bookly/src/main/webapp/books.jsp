<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book List</title>
</head>
<body>
    <h1>Available Books</h1>

    <table border="1">
        <thead>
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Assuming 'books' is a List<Book> object passed to the JSP
                List<Book> books = (List<Book>) request.getAttribute("books");
                for (Book book : books) {
            %>
                <tr>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td><%= book.getPrice() %></td>
                    
                    <td>
                        <form action="bookServlet" method="post">
                            <input type="hidden" name="bookId" value="<%= book.getId() %>">
                            <button type="submit">Reserve</button>
                        </form>
                    </td>
                </tr>
            <% 
                }
            %>
        </tbody>
    </table>

    <a href="index.jsp">Back to Home</a>
</body>
</html>

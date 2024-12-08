package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import dao.BookDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Book;

public class bookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO = new BookDAO();

    // Use java.util.logging.Logger for Java 8 compatibility
    private static final Logger logger = Logger.getLogger(bookServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Book> books = bookDAO.getAllBooks();
            request.setAttribute("books", books);
            RequestDispatcher dispatcher = request.getRequestDispatcher("bookList.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            // Log the exception using Level.SEVERE (appropriate for Java 8)
            logger.log(Level.SEVERE, "An error occurred while fetching books.", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching books.");
        }
    }
}



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            Book book = bookDAO.getBookById(bookId);
            request.setAttribute("book", book);
            RequestDispatcher dispatcher = request.getRequestDispatcher("bookDetails.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching the book details.");
        }
    }
}

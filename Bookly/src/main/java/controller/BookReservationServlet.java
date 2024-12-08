package controller;

import dao.BookReservationDAO;
import model.BookReservation;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookReservationServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookReservationDAO bookReservationDAO = new BookReservationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int clientId = Integer.parseInt(request.getParameter("clientId"));
            List<BookReservation> reservations = bookReservationDAO.getReservationsByClientId(clientId);
            request.setAttribute("reservations", reservations);
            RequestDispatcher dispatcher = request.getRequestDispatcher("bookReservations.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching book reservations.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int clientId = Integer.parseInt(request.getParameter("clientId"));
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            java.util.Date reservationDate = new java.util.Date();
            java.util.Date dueDate = new java.util.Date(reservationDate.getTime() + (7 * 24 * 60 * 60 * 1000)); // 7 days later

            BookReservation reservation = new BookReservation();
            reservation.setClientId(clientId);
            reservation.setBookId(bookId);
            reservation.setReservationDate(reservationDate);
            reservation.setDueDate(dueDate);
            reservation.setReturned(false);

            bookReservationDAO.addBookReservation(reservation);

            request.setAttribute("message", "Book reservation added successfully!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("bookReservationConfirmation.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the book reservation.");
        }
    }
}

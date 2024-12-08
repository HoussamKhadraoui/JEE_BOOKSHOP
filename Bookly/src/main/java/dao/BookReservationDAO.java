package dao;

import model.BookReservation;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookReservationDAO {

    public List<BookReservation> getReservationsByClientId(int clientId) throws SQLException {
        List<BookReservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM book_reservation WHERE client_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BookReservation reservation = new BookReservation();
                    reservation.setClientId(rs.getInt("client_id"));
                    reservation.setBookId(rs.getInt("book_id"));
                    reservation.setReservationDate(rs.getDate("reservation_date"));
                    reservation.setDueDate(rs.getDate("due_date"));
                    reservation.setReturned(rs.getBoolean("is_returned"));
                    reservations.add(reservation);
                }
            }
        }
        return reservations;
    }

    public void addBookReservation(BookReservation reservation) throws SQLException {
        String query = "INSERT INTO book_reservation (client_id, book_id, reservation_date, due_date, is_returned) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, reservation.getClientId());
            ps.setInt(2, reservation.getBookId());
            ps.setDate(3, new java.sql.Date(reservation.getReservationDate().getTime()));
            ps.setDate(4, new java.sql.Date(reservation.getDueDate().getTime()));
            ps.setBoolean(5, reservation.isReturned());
            ps.executeUpdate();
        }
    }

    public void updateBookReservationStatus(int clientId, int bookId, boolean isReturned) throws SQLException {
        String query = "UPDATE book_reservation SET is_returned = ? WHERE client_id = ? AND book_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setBoolean(1, isReturned);
            ps.setInt(2, clientId);
            ps.setInt(3, bookId);
            ps.executeUpdate();
        }
    }
}

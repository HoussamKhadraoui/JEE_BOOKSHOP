package dao;

import model.Stock;
import utils.DatabaseConnection;

import java.sql.*;

public class StockDAO {

    public Stock getStockByBookTitle(String bookTitle) throws SQLException {
        Stock stock = null;
        String query = "SELECT * FROM stock WHERE book_title = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, bookTitle);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    stock = new Stock();
                    stock.setId(rs.getInt("id"));
                    stock.setBookTitle(rs.getString("book_title"));
                    stock.setQuantity(rs.getInt("quantity"));
                }
            }
        }
        return stock;
    }

    public void updateStock(String bookTitle, int newQuantity) throws SQLException {
        String query = "UPDATE stock SET quantity = ? WHERE book_title = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, newQuantity);
            ps.setString(2, bookTitle);
            ps.executeUpdate();
        }
    }

    public void addStock(String bookTitle, int quantity) throws SQLException {
        String query = "INSERT INTO stock (book_title, quantity) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, bookTitle);
            ps.setInt(2, quantity);
            ps.executeUpdate();
        }
    }
}

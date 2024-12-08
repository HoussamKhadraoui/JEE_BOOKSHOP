package dao;

import model.Penalty;
import utils.DatabaseConnection;

import java.sql.*;

public class PenaltyDAO {

    public Penalty getPenaltyByClientId(int clientId) throws SQLException {
        Penalty penalty = null;
        String query = "SELECT * FROM penalty WHERE client_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    penalty = new Penalty();
                    penalty.setClientId(rs.getInt("client_id"));
                    penalty.setLateCount(rs.getInt("late_count"));
                    penalty.setPenalty(rs.getBoolean("penalty"));
                }
            }
        }
        return penalty;
    }

    public void updatePenalty(int clientId, int lateCount) throws SQLException {
        String query = "UPDATE penalty SET late_count = ?, penalty = ? WHERE client_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            boolean penaltyStatus = lateCount >= 2;
            ps.setInt(1, lateCount);
            ps.setBoolean(2, penaltyStatus);
            ps.setInt(3, clientId);
            ps.executeUpdate();
        }
    }

    public void addPenalty(int clientId, int lateCount) throws SQLException {
        String query = "INSERT INTO penalty (client_id, late_count, penalty) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            boolean penaltyStatus = lateCount >= 2;
            ps.setInt(1, clientId);
            ps.setInt(2, lateCount);
            ps.setBoolean(3, penaltyStatus);
            ps.executeUpdate();
        }
    }
}

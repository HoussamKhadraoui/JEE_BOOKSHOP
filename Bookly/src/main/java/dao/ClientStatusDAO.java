package dao;

import model.ClientStatus;
import utils.DatabaseConnection;

import java.sql.*;

public class ClientStatusDAO {

    public ClientStatus getClientStatus(int clientId) throws SQLException {
        ClientStatus clientStatus = null;
        String query = "SELECT * FROM client_status WHERE client_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    clientStatus = new ClientStatus();
                    clientStatus.setClientId(rs.getInt("client_id"));
                    clientStatus.setStatus(rs.getString("status"));
                }
            }
        }
        return clientStatus;
    }

    public void updateClientStatus(int clientId, String status) throws SQLException {
        String query = "UPDATE client_status SET status = ? WHERE client_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, clientId);
            ps.executeUpdate();
        }
    }

    public void addClientStatus(int clientId, String status) throws SQLException {
        String query = "INSERT INTO client_status (client_id, status) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, clientId);
            ps.setString(2, status);
            ps.executeUpdate();
        }
    }
}

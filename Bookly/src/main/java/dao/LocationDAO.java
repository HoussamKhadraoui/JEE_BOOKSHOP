package dao;

import model.Location;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {

    public List<Location> getAllLocations() throws SQLException {
        List<Location> locations = new ArrayList<>();
        String query = "SELECT * FROM location";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Location location = new Location();
                location.setId(rs.getInt("id"));
                location.setCity(rs.getString("city"));
                location.setAddress(rs.getString("address"));
                locations.add(location);
            }
        }
        return locations;
    }

    public Location getLocationById(int id) throws SQLException {
        Location location = null;
        String query = "SELECT * FROM location WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    location = new Location();
                    location.setId(rs.getInt("id"));
                    location.setCity(rs.getString("city"));
                    location.setAddress(rs.getString("address"));
                }
            }
        }
        return location;
    }
}

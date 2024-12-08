package controller;

import dao.LocationDAO;
import model.Location;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LocationServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocationDAO locationDAO = new LocationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Location> locations = locationDAO.getAllLocations();
            request.setAttribute("locations", locations);
            RequestDispatcher dispatcher = request.getRequestDispatcher("locationList.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching locations.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int locationId = Integer.parseInt(request.getParameter("locationId"));
            Location location = locationDAO.getLocationById(locationId);
            request.setAttribute("location", location);
            RequestDispatcher dispatcher = request.getRequestDispatcher("locationDetails.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching location details.");
        }
    }
}

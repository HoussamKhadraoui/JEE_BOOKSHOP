package controller;

import dao.PenaltyDAO;
import model.Penalty;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class PenaltyServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PenaltyDAO penaltyDAO = new PenaltyDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int clientId = Integer.parseInt(request.getParameter("clientId"));
            Penalty penalty = penaltyDAO.getPenaltyByClientId(clientId);
            if (penalty != null) {
                request.setAttribute("penalty", penalty);
                RequestDispatcher dispatcher = request.getRequestDispatcher("penaltyDetails.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Penalty details not found.");
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching penalty details.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int clientId = Integer.parseInt(request.getParameter("clientId"));
            int lateCount = Integer.parseInt(request.getParameter("lateCount"));

            Penalty penalty = penaltyDAO.getPenaltyByClientId(clientId);
            if (penalty != null) {
                // Update penalty
                penaltyDAO.updatePenalty(clientId, lateCount);
                request.setAttribute("message", "Penalty updated successfully!");
            } else {
                // Add new penalty
                penaltyDAO.addPenalty(clientId, lateCount);
                request.setAttribute("message", "Penalty added successfully!");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("penaltyUpdate.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating penalty details.");
        }
    }
}

package controller;

import dao.ClientStatusDAO;
import model.ClientStatus;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class ClientStatusServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClientStatusDAO clientStatusDAO = new ClientStatusDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int clientId = Integer.parseInt(request.getParameter("clientId"));
            ClientStatus clientStatus = clientStatusDAO.getClientStatus(clientId);
            if (clientStatus != null) {
                request.setAttribute("clientStatus", clientStatus);
                RequestDispatcher dispatcher = request.getRequestDispatcher("clientStatusDetails.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Client status not found.");
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching client status.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int clientId = Integer.parseInt(request.getParameter("clientId"));
            String status = request.getParameter("status");

            ClientStatus clientStatus = clientStatusDAO.getClientStatus(clientId);
            if (clientStatus != null) {
                // Update client status
                clientStatusDAO.updateClientStatus(clientId, status);
                request.setAttribute("message", "Client status updated successfully!");
            } else {
                // Add new client status
                clientStatusDAO.addClientStatus(clientId, status);
                request.setAttribute("message", "Client status added successfully!");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("clientStatusUpdate.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating client status.");
        }
    }
}

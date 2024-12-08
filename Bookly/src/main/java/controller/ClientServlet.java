package controller;

import dao.ClientDAO;
import model.Client;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ClientServlet extends HttpServlet {

    private ClientDAO clientDAO = new ClientDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch all clients from the database
            List<Client> clients = clientDAO.getAllClients();
            // Set the client list as a request attribute
            request.setAttribute("clients", clients);
            // Forward the request to the client JSP page for display
            RequestDispatcher dispatcher = request.getRequestDispatcher("clients.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle errors, e.g. when retrieving clients
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching client data.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get client data from the form
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            
            // Create a new client and save it to the database
            Client client = new Client();
            client.setName(name);
            client.setEmail(email);
            
            // Add the client to the database
            clientDAO.addClient(client);

            // Redirect to a success page or client listing page
            response.sendRedirect("clientList.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the client.");
        }
    }
}

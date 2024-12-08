	package controller;

import dao.StockDAO;
import model.Stock;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class StockServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StockDAO stockDAO = new StockDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String bookTitle = request.getParameter("bookTitle");
            Stock stock = stockDAO.getStockByBookTitle(bookTitle);
            if (stock != null) {
                request.setAttribute("stock", stock);
                RequestDispatcher dispatcher = request.getRequestDispatcher("stockDetails.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Stock record not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching stock details.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String bookTitle = request.getParameter("bookTitle");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Stock stock = stockDAO.getStockByBookTitle(bookTitle);
            
            if (stock != null) {
                // Update stock if it exists
                stockDAO.updateStock(bookTitle, quantity);
                request.setAttribute("message", "Stock updated successfully!");
            } else {
                // Add new stock if it doesn't exist
                stockDAO.addStock(bookTitle, quantity);
                request.setAttribute("message", "Stock added successfully!");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("stockUpdate.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating stock details.");
        }
    }
}

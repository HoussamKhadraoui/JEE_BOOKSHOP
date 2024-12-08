package model;

public class Stock {
    private int id;
    private String bookTitle;
    private int quantity;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to check if stock is available
    public boolean isStockAvailable() {
        return quantity > 0;
    }

    // Method to reduce stock by one
    public void reduceStock() {
        if (quantity > 0) {
            this.quantity--;
        }
    }
}

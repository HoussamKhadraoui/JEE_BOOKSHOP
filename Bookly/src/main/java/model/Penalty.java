package model;

public class Penalty {
    private int clientId;
    private int lateCount;
    private boolean penalty;

    // Getters and Setters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getLateCount() {
        return lateCount;
    }

    public void setLateCount(int lateCount) {
        this.lateCount = lateCount;
        checkPenalty();  // Check penalty whenever lateCount changes
    }

    public boolean isPenalty() {
        return penalty;
    }

    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }

    // Method to update penalty status based on lateCount
    private void checkPenalty() {
        if (lateCount >= 2) {  // Penalty is applied after 2 late returns
            this.penalty = true;
        } else {
            this.penalty = false;
        }
    }
}

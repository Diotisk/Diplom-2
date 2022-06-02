package praktikum.models;

import java.util.ArrayList;

public class GetUserOrdersResponse {

    private boolean success;
    private ArrayList<OrderDescription> orders;
    private int total;
    private int totalToday;
    private String message;

    public GetUserOrdersResponse(boolean success,
                                 ArrayList<OrderDescription> orders, int total,
                                 int totalToday) {
        this.success = success;
        this.orders = orders;
        this.total = total;
        this.totalToday = totalToday;
    }

    public GetUserOrdersResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<OrderDescription> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrderDescription> orders) {
        this.orders = orders;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalToday() {
        return totalToday;
    }

    public void setTotalToday(int totalToday) {
        this.totalToday = totalToday;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

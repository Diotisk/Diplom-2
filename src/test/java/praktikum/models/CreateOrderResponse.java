package praktikum.models;

public class CreateOrderResponse {

    private String name;
    private OrderNumber order;
    private boolean success;
    private String message;

    public CreateOrderResponse(String name, OrderNumber order, boolean success) {
        this.name = name;
        this.order = order;
        this.success = success;
    }

    public CreateOrderResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderNumber getOrder() {
        return order;
    }

    public void setOrder(OrderNumber order) {
        this.order = order;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

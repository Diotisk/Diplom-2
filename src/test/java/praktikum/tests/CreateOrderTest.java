package praktikum.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import praktikum.data.OrderOperations;
import praktikum.models.CreateOrderResponse;
import praktikum.models.OrderNumber;

import static org.junit.Assert.*;

public class CreateOrderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void createOrderPositiveTest() {

        OrderOperations operations = new OrderOperations();
        Response response = operations.createOrder();

        assertFalse(response.getBody().as(CreateOrderResponse.class).getName().isEmpty());
        assertTrue(response.getBody().as(CreateOrderResponse.class).isSuccess());
        assertNotEquals(0, response.getBody().as(CreateOrderResponse.class).getOrder().getNumber());

    }

    @Test
    public void createOrderWithInvalidIngredientsNegativeTest() {

        OrderOperations operations = new OrderOperations();
        Response response = operations.createOrderWithInvalidIngredients();

        assertEquals(500, response.getStatusCode());

    }

    @Test
    public void createOrderWithoutIngredientsNegativeTest() {

        OrderOperations operations = new OrderOperations();
        Response response = operations.createOrderWithoutIngredients();

        assertEquals(400, response.getStatusCode());
        assertFalse(response.getBody().as(CreateOrderResponse.class).isSuccess());
        assertEquals("Ingredient ids must be provided",
                response.getBody().as(CreateOrderResponse.class).getMessage());

    }

}

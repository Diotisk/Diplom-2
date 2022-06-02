package praktikum.data;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import praktikum.data.IngredientOperations;
import praktikum.models.CreateOrderRequest;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class OrderOperations {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    public Response createOrder() {

        IngredientOperations operations = new IngredientOperations();
        ArrayList<String> ingredients = operations.getSeveralIngredients();
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(ingredients);

        Response response = given()
                .headers("Content-Type", "application/json")
                .body(createOrderRequest)
                .post("/api/orders");

        return response;

    }

    public Response createOrderWithInvalidIngredients() {

        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add(RandomStringUtils.randomAlphabetic(3));
        ingredients.add(RandomStringUtils.randomAlphabetic(4));
        ingredients.add(RandomStringUtils.randomAlphabetic(5));
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(ingredients);

        Response response = given()
                .headers("Content-Type", "application/json")
                .body(createOrderRequest)
                .post("/api/orders");

        return response;

    }

    public Response createOrderWithoutIngredients() {

        ArrayList<String> ingredients = new ArrayList<>();

        CreateOrderRequest createOrderRequest = new CreateOrderRequest(ingredients);

        Response response = given()
                .headers("Content-Type", "application/json")
                .body(createOrderRequest)
                .post("/api/orders");

        return response;
    }

}

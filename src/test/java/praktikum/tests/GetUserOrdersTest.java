package praktikum.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import praktikum.data.OrderOperations;
import praktikum.data.UserOperations;
import praktikum.models.CreateOrLoginUserResponse;
import praktikum.models.GetUserOrdersResponse;
import praktikum.models.User;

import static org.junit.Assert.*;

public class GetUserOrdersTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void getUserOrdersPositiveTest() {

        UserOperations operations = new UserOperations();
        User newUser = operations.generateUserData();
        Response createUserResponse = operations.createUser(newUser);

        OrderOperations orderOperations = new OrderOperations();
        Response actualResponse = orderOperations.getUserOrders(createUserResponse.getBody()
                .as(CreateOrLoginUserResponse.class).getAccessToken());

        assertTrue(actualResponse.getBody().as(GetUserOrdersResponse.class).isSuccess());
        assertEquals(200, actualResponse.getStatusCode());

        operations.deleteUser(createUserResponse.getBody()
                .as(CreateOrLoginUserResponse.class).getAccessToken());

    }

    @Test
    public void getUserOrdersWithoutAuthorizationNegativeTest() {

        UserOperations operations = new UserOperations();
        User newUser = operations.generateUserData();
        Response createUserResponse = operations.createUser(newUser);

        OrderOperations orderOperations = new OrderOperations();
        Response actualResponse = orderOperations.getUserOrders("");

        assertFalse(actualResponse.getBody().as(GetUserOrdersResponse.class).isSuccess());
        assertEquals(401, actualResponse.getStatusCode());
        assertEquals("You should be authorised",
                actualResponse.getBody().as(GetUserOrdersResponse.class).getMessage());

        operations.deleteUser(createUserResponse.getBody()
                .as(CreateOrLoginUserResponse.class).getAccessToken());

    }

}

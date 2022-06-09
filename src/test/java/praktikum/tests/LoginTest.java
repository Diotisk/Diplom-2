package praktikum.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import praktikum.data.UserOperations;
import praktikum.models.CreateOrLoginUserResponse;
import praktikum.models.User;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void loginUserPositiveTest() {

        UserOperations operations = new UserOperations();
        User newUser = operations.generateUserData();
        Response createUserResponse = operations.createUser(newUser);

        Response actualResponse = operations.login(new User(newUser.getEmail(), newUser.getPassword()));

        assertEquals(200, actualResponse.getStatusCode());
        assertTrue(actualResponse.getBody().as(CreateOrLoginUserResponse.class).isSuccess());
        assertEquals(newUser.getName(), actualResponse.getBody().as(CreateOrLoginUserResponse.class)
                .getUser().getName());
        assertEquals(newUser.getEmail().toLowerCase(Locale.ROOT), actualResponse.getBody()
                .as(CreateOrLoginUserResponse.class)
                .getUser().getEmail());

        operations.deleteUser(createUserResponse.as(CreateOrLoginUserResponse.class).getAccessToken());

    }

}

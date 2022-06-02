package praktikum.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import praktikum.data.UserOperations;
import praktikum.models.CreateUserResponse;
import praktikum.models.User;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateUserTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void createNewUserPositiveTest() {

        UserOperations operationCreateUser = new UserOperations();
        User newUser = operationCreateUser.generateUserData();
        Response actualResponse = operationCreateUser.createUser(newUser);

        assertEquals(200, actualResponse.getStatusCode());
        assertTrue(actualResponse.getBody().as(CreateUserResponse.class).isSuccess());
        assertEquals(newUser.getName(), actualResponse.getBody().as(CreateUserResponse.class)
                .getUser().getName());
        assertEquals(newUser.getEmail().toLowerCase(Locale.ROOT), actualResponse.getBody()
                .as(CreateUserResponse.class)
                .getUser().getEmail());

        operationCreateUser.deleteUser(actualResponse.as(CreateUserResponse.class).getAccessToken());

    }

    @Test
    public void createNewUserWithExistingCredentialsNegativeTest() {

        UserOperations operationCreateUser = new UserOperations();
        User newUser = operationCreateUser.generateUserData();
        operationCreateUser.createUser(newUser);
        Response actualResponse = operationCreateUser.createUser(newUser);

        assertEquals(403, actualResponse.getStatusCode());
        assertEquals("User already exists", actualResponse.as(CreateUserResponse.class)
                .getMessage());

        operationCreateUser.deleteUser(actualResponse.as(CreateUserResponse.class).getAccessToken());

    }

}

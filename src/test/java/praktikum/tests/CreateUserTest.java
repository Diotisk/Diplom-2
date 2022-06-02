package praktikum.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import praktikum.data.UserOperations;
import praktikum.models.CreateUserResponse;
import praktikum.models.User;

import java.util.Locale;

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
        Response actualResponseAfterFirstAttempt = operationCreateUser.createUser(newUser);
        Response actualResponseAfterSecondAttempt = operationCreateUser.createUser(newUser);

        assertEquals(403, actualResponseAfterSecondAttempt.getStatusCode());
        assertEquals("User already exists", actualResponseAfterSecondAttempt.as(CreateUserResponse.class)
                .getMessage());

        operationCreateUser.deleteUser(actualResponseAfterFirstAttempt.as(CreateUserResponse.class).getAccessToken());

    }

}

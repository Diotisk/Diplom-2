package praktikum.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import praktikum.data.UserOperations;
import praktikum.models.CreateOrLoginUserResponse;
import praktikum.models.User;

import java.util.Locale;

import static org.junit.Assert.*;

public class ChangeUserDataTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void changeUserDataPositiveTest(){

        UserOperations operations = new UserOperations();
        User newUser = operations.generateUserData();
        Response createUserResponse = operations.createUser(newUser);
        String accessToken = createUserResponse.getBody().as(CreateOrLoginUserResponse.class).getAccessToken();

        User changedUserData = operations.generateUserData();
        Response actualResponse = operations.changeData(accessToken, changedUserData);

        assertEquals(200, actualResponse.getStatusCode());
        assertTrue(actualResponse.getBody().as(CreateOrLoginUserResponse.class).isSuccess());
        assertEquals(changedUserData.getName(), actualResponse.getBody().as(CreateOrLoginUserResponse.class)
                .getUser().getName());
        assertEquals(changedUserData.getEmail().toLowerCase(Locale.ROOT), actualResponse.getBody()
                .as(CreateOrLoginUserResponse.class)
                .getUser().getEmail());

        operations.deleteUser(accessToken);
    }

    @Test
    public void changeUserDataWithoutTokenNegativeTest() {

        UserOperations operations = new UserOperations();
        User newUser = operations.generateUserData();
        Response createUserResponse = operations.createUser(newUser);
        String accessToken = createUserResponse.getBody().as(CreateOrLoginUserResponse.class).getAccessToken();

        User changedUserData = operations.generateUserData();
        Response actualResponse = operations.changeData("", changedUserData);

        assertEquals(401, actualResponse.getStatusCode());
        assertFalse(actualResponse.getBody().as(CreateOrLoginUserResponse.class).isSuccess());
        assertEquals(actualResponse.getBody().as(CreateOrLoginUserResponse.class).getMessage(),
                "You should be authorised");

        operations.deleteUser(accessToken);

    }

    @Test
    public void changeUserDataWithParametersInUseNegativeTest() {

        UserOperations operations = new UserOperations();

        User newUser1 = operations.generateUserData();
        Response createUserResponse1 = operations.createUser(newUser1);
        String accessToken1 = createUserResponse1.getBody().as(CreateOrLoginUserResponse.class).getAccessToken();

        User newUser2 = operations.generateUserData();
        Response createUserResponse2 = operations.createUser(newUser2);
        String accessToken2 = createUserResponse2.getBody().as(CreateOrLoginUserResponse.class).getAccessToken();

        Response actualResponse = operations.changeData(accessToken1, newUser2);

        assertEquals(403, actualResponse.getStatusCode());
        assertFalse(actualResponse.getBody().as(CreateOrLoginUserResponse.class).isSuccess());
        assertEquals(actualResponse.getBody().as(CreateOrLoginUserResponse.class).getMessage(),
                "User with such email already exists");

        operations.deleteUser(accessToken1);
        operations.deleteUser(accessToken2);

    }

}

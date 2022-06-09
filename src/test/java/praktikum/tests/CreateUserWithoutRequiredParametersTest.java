package praktikum.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.data.UserOperations;
import praktikum.models.CreateOrLoginUserResponse;
import praktikum.models.User;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CreateUserWithoutRequiredParametersTest {

    private final User userCredentials;
    private final long expectedStatusCode;
    private final boolean expectedSuccessStatus;
    private final String expectedResponseMessage;

    public CreateUserWithoutRequiredParametersTest(User userCredentials,
                                                   long expectedStatusCode,
                                                   boolean expectedSuccessStatus,
                                                   String expectedResponseMessage) {
        this.userCredentials = userCredentials;
        this.expectedResponseMessage = expectedResponseMessage;
        this.expectedSuccessStatus = expectedSuccessStatus;
        this.expectedStatusCode = expectedStatusCode;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2} {3}")
    public static Object[] getTestData() {
        return new Object[][]{
                {new UserOperations().generateUserDataWithEmptyEmail(), 403,
                        false, "Email, password and name are required fields"},
                {new UserOperations().generateUserDataWithEmptyName(), 403,
                        false, "Email, password and name are required fields"},
                {new UserOperations().generateUserDataWithEmptyPassword(), 403,
                        false, "Email, password and name are required fields"},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void createUserWithoutRequiredParametersNegativeTest() {

        UserOperations createUserWithoutRequiredParameters = new UserOperations();

        Response actualResponse = createUserWithoutRequiredParameters.createUser(userCredentials);
        assertEquals(expectedStatusCode, actualResponse.getStatusCode());
        assertEquals(expectedSuccessStatus, actualResponse.getBody().as(CreateOrLoginUserResponse.class).isSuccess());
        assertEquals(expectedResponseMessage, actualResponse.getBody().as(CreateOrLoginUserResponse.class).getMessage());

    }

}

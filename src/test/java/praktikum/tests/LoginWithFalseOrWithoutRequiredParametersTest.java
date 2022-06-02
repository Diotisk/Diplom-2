package praktikum.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.data.UserOperations;
import praktikum.models.CreateOrLoginUserResponse;
import praktikum.models.User;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginWithFalseOrWithoutRequiredParametersTest {

    private final User userCredentials;
    private final long expectedStatusCode;
    private final boolean expectedSuccessStatus;
    private final String expectedResponseMessage;

    public LoginWithFalseOrWithoutRequiredParametersTest(User userCredentials,
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
                {new User("", RandomStringUtils.randomNumeric(5)), 401,
                        false, "email or password are incorrect"},
                {new User(RandomStringUtils.randomAlphabetic(3) + "@"
                        + RandomStringUtils.randomAlphabetic(5) + ".com", ""), 401,
                        false, "email or password are incorrect"},
                {new User(RandomStringUtils.randomAlphabetic(3) + "@"
                        + RandomStringUtils.randomAlphabetic(5) + ".com",
                        RandomStringUtils.randomNumeric(5)), 401,
                        false, "email or password are incorrect"},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    @Test
    public void loginWithFalseOrWithoutRequiredParametersNegativeTest() {

        UserOperations operations = new UserOperations();

        Response actualResponse = operations.login(userCredentials);
        assertEquals(expectedStatusCode, actualResponse.getStatusCode());
        assertEquals(expectedSuccessStatus, actualResponse.getBody().as(CreateOrLoginUserResponse.class).isSuccess());
        assertEquals(expectedResponseMessage, actualResponse.getBody().as(CreateOrLoginUserResponse.class).getMessage());

    }

}

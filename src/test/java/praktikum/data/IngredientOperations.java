package praktikum.data;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import praktikum.models.IngredientDescription;
import praktikum.models.IngredientsResponse;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class IngredientOperations {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    public ArrayList<String> getSeveralIngredients() {

        ArrayList<String> ingredientIds = new ArrayList<String>();

        Response response = given()
                .get("/api/ingredients");

        for (IngredientDescription ingredient :
                response.getBody().as(IngredientsResponse.class).getData()) {
            ingredientIds.add(ingredient.getId());
        }

        return ingredientIds;
    }

}
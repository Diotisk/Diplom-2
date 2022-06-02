package praktikum.models;

import java.util.ArrayList;

public class CreateOrderRequest {

    private ArrayList<String> ingredients;

    public CreateOrderRequest(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}

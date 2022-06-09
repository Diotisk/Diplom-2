package praktikum.models;

import java.util.ArrayList;

public class IngredientsResponse {

    private boolean success;
    private ArrayList<IngredientDescription> data;

    public IngredientsResponse(boolean success, ArrayList<IngredientDescription> data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<IngredientDescription> getData() {
        return data;
    }

    public void setData(ArrayList<IngredientDescription> data) {
        this.data = data;
    }
}

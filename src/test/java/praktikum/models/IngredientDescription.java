package praktikum.models;

import com.google.gson.annotations.SerializedName;

public class IngredientDescription {

    @SerializedName("_id")
    private final String id;
    private final String name;
    private final String type;
    private final int proteins;
    private final int fat;
    private final int carbohydrates;
    private final int calories;
    private final int price;
    private final String image;
    @SerializedName("image_mobile")
    private final String imageMobile;
    @SerializedName("image_large")
    private final String imageLarge;
    @SerializedName("v")
    private final int v;

    public IngredientDescription(String id, String name, String type, int proteins,
                                 int fat, int carbohydrates, int calories, int price,
                                 String image, String imageMobile, String imageLarge,
                                 int v) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.proteins = proteins;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.price = price;
        this.image = image;
        this.imageMobile = imageMobile;
        this.imageLarge = imageLarge;
        this.v = v;
    }

    @SerializedName("_id")
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getProteins() {
        return proteins;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public int getCalories() {
        return calories;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    @SerializedName("image_mobile")
    public String getImageMobile() {
        return imageMobile;
    }

    @SerializedName("image_large")
    public String getImageLarge() {
        return imageLarge;
    }

    @SerializedName("__v")
    public int getV() {
        return v;
    }
}

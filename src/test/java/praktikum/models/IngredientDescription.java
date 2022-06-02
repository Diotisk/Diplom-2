package praktikum.models;

public class IngredientDescription {

    private final String _id;
    private final String name;
    private final String type;
    private final int proteins;
    private final int fat;
    private final int carbohydrates;
    private final int calories;
    private final int price;
    private final String image;
    private final String image_mobile;
    private final String image_large;
    private final int __v;

    public IngredientDescription(String _id, String name, String type, int proteins,
                                 int fat, int carbohydrates, int calories, int price,
                                 String image, String image_mobile, String image_large,
                                 int __v) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.proteins = proteins;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.price = price;
        this.image = image;
        this.image_mobile = image_mobile;
        this.image_large = image_large;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
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

    public String getImage_mobile() {
        return image_mobile;
    }

    public String getImage_large() {
        return image_large;
    }

    public int get__v() {
        return __v;
    }
}

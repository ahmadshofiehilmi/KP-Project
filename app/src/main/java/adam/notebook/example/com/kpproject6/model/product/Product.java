package adam.notebook.example.com.kpproject6.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("product_name")
    @Expose
    private String product_name;

    @SerializedName("product_price")
    @Expose
    private String product_price;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("image")
    @Expose
    private Images image;

    public Product(String id, String product_name, String product_price, String description, Images image) {
        this.id = id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.description = description;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_prize(String product_prize) {
        this.product_price = product_prize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Images getImage() {
        return image;
    }

    public void setImage(Images images) {
        this.image = images;
    }
}

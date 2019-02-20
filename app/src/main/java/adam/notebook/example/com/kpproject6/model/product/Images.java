package adam.notebook.example.com.kpproject6.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("product_image")
    @Expose
    private String product_image;

    public Images(String id, String product_image) {
        this.id = id;
        this.product_image = product_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }
}

package adam.notebook.example.com.kpproject6.model.cart;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import adam.notebook.example.com.kpproject6.model.User;
import adam.notebook.example.com.kpproject6.model.product.Product;

public class CartItem implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("buyer")
    @Expose
    private User buyer;

    @SerializedName("product")
    @Expose
    private Product product;

    public CartItem(String id, String qty, String total, User buyer, Product product) {
        this.id = id;
        this.qty = qty;
        this.total = total;
        this.buyer = buyer;
        this.product = product;
    }

    protected CartItem(Parcel in) {
        id = in.readString();
        qty = in.readString();
        total = in.readString();
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(qty);
        dest.writeString(total);
    }
}

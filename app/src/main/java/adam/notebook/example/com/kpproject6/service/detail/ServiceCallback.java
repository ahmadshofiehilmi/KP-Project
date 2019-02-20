package adam.notebook.example.com.kpproject6.service.detail;

public interface ServiceCallback {
    void addToCartSuccess();

    void showMessage(String s);

    void savedToWishList();

    void removedFromWishList();
}

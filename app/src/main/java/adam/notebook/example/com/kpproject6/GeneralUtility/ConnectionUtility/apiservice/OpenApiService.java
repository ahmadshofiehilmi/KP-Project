package adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.apiservice;

import adam.notebook.example.com.kpproject6.model.cart.Cart;
import adam.notebook.example.com.kpproject6.model.cart.CartItem;
import adam.notebook.example.com.kpproject6.model.product.ProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenApiService {

    @GET("api/category/sortby/1")
    Call<ProductResponse> getProductResponse();

    @GET("api/category/sortby/2")
    Call<ProductResponse> getProductResponse2();

    @GET("api/order/cart")
    Call<Cart>getCartUser();
}

package adam.notebook.example.com.kpproject6.service.detail;

import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.ConnectionUrl;
import adam.notebook.example.com.kpproject6.GeneralUtility.LogUtility;
import adam.notebook.example.com.kpproject6.GeneralUtility.Utils;
import adam.notebook.example.com.kpproject6.MyApplication;
import adam.notebook.example.com.kpproject6.model.BaseResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailPresenter {
    private static final String TAG = "ProductDetailPresenter";

    public static void addToCart(String id, int jumlah, final ServiceCallback callback) {
        MyApplication.getApiService()
                .addToCart(ConnectionUrl.ADD_TO_CART, id, jumlah)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        LogUtility.logging(TAG, LogUtility.debugLog, "addToCart", "onResponse", Utils.toJson(response.body()));
                        if (response.body() != null) {
                            if (response.body().getStatus()) {
                                callback.addToCartSuccess();
                            } else {
                                callback.showMessage("Maaf, terjadi kesalahan pada server");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        LogUtility.logging(TAG, LogUtility.errorLog, "addToCart", "onFailure", t.getMessage());
                        t.printStackTrace();
                    }
                });
    }
}

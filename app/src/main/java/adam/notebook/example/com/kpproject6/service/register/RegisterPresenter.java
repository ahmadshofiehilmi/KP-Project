package adam.notebook.example.com.kpproject6.service.register;

import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.ConnectionUrl;
import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.apiservice.ApiService;
import adam.notebook.example.com.kpproject6.model.BaseResponse;
import adam.notebook.example.com.kpproject6.GeneralUtility.LogUtility;
import adam.notebook.example.com.kpproject6.MyApplication;
import adam.notebook.example.com.kpproject6.GeneralUtility.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {

    private static final String TAG = "RegisterPresenter";
    private static ApiService api = MyApplication.getApiService();

    public static void register(String email, String password, String fullname, String address, String city, String postal_code, final ServiceCallback callback) {
        callback.showProgress(true);
        api.register(ConnectionUrl.REGISTER , email, password, fullname, address, city, postal_code)
                .enqueue(new Callback<BaseResponse<Object>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<Object>> call, Response<BaseResponse<Object>> response) {
                        LogUtility.logging(TAG, LogUtility.debugLog, "register", "onResponse", Utils.toJson(response.body()));
                        if (response.body() != null) {
                            if (response.body().getStatus()) {
                                callback.onSuccess();
                            } else {
                                callback.onFailed(response.body().getMessage());
                            }
                        } else {
                            callback.onFailed("Maaf, terjadi kesalahan dari server");
                        }
                        callback.showProgress(false);
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<Object>> call, Throwable t) {
                        LogUtility.logging(TAG, LogUtility.errorLog, "editProfile", "onFailure", t.getMessage());
                        callback.onFailed(t.getMessage());
                        callback.showProgress(false);
                    }
                });
    }
}

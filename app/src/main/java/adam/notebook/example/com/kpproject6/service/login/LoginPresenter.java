package adam.notebook.example.com.kpproject6.service.login;

import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.ConnectionUrl;
import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.apiservice.ApiService;
import adam.notebook.example.com.kpproject6.model.BaseResponse;
import adam.notebook.example.com.kpproject6.model.User;
import adam.notebook.example.com.kpproject6.GeneralUtility.LogUtility;
import adam.notebook.example.com.kpproject6.MyApplication;
import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceKey;
import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceUtils;
import adam.notebook.example.com.kpproject6.GeneralUtility.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private static final String tag = LoginPresenter.class.getSimpleName();

    private static ApiService api = MyApplication.getApiService();
    private static PreferenceUtils pref = MyApplication.pref;

    public static void login( String email, String password, final ServiceCallback callback) {
        callback.showProgress(true);
        api.login(ConnectionUrl.LOGIN, email, password)
                .enqueue(new Callback<BaseResponse<User>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                        LogUtility.logging(tag, LogUtility.debugLog, "login", "onResponse", Utils.toJson(response.body()));
                        if (response.body() != null) {
                            if (response.body().getStatus()) {
                                callback.onSuccess(response.body().getData());
                                pref.putBoolean(PreferenceKey.IsLoggedIn, true);
                                pref.setUserSession(response.body().getData());
                            } else {
                                callback.onFailed(response.body().getMessage());
                            }
                        } else {
                            callback.onFailed("Email atau password salah!");
                        }
                        callback.showProgress(false);
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                        LogUtility.logging(tag, LogUtility.errorLog, "login", "onFailure", t.getMessage());
                        callback.onFailed(t.getMessage());
                        callback.showProgress(false);
                    }
                });
    }
}
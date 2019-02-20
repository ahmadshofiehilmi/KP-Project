package adam.notebook.example.com.kpproject6.service.profile;

import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.ConnectionUrl;
import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.apiservice.ApiService;
import adam.notebook.example.com.kpproject6.GeneralUtility.LogUtility;
import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceUtils;
import adam.notebook.example.com.kpproject6.GeneralUtility.Utils;
import adam.notebook.example.com.kpproject6.MyApplication;
import adam.notebook.example.com.kpproject6.model.BaseResponse;
import adam.notebook.example.com.kpproject6.module.profile.ProfileActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {

    private static final String TAG = "ProfilePresenter";
    private static ApiService api = MyApplication.getApiService();
    private static PreferenceUtils pref = MyApplication.pref;

    public static void editProfile(String email, String fullname, String address, String city, String postal_code, String profile_image, final ProfileActivity callback) {
        api.updateProfile(ConnectionUrl.UPDATE_PROFILE, email, fullname, address, city, postal_code, profile_image)
                .enqueue(new Callback<BaseResponse<Object>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<Object>> call, Response<BaseResponse<Object>> response) {
                        LogUtility.logging(TAG, LogUtility.debugLog, "editProfile", "onResponse", Utils.toJson(response.body()));
                        if (response.body() != null) {
                            if (response.body().getStatus()) {
                                callback.onSuccess();
                                pref.updateToken(response.body().getNewToken());
                            } else {
                                callback.onFailed(response.body().getMessage());
                            }
                        } else {
                            callback.onFailed("Maaf, terjadi kesalahan pada server");
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<Object>> call, Throwable t) {
                        LogUtility.logging(TAG, LogUtility.errorLog, "editProfile", "onFailure", t.getMessage());
                        t.printStackTrace();
                        callback.onFailed("Maaf, tidak dapat mengubah profil. Periksa koneksi internet");
                    }
                });
    }


    public static void getProfile(ServiceCallback callback) {
        api.getProfile(ConnectionUrl.GET_PROFILE)
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

                    }
                });
    }
}

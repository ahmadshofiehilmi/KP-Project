package adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils;

import android.content.Context;
import android.content.SharedPreferences;

import adam.notebook.example.com.kpproject6.model.User;

public class PreferenceUtils {
    private String TAG = PreferenceUtils.class.getSimpleName();

    private SharedPreferences sp;
    private SharedPreferences.Editor spe;

    public PreferenceUtils(Context context) {
        sp = context.getSharedPreferences("com.kpproject5", Context.MODE_PRIVATE);
        spe = sp.edit();
        spe.apply();
    }

    public void putString(String key, String value) {
        spe.putString(key, value).apply();
    }

    public void putBoolean(String key, Boolean value) {
        spe.putBoolean(key, value).apply();
    }

    public String getString(String key) {
        return sp.getString(key, "");
    }

    public Boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public void setUserSession(User data) {
        putString("id", data.getIdUser());
        putString("email", data.getEmailUser());
        putString("fullname", data.getNameUser());
        putString("address", data.getAddressUser());
        putString("city", data.getCityUser());
        putString("postal_code", data.getPostal_codeUser());
        putString("profil_image", data.getProfile_imageUser());
        putString("api_token", data.getTokenUser());
    }

    public User getUserSession() {
        User data = new User();
        data.setIdUser(sp.getString("id", ""));
        data.setEmailUser(sp.getString("email", ""));
        data.setNameUser(sp.getString("fullname", ""));
        data.setAddressUser(sp.getString("address", ""));
        data.setCityUser(sp.getString("city", ""));
        data.setPostal_codeUser(sp.getString("postal_code", ""));
        data.setProfile_imageUser(sp.getString("profil_image", ""));
        data.setTokenUser(sp.getString("api_token",""));
        return data;
    }

    public void updateToken(String newToken) {
        if (null == newToken || "".equals(newToken))
            return;
        putString("token", newToken);
    }

    public void clearSession() {
        spe.clear().apply();
        spe.putBoolean(PreferenceKey.IsNotFirstUse, true);
    }

    public String getToken() {
        return sp.getString("token", "");
    }

    public void setToken(String token) {
        spe.putString("token", token).apply();
    }

//    public String getFcmToken() {
//        return sp.getString("fcm_token", "");
//    }
//
//    public void setFcmToken(String newToken) {
//        spe.putString("fcm_token", newToken).apply();
//    }

    public Boolean isLoggedIn() {
        return getBoolean(PreferenceKey.IsLoggedIn);
    }
}
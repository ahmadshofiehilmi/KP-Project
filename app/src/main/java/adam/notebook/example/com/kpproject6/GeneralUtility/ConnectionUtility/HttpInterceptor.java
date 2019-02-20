package adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpInterceptor implements Interceptor {

    //    /*private Context context;*/
    private PreferenceUtils pref;

    public HttpInterceptor(Context context) {
        /*this.context = context;*/
        pref = new PreferenceUtils(context);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        String TAG = "HttpInterceptor";

        String token = pref.getToken();
        Log.i(TAG, "intercept: token "+token);
        Request request = chain.request();

        Request modifiedRequest = request.newBuilder()
                .header("Authorization", token)
                .build();

        Response response = chain.proceed(modifiedRequest);

        if (response.code() == 401) {
            if (response.body() != null) {
                try {
                    String jsonData = response.body().string();
                    JSONObject jsonObject = new JSONObject(jsonData);
                    //set new token
                    token = jsonObject.getString("new_token");
                    pref.setToken(token); //save new token
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Request newRequest = request.newBuilder()
                        .header("Authorization", token)
                        .build();
                return chain.proceed(newRequest);
            }
        }

        return response;
    }
}
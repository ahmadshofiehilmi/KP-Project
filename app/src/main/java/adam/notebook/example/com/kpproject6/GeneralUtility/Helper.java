package adam.notebook.example.com.kpproject6.GeneralUtility;

import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.apiservice.OpenApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Helper {

    /********
     * URLS
     *******/
//    private static final String ROOT_URL = "http://api.androiddeft.com/";
    private static final String ROOT_URL = "http://bukanjaknote.site/";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static OpenApiService getApiService() {
        return getRetrofitInstance().create(OpenApiService.class);
    }
}

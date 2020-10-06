package danijelsokac.android.personaldatamanagement.services;

import com.google.gson.Gson;

import danijelsokac.android.personaldatamanagement.AppConstants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class ServiceGenerator {

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(AppConstants.WebAPI.BASE_URL + "/" + AppConstants.WebAPI.API_PREFIX)
            .addConverterFactory(GsonConverterFactory.create(new Gson().newBuilder().excludeFieldsWithoutExposeAnnotation().create()));

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    public static <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}

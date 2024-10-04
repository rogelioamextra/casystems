package com.amextra.io;

import static com.amextra.utils.Constants.NO_TOKEN_VALUE;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {
    private static ApiService API_SERVICE;
    //private static final String BASE_URL = "http://192.168.1.132:8000";
    private static final String BASE_URL = "http://dfixtapaluca2.dyndns.org:8000";

    public static ApiService getApiService(final String token) {

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(240, TimeUnit.SECONDS)
                .connectTimeout(240, TimeUnit.SECONDS);

        httpClient.addInterceptor(logging);
        if (!Objects.equals(token, NO_TOKEN_VALUE)) {
            httpClient.addInterceptor(new AuthInterceptor(token));
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        API_SERVICE = retrofit.create(ApiService.class);

        return API_SERVICE;
    }
}

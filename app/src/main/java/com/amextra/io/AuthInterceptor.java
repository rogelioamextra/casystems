package com.amextra.io;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final String authToken;

    public AuthInterceptor(String authToken) {
        this.authToken = authToken;
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request.Builder builder = originalRequest.newBuilder()
                .header("Authorization", authToken);

        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}
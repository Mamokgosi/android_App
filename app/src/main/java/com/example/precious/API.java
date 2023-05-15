package com.example.precious;

import kotlin.jvm.JvmSuppressWildcards;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

    public interface API {

        @POST("register")
        @JvmSuppressWildcards
        Call<ResponseBody> createUser (
                @Body User user
        );

        @POST("login")
        @JvmSuppressWildcards
        Call<ResponseBody> checkUser (
                @Body User user
        );

    }



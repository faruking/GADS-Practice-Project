package com.faruking.practiceproject2.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstanceSubmit {
    private static Retrofit sRetrofit;
    private  static final String BASE_API_URL = "https://docs.google.com/forms/d/e/";

    public static Retrofit getRetrofitInstance(){
        if (sRetrofit == null) {
            sRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}

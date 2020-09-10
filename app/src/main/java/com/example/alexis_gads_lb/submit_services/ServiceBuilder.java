package com.example.alexis_gads_lb.submit_services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    public static final String URL="https://docs.google.com/forms/d/e/";
    private static Retrofit.Builder builder= new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType){
        return retrofit.create(serviceType);
    }
}

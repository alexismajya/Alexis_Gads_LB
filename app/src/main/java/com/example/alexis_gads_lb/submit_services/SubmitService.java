package com.example.alexis_gads_lb.submit_services;

import android.os.Build;

import androidx.annotation.RequiresApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SubmitService {
//    @RequiresApi(api = Build.VERSION_CODES.N)
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Void> Submit(@Field("entry.1824927963") String email,
                      @Field("entry.1877115667") String fName,
                      @Field("entry.2006916086") String lName,
                      @Field("entry.284483984") String link);
}

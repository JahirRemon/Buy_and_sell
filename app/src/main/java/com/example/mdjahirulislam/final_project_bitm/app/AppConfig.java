package com.example.mdjahirulislam.final_project_bitm.app;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mdjahirulislam on 28/04/17.
 */

public class AppConfig {
//    public static String BASE_URL = "http://192.168.0.190/";
//    public static String BASE_URL = "http://192.168.43.67/";
//    public static String BASE_URL = "http://192.168.43.12/";
    public static String BASE_URL = "http://www.firstdate4you.com/";
//    public static String WEATHER_BASE_URL = "https://query.yahooapis.com/";
//    public static String IMAGE_CODE_URL = "http://fullnightfun.com/weather_icon/";



    public static Retrofit getRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

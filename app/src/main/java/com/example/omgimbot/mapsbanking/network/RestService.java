package com.example.omgimbot.mapsbanking.network;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by github.com/adip28 on 7/17/2018.
 */
public class RestService {
    private static final String BASE_URL = "http://192.168.18.5/GIS/index.php/";
    private static Retrofit retrofit = null ;

    public static Retrofit getRetroftInstance() {
        if (retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static OkHttpClient getClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        }).build();
        return okHttpClient;
    }

}

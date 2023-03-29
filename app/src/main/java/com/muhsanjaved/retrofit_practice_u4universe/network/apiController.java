package com.muhsanjaved.retrofit_practice_u4universe.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiController {

    static String BASE_URI = "https://jsonplaceholder.typicode.com/";
    private static apiController clientObject;
    private static Retrofit retrofit;

//    public static Retrofit getRetrofit(){
    apiController(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();


        Gson gson = new GsonBuilder().serializeNulls().create();

//        Retrofit retrofit = new Retrofit.Builder()
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

//       return retrofit;
    }

    public static synchronized apiController getInstance(){
        if (clientObject == null)
            clientObject = new apiController();
        return clientObject;
    }

   public static ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
   }

}


   /* static String BASE_URI = "https://jsonplaceholder.typicode.com/";

    public static Retrofit apiController(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();


        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit;
    }
    // Main Activity calling
    ApiInterface apiInterface;
       myWebService = MyWebService.RETROFIT.create(MyWebService.class);
        //myWebService = apiController.getRetrofit().create(MyWebService.class);
        apiInterface = apiController.getRetrofit().create(ApiInterface.class);

    */


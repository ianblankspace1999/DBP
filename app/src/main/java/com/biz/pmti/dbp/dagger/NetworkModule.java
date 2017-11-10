package com.biz.pmti.dbp.dagger;


import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ian.blanco on 10/13/2017.
 */

@Module
public class NetworkModule {

    private static final String NAME_BASE_URL = "NAME_BASE_URL";

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return "http://ec2-52-76-75-52.ap-southeast-1.compute.amazonaws.com/";
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideInterceptot() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttp(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
//                                .addHeader("X-Authorization", authKey)
//                                .addHeader("X-OS", "android")
//                                .addHeader("X-App-Version", finalAppVersionName)
//                                .addHeader("X-OS-Version", osVersion)
//                                .addHeader("X-Device-Name", getDeviceName())
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, OkHttpClient okHttpClient, @Named(NAME_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

//    @Provides
//    @Singleton
//    TestInterface provideTestInterface(Retrofit retrofit) {
//        return retrofit.create(TestInterface.class);
//    }
}

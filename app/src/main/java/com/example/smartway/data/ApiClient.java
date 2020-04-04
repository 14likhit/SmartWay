package com.example.smartway.data;

import android.content.Context;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class for retrofit instance creation.
 */
public class ApiClient {

    private static Retrofit retrofit;

    private static final String base_url = "https://location-service-mxl7a62gpq-uc.a.run.app/";

    /**
     * Method to instantiate retrofit instance.
     *
     * @param context->Application Context
     */
    public static void initialiseRetrofitInstance(Context context) {
        if (retrofit == null) {
            //creating new file for caching
            File httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
            //creating cache
            Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

            //Build new Interceptor to cache response
//            OkHttpClient httpClient = new OkHttpClient.Builder()
//                    .cache(cache)
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            try {
//                                return chain.proceed(chain.request());
//                            } catch (Exception e) {
//                                Request offlineRequest = chain.request().newBuilder()
//                                        .header("Cache-Control", "public, only-if-cached," +
//                                                "max-stale=" + 60 * 60 * 2)
//                                        .build();
//                                return chain.proceed(offlineRequest);
//                            }
//                        }
//                    })
//                    .build();

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request.Builder builder = original.newBuilder().removeHeader("@");
                            builder.header("Content-Type", "application/json");
                            Request request = builder.build();
                            return chain.proceed(request);
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
    }

    /**
     * Method to get retrofit instance
     *
     * @return->Retrofit instance
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            throw new IllegalStateException("ApiClient not initialized, use initialize()");
        }
        return retrofit;
    }

}

package com.laioffer.tinnews.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String API_KEY = "f3507d91e569497c970145227a853ba6";
    private static final String BASE_URL = "https://newsapi.org/v2/";   //  1. base URL

    public static Retrofit newInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())    //  2. A header interceptor
                // helper class有override intercept的定义
                .build();
        return new Retrofit.Builder()   //  provide a configured Retrofit instance, that can then
                //  instantiate a NewsApi implementation
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //  3. Gson adaptor to tell how a JSON response can be deserialized into model classes
                .client(okHttpClient)
                .build();
    }

    private static class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request(); //  可以把original request拿出来
            Request request = original
                    .newBuilder()
                    .header("X-Api-Key", API_KEY)   // 拦截一下request再加上api key as
                    // the token再发出去
                    //  You can attach custom or standard header information to all requests.
                    .build();
            return chain.proceed(request);
        }
    }
}


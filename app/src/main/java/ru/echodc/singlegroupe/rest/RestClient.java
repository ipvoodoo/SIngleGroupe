package ru.echodc.singlegroupe.rest;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//  Для инициализации Retrofit
public class RestClient {

  //  Константы для хранения URL запросов
  private static final String VK_BASE_URL = "https://api.vk.com/method/";

  private Retrofit mRetrofit;

  public RestClient() {

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

    mRetrofit = new Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(VK_BASE_URL)
        .client(httpClient)
        .build();
  }

  //  Для инициализации сервисов
  public <S> S createService(Class<S> serviceClass) {
    return mRetrofit.create(serviceClass);
  }
}
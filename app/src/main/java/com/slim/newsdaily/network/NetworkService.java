package com.slim.newsdaily.network;

import com.slim.newsdaily.BuildConfig;
import com.slim.newsdaily.network.models.NewsPage;


import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
public class NetworkService implements INetworkService {

    private INewsAPIService newsAPIService;

    private Retrofit retrofit;

    NetworkService() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client)
                .build();

        newsAPIService = retrofit.create(INewsAPIService.class);
    }

    @Override
    public Single<NewsPage> getHeadlines(String country, int page) {
        return newsAPIService.getHeadlines(BuildConfig.API_KEY, country, 10, page);
    }

    @Override
    public Single<NewsPage> searchNews(String query, int page) {
        return newsAPIService.searchNews(BuildConfig.API_KEY, "en", query, 10, page);
    }

    public interface INewsAPIService {

        @GET("top-headlines")
        Single<NewsPage> getHeadlines(@Query("apiKey") String apiKey,
                                      @Query("country") String country,
                                      @Query("pageSize") int pageSize,
                                      @Query("page") int page);

        @GET("everything")
        Single<NewsPage> searchNews(@Query("apiKey") String apiKey,
                                    @Query("language") String language,
                                    @Query("q") String q,
                                    @Query("pageSize") int pageSize,
                                    @Query("page") int page);
    }
}

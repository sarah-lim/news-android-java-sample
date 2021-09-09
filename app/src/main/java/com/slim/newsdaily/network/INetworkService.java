package com.slim.newsdaily.network;

import com.slim.newsdaily.network.models.NewsPage;

import io.reactivex.rxjava3.core.Single;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
public interface INetworkService {

    Single<NewsPage> getHeadlines(String country, int page);

    Single<NewsPage> searchNews(String query, int page);
}

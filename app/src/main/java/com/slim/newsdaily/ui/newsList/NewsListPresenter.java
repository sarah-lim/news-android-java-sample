package com.slim.newsdaily.ui.newsList;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.slim.newsdaily.network.INetworkService;
import com.slim.newsdaily.network.models.Articles;
import com.slim.newsdaily.network.models.NewsPage;
import com.slim.newsdaily.ui.MainApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
public class NewsListPresenter implements INewsListPresenter {

    @Inject
    INetworkService service;

    @Inject
    Context context;

    private CompositeDisposable disposables;

    private INewsListView view;

    private int currentPage = 0;
    private int nextPage = 1;
    private boolean bottomReached = false;

    private String countryCode = "us";

    public NewsListPresenter(INewsListView view) {
        MainApplication.getApplicationComponent().inject(this);

        TelephonyManager tm = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
        if(!TextUtils.isEmpty(tm.getNetworkCountryIso())) {
            countryCode = tm.getNetworkCountryIso();
        }

        this.view = view;
    }

    @Override
    public void attach() {
        disposables = new CompositeDisposable();
        getHeadlines();
    }

    @Override
    public void detach() {
        disposables.dispose();
    }

    private void getHeadlines() {

        disposables.add(service.getHeadlines(countryCode, nextPage)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::getHeadlinesSuccess, this::getHeadlinesFailed));
    }

    private void getHeadlinesSuccess(NewsPage newsPage) {
        view.showProgress(false);
        if(newsPage != null) {
            currentPage = nextPage;
            List<Articles> articlesList = newsPage.articles;
            if(newsPage.totalResults == 0) {
                view.showList(new ArrayList<>());
                view.showEmptyView(true);
            } else if(currentPage == 1 && (articlesList != null && articlesList.size() > 0)){
                view.showList(articlesList);
                view.showEmptyView(false);
            } else if(articlesList != null && articlesList.size() > 0) {
                view.addList(articlesList);
                view.showEmptyView(false);
            }

            if(articlesList == null | articlesList.size() == 0) {
                bottomReached = true;
            }
        }
    }

    private void getHeadlinesFailed(Throwable throwable) {
        view.showProgress(false);
        view.showMessage("Error: " + throwable.getLocalizedMessage());
    }

    @Override
    public void loadNextPage() {
        if(!bottomReached) {
            nextPage = currentPage + 1;
            getHeadlines();
        }
    }

    @Override
    public void refresh() {
        nextPage = 1;
        currentPage = 0;
        bottomReached = false;
        getHeadlines();
    }
}

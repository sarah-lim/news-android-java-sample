package com.slim.newsdaily.ui.newsList;

import com.slim.newsdaily.network.models.Articles;

import java.util.List;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
public interface INewsListView {

    void showList(List<Articles> articles);

    void addList(List<Articles> articles);

    void showEmptyView(boolean show);

    void showMessage(String message);

    void showProgress(boolean show);
}

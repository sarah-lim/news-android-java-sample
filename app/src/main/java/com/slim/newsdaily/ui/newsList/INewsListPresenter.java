package com.slim.newsdaily.ui.newsList;

import com.slim.newsdaily.ui.base.IBasePresenter;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
public interface INewsListPresenter extends IBasePresenter {

    void loadNextPage();

    void refresh();
}

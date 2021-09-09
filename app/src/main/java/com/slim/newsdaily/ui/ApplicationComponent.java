package com.slim.newsdaily.ui;


import com.slim.newsdaily.network.NetworkModule;
import com.slim.newsdaily.ui.newsList.NewsListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
@Singleton
@Component(modules ={
        AppContextModule.class,
        NetworkModule.class
})
public interface ApplicationComponent {
    void inject(NewsListPresenter presenter);
}

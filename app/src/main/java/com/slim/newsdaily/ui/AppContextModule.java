package com.slim.newsdaily.ui;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
@Module
public class AppContextModule {

    private Context context;

    public AppContextModule(Application application) {
        context = application.getApplicationContext();
    }

    @Singleton
    @Provides
    public Context provideApplicationContext() {
        return context;
    }
}

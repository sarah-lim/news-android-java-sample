package com.slim.newsdaily.ui;

import android.app.Application;

import com.slim.newsdaily.BuildConfig;

import timber.log.Timber;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
public class MainApplication extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        initDependencies();
    }

    private void initDependencies() {
        applicationComponent = DaggerApplicationComponent.builder()
                .appContextModule(new AppContextModule(this)).build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

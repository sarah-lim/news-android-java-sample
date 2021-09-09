package com.slim.newsdaily.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    public INetworkService provideNetworkService() {
        return new NetworkService();
    }
}

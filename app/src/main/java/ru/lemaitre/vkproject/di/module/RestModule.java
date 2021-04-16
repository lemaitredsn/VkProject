package ru.lemaitre.vkproject.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.lemaitre.vkproject.rest.RestClient;
import ru.lemaitre.vkproject.rest.api.WallApi;

@Module
public class RestModule {
    private RestClient mRestClient;

    public RestModule() {
        this.mRestClient = new RestClient();
    }

    @Singleton
    @Provides
    public RestClient provideRestClient() {
        return mRestClient;
    }

    @Singleton
    @Provides
    public WallApi provideWallApi(){
        return mRestClient.createService(WallApi.class);
    }
}

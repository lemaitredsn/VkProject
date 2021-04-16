package ru.lemaitre.vkproject.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.lemaitre.vkproject.common.manager.MyFragmentManager;

@Module
public class ManagerModule {

    @Singleton
    @Provides
    MyFragmentManager provideMyFragmentManager(){
        return new MyFragmentManager();
    }
}

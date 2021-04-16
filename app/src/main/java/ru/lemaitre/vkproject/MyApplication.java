package ru.lemaitre.vkproject;

import android.app.Application;

import com.vk.sdk.VKSdk;

import ru.lemaitre.vkproject.di.component.ApplicationComponent;
import ru.lemaitre.vkproject.di.component.DaggerApplicationComponent;
import ru.lemaitre.vkproject.di.module.ApplicationModule;

public class MyApplication extends Application {
    private static ApplicationComponent sApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();
        VKSdk.initialize(this);
    }

    private void initComponent() {
        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getsApplicationComponent(){
        return sApplicationComponent;
    }
}

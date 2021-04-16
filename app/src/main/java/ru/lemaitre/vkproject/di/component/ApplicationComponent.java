package ru.lemaitre.vkproject.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.lemaitre.vkproject.di.module.ApplicationModule;
import ru.lemaitre.vkproject.di.module.ManagerModule;
import ru.lemaitre.vkproject.di.module.RestModule;
import ru.lemaitre.vkproject.ui.activity.BaseActivity;
import ru.lemaitre.vkproject.ui.activity.MainActivity;
import ru.lemaitre.vkproject.ui.fragment.NewsFeedFragment;

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {

    //activities
    void inject(BaseActivity activity);
    void inject(MainActivity activity);

    void inject(NewsFeedFragment fragment);
}

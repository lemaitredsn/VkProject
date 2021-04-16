package ru.lemaitre.vkproject.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;


import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.lemaitre.vkproject.CurrentUser;
import ru.lemaitre.vkproject.MyApplication;
import ru.lemaitre.vkproject.R;
import ru.lemaitre.vkproject.consts.ApiConstants;
import ru.lemaitre.vkproject.mvp.presenter.MainPresenter;
import ru.lemaitre.vkproject.mvp.view.MainView;
import ru.lemaitre.vkproject.ui.fragment.NewsFeedFragment;

public class MainActivity extends BaseActivity implements MainView {
    //TODO для управления ж\ц презентера
    @InjectPresenter
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getsApplicationComponent().inject(this);

        mPresenter.checkAuth();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                mPresenter.checkAuth();
            }

            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                Toast.makeText(getBaseContext(), "error" + error.errorMessage, Toast.LENGTH_SHORT).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void startSign() {
        VKSdk.login(this, ApiConstants.DEFAULT_LOGIN_SCOPE);

    }

    @Override
    public void signedId() {
        Toast.makeText(this, "Current user id = " + CurrentUser.getId(), Toast.LENGTH_SHORT).show();
        setContent(new NewsFeedFragment());
    }
}
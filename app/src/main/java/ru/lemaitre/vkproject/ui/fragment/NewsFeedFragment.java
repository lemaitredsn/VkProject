package ru.lemaitre.vkproject.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.lemaitre.vkproject.CurrentUser;
import ru.lemaitre.vkproject.MyApplication;
import ru.lemaitre.vkproject.R;
import ru.lemaitre.vkproject.rest.api.WallApi;
import ru.lemaitre.vkproject.rest.module.request.WallGetRequestModel;
import ru.lemaitre.vkproject.rest.module.response.BaseItemResponse;
import ru.lemaitre.vkproject.rest.module.response.Full;
import ru.lemaitre.vkproject.rest.module.response.WallGetResponse;

public class NewsFeedFragment extends BaseFragment {

    @Inject
    WallApi mWallapi;

    public NewsFeedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getsApplicationComponent().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mWallapi.get(new WallGetRequestModel(-86529522).toMap()).enqueue(new Callback<WallGetResponse>() {
            @Override
            public void onResponse(Call<WallGetResponse> call, Response<WallGetResponse> response) {
                Toast.makeText(getActivity(), "Likes " + response.body().response.getItems().get(0).getLikes().getCount(), Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onFailure(Call<WallGetResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_feed;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }
}
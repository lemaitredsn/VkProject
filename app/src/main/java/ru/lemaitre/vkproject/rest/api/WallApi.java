package ru.lemaitre.vkproject.rest.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import ru.lemaitre.vkproject.rest.module.response.BaseItemResponse;
import ru.lemaitre.vkproject.rest.module.response.Full;
import ru.lemaitre.vkproject.rest.module.response.WallGetResponse;

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Call<WallGetResponse> get(@QueryMap Map<String, String> map);
}

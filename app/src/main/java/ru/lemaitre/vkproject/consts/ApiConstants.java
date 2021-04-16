package ru.lemaitre.vkproject.consts;


import com.vk.sdk.VKScope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiConstants {
    public static final String[] DEFAULT_LOGIN_SCOPE = {VKScope.AUDIO, VKScope.DIRECT, VKScope.VIDEO, VKScope.WALL,
            VKScope.PHOTOS, VKScope.PAGES, VKScope.GROUPS, VKScope.EMAIL,VKScope.STATS, VKScope.DOCS};
    public static final Double DEFAULT_VERSION = 5.130;
    public static final int DEFAULT_COUNT = 10;

}

package ru.lemaitre.vkproject.mvp.view;


import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {
    @StateStrategyType(AddToEndStrategy.class)
    void startSign();
    @StateStrategyType(AddToEndStrategy.class)
    void signedId();
}

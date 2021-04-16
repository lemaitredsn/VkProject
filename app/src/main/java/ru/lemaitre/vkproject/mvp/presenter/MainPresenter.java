package ru.lemaitre.vkproject.mvp.presenter;



import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.lemaitre.vkproject.CurrentUser;
import ru.lemaitre.vkproject.mvp.view.MainView;

//TODO привязки презентера к вьюстэйт
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public void checkAuth(){
        if(!CurrentUser.isAuthorized()){
            getViewState().startSign();
        }else {
            getViewState().signedId();
        }
    }
}

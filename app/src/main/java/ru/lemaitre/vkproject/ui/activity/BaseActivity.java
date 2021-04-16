package ru.lemaitre.vkproject.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.Toolbar;

import javax.inject.Inject;

import moxy.MvpAppCompatActivity;
import ru.lemaitre.vkproject.MyApplication;
import ru.lemaitre.vkproject.R;
import ru.lemaitre.vkproject.common.manager.MyFragmentManager;
import ru.lemaitre.vkproject.ui.fragment.BaseFragment;

public abstract class BaseActivity extends MvpAppCompatActivity {

    @Inject
    MyFragmentManager myFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getsApplicationComponent().inject(this);

        setContentView(R.layout.activity_base);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FrameLayout parent = findViewById(R.id.main_wrapper);
        getLayoutInflater().inflate(getMainContentLayout(), parent);
    }

    @LayoutRes
    protected abstract int getMainContentLayout();

    //for change fragment
    public void fragmentOnScreen(BaseFragment fragment){
        setToolbarTitle(fragment.createToolbarTitle(this));
    }

    public void setToolbarTitle(String title){
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
        }
    }

    //TODO закончил 20-00

    //---------------------------------------------------------------------------------------------
    //Управление фрагментами
    public void setContent(BaseFragment fragment){
        myFragmentManager.setFragment(this, fragment, R.id.main_wrapper);
    }

    public void addContent(BaseFragment fragment){
        myFragmentManager.addFragment(this, fragment, R.id.main_wrapper);
    }

    public boolean removeCurrentFragment(){
        return myFragmentManager.removeCurrentFragment(this);
    }

    public boolean removeFragment(BaseFragment fragment){
        return myFragmentManager.removeFragment(this, fragment);
    }
    //---------------------------------------------------------------------------------------------

    @Override
    public void onBackPressed() {
        removeCurrentFragment();
    }
}

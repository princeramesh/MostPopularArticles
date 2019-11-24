package com.ramesh.app.ui;

import android.os.Bundle;
import android.support.test.espresso.IdlingRegistry;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.ramesh.app.R;
import com.ramesh.app.ui.fragments.ArticlesFragment;
import com.ramesh.app.utils.EspressoIdlingResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends DaggerAppCompatActivity {

    //variables
    private static final String CURRENT_FRAGMENT_TAG = "current_fragment";

    @Inject
    public ArticlesFragment articlesFragment;

    @Inject
    public CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            initView();
        } else {
            handleOnConfigurationChanged();
        }
    }

    //init views
    private void initView() {
        replaceCurrentFragment(articlesFragment, false);
    }

    //handle if configuration changed
    private void handleOnConfigurationChanged() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(CURRENT_FRAGMENT_TAG);
        replaceCurrentFragment(fragment, false);
    }

    //replace current fragment
    public void replaceCurrentFragment(Bundle data, Fragment fragment) {
        fragment.setArguments(data);
        replaceCurrentFragment(fragment, true);
    }

    //replace current fragment
    private void replaceCurrentFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, CURRENT_FRAGMENT_TAG);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(articlesFragment.getClass().getName());
        fragmentTransaction.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.isDisposed();
    }
}

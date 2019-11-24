package com.ramesh.app.dagger;


import com.ramesh.app.dagger.scopes.PerActivity;
import com.ramesh.app.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivityBuilder {


    @PerActivity
    @ContributesAndroidInjector(modules = {ApiModule.class, ActivityModule.class, FragmentBuilder.class})
    MainActivity getMainActivity();
}




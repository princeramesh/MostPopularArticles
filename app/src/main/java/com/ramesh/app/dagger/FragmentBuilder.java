package com.ramesh.app.dagger;

import com.ramesh.app.ui.fragments.ArticlesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface FragmentBuilder {

    @ContributesAndroidInjector
    ArticlesFragment getArticlesFragment();
}

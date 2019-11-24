package com.ramesh.app.dagger;

import com.ramesh.app.dagger.scopes.PerActivity;
import com.ramesh.app.ui.fragments.ArticlesFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    @Provides
    @PerActivity
    public ArticlesFragment provideArticleFragment() {
        return new ArticlesFragment();
    }


}

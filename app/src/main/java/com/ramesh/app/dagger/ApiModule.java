package com.ramesh.app.dagger;


import com.ramesh.app.dagger.scopes.PerActivity;
import com.ramesh.app.data.services.ArticlesService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @PerActivity
    public ArticlesService getService(Retrofit retrofit) {
        return retrofit.create(ArticlesService.class);
    }


}

package com.ramesh.app.dagger.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {



    /*@Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }*/

    @Provides
    @Singleton
    public Context provideApplication(Application application) {
        return application;
    }


}
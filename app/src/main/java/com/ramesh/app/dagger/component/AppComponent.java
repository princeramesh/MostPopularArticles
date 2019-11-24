package com.ramesh.app.dagger.component;

import android.app.Application;

import com.ramesh.app.MyApplication;
import com.ramesh.app.dagger.ActivityBuilder;
import com.ramesh.app.dagger.modules.ContextModule;
import com.ramesh.app.dagger.modules.NetworkModule;
import com.ramesh.app.dagger.modules.PicassoModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, NetworkModule.class, ActivityBuilder.class, PicassoModule.class, ContextModule.class})
public interface AppComponent {
    void inject(MyApplication app);


    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder url(@Named("url") String url);

        AppComponent build();
    }

}

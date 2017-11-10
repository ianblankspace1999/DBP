package com.biz.pmti.dbp;

import android.app.Application;

import com.biz.pmti.dbp.dagger.AppComponent;
import com.biz.pmti.dbp.dagger.AppModule;
import com.biz.pmti.dbp.dagger.DaggerAppComponent;

/**
 * Created by ian.blanco on 11/7/2017.
 */

public class BaseApplication extends Application {

    private AppComponent mAppComponent;

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    protected AppComponent initDagger(BaseApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = initDagger(this);
    }
}

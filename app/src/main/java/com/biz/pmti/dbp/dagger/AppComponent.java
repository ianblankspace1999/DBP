package com.biz.pmti.dbp.dagger;


import com.biz.pmti.dbp.activities.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ian.blanco on 10/13/2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

void inject(BaseActivity activity);


}

package com.biz.pmti.dbp.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.biz.pmti.dbp.BaseApplication;
import com.biz.pmti.dbp.R;

import butterknife.ButterKnife;

/**
 * Created by ian.blanco on 11/8/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    protected abstract int getLayoutResource();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
//        setContentView(getLayoutResource());

        setContentView(getLayoutResource());
        ButterKnife.bind(this);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }


        ((BaseApplication) getApplication()).getAppComponent().inject(this);
    }
}

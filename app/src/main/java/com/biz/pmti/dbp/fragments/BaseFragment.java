package com.biz.pmti.dbp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.biz.pmti.dbp.activities.MainActivity;

/**
 * Created by ian.blanco on 11/9/2017.
 */

public abstract class BaseFragment extends Fragment {

    protected MainActivity mMainActivity;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivity =(MainActivity) getActivity();
    }

    public boolean isValid(){
        return true;
    }
}

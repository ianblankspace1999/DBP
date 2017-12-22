package com.biz.pmti.dbp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;

import com.biz.pmti.dbp.activities.MainActivity;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by ian.blanco on 11/9/2017.
 */

public abstract class BaseFragment extends Fragment {

    protected MainActivity parent;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent =(MainActivity) getActivity();
    }

    public boolean isValid(){
        return true;
    }


    protected void hideSoftKeyboard() {
        if (getActivity().getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) parent.getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(parent.getCurrentFocus().getWindowToken(), 0);
        }
    }


}

package com.wx.waterripple;

import rajawali.RajawaliActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends RajawaliActivity {

    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

}

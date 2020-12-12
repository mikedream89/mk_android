package com.joybar.androidrouter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.me.obo.routertable.RouterTable$$Moduleuser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gotoUserMain();
    }

    private void gotoUserMain() {
        RouterTable$$Moduleuser.launchMain().navigate(MainActivity.this);
        finish();
    }

}


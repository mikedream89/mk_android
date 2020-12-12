package com.mk.login;


import android.content.Intent;
import android.os.Bundle;

import com.mk.proxy.plugin.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login).setOnClickListener(v -> {
//            Intent intent = new Intent();
//            intent.setClass(that, TwoActivity.class);
            startActivity(new Intent(that, TwoActivity.class));
        });
    }

}
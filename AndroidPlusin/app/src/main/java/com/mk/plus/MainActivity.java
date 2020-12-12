package com.mk.plus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mk.proxy.plugin.PluginManager;
import com.mk.proxy.plugin.ProxyActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jumpLogin(View view) {
        String name = PluginManager.getInstance().pluginActivity;
        Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
        intent.putExtra("className", name);
        startActivity(intent);
    }
}
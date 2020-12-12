package com.joybar.moduleshop;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.joybar.annotation.RegisterRouter;
import com.joybar.moduleeventbus.ModuleEventBus;
import com.joybar.moduleeventbus.data.ShopInfo;

@RegisterRouter(module = "shop", path = "post_module_data")
public class PostModuleDataActivity extends AppCompatActivity {
    private static final String TAG = "PostDataActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_activity_post_data);
        findViewById(R.id.btn_post_module_data1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModuleEventBus.getInstance().post("I am form shop");
            }
        });
        findViewById(R.id.btn_post_module_data2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModuleEventBus.getInstance().post(new ShopInfo("KFC", "Hangzhou Xihu"));
            }
        });

    }


}



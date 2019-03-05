package com.jeve.gestures;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jeve.gestures.content.CheckContent;
import com.jeve.gestures.service.BmobTool;

import java.util.List;

/**
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
 */
public class SplashActivity extends BaseActivity {

    private final String PERMISSION1 = "android.permission.WRITE_EXTERNAL_STORAGE";
    private final String PERMISSION2 = "android.permission.READ_PHONE_STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CheckContent content = new CheckContent();
        content.setTime(1000 * 60L);
        content.setCode("LJW");
        BmobTool.save(content);

        requestPermission();
    }

    private void requestPermission() {
        requestPermission(this, new String[]{PERMISSION1, PERMISSION2}, 1);
    }

    private void goTo() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }

    @Override
    public void requestSuccess(int requestCode, List<String> permission) {
        super.requestSuccess(requestCode, permission);
        if (permission.contains(PERMISSION1) && permission.contains(PERMISSION2)) {
            goTo();
        }
    }

    @Override
    public void requestError(int requestCode, List<String> permission) {
        super.requestError(requestCode, permission);
        if (!permission.isEmpty()) {
            Toast.makeText(this, "权限申请失败", Toast.LENGTH_SHORT).show();
        }
    }
}

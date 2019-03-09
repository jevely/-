package com.jeve.gestures;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jeve.gestures.action.ActionManager;
import com.jeve.gestures.adapter.MainAdapter;
import com.jeve.gestures.content.AppContent;
import com.jeve.gestures.content.CheckContent;
import com.jeve.gestures.content.ContentManager;
import com.jeve.gestures.inter.BmobResultCallBack;
import com.jeve.gestures.service.BmobManager;
import com.jeve.gestures.service.BmobTool;
import com.jeve.gestures.tool.Logger;
import com.jeve.gestures.tool.SharePreferenceTool;
import com.jeve.gestures.tool.Utils;
import com.jeve.gestures.window.ActionFloatButtonWindow;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, MainAdapter.ClickCallBack, BmobResultCallBack {

    private RecyclerView recyclerView;

    private MainAdapter adapter;

    private RelativeLayout code_re;
    private EditText edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initEvent();
        BmobManager.getInstance().setBmobResultCallBack(this);
        BmobManager.getInstance().checkUser();
    }


    private void init() {
        List<AppContent> allContent = ContentManager.getInstance().getAllContent();
        adapter = new MainAdapter(this, allContent);
        edit = findViewById(R.id.edit);
        code_re = findViewById(R.id.code_re);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setClickCallBack(this);

        for (AppContent appContent : allContent) {
            if (appContent.isCheck()) {
                ActionManager.getInstance().addActionContent(appContent);
            }
        }
    }

    private void initEvent() {
        findViewById(R.id.bt).setOnClickListener(this);
        findViewById(R.id.bt02).setOnClickListener(this);
        findViewById(R.id.sure).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                requestPermission();
                break;
            case R.id.bt02:
                List<AppContent> list = ActionManager.getInstance().getAppList();
                if (!list.isEmpty()) {
                    Utils.startApp(list.get(0).getPackageName());
                }
                break;
            case R.id.sure:
                //验证
                final String code = edit.getText().toString().trim();
                BmobManager.getInstance().checkCode(code);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accessRequest) {
            accessRequest = false;
            Utils.openFloatWindowPermission(this);
        }
    }

    private boolean accessRequest = false;

    /**
     * 权限辅助功能申请
     */
    private void requestPermission() {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
        accessRequest = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {//悬浮窗界面返回
            if (Settings.canDrawOverlays(this)) {
                //悬浮窗申请成功
                ActionFloatButtonWindow.getInstence().initView(this);
                ActionFloatButtonWindow.getInstence().show2();
            } else {
                Toast.makeText(this, "权限申请不成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * item点击
     */
    @Override
    public void clickCallBack(AppContent content) {
        if (content.isCheck()) {
            ActionManager.getInstance().addActionContent(content);
        } else {
            ActionManager.getInstance().removeActionContent(content);
        }
    }

    @Override
    public void checkUser(boolean isUser, int code) {
        if (isUser) {
            code_re.setVisibility(View.GONE);
        } else {
            switch (code) {
                case 1:

                    break;
                case 2:
                    Toast.makeText(this, "验证码已到期", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(this, "未知错误", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    Toast.makeText(this, "请检查网络,重启应用", Toast.LENGTH_SHORT).show();
                    break;
            }
            code_re.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void checkCode(boolean success, int code) {
        Logger.d("checkCode thread = " + Thread.currentThread().getName());
        if (success) {
            code_re.setVisibility(View.GONE);
        } else {
            switch (code) {
                case 1:
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(this, "验证码已经使用过", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}

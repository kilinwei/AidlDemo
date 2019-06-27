package com.example.serveraidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.serveraidl.aidl.LoginService;

public class MainActivity extends AppCompatActivity implements LoginService.OnLoginListener {
    private static final String TAG = "MainActivity";
    private LoginService.MyBinder mMyBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("com.example.serveraidl.aidl.LoginService");
        intent.setPackage("com.example.serveraidl");
//        Intent intent = new Intent(this, LoginService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: 本地绑定成");
            mMyBinder = (LoginService.MyBinder) service;
            LoginService loginService = mMyBinder.getService();
            loginService.setOnLoginListener(MainActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Handler mHandler = new Handler();

    @Override
    public void login(String userName, String password) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

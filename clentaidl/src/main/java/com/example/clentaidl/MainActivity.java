package com.example.clentaidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.serveraidl.aidl.ILoginInterface;

public class MainActivity extends AppCompatActivity {

    private ILoginInterface mILoginInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bindService(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.serveraidl.aidl.LoginService");
        intent.setPackage("com.example.serveraidl");
        bindService(intent, new ConnectCallBack(), Context.BIND_AUTO_CREATE);
    }

    int id;

    public void login(View view) {
        try {
            if (mILoginInterface != null) {
                mILoginInterface.login("张三" + id, "密码" + id++);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    class ConnectCallBack implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mILoginInterface = ILoginInterface.Stub.asInterface(service);
            Toast.makeText(MainActivity.this,"绑定成功",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mILoginInterface = null;
        }
    }
}

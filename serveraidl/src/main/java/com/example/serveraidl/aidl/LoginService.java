package com.example.serveraidl.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class LoginService extends Service {

    private static final String TAG = "LoginService";
    private OnLoginListener mOnLoginListener;

    public interface OnLoginListener {
        void login(String userName, String password);
    }

    public void setOnLoginListener(OnLoginListener listener) {
        mOnLoginListener = listener;
    }

    public LoginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

  public   class MyBinder extends ILoginInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void login(String userName, String password) throws RemoteException {
            Log.i(TAG, "login: 服务端收到登录请求 userName; "+ userName);
            if (mOnLoginListener != null) {
                mOnLoginListener.login(userName, password);
            }
        }

        public LoginService getService() {
            return LoginService.this;
        }
    }
}

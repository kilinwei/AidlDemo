package com.example.aidldemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.aidldemo.aidl.Book;
import com.example.aidldemo.aidl.BookManagerService;
import com.example.aidldemo.aidl.IBookManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIBookManager = IBookManager.Stub.asInterface(service);
            Toast.makeText(MainActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private IBookManager mIBookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void bindService(View view) {

        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    public void unBindService(View view) {

    }

    int id;

    public void addBook(View view) {
        if (mIBookManager != null) {
            Book book = new Book();
            book.setBookId(id++);
            book.setBookName("书名" + id++);
            try {
                mIBookManager.addBook(book);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    public void getBookList(View view) {
        try {
            Log.i(TAG, "getBookList: 获取到的书的数量： "+ mIBookManager.getBookList().size());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

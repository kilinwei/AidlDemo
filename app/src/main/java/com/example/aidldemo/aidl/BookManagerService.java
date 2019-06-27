package com.example.aidldemo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookManagerService extends Service {
    private static final String TAG = "BookManagerService";
    private List<Book> mBookList = new ArrayList<>();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

    public BookManagerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return mBinder;
    }

    private IBookManager.Stub mBinder = new IBookManager.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
            final int size = mBookList.size();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BookManagerService.this, String.format("添加了一本新书，现在有%d本书", size)
                            , Toast.LENGTH_SHORT).show();
                }
            });
            Log.i(TAG, "addBook: " + String.format("添加了一本新书，现在有%d本书", size));
            Log.i(TAG, "addBook: cuuuentThread: " + Thread.currentThread().getName());

        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }
    };
}

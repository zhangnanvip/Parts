package com.parts.zn.nine.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.parts.zn.nine.aidl.Book;
import com.parts.zn.nine.aidl.LibraryManager;

/**
 * @author zhangnan
 * @date 2018/1/31
 */

public class LibraryService extends Service {

    private LibraryManager mLibraryManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mLibraryManager = new LibraryManager();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    Book book = mLibraryManager.borrow("天龙八部");
                    Thread.sleep(1000);
                    mLibraryManager.repay(book);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mLibraryManager;
    }
}

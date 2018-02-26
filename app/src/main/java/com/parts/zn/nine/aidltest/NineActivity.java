package com.parts.zn.nine.aidltest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parts.zn.R;
import com.parts.zn.nine.aidl.Book;

/**
 * @author zhangnan
 * @date 2018/1/30
 */

public class NineActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = NineActivity.class.getSimpleName();

    private Button mBtBorrow;
    private Button mBtRepay;
    private TextView mTvResult;

    private LibraryServiceConnection mLibraryServiceConnection;

    private Book mBook;

    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine);

        mBtBorrow = findViewById(R.id.bt_borrow);
        mBtRepay = findViewById(R.id.bt_repay);
        mTvResult = findViewById(R.id.tv_result);

        mBtBorrow.setOnClickListener(this);
        mBtRepay.setOnClickListener(this);

        mIntent = new Intent(this, LibraryService.class);
        startService(mIntent);

        mLibraryServiceConnection = new LibraryServiceConnection(new LibraryCallback() {
            @Override
            public void onBookBorrow(Book book) {
                if (book != null) {
                    mBook = book;
                    Log.d(TAG, "您借了一本 " + book.toString());
                } else {
                    Log.d(TAG, "您想借的书不存在或者已被借完");
                }
            }

            @Override
            public void onBookRepay(boolean result) {
                mBook = (result ? null : mBook);
                Log.d(TAG, "您归还图书 " + (result ? "成功" : "失败"));
            }
        });

        bindService(mIntent, mLibraryServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLibraryServiceConnection.unregisterListener();
        unbindService(mLibraryServiceConnection);
        stopService(mIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_borrow:
                try {
                    mLibraryServiceConnection.takeLibrary().borrow("天龙八部");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_repay:
                if (mBook == null) {
                    return;
                }
                try {
                    mLibraryServiceConnection.takeLibrary().repay(mBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}

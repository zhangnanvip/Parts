package com.parts.zn.nine.aidl;

import android.os.IBinder;

/**
 * @author zhangnan
 * @date 2018/1/30
 */

public abstract class LibraryChangeListener extends ILibraryChangeListener.Stub {

    @Override
    public abstract void onBookBorrow(Book book);

    @Override
    public abstract void onBookRepay(boolean result);

    @Override
    public IBinder asBinder() {
        return super.asBinder();
    }

}

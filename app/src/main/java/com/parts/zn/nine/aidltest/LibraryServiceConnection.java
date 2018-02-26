package com.parts.zn.nine.aidltest;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.parts.zn.nine.aidl.Book;
import com.parts.zn.nine.aidl.Library;
import com.parts.zn.nine.aidl.LibraryChangeListener;

public class LibraryServiceConnection implements ServiceConnection {

    private Library mLibrary;
    private LibraryCallback mLibraryCallback;
    private LibraryChangeListener mLibraryChangeListener;

    private boolean isConnected = false;

    public LibraryServiceConnection(LibraryCallback callback) {
        this.mLibraryCallback = callback;
        this.mLibraryChangeListener = new LibraryChangeListener() {
            @Override
            public void onBookBorrow(Book book) {
                mLibraryCallback.onBookBorrow(book);
            }

            @Override
            public void onBookRepay(boolean result) {
                mLibraryCallback.onBookRepay(result);
            }
        };
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        isConnected = true;

        mLibrary = Library.Stub.asInterface(service);

        try {
            mLibrary.registerOnLibraryChangeListener(mLibraryChangeListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isConnected = false;
    }

    public void unregisterListener() {
        try {
            mLibrary.unregisterOnLibraryChangeListener(mLibraryChangeListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Library takeLibrary() {
        return mLibrary;
    }

    public boolean isConnected() {
        return isConnected;
    }
}

package com.parts.zn.nine.aidl;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zhangnan
 * @date 2018/1/30
 */

public class LibraryManager extends Library.Stub {

    public static final String TAG = LibraryManager.class.getSimpleName();

    private RemoteCallbackList<ILibraryChangeListener> mLibraryChangeList;
    private ConcurrentLinkedQueue<Book> mBookList;

    public LibraryManager() {
        mLibraryChangeList = new RemoteCallbackList<>();
        mBookList = new ConcurrentLinkedQueue<>();
        mBookList.add(new Book("天龙八部", "金庸", "2222年2月22日"));
    }

    @Override
    public Book borrow(String bookName) {
        Book borrowBook = null;
        for (Book book : mBookList) {
            if (TextUtils.equals(book.getName(), bookName)) {
                borrowBook = mBookList.remove();
            }
        }
        final Book finalBorrowBook = borrowBook;
        notifyLibraryChange(new LibraryChangeHandler() {
            @Override
            public void onLibraryChange(ILibraryChangeListener broadcastItem) throws RemoteException {
                broadcastItem.onBookBorrow(finalBorrowBook);
                if (finalBorrowBook != null) {
                    Log.d(TAG, "图书馆借出一本 " + finalBorrowBook.toString());
                } else {
                    Log.d(TAG, "图书馆暂时没有您要借的书");
                }
            }
        });
        return finalBorrowBook;
    }

    @Override
    public boolean repay(final Book book) {
        final boolean result = mBookList.add(book);
        notifyLibraryChange(new LibraryChangeHandler() {
            @Override
            public void onLibraryChange(ILibraryChangeListener broadcastItem) throws RemoteException {
                broadcastItem.onBookRepay(result);
                Log.d(TAG, "图书馆收回一本 " + book.toString());
            }
        });
        return result;
    }

    @Override
    public void registerOnLibraryChangeListener(ILibraryChangeListener libraryChangeListener) throws RemoteException {
        mLibraryChangeList.register(libraryChangeListener);
    }

    @Override
    public void unregisterOnLibraryChangeListener(ILibraryChangeListener libraryChangeListener) throws RemoteException {
        mLibraryChangeList.unregister(libraryChangeListener);
    }

    private void notifyLibraryChange(LibraryChangeHandler libraryChangeHandler) {
        final int N = mLibraryChangeList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            try {
                ILibraryChangeListener broadcastItem = mLibraryChangeList.getBroadcastItem(i);
                if (broadcastItem != null) {
                    libraryChangeHandler.onLibraryChange(broadcastItem);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mLibraryChangeList.finishBroadcast();
    }

    protected interface LibraryChangeHandler {

        void onLibraryChange(ILibraryChangeListener broadcastItem) throws RemoteException;
    }
}

package com.parts.zn.nine.aidltest;

import com.parts.zn.nine.aidl.Book;

/**
 * @author zhangnan
 * @date 2018/1/30
 */

public interface LibraryCallback {

    void onBookBorrow(Book book);

    void onBookRepay(boolean result);

}

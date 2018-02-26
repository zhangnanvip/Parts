package com.parts.zn.nine.aidl;

import com.parts.zn.nine.aidl.Book;

interface ILibraryChangeListener{

    void onBookBorrow(inout Book book);

    void onBookRepay(boolean result);

}
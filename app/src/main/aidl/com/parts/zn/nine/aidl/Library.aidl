package com.parts.zn.nine.aidl;

import com.parts.zn.nine.aidl.Book;
import com.parts.zn.nine.aidl.ILibraryChangeListener;

interface Library{

   Book borrow(String bookName);

   boolean repay(inout Book book);

   void registerOnLibraryChangeListener(ILibraryChangeListener libraryChangeListener);

   void unregisterOnLibraryChangeListener(ILibraryChangeListener libraryChangeListener);

}
package com.parts.zn.nine.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author zhangnan
 * @date 2018/1/30
 */

public class Book implements Parcelable {

    private String name;
    private String author;
    private String publicationDate;

    public Book(String name, String author, String publicationDate) {
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.author);
        dest.writeString(this.publicationDate);
    }

    public void readFromParcel(Parcel in) {
        this.name = in.readString();
        this.author = in.readString();
        this.publicationDate = in.readString();
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.name = in.readString();
        this.author = in.readString();
        this.publicationDate = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"author\":\"")
                .append(author).append('\"');
        sb.append(",\"publicationDate\":\"")
                .append(publicationDate).append('\"');
        sb.append('}');
        return sb.toString();
    }
}

package com.example.haojie06.everydayn.object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by haojie06 on 2018/3/31.
 */

public class Books implements Parcelable {
    private String bookTitle,bookAuthor,bookCatalog,bookContent,bookPicUrl;

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookCatalog() {
        return bookCatalog;
    }

    public String getBookContent() {
        return bookContent;
    }

    public String getBookPicUrl() {
        return bookPicUrl;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookCatalog(String bookCatalog) {
        this.bookCatalog = bookCatalog;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }

    public void setBookPicUrl(String bookPicUrl) {
        this.bookPicUrl = bookPicUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookTitle);
        dest.writeString(this.bookAuthor);
        dest.writeString(this.bookCatalog);
        dest.writeString(this.bookContent);
        dest.writeString(this.bookPicUrl);
    }

    public Books() {
    }

    protected Books(Parcel in) {
        this.bookTitle = in.readString();
        this.bookAuthor = in.readString();
        this.bookCatalog = in.readString();
        this.bookContent = in.readString();
        this.bookPicUrl = in.readString();
    }

    public static final Parcelable.Creator<Books> CREATOR = new Parcelable.Creator<Books>() {
        @Override
        public Books createFromParcel(Parcel source) {
            return new Books(source);
        }

        @Override
        public Books[] newArray(int size) {
            return new Books[size];
        }
    };
}

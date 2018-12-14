package com.example.yue2018a.model;



import com.example.yue2018a.bean.BookBean;
import com.example.yue2018a.bean.BookDetails;
import com.example.yue2018a.contact.IBookApi;
import com.example.yue2018a.utils.RetrofitManager;

import io.reactivex.Observable;

/**
 * date:2018/12/21
 * author:mxy(M)
 * function:
 */

public class BookModel {

    public Observable<BookBean> getBook(String tag, int start, int count) {
        IBookApi iBookApi = RetrofitManager.getInstance().create(IBookApi.class);
        return iBookApi.getBook(tag, start, count);
    }

    public Observable<BookDetails> getBookDetails(long bookId, int start, int count) {
        IBookApi iBookApi = RetrofitManager.getInstance().create(IBookApi.class);
        return iBookApi.getBookDetails(bookId, start, count);
    }
}

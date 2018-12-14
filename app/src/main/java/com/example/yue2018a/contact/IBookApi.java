package com.example.yue2018a.contact;


import com.example.yue2018a.bean.BookBean;
import com.example.yue2018a.bean.BookDetails;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * date:2018/12/21
 * author:mxy(M)
 * function:
 */

public interface IBookApi {

    @GET("book/search")
    Observable<BookBean> getBook(@Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    @GET("book/{bookId}/reviews")
    Observable<BookDetails> getBookDetails(@Path("bookId") long bookId, @Query("start") int start, @Query("count") int count);
}

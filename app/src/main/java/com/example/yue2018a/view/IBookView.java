package com.example.yue2018a.view;

import com.example.yue2018a.base.IView;
import com.example.yue2018a.bean.BookBean;
import com.example.yue2018a.bean.BookDetails;

/**
 * date:2018/12/21
 * author:mxy(M)
 * function:
 */

public interface IBookView extends IView {

    void getBook(BookBean data);

    void getBookDetails(BookDetails data);

    void onBookFailed(Throwable t);
}

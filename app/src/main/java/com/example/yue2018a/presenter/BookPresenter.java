package com.example.yue2018a.presenter;



import com.example.yue2018a.base.BasePresenter;
import com.example.yue2018a.bean.BookBean;
import com.example.yue2018a.bean.BookDetails;
import com.example.yue2018a.model.BookModel;
import com.example.yue2018a.view.IBookView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/21
 * author:mxy(M)
 * function:
 */

public class BookPresenter extends BasePresenter<IBookView> {

    private BookModel model;

    @Override
    protected void provideModel() {
        model = new BookModel();
    }

    public void getBook(String tag, int start, int count) {
        model.getBook(tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookBean>() {
                    @Override
                    public void accept(BookBean bookBean) throws Exception {
                        if (bookBean != null) {
                            if (iView != null) {
                                iView.getBook(bookBean);
                                return;
                            } else {
                                iView.onBookFailed(new Throwable("服务未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onBookFailed(new Throwable("请求失败"));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onBookFailed(new Throwable("请求失败"));
                        }
                    }
                });
    }

    public void getBookDetails(long bookId, int start, int count) {
        model.getBookDetails(bookId, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookDetails>() {
                    @Override
                    public void accept(BookDetails bookDetails) throws Exception {
                        if (bookDetails != null && bookDetails.getReviews() != null) {
                            if (iView != null) {
                                iView.getBookDetails(bookDetails);
                                return;
                            } else {
                                iView.onBookFailed(new Throwable("服务未响应"));
                            }
                        } else {
                            if (iView != null) {
                                iView.onBookFailed(new Throwable("请求失败"));
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iView != null) {
                            iView.onBookFailed(new Throwable("请求失败"));
                        }
                    }
                });
    }
}

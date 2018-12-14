package com.example.yue2018a.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;


import com.example.yue2018a.BookDetailsActivity;
import com.example.yue2018a.R;
import com.example.yue2018a.adapter.BookAdapter;
import com.example.yue2018a.base.BaseFragment;
import com.example.yue2018a.bean.BookBean;
import com.example.yue2018a.bean.BookDetails;
import com.example.yue2018a.presenter.BookPresenter;
import com.example.yue2018a.view.IBookView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * date:2018/12/21
 * author:mxy(M)
 * function:
 */

public class BookFragment extends BaseFragment<BookPresenter> implements IBookView {

    private static final String TAG = "BookFragment";
    public static final String TAB = "tab";
    @BindView(R.id.xrv_books)
    XRecyclerView xrvBooks;
    Unbinder unbinder;
    private String type;
    private int start = 0;
    private int count = 10;
    private List<BookBean.BooksBean> list;
    private BookAdapter bookAdapter;
    private boolean isLoadMore = false;

    @Override
    public void getBook(BookBean data) {
        if (!isLoadMore) {
            list.clear();
            xrvBooks.refreshComplete();
        } else {
            xrvBooks.loadMoreComplete();
        }
        list.addAll(data.getBooks());
        bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBookDetails(BookDetails data) {

    }

    @Override
    public void onBookFailed(Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        // 获取单例传来的参数
        Bundle bundle = getArguments();
        type = bundle.getString(TAB);

        // 列表的配置
        xrvBooks.setPullRefreshEnabled(true);
        xrvBooks.setLoadingMoreEnabled(true);
        xrvBooks.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                start = 0;
                isLoadMore = false;
                presenter.getBook(type, start, count);
            }

            @Override
            public void onLoadMore() {
                start += count;
                isLoadMore = true;
                presenter.getBook(type, start, count);
            }
        });
        list = new ArrayList<>();
        bookAdapter = new BookAdapter(getActivity(), list);
        // book的点击监听
        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                BookBean.BooksBean book = list.get(position);
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                intent.putExtra("book", book);
                startActivity(intent);
            }
        });
        xrvBooks.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrvBooks.setAdapter(bookAdapter);

        // 请求网络数据
        presenter.getBook(type, start, count);
    }

    public static BookFragment getInstance(String tab) {
        BookFragment bookFragment = new BookFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TAB, tab);
        bookFragment.setArguments(bundle);
        return bookFragment;
    }

    @Override
    protected BookPresenter providePresenter() {
        return new BookPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_book;
    }

   /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        type = bundle.getString(TAB);

        // 列表的配置
        xrvBooks.setPullRefreshEnabled(true);
        xrvBooks.setLoadingMoreEnabled(true);
        list = new ArrayList<>();
        bookAdapter = new BookAdapter(getActivity(), list);
        xrvBooks.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrvBooks.setAdapter(bookAdapter);

        // 请求网络数据
        presenter = new BookPresenter();
        presenter.getBook(type, start, count);
    }

    public static BookFragment getInstance(String tab) {
        BookFragment bookFragment = new BookFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TAB, tab);
        bookFragment.setArguments(bundle);
        return bookFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getBook(BookBean data) {
        Log.i(TAG, "getBook: " + data.getBooks().size());
        list.addAll(data.getBooks());
        bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBookDetails(BookDetails data) {

    }

    @Override
    public void onBookFailed(Throwable t) {

    }*/
}

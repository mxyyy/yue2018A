package com.example.yue2018a;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.yue2018a.adapter.JudgeAdapter;
import com.example.yue2018a.base.BaseActivity;
import com.example.yue2018a.bean.BookBean;
import com.example.yue2018a.bean.BookDetails;
import com.example.yue2018a.presenter.BookPresenter;
import com.example.yue2018a.view.IBookView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BookDetailsActivity extends BaseActivity<BookPresenter> implements IBookView {

    @BindView(R.id.sdv_logo)
    SimpleDraweeView sdvLogo;
    @BindView(R.id.img_cancel)
    ImageView imgCancel;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.txt_book_name)
    TextView txtBookName;
    @BindView(R.id.txt_rating)
    TextView txtRating;
    @BindView(R.id.txt_introduce)
    TextView txtIntroduce;
    @BindView(R.id.txt_summary)
    TextView txtSummary;
    @BindView(R.id.txt_extends)
    TextView txtExtends;
    @BindView(R.id.rv_judge)
    XRecyclerView rvJudge;
    private int start = 0;
    private int count = 10;
    private BookBean.BooksBean book;
    private boolean isExtend = false;
    private List<BookDetails.ReviewsBean> list;
    private JudgeAdapter judgeAdapter;
    private boolean isLoadMore = false;
    private String id;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        book = (BookBean.BooksBean) intent.getSerializableExtra("book");
        id = book.getId();
        presenter.getBookDetails(Long.parseLong(id), start, count);

        // 设置书本信息
        sdvLogo.setImageURI(book.getImage());
        txtBookName.setText(book.getTitle());
        txtRating.setText("评分：" + book.getRating().getAverage() + "\t\t" + book.getRating().getNumRaters() + "人点评");
        if (book.getAuthor().size() != 0) {
            if (book.getPubdate().length() != 0) {
                txtIntroduce.setText(book.getAuthor().get(0) + "/" + book.getPublisher() + "/" + book.getPubdate());
            } else {
                txtIntroduce.setText(book.getAuthor().get(0) + "/" + book.getPublisher());
            }
        } else {
            if (book.getPubdate().length() != 0) {
                txtIntroduce.setText(book.getPublisher() + "/" + book.getPubdate());
            } else {
                txtIntroduce.setText(book.getPublisher());
            }
        }
        txtSummary.setText(book.getSummary());

        // 列表的配置
        rvJudge.setPullRefreshEnabled(true);
        rvJudge.setLoadingMoreEnabled(true);
        rvJudge.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                start = 0;
                isLoadMore = false;
                presenter.getBookDetails(Long.parseLong(id), start, count);
            }

            @Override
            public void onLoadMore() {
                start += count;
                isLoadMore = true;
                presenter.getBookDetails(Long.parseLong(id), start, count);
            }
        });
        // 配置评论列表
        list = new ArrayList<>();
//        rvJudge.setNestedScrollingEnabled(false);
        judgeAdapter = new JudgeAdapter(this, list);
        rvJudge.setLayoutManager(new LinearLayoutManager(this));
        rvJudge.setAdapter(judgeAdapter);
    }

    @Override
    protected BookPresenter providePresenter() {
        return new BookPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_book_details;
    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void getBook(BookBean data) {

    }

    @Override
    public void getBookDetails(BookDetails data) {
        if (!isLoadMore) {
            list.clear();
            rvJudge.refreshComplete();
        } else {
            rvJudge.loadMoreComplete();
        }
        list.addAll(data.getReviews());
        judgeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBookFailed(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.img_cancel, R.id.img_share, R.id.txt_extends})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_cancel:
                finish();
                break;
            case R.id.img_share:
                Toast.makeText(this, "点击分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt_extends:
                isExtend = !isExtend;
                if (isExtend) {
                    txtExtends.setText("——————————点击收起——————————");
                    txtSummary.setLines(30);
                    txtSummary.setEllipsize(null);
                } else {
                    txtExtends.setText("——————————点击展开——————————");
                    txtSummary.setEllipsize(TextUtils.TruncateAt.END);
                    txtSummary.setLines(4);
                }
                break;
        }
    }
}

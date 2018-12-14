package com.example.yue2018a;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.util.Log;

import com.example.yue2018a.base.BaseActivity;
import com.example.yue2018a.bean.BookBean;
import com.example.yue2018a.bean.BookDetails;
import com.example.yue2018a.fragment.BookFragment;
import com.example.yue2018a.presenter.BookPresenter;
import com.example.yue2018a.view.IBookView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.Nullable;

public class MainActivity extends BaseActivity<BookPresenter> implements IBookView {

    private static final String TAG = "MainActivity";
    @BindView(R.id.tbl_tab)
    TabLayout tblTab;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    String[] tabs = {"新书", "推荐", "热门", "小说"};
    private List<String> titleList;
    private List<BookFragment> fragmentList;
    private FragmentPagerAdapter adapter;

    @Override
    protected void initData() {
        // 配置tab标签 和fragment集合
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        for (String tab : tabs) {
            titleList.add(tab);
            Log.i(TAG, "initData: " + tab);
            BookFragment bookFragment = BookFragment.getInstance(tab);
            fragmentList.add(bookFragment);
        }

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return titleList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        };
        vpContent.setAdapter(adapter);
        tblTab.setTabMode(TabLayout.MODE_FIXED);
        tblTab.setupWithViewPager(vpContent);
    }

    @Override
    protected BookPresenter providePresenter() {
        return new BookPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getBook(BookBean data) {

    }

    @Override
    public void getBookDetails(BookDetails data) {

    }

    @Override
    public void onBookFailed(Throwable t) {

    }
}

package com.example.yue2018a.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.yue2018a.R;
import com.example.yue2018a.bean.BookBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2018/12/21
 * author:mxy(M)
 * function:
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private static final String TAG = "BookAdapter";
    private Context context;
    private List<BookBean.BooksBean> list;

    public BookAdapter(Context context, List<BookBean.BooksBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        View view = View.inflate(context, R.layout.item_book, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        BookBean.BooksBean books = list.get(position);
        holder.sdvLogo.setImageURI(books.getImage());
        holder.txtBookName.setText(books.getTitle());
        if (books.getAuthor().size() != 0) {
            if (books.getPubdate().length() != 0) {
                holder.txtIntroduce.setText(books.getAuthor().get(0) + "/" + books.getPublisher() + "/" + books.getPubdate());
            } else {
                holder.txtIntroduce.setText(books.getAuthor().get(0) + "/" + books.getPublisher());
            }
        } else {
            if (books.getPubdate().length() != 0) {
                holder.txtIntroduce.setText(books.getPublisher() + "/" + books.getPubdate());
            } else {
                holder.txtIntroduce.setText(books.getPublisher());
            }
        }
        holder.txtSummary.setText("\t\t" + books.getSummary());
        holder.txtRating.setText("评分：" + books.getRating().getAverage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sdv_logo)
        SimpleDraweeView sdvLogo;
        @BindView(R.id.txt_book_name)
        TextView txtBookName;
        @BindView(R.id.txt_rating)
        TextView txtRating;
        @BindView(R.id.txt_introduce)
        TextView txtIntroduce;
        @BindView(R.id.txt_summary)
        TextView txtSummary;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

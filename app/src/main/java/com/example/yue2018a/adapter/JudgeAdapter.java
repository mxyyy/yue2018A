package com.example.yue2018a.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.yue2018a.R;
import com.example.yue2018a.bean.BookDetails;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2018/12/21
 * author:mxy(M)
 * function:
 */

public class JudgeAdapter extends RecyclerView.Adapter<JudgeAdapter.ViewHolder> {

    private Context context;
    private List<BookDetails.ReviewsBean> list;

    public JudgeAdapter(Context context, List<BookDetails.ReviewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_judge, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sdvLogo.setImageURI(list.get(position).getAuthor().getAvatar());
        holder.txtUserName.setText(list.get(position).getAuthor().getName());
        holder.txtRating.setText("评分：" + list.get(position).getRating().getValue());
        holder.txtTitle.setText(list.get(position).getTitle());
        holder.txtDetails.setText(list.get(position).getSummary());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sdv_logo)
        SimpleDraweeView sdvLogo;
        @BindView(R.id.txt_user_name)
        TextView txtUserName;
        @BindView(R.id.txt_rating)
        TextView txtRating;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_details)
        TextView txtDetails;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

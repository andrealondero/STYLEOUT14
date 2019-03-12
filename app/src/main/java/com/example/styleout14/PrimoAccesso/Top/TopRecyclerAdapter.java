package com.example.styleout14.PrimoAccesso.Top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.styleout14.R;

import java.util.List;

public class TopRecyclerAdapter extends RecyclerView.Adapter<TopRecyclerAdapter.MyViewHolder> {

    Context mContext;
    List<Top> mData;

    public TopRecyclerAdapter(Context mContext, List<Top> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate( R.layout.top_item_recycler, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder myViewHolder, int position) {
        myViewHolder.imageView.setImageResource(mData.get(position).getModelImage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public MyViewHolder( View itemView) {
            super( itemView );

            imageView = (ImageView) itemView.findViewById( R.id.img_top);
        }
    }
}

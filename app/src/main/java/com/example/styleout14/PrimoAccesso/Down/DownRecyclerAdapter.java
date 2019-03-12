package com.example.styleout14.PrimoAccesso.Down;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.styleout14.R;

import java.util.List;

public class DownRecyclerAdapter extends RecyclerView.Adapter<DownRecyclerAdapter.MyViewHolder> {

    Context dContext;
    List<Down> dData;

    public DownRecyclerAdapter(Context dContext, List<Down> dData) {
        this.dContext = dContext;
        this.dData = dData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(dContext).inflate( R.layout.down_item_recycler, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder myViewHolder, int position) {
        myViewHolder.imageDownView.setImageResource(dData.get(position).getModelDownImage());
    }

    @Override
    public int getItemCount() {
        return dData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageDownView;

        public MyViewHolder( View itemView) {
            super( itemView );

            imageDownView = (ImageView) itemView.findViewById(R.id.img_down);
        }
    }
}

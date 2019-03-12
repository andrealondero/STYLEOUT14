package com.example.styleout14.PrimoAccesso.Up;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.styleout14.R;

import java.util.List;

public class UpRecyclerAdapter extends RecyclerView.Adapter<UpRecyclerAdapter.MyViewHolder> {

    Context uContext;
    List<Up> uData;

    public UpRecyclerAdapter(Context uContext, List<Up> uData) {
        this.uContext = uContext;
        this.uData = uData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(uContext).inflate( R.layout.up_item_recycler, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder myViewHolder, int position) {
        myViewHolder.imageUpView.setImageResource(uData.get(position).getModelUpImage());
    }

    @Override
    public int getItemCount() {
        return uData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageUpView;

        public MyViewHolder( View itemView) {
            super( itemView );

            imageUpView = (ImageView) itemView.findViewById(R.id.img_up);
        }
    }
}

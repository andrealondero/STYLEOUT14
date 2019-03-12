package com.example.styleout14.PrimoAccesso;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.styleout14.R;

import java.util.List;

public class ColourSelectorAdapter extends ArrayAdapter<RowItem> {

    LayoutInflater colorInflater;
    Context colorContext;
    RowItem rowItem;

    public ColourSelectorAdapter(Activity context, int resourceId, int textViewId, List<RowItem> list) {

        super( context, resourceId, textViewId, list );
        colorInflater = context.getLayoutInflater();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = colorInflater.inflate( R.layout.colour_spinner, parent, false );
        }
        RowItem rowItem = getItem( position );
        TextView txtTitle = (TextView) convertView.findViewById( R.id.color );
        txtTitle.setText( rowItem.getImageName() );
        ImageView imageView = (ImageView) convertView.findViewById( R.id.colourImage );
        imageView.setImageResource( rowItem.getImageId() );
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return rowView( convertView, position );
    }

    private View rowView(View convertView, int position) {

        RowItem rowItem = getItem( position );
        viewHolder holder;
        View rowView = convertView;

        if (rowView == null) {
            holder = new viewHolder();
            colorInflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            rowView = colorInflater.inflate( R.layout.colour_spinner, null, false );

            holder.txtTitle = (TextView) rowView.findViewById( R.id.color );
            holder.imageView = (ImageView) rowView.findViewById( R.id.colourImage );
            rowView.setTag( holder );
        } else {
            holder = (viewHolder) rowView.getTag();
        }
        holder.imageView.setImageResource( rowItem.getImageId() );
        holder.txtTitle.setText( rowItem.getImageName() );
        return rowView;
    }

    private class viewHolder {

        TextView txtTitle;
        ImageView imageView;

    }
}

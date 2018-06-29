package com.example.janusz.finalapp_v1.SQLiteNotes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.janusz.finalapp_v1.R;

import java.util.ArrayList;

/**
 * Created by janusz on 11.04.2018.
 */

public class NotesAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Notes> notelist;

    public NotesAdapter(Context context, int layout, ArrayList<Notes> foodsList) {
        this.context = context;
        this.layout = layout;
        this.notelist = foodsList;
    }

    @Override
    public int getCount() {
        return notelist.size();
    }

    @Override
    public Object getItem(int position) {
        return notelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtNote, txtDate;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtNote = (TextView) row.findViewById(R.id.tvNote);
            holder.txtDate = (TextView) row.findViewById(R.id.tvTitle);
            holder.imageView = (ImageView) row.findViewById(R.id.imgUser);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Notes food = notelist.get(position);

        holder.txtNote.setText(food.getNote());
        holder.txtDate.setText(food.getDate());

//        byte[] foodImage = food.getImage();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
//        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
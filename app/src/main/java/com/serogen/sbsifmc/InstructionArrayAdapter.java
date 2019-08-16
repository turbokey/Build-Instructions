package com.serogen.sbsifmc;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InstructionArrayAdapter extends ArrayAdapter<InstructionItem> {

    private final Activity context;
    private final ArrayList<InstructionItem> list;
    private final int layoutResourceId;

    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }



    public InstructionArrayAdapter(Activity context, int layoutResourceId,ArrayList<InstructionItem> list) {
        super(context, layoutResourceId,list);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(layoutResourceId,null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = rowView.findViewById(R.id.instruction_text);
            viewHolder.image = rowView.findViewById(R.id.instruction_picture);
            rowView.setTag(viewHolder);
        }

        final ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.text.setText(list.get(position).getText());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (50 * scale + 0.5f);
        int width = displayMetrics.widthPixels - pixels;
        holder.image.getLayoutParams().height = (int)(width*0.75);
        Picasso
                .with(context)
                .load(list.get(position).getImageResourceId())
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(holder.image);

        return rowView;
    }
}

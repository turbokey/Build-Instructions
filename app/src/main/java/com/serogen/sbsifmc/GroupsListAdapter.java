package com.serogen.sbsifmc;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GroupsListAdapter extends BaseAdapter {

    private final Activity context;
    private final ArrayList<GroupItem> list;


    public GroupsListAdapter(Activity context, ArrayList<GroupItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.group_list_item,viewGroup,false);
        }

        TextView textView = view.findViewById(R.id.group_item_text);
        ImageView imageView = view.findViewById(R.id.group_item_image);

        textView.setText(list.get(position).getText());
        Picasso
                .with(context)
                .load(list.get(position).getImageResourceId())
                .placeholder(R.drawable.placeholder)
                .into(imageView);

        return view;
    }
}

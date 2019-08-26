package com.serogen.sbsifmc;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InstructionListAdapter extends ArrayAdapter<InstructionItemFromInstructionsList> implements Filterable {

    private Activity context;
    private ArrayList<InstructionItemFromInstructionsList> list;
    private ArrayList<InstructionItemFromInstructionsList> filteredList;
    private int layoutResourceId;

    private ModelFilter filter;

    static class ViewHolder {
        public TextView text;
        public ImageView image;
        public Button btn;
    }



    public InstructionListAdapter(Activity context, int layoutResourceId,ArrayList<InstructionItemFromInstructionsList> list) {
        super(context, layoutResourceId,list);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.list = new ArrayList<>();
        this.list.addAll(list);
        this.filteredList = new ArrayList<>();
        this.filteredList.addAll(list);
        getFilter();
    }
    @Override
    public Filter getFilter() {
        if (filter == null){
            filter  = new ModelFilter();
        }
        return filter;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(layoutResourceId,null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = rowView.findViewById(R.id.instruction_item_name);
            viewHolder.image = rowView.findViewById(R.id.instruction_item_image);
            viewHolder.btn = rowView.findViewById(R.id.instruction_item_open_btn);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.text.setText(filteredList.get(position).getInstructionName());
        Picasso
                .with(context)
                .load(filteredList.get(position).getImageResourceId())
                .fit()
                .centerCrop()
                .into(holder.image);
        holder.btn.setTag(position);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer)v.getTag();
                ((MainActivity)context).onInstructionItemClicked(filteredList.get(position).getId());
            }
        });

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
    private class ModelFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if(constraint.toString().length() > 0)
            {
                ArrayList<InstructionItemFromInstructionsList> filteredItems = new ArrayList<InstructionItemFromInstructionsList>();

                for(int i = 0, l = list.size(); i < l; i++)
                {
                    InstructionItemFromInstructionsList m = list.get(i);
                    if(m.getInstructionName().toLowerCase().contains(constraint) || (((String) constraint).toLowerCase().equals("член") && m.getId()==14))
                        filteredItems.add(m);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }
            else
            {
                synchronized(this)
                {
                    result.values = list;
                    result.count = list.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            filteredList = (ArrayList<InstructionItemFromInstructionsList>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0, l = filteredList.size(); i < l; i++)
                add(filteredList.get(i));
            notifyDataSetInvalidated();
        }
    }
}

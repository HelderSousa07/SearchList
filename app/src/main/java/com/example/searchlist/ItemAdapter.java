package com.example.searchlist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private List<Item> itemList;
    private List<Item> itemListFull;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
        itemListFull = new ArrayList<>(itemList);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item currentItem = itemList.get(position);

        holder.imageView.setImageResource(currentItem.getImageSrc());
        holder.textView1.setText(currentItem.getName());
        holder.textView2.setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Item> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(itemListFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Item item : itemListFull){

                    int match_ratio = FuzzySearch.partialRatio(item.getName().toLowerCase(),filterPattern);
                    if (match_ratio > 55){
                        filteredList.add(item);
                    }

                    Log.i("TESTE", "palavra 1: "+item.getName().toLowerCase()+" palavra 2: "
                            +filterPattern+" ratio: " + FuzzySearch.partialRatio(item.getName().toLowerCase(),filterPattern));
                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            itemList.clear();
            itemList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView1;
        TextView textView2;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView1 = itemView.findViewById(R.id.text_view1);
            textView2 = itemView.findViewById(R.id.text_view2);
        }
    }

}

package com.web2droid.endlessscrollingrecylerview;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Harsh on 07-Aug-17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    OnRecylerViewItemClickListner onRecylerViewItemClickListner;
    private List<String> myList;

    public DataAdapter(List<String> myList, OnRecylerViewItemClickListner onRecylerViewItemClickListner) {
        this.myList = myList;
        this.onRecylerViewItemClickListner = onRecylerViewItemClickListner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView.setText(myList.get(position));

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecylerViewItemClickListner.OnItemClick(holder.textView.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = (AppCompatTextView) itemView.findViewById(R.id.text1);
        }
    }
}

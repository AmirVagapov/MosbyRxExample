package com.vagapov.amir.a3lesson1.view;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerHolder>{


    private List<String> list;

    MyRecyclerViewAdapter(){

    }

    @Override
    public MyRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
        return new MyRecyclerHolder(view);
    }

    ArrayList<String> getList(){
        return (ArrayList<String>) list;
    }

    void setList(ArrayList<String> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MyRecyclerHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @NonNull
    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        } else return 0;
    }

    static class MyRecyclerHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        MyRecyclerHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(android.R.id.text1);
        }
    }
}

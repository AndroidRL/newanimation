package com.newanimation.mixanimation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.newanimation.mixanimation.R;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> feedItems;

    public TestAdapter(Context context2, List<String> list) {
        this.feedItems = list;
        this.context = context2;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_test, viewGroup, false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    public int getItemCount() {
        return this.feedItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txt;

        public MyViewHolder(View view) {
            super(view);

            txt = (TextView) view.findViewById(R.id.txt);

        }
    }

}

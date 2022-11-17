package com.example.training;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.training.db.entity.Task;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

    private final List<Task> mList;
    private final Context mContext;

    public CountriesAdapter(Context context, List<Task> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = mList.get(position);
        holder.textViewName.setText(task.getName());
        holder.textViewDescription.setText(task.getDescription());

//        holder.layout.setOnClickListener(view -> {
//            Intent homeDetailIntent = HomeDetailActivity.getStartIntent(mContext, countryName, mUserName);
//            mContext.startActivity(homeDetailIntent);
//        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout layout;
        public TextView textViewName, textViewDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.listItemLayout);
            textViewName = itemView.findViewById(R.id.textViewListItemName);
            textViewDescription = itemView.findViewById(R.id.textViewListItemDescription);
        }
    }
}

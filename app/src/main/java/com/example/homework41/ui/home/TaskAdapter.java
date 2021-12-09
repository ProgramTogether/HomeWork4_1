package com.example.homework41.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework41.databinding.ItemRvBinding;
import com.example.homework41.ui.form.FormModel;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ItemRvBinding binding;
    List<FormModel> list = new ArrayList<>();
    LayoutInflater inflater;

    public TaskAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void addText(FormModel text) {
        list.add(text);
        notifyDataSetChanged();
    }

    public List<FormModel> getList() {
        return list;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        binding.txtTitle.setText(list.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

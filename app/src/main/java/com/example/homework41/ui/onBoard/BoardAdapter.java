package com.example.homework41.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework41.R;
import com.example.homework41.databinding.PageBoardBinding;

import static com.example.homework41.R.drawable.img_d;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private String[] list = {"1", "2", "3", "4"};
    private int[] imgList = {R.drawable.img_a, R.drawable.img_b, R.drawable.img_c, img_d};
    private PageBoardBinding binding;

    protected ClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PageBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());

    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.onBoard(position);
        binding.btnBoard.setOnClickListener(v -> {
            listener.click();
        });
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }


        public void onBoard(int position) {
            binding.boardFirstTv.setText(list[position]);
            binding.boardImgView.setImageResource(imgList[position]);
            if (imgList[position] == img_d) {
                binding.btnBoard.setVisibility(View.VISIBLE);
            }
        }
    }

    interface ClickListener{
        void click();
    }
}


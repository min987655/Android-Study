package com.cos.androidappv02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder>{

    private static final String TAG = "ProfContentAdapter";
    private List<Content> items = new ArrayList<>();

    public void addItem(Content content) {
        items.add(content);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_content, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Content content = items.get(position);
        holder.setItem(content);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgContent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgContent = itemView.findViewById(R.id.img_content);
        }
        public void setItem(Content content){
            imgContent.setImageResource(content.getImgContent());
        }
    }
}

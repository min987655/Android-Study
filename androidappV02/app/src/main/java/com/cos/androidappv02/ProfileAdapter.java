package com.cos.androidappv02;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder>{

    private static final String TAG = "ProfileAdapter";
    private List<Profile> items = new ArrayList<>();

    public void addItem(Profile profile) {
        items.add(profile);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_profile, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Profile profile = items.get(position);
        holder.setItem(profile);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgPro;
        private TextView tvProfile;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPro = itemView.findViewById(R.id.img_pro);
            tvProfile = itemView.findViewById(R.id.tv_profile);
        }
        public void setItem(Profile profile){
            imgPro.setImageResource(profile.getImgPro());
            tvProfile.setText(profile.getProTitle());
        }
    }
}

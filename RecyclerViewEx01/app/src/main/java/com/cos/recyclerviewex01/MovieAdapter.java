package com.cos.recyclerviewex01;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private static final String TAG = "MovieAdapter";
    private List<Movie> items = new ArrayList<>();

    // 아이템 한건씩 받는 함수(컬렉션을 수정하는 함수, 삭제 등등도 만들어서 사용해야 함)
    public void addItem(Movie movie){
        items.add(movie);
    }

    // 1번으로 실행 됨
    // 리스트뷰의 겟뷰와 비슷
    // 최초에(inCreate()) 생성 됨. 최초 이후에는 생성 안됨
    // 뷰홀더의 생김새만 던져 줌(몇개의 뷰가 끼워진 홀더인지) : 껍데기만 만듬.
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    // 2번으로 실행 됨
    // 데이터를 뷰홀더에 끼워 넣음 : 껍데기에 데이터 바인딩 해줌
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: "+position);
        Movie movie = items.get(position);
        holder.setItem(movie);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ViewHolder : 뷰들의 책꽂이
    // 인플레이터 된 뷰들을 넣을 홀더
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // 규칙 1 : 해당 xml이 가진 모든 뷰를 다 선언
        private TextView tvTitle;
        private ImageView ivImgResource;

        // 최초에 화면 사이즈만큼 뷰홀더에 뷰를 담고, 그 뒤로는 뷰홀더 안만듬.
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // 규칙 2
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivImgResource = itemView.findViewById(R.id.iv_img_resource);
        }

        // 규칙 3 : 데이터 바인딩을 위한 함수 생성
        public void setItem(Movie movie) {
            tvTitle.setText(movie.getTitle());
            ivImgResource.setImageResource(movie.getImgResource());
        }
    }
}

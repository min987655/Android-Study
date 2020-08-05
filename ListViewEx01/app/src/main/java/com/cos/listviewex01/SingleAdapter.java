package com.cos.listviewex01;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SingleAdapter extends BaseAdapter {

    private static final String TAG = "SingleAdapter";

    // 데이터 한건씩 받는것도 만들어 놔야 함
    private List<Movie> items = new ArrayList<>();

    public SingleAdapter() {
        items.add(new Movie("써니",R.drawable.mov01));
        items.add(new Movie("완득이",R.drawable.mov02));
        items.add(new Movie("괴물",R.drawable.mov03));
        items.add(new Movie("라디오스타",R.drawable.mov04));
        items.add(new Movie("비열한거리",R.drawable.mov05));
        items.add(new Movie("왕의남자",R.drawable.mov06));
        items.add(new Movie("아일랜드",R.drawable.mov07));
        items.add(new Movie("웰컴투동막골",R.drawable.mov08));
        items.add(new Movie("헬보이",R.drawable.mov09));
        items.add(new Movie("백투터퓨쳐",R.drawable.mov10));
        items.add(new Movie("여인의향기",R.drawable.mov11));
        items.add(new Movie("쥬라기공원",R.drawable.mov12));
    }

    // 런타임 시 화면에 뷰를 몇개를 만들지 결정하기 위해 사이즈 받음.
    @Override
    public int getCount() {
        Log.d(TAG, "getCount: ");
        return items.size();
    }

    // 이벤트 발생 시 아이템이 가진 데이터와 화면을 매칭하여 아이템의 정보를 받음.
    @Override
    public Object getItem(int position) {
        Log.d(TAG, "getItem: ");
        return items.get(position);
    }


    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId: ");
        return 0;
    }

    // 뷰를 생성하는 함수
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: " + position);
        // 레이아웃 인플레이터로 인틀레이터 객체 접근하기
        // 인플레이터 객체 생성. 인플레이터로 뷰를 메모리에 띄움
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.item, parent, false);
        TextView tv = itemView.findViewById(R.id.tv_title);
        ImageView iv = itemView.findViewById(R.id.iv_img_resource);
        String title = ((Movie) getItem(position)).getTitle();
        int imgResource = ((Movie) getItem(position)).getImgResource();
        tv.setText(title);
        iv.setImageResource(imgResource);
        return itemView;
    }
}

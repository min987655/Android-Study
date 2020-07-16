package com.cos.newapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// 어딘가에서 new Frag1();
// 동적바인딩하여 프레그먼트 내부에서 onCreateView()를 콜백해 줌
// frag_1.xml을 메모리에 띄워줌
// 함수 호출 시 컨테이너 위치 잡힘(this와 비슷)
public class Frag2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_2, container, false);

        return v;
    }
}

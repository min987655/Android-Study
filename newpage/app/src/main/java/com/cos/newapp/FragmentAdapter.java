package com.cos.newapp;

// Fragment 2가지. 어떤것을 선택할지 결정해야 함.
// FragmentPagerAdapter : 모든 프레그먼트를 메모리에 미리 로딩 시켜 둠. 소멸 안시킴 (탭 적을 시 사용)
// FragmentStatePagerAdapter : default 3, 자기를 포함한 양 옆만 메모리에 로딩 됨.

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragmentList = new ArrayList<>();

    // 디폴트 생성자
    // behavior : 숫자로 애니메이션 넣을 수 있음(defult 1)
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    // FragmentList에 아이템 추가하는 함수 필요
    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }


    // 프레그먼드의 내용을 잡아옴.
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    // new 할 때 콜백됨.
    // 전체 데이터 확인하여 몇건 띄울지 등 관리
    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

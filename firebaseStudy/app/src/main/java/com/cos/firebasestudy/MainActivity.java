package com.cos.firebasestudy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private FirebaseFirestore db;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 양방향 stream
        db = FirebaseFirestore.getInstance();

//        save();
        findAll();
//        findById();
        myStream();
    }

    // 실시간 데이터 변경 감지
    private void myStream() {
        final DocumentReference docRef = db.collection("user").document("1");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                Log.d(TAG, "myStream : onEvent: 데이터 변경됨");
                User user = documentSnapshot.toObject(User.class);
                Log.d(TAG, "myStream : onEvent: user : " + user.getPassword());
            }
        });
    }

    private void findById() {
//      db.collection("user").document("3")
        db.document("user/3")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            User user = document.toObject(User.class);
                            Log.d(TAG, "findById : onComplete: 이메일 : " + user.getEmail());
                        } else {
                            // 사용자한테 토스트 메세지 파싱해서 보여주기
                            Log.d(TAG, "onComplete: 실패 : " + task.getException());
                        }
                    }
                });
    }

    private void findAll() {
        db.collection("user").orderBy("id", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) { //QuerySnapshot 셀렉트할때 사용. 형상을 가져 옴
                        if (task.isSuccessful()) {
//                            Log.d(TAG, "onComplete: task 성공 : " + task.getResult().getDocuments());
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "findAll : onComplete: doscument : " + document.getId() + " => " + document.getData());
                                Log.d(TAG, "findAll : onComplete: email : " + document.getData().get("email"));
                                users.add(document.toObject(User.class));
                                // 뷰모델 레퍼런스 접근.data(MutableLiveData).serValue(컬렉션); : 옵져버패턴 발동해서 ui 변경 됨
                            }
                            Log.d(TAG, "findAll : onComplete: users : " + users);
                        } else {
                            Log.d(TAG, "onComplete: task 실패 : " + task.getException());
                        }
                    }
                });
    }

    private void save() {
        User user = User.builder()
                .id(4)
                .username("test4")
                .password("1111")
                .email("test4@nate.com")
                .createDate(Timestamp.now())
                .build();

        db.collection("user").document("4")
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() { // 제대로 인서트 되면 함수의 주소를 가지고 있다가 넘겨줌.
                    @Override
                    public void onSuccess(Void aVoid) {
                        // 로그인 성공시 intent로 화면전환
                        Log.d(TAG, "save : onSuccess: 인서트 잘됨");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }
                });
    }
}
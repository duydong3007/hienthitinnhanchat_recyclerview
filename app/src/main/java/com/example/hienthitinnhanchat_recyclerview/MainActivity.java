package com.example.hienthitinnhanchat_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnsend;
    private EditText edtmess;
    private RecyclerView rcvmess;
    private List<messenger> messengerList;
    private messAdapter messAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvmess=(RecyclerView) findViewById(R.id.rcvmess);
        btnsend=(Button) findViewById(R.id.btnsend);
        edtmess=(EditText) findViewById(R.id.edtmess);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rcvmess.setLayoutManager(linearLayoutManager);

        messengerList=new ArrayList<>();
        messAdapter=new messAdapter();
        messAdapter.setdata(messengerList);

        rcvmess.setAdapter(messAdapter);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setdata();
            }
        });

        edtmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkkeyboard();
            }
        });




    }

    private void setdata(){
        String mess=edtmess.getText().toString().trim();
        if(TextUtils.isEmpty(mess)){
            return;
        }

        messengerList.add(new messenger(mess));
        messAdapter.notifyDataSetChanged();
        rcvmess.scrollToPosition(messengerList.size()-1);

        edtmess.setText("");
    }


    //cuộn đến tin nhắn mới nhất khi có sự thay đổi của view
    private void checkkeyboard(){
        final View activityRootView=findViewById(R.id.layoutRoot); //truyền tới layout gốc
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r=new Rect();

                activityRootView.getWindowVisibleDisplayFrame(r);

                int heightDiff=activityRootView.getRootView().getHeight()-r.height();
                if(heightDiff>0.25*activityRootView.getRootView().getHeight()-r.height()){
                    rcvmess.scrollToPosition(messengerList.size()-1);
                    activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }
}
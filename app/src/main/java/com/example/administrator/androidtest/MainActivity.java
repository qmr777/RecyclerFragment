package com.example.administrator.androidtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.androidtest.activity.Aty1;
import com.example.administrator.androidtest.activity.Aty2;
import com.example.administrator.androidtest.adapter.CurrentPageNum;
import com.example.administrator.androidtest.fragment.Fragment1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                startActivity(new Intent(this, Aty1.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(this, Aty2.class));
                break;
        }
    }
}

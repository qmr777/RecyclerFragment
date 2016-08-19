package com.example.administrator.androidtest.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Aty3 extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "Aty3";

    SharedPreferences sp;

    public final String A123 = getSpBoolean()?"是":"否";

    @BindView(R.id.btn)
    Button button;
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty3);
        ButterKnife.bind(this);
        sp = getPreferences(MODE_PRIVATE);
        button.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    void setSpBoolean(boolean b){
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("Editor",b);
        editor.apply();
    }

    boolean getSpBoolean(){
        return sp!=null&&sp.getBoolean("Editor",false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                setSpBoolean(!getSpBoolean());
                break;
            case R.id.btn2:
                Log.i(TAG, Boolean.toString(getSpBoolean()) +"   " + A123);
        }
    }
}

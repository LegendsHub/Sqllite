package com.example.sqlitedemo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public class modifydata extends AppCompatActivity {
    Context context;

    EditText t;
    Button up,dp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifydata);
        t=findViewById(R.id.title_op);
        up=findViewById(R.id.up);
        dp=findViewById(R.id.dp);
        Bundle bundle = getIntent().getExtras();
        String ti=bundle.getString("title");
        t.setText(ti);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhandler d=new dbhandler(modifydata.this);
                d.updatedata(t.getText().toString().trim(),ti);
            }
        });
        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhandler d=new dbhandler(modifydata.this);
                d.deletedata(ti);
            }
        });
    }
}

package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
EditText title,author,pages;
Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        title=findViewById(R.id.title_ip);
        author=findViewById(R.id.author_ip);
        pages=findViewById(R.id.page_ip);
        b=findViewById(R.id.add);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhandler db=new dbhandler(AddActivity.this);
                db.addBook(title.getText().toString().trim(),
                        author.getText().toString().trim(),
                        Long.parseLong(pages.getText().toString().trim()));
            }
        });
    }
}
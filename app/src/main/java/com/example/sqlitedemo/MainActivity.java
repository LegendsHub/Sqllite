package com.example.sqlitedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView lv;
dbhandler d;
//CustomAdapter customAdapter;
ArrayList<String> id,title,author,page;

FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy()).detectLeakedClosableObjects().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        recyclerView=findViewById(R.id.recycler);
        floatingActionButton=findViewById(R.id.fl);
        lv=findViewById(R.id.lv);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,AddActivity.class);
            startActivity(intent);
            }
        });
    d=new dbhandler(MainActivity.this);
    id=new ArrayList<>();
    title=new ArrayList<>();
    author=new ArrayList<>();
    page=new ArrayList<>();
    store();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,title);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = title.get(position);
                Intent intent=new Intent(MainActivity.this,modifydata.class);
                Bundle bundle=new Bundle();
                bundle.putString("title",selectedItem);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void store() {
        Cursor c=d.readAll();
        if(c.getCount()==0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (c.moveToNext()) {
                id.add(c.getString(0));
                title.add(c.getString(1));
                author.add(c.getString(2));
                page.add(c.getString(3));
//                c.moveToNext();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.da){
            dbhandler d=new dbhandler(MainActivity.this);
            d.deleteall();
            startActivity(new Intent(this,MainActivity.class));
            Toast.makeText(this, "All Deleted", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
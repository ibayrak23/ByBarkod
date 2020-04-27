package com.example.myapplication;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

class Listeleme extends AppCompatActivity {
    veriTabani veritabani;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeleme);
        list= (ListView) findViewById(R.id.listesiz);
        veritabani=new veriTabani(Listeleme.this);
        final List<String> vVeriler=veritabani.veriListele();
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(Listeleme.this, android.R.layout.simple_list_item_1, android.R.id.text1,vVeriler);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                veritabani.delete((long) (id));
                //ArrayAdapter<String> adapter=new ArrayAdapter<String>(Listeleme.this, android.R.layout.simple_list_item_1, android.R.id.text1,vVeriler);



            }
        });
        list.setAdapter(adapter);

    }
}

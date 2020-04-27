package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    Button goster,ekle,barkod;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barkod= (Button) findViewById(R.id.BarkodOku);
        goster= (Button) findViewById(R.id.goster);
        ekle= (Button) findViewById(R.id.ekle);
        list= (ListView) findViewById(R.id.listesiz);
        barkod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent net=new Intent(MainActivity.this, Barkod.class);
                startActivity(net);

            }
        });
        goster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent net=new Intent(MainActivity.this, Listeleme.class);
                startActivity(net);

            }
        });
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent net=new Intent(MainActivity.this,Veri_Ekle.class);
                net.putExtra("mesaj", "");
                startActivity(net);

            }
        });

    }
}


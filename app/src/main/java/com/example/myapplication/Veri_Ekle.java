package com.example.myapplication;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

class Veri_Ekle extends AppCompatActivity {
    Button kayit;
    EditText no,adres;
    String deger="";
    veriTabani vvriTabani;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veri__ekle);
        vvriTabani=new veriTabani(Veri_Ekle.this);
        kayit= (Button) findViewById(R.id.kayit);
        no= (EditText) findViewById(R.id.no);
        adres= (EditText) findViewById(R.id.adres);
        Bundle extras = getIntent().getExtras();
        if (extras!=null){deger = extras.getString("mesaj");}
        if(deger.toString()==""){
            no.setText(no.getText().toString());
        }

        else{
            no.setText(deger.toString());
        }


        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor sonuc=null;
                String adim = null,soyadim=null;
                veriTabani database=new veriTabani(Veri_Ekle.this);
                Boolean state=false;
                SQLiteDatabase db = database.getReadableDatabase();
                sonuc = db.rawQuery("SELECT ingilizce, turkce FROM kelimeler WHERE ingilizce like '"+no.getText().toString()+"'", null);

                if  (sonuc.moveToFirst()) {
                    state=true;
                    do {
                        adim = sonuc.getString(sonuc.getColumnIndex("ingilizce"));
                        soyadim = sonuc.getString(sonuc.getColumnIndex("turkce"));//link
                        Log.v("Ad", adim);
                        Log.v("Soyad", soyadim);
                    }while (sonuc.moveToNext());

                    if (state) {

                        Toast.makeText(Veri_Ekle.this, "Bu ürün kayıtlıdır. Barkodunuzu tekrar kontrol ediniz...", Toast.LENGTH_SHORT).show();

                    }

                }

                else{
                    vvriTabani.veriEkle(no.getText().toString(),adres.getText().toString());
                    Toast.makeText(Veri_Ekle.this,"Veri Ekledi", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Veri_Ekle.this,MainActivity.class);
                    startActivity(intent);
                    finish();


                }

            }

        });



    }
}

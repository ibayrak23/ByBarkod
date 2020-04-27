package com.example.myapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

class Barkod extends AppCompatActivity {
    private ZXingScannerView scannerView;
    ListView liste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barkod);
        liste= (ListView) findViewById(R.id.liste);
        scanCode();
    }
    public void scanCode(){
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());
        setContentView(scannerView);
        scannerView.startCamera();
    }
    @Override
    public void onPause(){
        super.onPause();
        scannerView.stopCamera();
    }
    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler
    {
        @Override
        public void handleResult(com.google.zxing.Result result) {
            int adIndex; String mesaj; Cursor sonuc = null;

            final String resultCode = result.getText();
            Toast.makeText(Barkod.this,resultCode, Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_barkod);
            scannerView.stopCamera();

            String adim = null,soyadim=null;
            veriTabani database=new veriTabani(Barkod.this);
            Boolean state=false;
            SQLiteDatabase db = database.getReadableDatabase();
            sonuc = db.rawQuery("SELECT ingilizce, turkce FROM kelimeler WHERE ingilizce like '"+resultCode.toString()+"'", null);


            if  (sonuc.moveToFirst()) {
                state=true;
                do {
                    adim = sonuc.getString(sonuc.getColumnIndex("ingilizce"));
                    soyadim = sonuc.getString(sonuc.getColumnIndex("turkce"));//link
                    Log.v("Ad", adim);
                    Log.v("Soyad", soyadim);
                }while (sonuc.moveToNext());
            }
            if (state){
                Intent net=new Intent(Barkod.this,Browser.class);
                net.putExtra("link",soyadim.toString());
                startActivity(net);

            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(Barkod.this);
                builder.setMessage("Eklemek istermisiniz");
                builder.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent net = new Intent(Barkod.this, MainActivity.class);
                        startActivity(net);
                    }
                });
                builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent net = new Intent(Barkod.this, Veri_Ekle.class);
                        net.putExtra("mesaj", resultCode.toString());
                        startActivity(net);
                    }
                });
                builder.show();
            }
        }

    }
}
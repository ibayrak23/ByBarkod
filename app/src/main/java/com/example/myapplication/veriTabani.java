package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class veriTabani extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="yt_vt";
    public static final int DATABASE_VERSION=1;
    public static final String USER_TABLE="kelimeler";

    public static final String ROW_ID="id";
    public static final String ROW_INGILIZCE="ingilizce";
    public static final String ROW_TURKCE="turkce";

    public veriTabani(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USER_TABLE +"("+ROW_ID+" INTEGER PRIMARY KEY, " + ROW_INGILIZCE+" TEXT NOT NULL, "+ROW_TURKCE+" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE);
        onCreate(db);
    }

    public void veriEkle(String ingilizce, String turkce){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(ROW_INGILIZCE,ingilizce.trim());
        cv.put(ROW_TURKCE,turkce.trim());
        db.insert(USER_TABLE, null,cv);
        db.close();
    }

    public List<String> veriListele(){
        List<String> veriler=new ArrayList<String>();
        SQLiteDatabase db=this.getWritableDatabase();
        String[] sutunlar={ROW_ID, ROW_INGILIZCE,ROW_TURKCE};
        Cursor cursor=db.query(USER_TABLE,sutunlar,null,null,null,null,null);
        while (cursor.moveToNext()){
            veriler.add(cursor.getInt(0)+ " - " + cursor.getString(1)+" - "+ cursor.getString(2));
        }
        return veriler;
    }
    public void delete(Long id)
    {
        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("DELETE FROM kelimeler WHERE id = '" + id + "'");
        db.close();
    }

}

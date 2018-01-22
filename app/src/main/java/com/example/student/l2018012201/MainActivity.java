package com.example.student.l2018012201;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v)  // 抓取Resouse 的DB資源 寫入本機
    {
        File filedb = new File(getFilesDir(),"student.db");
        InputStream is = getResources().openRawResource(R.raw.student);
        try {
            OutputStream os =new FileOutputStream(filedb);
            int r;
            while((r = is.read()) != -1)
            {
                    os.write(r);
            }
            os.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click2(View v) //讀取本機DB   rawQuery
    {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase sd =SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        Cursor c = sd.rawQuery("Select * from students",null);
        c.moveToFirst();
        Log.d("DB",c.getString(1)+","+c.getString(2));
        while (c.moveToNext())
        {
            Log.d("DB",c.getString(1)+","+c.getString(2));
        }
    }

    public void click3(View v)  //讀取本機DB  rawQuery 加上where條件搜尋
    {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase sd =SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        String strsql = "Select * from students where _id=?";
        Cursor c = sd.rawQuery(strsql,new String[] {"2"});
        c.moveToFirst();
        Log.d("DB",c.getString(1)+","+c.getString(2));

    }

    public void click4(View v) //讀取本機DB query
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.query("students",new String[] {"_id","name","score"},null,null,null,null,null);

        c.moveToFirst();
        Log.d("DB", c.getString(1) + "," + c.getInt(2));
    }

    public void click5(View v) //讀取本機DB query
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        Cursor c = db.query("students",new String[] {"_id","name","score"},"_id=?",new String[] {"2"},null,null,null);
        c.moveToFirst();
        Log.d("DB",c.getString(1)+","+c.getInt(2));
    }

    public void click6(View v) //SQL 語法存資料
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        db.execSQL("Insert into students(_id,name,score) values(6,'聒聒',66)");
        db.close();

    }

    public void click7(View v) //map方式存資料
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();
        cv.put("_id",7);
        cv.put("name","寶寶");
        cv.put("score",99);
        db.insert("students",null,cv);
        db.close();
    }

    public void click8(View v) //修改
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();
        cv.put("score",55);
        db.update("students",cv,"_id=?",new String[] {"7"});
        db.close();
    }

    public void click9(View v) //刪除
    {
        File dbFile = new File(getFilesDir(), "student.db");
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        db.delete("students","_id=?",new String[] {"7"});
        db.close();
    }
}

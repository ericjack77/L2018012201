package com.example.student.l2018012201;

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

    public void click1(View v)
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

    public void click2(View v)
    {
        File dbFile = new File(getFilesDir(),"student.db");
        SQLiteDatabase sd =SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(),null,SQLiteDatabase.OPEN_READWRITE);
        Cursor c = sd.rawQuery("Select * from students",null);
        c.moveToFirst();
        Log.d("DB",c.getString(1)+","+c.getString(2));
        c.moveToNext();
        Log.d("DB",c.getString(1)+","+c.getString(2));

    }
}

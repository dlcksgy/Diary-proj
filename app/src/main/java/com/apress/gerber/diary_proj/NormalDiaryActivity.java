package com.apress.gerber.diary_proj;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

/**
 * Created by Arduino on 2017-05-30.
 */

public class NormalDiaryActivity extends AppCompatActivity {


    //Api23부터 permission함수와 ask함수가 자바파일에도 있어야  외부메모리에 파일쓰기 권한을 가질 수있다.
    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }
    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };

        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }



    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_normal_diary);
        final EditText diaryEditText = (EditText) findViewById(R.id.diaryEditText);
        Button saveButton = (Button) findViewById(R.id.saveButton);


        Button dateButton = (Button) findViewById(R.id.dateButton);

        dateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                EditText yearText = (EditText) findViewById(R.id.yearText);
                EditText monthText = (EditText) findViewById(R.id.monthText);
                EditText dayText = (EditText) findViewById(R.id.dayText);
                yearText.setText(Integer.toString(Calendar.DAY_OF_YEAR));
                monthText.setText(Integer.toString(Calendar.DAY_OF_MONTH));
                dayText.setText(Integer.toString(Calendar.DATE));
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener(){   //저장버튼 눌렀을 시 동작부
            @Override
            public void onClick(View v){
                EditText yearText = (EditText) findViewById(R.id.yearText);
                EditText monthText = (EditText) findViewById(R.id.monthText);
                EditText dayText = (EditText) findViewById(R.id.dayText);
                Toast.makeText(getApplicationContext(),"저장이 되려나...",Toast.LENGTH_SHORT).show();
                Diary diary = new Diary(getApplicationContext(), diaryEditText.getText().toString());
                diary.setYear(Integer.parseInt(yearText.getText().toString()));
                diary.setMonth(Integer.parseInt(monthText.getText().toString()));
                diary.setDay(Integer.parseInt(dayText.getText().toString()));
                File file = new File("/storage/emulated/0/.Diaries/" + yearText.getText().toString() +
                                                                      "/" + monthText.getText().toString() +
                                                                      "/" + dayText.getText().toString() +
                                                                      "/" + "일기제목"+".txt");

                FileWriter fw = null;
                BufferedWriter bufw = null;
                if(shouldAskPermissions()){
                    askPermissions();
                }
                try {
                    fw = new FileWriter(file);
                    bufw = new BufferedWriter(fw);
                    bufw.write(diary.getDiaryText());
                    Toast.makeText(getApplicationContext(),"저장완료!",Toast.LENGTH_SHORT).show();
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(),"File Error : "+ex.toString(),Toast.LENGTH_SHORT).show();
                }
                try{
                    if(bufw !=null) bufw.close();
                    if(fw!=null) fw.close();
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(),"File Error : "+ex.toString(),Toast.LENGTH_SHORT).show();
                }


            }
        }); //저장버튼 동작부 끝
    }
}





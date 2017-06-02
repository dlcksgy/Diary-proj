package com.apress.gerber.diary_proj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;


/**
 * Created by Arduino on 2017-05-30.
 */

public class NormalDiaryViewActivity extends AppCompatActivity{
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);

        setContentView(R.layout.activity_normal_diary_view);
        TextView normalDiaryView = (TextView) findViewById(R.id.normalDiaryView);
        Button correctButton = (Button) findViewById(R.id.correctButton);


        try {                                                               //저장되어있던 파일에서 일기 읽어옴
            normalDiaryView.setText(Diary.loadDiary("text.txt"));
        }catch(Exception ex){
            Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_SHORT);
        }
        correctButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), NormalDiaryActivity.class);
                startActivity(intent);
            }
        });
    }

}

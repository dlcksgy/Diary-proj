package com.apress.gerber.diary_proj;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by Arduino on 2017-05-30.
 */






public class NormalDiaryViewActivity extends AppCompatActivity{
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);



        Intent intent = getIntent();
        String diaryName = intent.getExtras().getString("diaryName");

        setContentView(R.layout.activity_normal_diary_view);

        TextView normalDiaryView = (TextView) findViewById(R.id.normalDiaryView);

        TextView titleText = (TextView) findViewById(R.id.titleText);

        Button correctButton = (Button) findViewById(R.id.correctButton);

        String line;

        String title[] = diaryName.split(" "); //여기서팅기는건가?

        titleText.setText(title[1]); // 왜 여기서 앱이 팅기지 -> xml파일에서 텍스트뷰값이 안맞았음 ㅋㅋ-> 근데 아직도팅김;;;

        try {
            FileReader fr = new FileReader(diaryName);
            BufferedReader bufr = new BufferedReader(fr);
            line = bufr.readLine();
            while(line!=null){
                normalDiaryView.append(line);
                line = bufr.readLine();
            }

        }catch(FileNotFoundException ex){
            Toast.makeText(getApplicationContext(),"파일을 찾을수가 읎다!",Toast.LENGTH_SHORT).show();
        }catch(IOException ex){
            Toast.makeText(getApplicationContext(),"ioexception",Toast.LENGTH_SHORT).show();
        }
    }



}

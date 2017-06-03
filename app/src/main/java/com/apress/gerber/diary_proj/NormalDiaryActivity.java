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
import java.io.FileWriter;

/**
 * Created by Arduino on 2017-05-30.
 */

public class NormalDiaryActivity extends AppCompatActivity {


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
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),"저장이 되려나...",Toast.LENGTH_SHORT).show();
                File file = new File("/storage/emulated/0/text.txt");
                String txt = diaryEditText.getText().toString();
                FileWriter fw = null;
                BufferedWriter bufw = null;
                if(shouldAskPermissions()){
                    askPermissions();
                }
                try {
                    fw = new FileWriter(file);
                    bufw = new BufferedWriter(fw);
                    bufw.write(txt);
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



                /*                Intent intent = new Intent(getApplicationContext(), NormalDiaryViewActivity.class);
                intent.putExtra("normalDiaryView", txt);
                startActivity(intent);
                */


            }
        });
    }
}

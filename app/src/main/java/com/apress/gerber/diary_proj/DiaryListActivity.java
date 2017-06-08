package com.apress.gerber.diary_proj;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.io.File;

/**
 * Created by Arduino on 2017-06-05.
 */

public class DiaryListActivity extends ListActivity {

    //파일의 경로를 가져올 파일객체
    private File diary;


    //일기 리스트를 저장할 문자열 배열
    private String[] diaryList;



    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_diary_list);








    }
}

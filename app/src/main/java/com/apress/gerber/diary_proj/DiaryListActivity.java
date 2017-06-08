package com.apress.gerber.diary_proj;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arduino on 2017-06-05.
 */

public class DiaryListActivity extends ListActivity{

    //파일의 경로를 가져올 파일객체
    private File diary;

    //월을 나타내는 디렉토리 목록을 저장할 문자열 배열
    private ArrayList<String> monthList;

    //년도를 나타낼 디렉토리 목록을 저장할 문자열 배열
    private ArrayList<String> yearList;
    private int year;

    //일기 리스트를 저장할 문자열 배열
    private ArrayList<String> diaryList;



    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_diary_list);

        final Spinner yearSpinner = (Spinner) findViewById(R.id.yearSpinner);

        final Spinner monthSpinner = (Spinner) findViewById(R.id.monthSpinner);

        final ListView list = (ListView) findViewById(R.id.diaryList);

        File diaryDir = new File("/storage/emulated/0/diary");

        File temp[] = diaryDir.listFiles();

        for(File file : temp){
            yearList.add(file.getName());
        }
        //year spinner 설정
        ArrayAdapter yearAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, yearList);
        yearAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);


        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                monthList.clear();  //기존에있던 monthList를 비워준다
                year = Integer.parseInt(yearList.get(position));
                File yeardir = new File("/storage/emulated/0/diary/" + yearList.get(position));

                File temp[] = yeardir.listFiles();
                for(File file:temp){
                    monthList.add(file.getName());  //monthList에 새로운 값들을 넣어준다.
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        //month spinner 설정
        ArrayAdapter monthAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, monthList);
        monthAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                diaryList.clear();
                File monthDir = new File("/storage/emulated/0/diary/" + year + "/" + diaryList.get(position));
                File temp[] = monthDir.listFiles();
                for(File file:temp){
                    diaryList.add(file.getName());
                }
                ArrayAdapter diaryAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, diaryList);
                list.setAdapter(diaryAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });






    }
}

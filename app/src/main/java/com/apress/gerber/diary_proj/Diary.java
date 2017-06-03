package com.apress.gerber.diary_proj;
import java.util.Calendar;

import android.annotation.TargetApi;
import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import android.content.Context;
/**
 * Created by Arduino on 2017-06-01.
 */

public class Diary {


    private Context context;
    private int year;
    private int month;
    private int day;
    private String diaryText;

    public Diary(Context context,String txt){
        this.context = context;
        this.diaryText = txt;
    }

    public int getYear() {
        return year;
    }

    public int setYear(int year) {
        if(year <= Calendar.DAY_OF_YEAR-2) {
            Toast.makeText(context, "2년이상 지난 날의 일기는 작성,수정 할 수 없습니다.", Toast.LENGTH_SHORT).show();
            return 1;
        }
        if (Calendar.DAY_OF_YEAR < year){
            Toast.makeText(context, "미래일기는 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
            return 1;
        }
        this.year = year;
        return 0;
    }

    public int getMonth() {
        return month;
    }

    public int setMonth(int month) {
        if((month < 1) || (12 < month)) {
            Toast.makeText(context, "월을 똑바로 입력하세요.", Toast.LENGTH_SHORT).show();
            return 1;
        }
        this.month = month;
        return 0;
    }

    public int getDay() {
        return day;
    }

    public int setDay(int day) {
        if((day < 1) || (31 < day)) {
            Toast.makeText(context,"일을 똑바로 입력하세요.",Toast.LENGTH_SHORT).show();
            return 1;
        }
        if(day==31) {
            switch (Calendar.DAY_OF_MONTH) {
                case 2: case 4: case 6: case 9: case 11:
                    Toast.makeText(context, "이 달에 31일은 없 습 니 다 .",Toast.LENGTH_SHORT).show();
            }
        }
        this.day = day;
        return 0;
    }

    public String getDiaryText() {
        return diaryText;
    }

    public void setDiaryText(String diaryText) {
        this.diaryText = diaryText;
    }
}

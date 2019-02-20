package com.diesen.winterkc3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Intent intent = getIntent();
        ArrayList<Integer> pointList = intent.getIntegerArrayListExtra("pointList");

        final TextView textView = (TextView)findViewById(R.id.resultText);
        textView.setText(pointList.get(0).toString());
    }
}

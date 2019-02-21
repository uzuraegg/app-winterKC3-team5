package com.diesen.winterkc3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Intent intent = getIntent();
        ArrayList<Integer> pointList = intent.getIntegerArrayListExtra("pointList");
        Integer maxIndex = this.getHiScoreId(pointList);

        final TextView scoreText = (TextView)findViewById(R.id.winNum);
        // indexは0オリジン -> 人は1オリジン
        String text = String.format("%d人目：%d票", maxIndex+1, pointList.get(maxIndex));
        scoreText.setText(text);

        ArrayList<String> textList = intent.getStringArrayListExtra("textList");
        final TextView answer = (TextView)findViewById(R.id.winText);
        answer.setText(textList.get(maxIndex));

        final Button button = (Button)findViewById(R.id.nextButton);

    }

    public Integer getHiScoreId(ArrayList<Integer> pointList){
        Integer max = new Integer(-1);
        Integer maxIndex = new Integer(-1);
        Integer value = new Integer(-1);
        for(Integer i = 0; i < pointList.size(); i++){
            value = pointList.get(i);
            if(max < value){
                max = value;
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}

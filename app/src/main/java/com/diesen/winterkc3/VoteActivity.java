package com.diesen.winterkc3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class VoteActivity extends AppCompatActivity {
    Integer num = 5; //人数
    Integer index = 1; //1人目
    ArrayList<String> textList = new ArrayList<String>();
    ArrayList<String> randamText = new ArrayList<String>();
    ArrayList<Integer> pointList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote);
        textList = this.setList();
        randamText = this.setRandamList(textList);
        pointList = this.initPointList();

        final TextView textView = (TextView)findViewById(R.id.playerNum);
        final ListView listView = (ListView)findViewById(R.id.listView);
        final Button button = (Button)findViewById(R.id.nextButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_single_choice,
                randamText){
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                setPointList(listView.getCheckedItemPosition());
                if(num < index){
                    Intent intent = new Intent(VoteActivity.this, ResultActivity.class);
                    intent.putIntegerArrayListExtra("pointList", pointList);
                    intent.putStringArrayListExtra("textList", textList);
                    startActivity(intent);
                }else{
//                    listView.clearChoices();
//                    listView.requestLayout();
                    textView.setText(index.toString() + "人目");
                }
            }
        });
    }

    private ArrayList<String> setList(){
        ArrayList<String> aList = new ArrayList<>();
        aList.add("text1");
        aList.add("text2");
        aList.add("text3");
        aList.add("text4");
        aList.add("text5");

        return aList;
    }

    private ArrayList<String> setRandamList(ArrayList<String> textList){
        ArrayList<String> randomList = (ArrayList<String>)textList.clone();
        Collections.shuffle(randomList);
        return randomList;
    }
    private ArrayList<Integer> initPointList(){
        ArrayList<Integer> aList = new ArrayList<>();
        for (Integer i = 0; i < this.num; i++){
            aList.add(i, 0);
        }
        return aList;
    }

    private void setPointList(int index){
        String targetString = randamText.get(index); //　ここはランダムデータ
        for (Integer i = 0; i < textList.size(); i++){
            // aStringは本来元データからgetして入れるところ
            // 並び替えられたものから 元のidを探して得点を追加する
            String aString = textList.get(i);
            if(targetString.equals(aString)){
                Integer point = pointList.get(i) + 1;
                pointList.set(i, point);
            }
        }
    }
}

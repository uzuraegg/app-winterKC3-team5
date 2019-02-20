package com.diesen.winterkc3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

//import java.util.List;
//import java.util.ArrayList;

public class VoteActivity extends AppCompatActivity {
//    List<String> aList = new ArrayList<>();
//    aList.add("text1");
//    aList.add("text2");
//    aList.add("text3");
//    aList.add("text4");

    // ListView に表示させる文字列
    // ListView に表示させる文字列
    private static final String[] SIZES = new String[] {"XS(eXtra Small)", "S(Small)", "M(Medium)", "L(Large)", "XL(eXtra Large)"};

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_single_choice,
                SIZES)
        );

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        // 現在チェックされているアイテムの position を取得
        listView.getCheckedItemPosition();
    }
}

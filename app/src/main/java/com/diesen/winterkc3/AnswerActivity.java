package com.diesen.winterkc3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {
    private ImageView imgView;
    private int index;
    private TextView textView;
    private EditText editText;
    public static ArrayList<String> textList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);

        imgView = findViewById(R.id.imageView2);
        imgView.setImageBitmap(UploadActivity.img);
        textView = findViewById(R.id.playerNum);
        editText = findViewById(R.id.editText2);

        findViewById(R.id.nextButton).setOnClickListener(nextBtnListener);
    }

    View.OnClickListener nextBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            textList.add(editText.getText().toString());
            if (index >= 4){
                Intent intent = new Intent(AnswerActivity.this, ConfirmActivity.class);
                startActivity(intent);
            }else {
                index++;
                textView.setText((index+1)+"人目");
            }
        }
    };
}

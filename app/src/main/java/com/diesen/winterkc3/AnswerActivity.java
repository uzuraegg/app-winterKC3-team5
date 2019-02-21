package com.diesen.winterkc3;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
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
    private SoundPool soundPool;
    private int se_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);

        imgView = findViewById(R.id.imageView2);
        imgView.setImageBitmap(UploadActivity.img);
        textView = findViewById(R.id.playerNum);
        editText = findViewById(R.id.editText2);

        findViewById(R.id.nextButton).setOnClickListener(nextBtnListener);

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        // one.wav をロードしておく
        se_button = soundPool.load(this, R.raw.button, 1);
    }

    View.OnClickListener nextBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            soundPool.play(se_button, 3.0f, 3.0f, 0, 0, 1);
            textList.add(editText.getText().toString());
            editText.getEditableText().clear();
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

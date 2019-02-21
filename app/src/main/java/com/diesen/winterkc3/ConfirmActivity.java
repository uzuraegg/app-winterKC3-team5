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

public class ConfirmActivity extends AppCompatActivity {    private ImageView imgView;
    private int index;
    private TextView textView;
    private TextView ansView;
    private EditText editText;
    private SoundPool soundPool;
    private int se_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation);

        imgView = findViewById(R.id.imageView_con);
        imgView.setImageBitmap(UploadActivity.img);
        textView = findViewById(R.id.playerNum_con);
        ansView = findViewById(R.id.answerText);

        ansView.setText(AnswerActivity.textList.get(0));

        findViewById(R.id.nextButton).setOnClickListener(nextBtnListener);

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        // one.wav をロードしておく
        se_button = soundPool.load(this, R.raw.button, 1);
    }

    View.OnClickListener nextBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            soundPool.play(se_button, 3.0f, 3.0f, 0, 0, 1);
            if (index >= 4){
                Intent intent = new Intent(ConfirmActivity.this, VoteActivity.class);
                intent.putExtra("list",AnswerActivity.textList);
                startActivity(intent);
            }else {
                index++;
                textView.setText((index+1)+"人目");
                ansView.setText(AnswerActivity.textList.get(index));
            }
        }


    };
}

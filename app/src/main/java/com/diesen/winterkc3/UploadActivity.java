package com.diesen.winterkc3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.InputStream;

public class UploadActivity extends AppCompatActivity {
    private ImageView imgView;
    public static Bitmap img;
    private SoundPool soundPool;
    private int se_button;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        imgView = findViewById(R.id.imageView);

        findViewById(R.id.uploadButton).setOnClickListener(uploadBtnListener);
        findViewById(R.id.decideButton).setOnClickListener(decideBtnListener);

        // MediaPlayer のインスタンス生成
        mediaPlayer = MediaPlayer.create(this,R.raw.opening);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        // one.wav をロードしておく
        se_button = soundPool.load(this, R.raw.button, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // TODO Auto-generated method stub
        if (requestCode == 0 && resultCode == RESULT_OK) {
            try {
                InputStream in = getContentResolver().openInputStream(data.getData());
                img = BitmapFactory.decodeStream(in);
                img.createScaledBitmap(img, 100, 100, true);
                in.close();
    // 選択した画像を表示
                imgView.setImageBitmap(img);
            } catch (Exception e) {
            }
        }
    }

    View.OnClickListener uploadBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // ギャラリー用のIntent作成
            Intent intentGallery;
            if (Build.VERSION.SDK_INT < 19) {
                intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
                intentGallery.setType("image/*");
            } else {
                intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
                intentGallery.setType("image/jpeg");
            }
            startActivityForResult(intentGallery, 0);
        }


    };

    View.OnClickListener decideBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mediaPlayer.stop();
            soundPool.play(se_button, 3.0f, 3.0f, 0, 0, 1);
            Intent intent = new Intent(UploadActivity.this, AnswerActivity.class);
            startActivity(intent);
        }
    };

}

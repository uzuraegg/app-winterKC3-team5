package com.diesen.winterkc3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

public class UploadActivity extends AppCompatActivity {
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        imgView = findViewById(R.id.imageView);

        findViewById(R.id.uploadButton).setOnClickListener(uploadBtnListener);
        findViewById(R.id.decideButton).setOnClickListener(decideBtnListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // TODO Auto-generated method stub
        if (requestCode == 0 && resultCode == RESULT_OK) {
            try {
                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);
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
            Intent intent = new Intent(UploadActivity.this, ConfirmActivity.class);
            startActivity(intent);
        }
    };

}

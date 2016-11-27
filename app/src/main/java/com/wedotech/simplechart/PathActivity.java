package com.wedotech.simplechart;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.wedotech.simplechart.view.PathTestView;

/**
 * Created by YangYan on 2016/11/27.
 */

public class PathActivity extends AppCompatActivity {

    private View btnReset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        final PathTestView pathTestView = (PathTestView) findViewById(R.id.pathView);
        btnReset = findViewById(R.id.btn_reset);
        final ImageView imageView = (ImageView) findViewById(R.id.iv);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pathTestView.reset();
            }
        });
        View save = findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pathTestView.saveToGallery("path", "path", "path", Bitmap.CompressFormat.JPEG, 100);
                Bitmap bitmap = BitmapFactory.decodeFile(pathTestView.getPath());
                imageView.setImageBitmap(bitmap);
            }
        });


    }
}

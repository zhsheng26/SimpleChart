package com.wedotech.simplechart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pathTestView.reset();
            }
        });

    }
}

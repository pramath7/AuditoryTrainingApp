package com.example.duht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class MainActivity extends AppCompatActivity {
    private String level;
    BlurView blurView;
    TextView app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startlevel1=(Button)findViewById(R.id.level1button);
        Button startlevel2=(Button)findViewById(R.id.level2button);
        Button startlevel3=(Button)findViewById(R.id.level3button);
        Button startlevel4=(Button)findViewById(R.id.level4button);
        blurView = (BlurView)findViewById(R.id.blurView);
        app = (TextView)findViewById(R.id.app_name);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/MachineGunk-nyqg.ttf");
        app.setTypeface(typeface);
        Blurbackground();
        startlevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startone();
            }
        });
        startlevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starttwo();
            }
        });
        startlevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startthree();
            }
        });
        startlevel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startfour();
            }
        });

    }

    private void Blurbackground() {
        float radius = 5f;

        View decorView = getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(this))
                .setBlurRadius(radius)
                .setHasFixedTransformationMatrix(true);
    }

    private void startone(){
        level="one";
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        intent.putExtra("level",level);
        startActivity(intent);
    }
    private void starttwo(){
        level="two";
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        intent.putExtra("level",level);
        startActivity(intent);

    }
    private void startthree(){
        level="three";
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        intent.putExtra("level",level);
        startActivity(intent);

    }

    private void startfour(){
        level="four";
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        intent.putExtra("level",level);
        startActivity(intent);

    }







}

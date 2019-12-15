package com.example.duht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startlevel1=(Button)findViewById(R.id.level1button);
        Button startlevel2=(Button)findViewById(R.id.level2button);
        Button startlevel3=(Button)findViewById(R.id.level3button);
        Button startlevel4=(Button)findViewById(R.id.level4button);
        startlevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startone();
            }
        });
    }

    private void startone(){
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        startActivity(intent);

    }





}

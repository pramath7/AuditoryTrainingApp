package com.example.duht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private Button normalsound;
    private Button slowsound;
    private ListView options;
    private String option_list[];
    private RadioGroup rg;
    private Button nextQuestion;
    private boolean answered=false;
    private ColorStateList defaultcolor;
    private List<Question> questionList;
    private int questionCounter=0;
    private int totalquestions;
    private String level;
    private Question currentQuestion;
    private MediaPlayer mp1;
    private MediaPlayer mp2;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        normalsound=(Button)findViewById(R.id.playbutton);
        result=(TextView)findViewById(R.id.result);
        slowsound=(Button)findViewById(R.id.slowplaybutton);
        options = (ListView) findViewById(R.id.options);
        nextQuestion=(Button)findViewById(R.id.nextQuestion);
        nextQuestion.setVisibility(View.GONE);
        nextQuestion.setOnClickListener(this);

        QuizDBHelper dbHelper=new QuizDBHelper(this);
        level = getIntent().getStringExtra("level");
        questionList=dbHelper.getlevelQuestions(level);

        totalquestions=questionList.size();
        Collections.shuffle(questionList);
        String num=Integer.toString(totalquestions);
        Toast.makeText(getApplicationContext(),num,Toast.LENGTH_SHORT).show();
        showNextQuestion();
    }

    private void checkAnswer(int position){
        answered=true;
        if((option_list[position]).equals(currentQuestion.getAnswer())){
            result.setText("Congratulaions! Correct");
            nextQuestion.setVisibility(View.VISIBLE);
            questionList.remove(currentQuestion);


        }
        else{
            result.setText("Sorry! Incorrect");
            nextQuestion.setVisibility(View.VISIBLE);


        }
    }
    private void showNextQuestion(){

        if(questionList.isEmpty()){
            Intent i=new Intent(QuestionActivity.this,LevelComplete.class);
            startActivity(i);
            finish();
        }
        else{
            answered=false;
            Collections.shuffle(questionList);
            currentQuestion=questionList.get(0);
            option_list = new String[] {currentQuestion.getOptionone(), currentQuestion.getOptiontwo(), currentQuestion.getOptionthree(), currentQuestion.getOptionfour()};
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, option_list);
            options.setAdapter(arrayAdapter);
            options.setOnItemClickListener(this);
            result.setText("");

            normalsound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mp1!=null){
                        mp1.stop();
                        mp1.release();
                    }
                    String name="normal"+currentQuestion.getSoundname();
                    int sound_id = getApplicationContext().getResources().getIdentifier(name, "raw",
                            getApplicationContext().getPackageName());
                  //  Uri u= Uri.parse(name);
                    mp1=MediaPlayer.create(getApplicationContext(),sound_id);
                    mp1.start();

                }
            });
            slowsound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mp2!=null){
                        mp2.stop();
                        mp2.release();

                    }
                    String name="slow"+currentQuestion.getSoundname();

                    int sound_id = getApplicationContext().getResources().getIdentifier(name, "raw",
                            getApplicationContext().getPackageName());
                     mp2=MediaPlayer.create(getApplicationContext(),sound_id);
                     mp2.start();

                }
            });



        }

    }

    @Override
    public void onClick(View v) {
        showNextQuestion();
        nextQuestion.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        checkAnswer(position);
    }
}

//
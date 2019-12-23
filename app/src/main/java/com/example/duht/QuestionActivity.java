package com.example.duht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    private Button normalsound;
    private Button slowsound;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private RadioButton option4;
    private RadioGroup rg;
    private Button confirm;
    private boolean answered=false;
    private ColorStateList defaultcolor;
    private List<Question> questionList;
    private int questionCounter=0;
    private int totalquestions;
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
        option1=(RadioButton)findViewById(R.id.option1);
        option2=(RadioButton)findViewById(R.id.option2);
        option3=(RadioButton)findViewById(R.id.option3);
        option4=(RadioButton)findViewById(R.id.option4);
        confirm=(Button)findViewById(R.id.confirm);
        rg=(RadioGroup)findViewById(R.id.rbg);
        defaultcolor=option1.getTextColors();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(!answered){
                    if(option1.isChecked()||option2.isChecked()||option3.isChecked()||option4.isChecked()){
                       checkAnswer();
                    }
                    else{
                        Toast.makeText(QuestionActivity.this,"Please select an answer",Toast.LENGTH_SHORT).show();
                    }}
                else{
                   showNextQuestion();
                }

            }
        });


        QuizDBHelper dbHelper=new QuizDBHelper(this);
        questionList=dbHelper.getlevelQuestions("one");

        totalquestions=questionList.size();
        Collections.shuffle(questionList);
        String num=Integer.toString(totalquestions);
        Toast.makeText(getApplicationContext(),num,Toast.LENGTH_SHORT).show();
        showNextQuestion();





    }

    private void checkAnswer(){
        answered=true;
        RadioButton rb=findViewById(rg.getCheckedRadioButtonId());
        if((rb.getText().toString()).equals(currentQuestion.getAnswer())){
            result.setText("Congratulaions! Correct");
            confirm.setText("Next");
            questionList.remove(currentQuestion);


        }
        else{
            result.setText("Sorry! Incorrect");
            confirm.setText("Next");


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
            confirm.setText("CONFIRM");
            option1.setText(currentQuestion.getOptionone());
            option2.setText(currentQuestion.getOptiontwo());
            option3.setText(currentQuestion.getOptionthree());
            option4.setText(currentQuestion.getOptionfour());
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
}

//
package com.example.duht;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    private Button normalsound;
    private Button slowsound;
    private RadioButton option1;
    private RadioButton option2;
    private RadioButton option3;
    private RadioButton option4;
    private Button confirm;
    private List<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        normalsound=(Button)findViewById(R.id.playbutton);
        slowsound=(Button)findViewById(R.id.slowplaybutton);
        option1=(RadioButton)findViewById(R.id.option1);
        option2=(RadioButton)findViewById(R.id.option2);
        option3=(RadioButton)findViewById(R.id.option3);
        option4=(RadioButton)findViewById(R.id.option4);
        confirm=(Button)findViewById(R.id.confirm);


        QuizDBHelper dbHelper=new QuizDBHelper(this);
        questionList=dbHelper.getlevelQuestions("one");
    }
}

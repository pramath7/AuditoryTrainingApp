package com.example.duht;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.duht.QuizContract.*;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="quiz.db";
    private static final int DATABASE_VERSION=1;

    private SQLiteDatabase db;
    public QuizDBHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;

        final String SQL_CREATE_QUESTIONS_TABLE="CREATE TABLE "+QuestionsTable.TABLE_NAME+
                " ( "+ QuestionsTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+QuestionsTable.COLUMN_SOUNDNAME
                +" TEXT, "+
                QuestionsTable.COLUMN_OPTIONONE + " TEXT, "+
                QuestionsTable.COLUMN_OPTIONTWO + " TEXT, "+
                QuestionsTable.COLUMN_OPTIONTHREE + " TEXT , "+
                QuestionsTable.COLUMN_OPTIONFOUR + " TEXT , "+
                QuestionsTable.COLUMN_ANSWER + " TEXT , "+
                QuestionsTable.COLUMN_LEVEL + " TEXT , "+
                QuestionsTable.COLUMN_STATUS + " TEXT ) ";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+QuestionsTable.TABLE_NAME);
        onCreate(db);
        fillquestionstable();
    }

    private void fillquestionstable(){
        Question q1=new Question("kashish","Kashish","Kashmir","Cashew","Cache","Kashish","No",
                "one");
        addQuestion(q1);
        Question q2=new Question("namaste","Namibia","Numb","New","Namaste","Namaste","No",
                "one");
        addQuestion(q2);
        Question q3=new Question("hello","Hi","Ola","Hello","Bonjour","Hello","No",
                "one");
        addQuestion(q3);
        Question q4=new Question("howareyou","How is that?","How are you?","Howdy?","HoverCraft","How are you?","No",
                "two");
        addQuestion(q4);
        Question q5=new Question("Nice shirt","NIce shirt","I am hurt","There is some dirt","Heart","Nice shirt","No",
                "two");
        addQuestion(q5);
    }

    private void addQuestion(Question q){
        ContentValues cv=new ContentValues();
        cv.put(QuestionsTable.COLUMN_SOUNDNAME,q.getSoundname());
        cv.put(QuestionsTable.COLUMN_OPTIONONE,q.getOptionone());
        cv.put(QuestionsTable.COLUMN_OPTIONTWO,q.getOptiontwo());
        cv.put(QuestionsTable.COLUMN_OPTIONTHREE,q.getOptionthree());
        cv.put(QuestionsTable.COLUMN_OPTIONFOUR,q.getOptionfour());
        cv.put(QuestionsTable.COLUMN_ANSWER,q.getAnswer());
        cv.put(QuestionsTable.COLUMN_STATUS,q.getStatus());
        cv.put(QuestionsTable.COLUMN_LEVEL,q.getLevel());
        db.insert(QuestionsTable.TABLE_NAME,null,cv);
    }

    public List<Question> getlevelQuestions(String level){
        List<Question> questionList=new ArrayList<>();
        db=getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM "+ QuestionsTable.TABLE_NAME ,null);
        if(c.moveToFirst()){
            do{
                Question q=new Question();
                q.setSoundname(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_SOUNDNAME)));
                q.setOptionone(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTIONONE)));
                q.setOptiontwo(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTIONTWO)));
                q.setOptionthree(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTIONTHREE)));
                q.setOptionfour(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTIONFOUR)));
                q.setAnswer(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER)));
                q.setStatus(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_STATUS)));
                q.setLevel(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_LEVEL)));

                questionList.add(q);
            }while (c.moveToNext());
        }

        c.close();
        return questionList;
    }


}

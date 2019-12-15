package com.example.duht;

import android.provider.BaseColumns;

public final class QuizContract {
    private QuizContract() {}
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME="quiz_questions";
        public static final String COLUMN_SOUNDNAME="soundname";
        public static final String COLUMN_OPTIONONE="option1";
        public static final String COLUMN_OPTIONTWO="option2";
        public static final String COLUMN_OPTIONTHREE="option3";
        public static final String COLUMN_OPTIONFOUR="option4";
        public static final String COLUMN_ANSWER="answer";
        public static final String COLUMN_STATUS="remaining";
        public static final String COLUMN_LEVEL="Level";
    }
}

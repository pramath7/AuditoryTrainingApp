package com.example.duht;

public class Question {
    private String soundname;
    private String optionone;
    private String optiontwo;
    private String optionthree;
    private String optionfour;
    private String answer;
    private String status;
    private String level;

    public Question(){}
    public Question(String soundname,String optionone,String optiontwo,String optionthree,String
                    optionfour,String answer,String status,String level){
        this.answer=answer;
        this.soundname=soundname;
        this.optionone=optionone;
        this.optiontwo=optiontwo;
        this.optionthree=optionthree;
        this.optionfour=optionfour;
        this.status=status;
        this.level=level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSoundname() {
        return soundname;
    }

    public void setSoundname(String soundname) {
        this.soundname = soundname;
    }

    public String getOptionone() {
        return optionone;
    }

    public void setOptionone(String optionone) {
        this.optionone = optionone;
    }

    public String getOptiontwo() {
        return optiontwo;
    }

    public void setOptiontwo(String optiontwo) {
        this.optiontwo = optiontwo;
    }

    public String getOptionthree() {
        return optionthree;
    }

    public void setOptionthree(String optionthree) {
        this.optionthree = optionthree;
    }

    public String getOptionfour() {
        return optionfour;
    }

    public void setOptionfour(String optionfour) {
        this.optionfour = optionfour;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

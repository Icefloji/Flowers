package com.ice.saber.ice;


import java.io.Serializable;

public class question implements Serializable{
    private String myquestion;
    private boolean  myanswer;
    private int  score=0;
    private boolean oldanswer;
    private boolean isselect=false;


    public boolean isselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }

    public boolean isMyanswer() {return myanswer;}

    public void setMyquestion(String myquestion) {this.myquestion = myquestion;}

    public void setMyanswer(boolean myanswer) {this.myanswer = myanswer;}

    public String getMyquestion() {return myquestion;}

    public question() {}

    public question(String myquestion, boolean myanswer) {
        this.myquestion = myquestion;
        this.myanswer = myanswer;

    }
    public boolean isOldanswer() {return oldanswer;}

    public void setOldanswer(boolean oldanswer) {this.oldanswer = oldanswer;}

    public int getScore() {
        return score;
    }

    public void setScore(int score) {this.score = score;}
}

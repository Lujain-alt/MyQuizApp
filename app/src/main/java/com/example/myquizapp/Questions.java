package com.example.myquizapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Questions implements Parcelable {

    int textResTd;
    boolean answerTrue;
    int type;

    public Questions(int textResTd, boolean answerTrue, int type) {
        this.textResTd = textResTd;
        this.answerTrue = answerTrue;
        this.type = type;
    }

    protected Questions(Parcel in) {
        textResTd = in.readInt();
        answerTrue = in.readByte() != 0;
        type = in.readInt();
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    public int getTextResTd() {
        return textResTd;
    }

    public void setTextResTd(int textResTd) {
        this.textResTd = textResTd;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(textResTd);
        parcel.writeByte((byte) (answerTrue ? 1 : 0));
        parcel.writeInt(type);
    }
}

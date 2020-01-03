package com.example.myquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static com.example.myquizapp.R.drawable.male;

public class QuizActivity extends AppCompatActivity {
    boolean easy=true;
    boolean mid=true;
    boolean dif=true;
    boolean sheat=false;
    TextView userName,trueCounter,falseCounter,text_question;
    ImageView userProfile,trueImage,falseImage;
    Button hintBTN,true_btn,false_btn;
    TextView counter;
    int count=0;
    int current_index;
    Questions[] questionText=new Questions[]{};
   final int max=8,min=0;
    Parcelable[] parcelables=new Parcelable[]{};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userProfile = findViewById(R.id.user_profile);
        userName = findViewById(R.id.user_name);
        trueImage = findViewById(R.id.true_image);
        falseImage = findViewById(R.id.false_image);
        trueCounter = findViewById(R.id.true_counter);
        falseCounter = findViewById(R.id.false_counter);
        text_question = findViewById(R.id.text_question);
        true_btn = findViewById(R.id.t_btn);
        false_btn = findViewById(R.id.f_btn);
        hintBTN = findViewById(R.id.hint_btn);
        counter = findViewById(R.id.textCounter);

          getIntent().getExtras();
        boolean genderValue = getIntent().getBooleanExtra("gender", false);

        if (genderValue == true) {

            //userProfile.setImageDrawable(getResources().getDrawable(male));
            //userProfile.setImageDrawable(getResources().getDrawable(male));
            userProfile.setImageResource(R.drawable.male);
            //setImageDrawable(getResources().getDrawable(R.drawable.mm));

        } else if (genderValue == false) {
            userProfile.setImageResource(R.drawable.ff);

        }

        userName.setText(getIntent().getStringExtra("name"));

        Parcelable parcelable[] = getIntent().getParcelableArrayExtra("questionText");

        questionText = new Questions[3];
        for (int i = 0; i < 3; i++) {

            Questions x[] = new Questions[parcelable.length];
            final int random = new Random().nextInt((max - min) + 1) + min;
            x[i] = (Questions) parcelable[random];
            if (x[i].getType() == 1 && easy == true) {
                questionText[i] = (Questions) parcelable[random];
                easy = false;
            }

            if (x[i].getType() == 2 && mid == true) {
                questionText[i] = (Questions) parcelable[random];
                mid = false;
            }

            if (x[i].getType() == 3 && dif == true) {
                questionText[i] = (Questions) parcelable[random];
                dif = false;
            }
            if (questionText.length != i + 1) {
                --i;
            }

        }


        /*--------------------------------------------------------------*/
      /*  if(savedInstanceState!=null)
        {
            //counter.setText(savedInstanceState.getString("CurrentInt"));
            count= savedInstanceState.getInt("CurrentCount");
            current_index=savedInstanceState.getInt("currentIndex");
            counter.setText(count+"");
            questionText=(Questions[]) savedInstanceState.getSerializable("currentArray");
            text_question.setText(questionText[current_index].getTextResTd());


            text_question.setVisibility(View.VISIBLE);


        }*/


      true_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(true);
                current_index++;
                int question = questionText[current_index].getTextResTd();
                text_question.setText(question);
            }
        });

        false_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(false);
                current_index++;
                int question = questionText[current_index].getTextResTd();
                text_question.setText(question);

                preQuestion();
            }

        });

        hintBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondIntent=new Intent();
               // secondIntent.putExtra()
               // secondIntent.putExtra()

            }
        });


    }

    private void checkAnswer(boolean userAnswerTrue)
    {
        boolean answerTrue=questionText[current_index].isAnswerTrue();
        int messageResId;

        if(userAnswerTrue==answerTrue)
        {
            messageResId=R.string.true_btn;
            if(sheat==false) {
                if (questionText[current_index].getType() == 1)
                    count++;

                if (questionText[current_index].getType() == 2)
                    count = count + 2;
                if (questionText[current_index].getType() == 3)
                    count = count + 3;
                counter.setText(String.valueOf(count));
                updateQuestion();
            }
            updateQuestion();
        }
        else

        {
            messageResId=R.string.false_btn;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_LONG) .show();

        updateQuestion();
    }


    private void updateQuestion()
    {
        int question = questionText[current_index].getTextResTd();
        text_question.setText(question);


    }
    private void preQuestion() {
        if (current_index == 0) {
            int question = questionText[current_index].getTextResTd();
            text_question.setText(question);

        } else
        {
            current_index --;
            int question = questionText[current_index].getTextResTd();
            text_question.setText(question);

        }
    }



   /* @Override
   public void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        // outState.putString("CurrentIndex",String.valueOf(current_index));
        //outState.putString("CurrentInt", String.valueOf(counter));
        outState.putInt("CurrentCount",count);
        outState.putInt("currentIndex",current_index);
        //outState.putString("currentText", String.valueOf(counter));
        outState.putSerializable("currentArray", questionText);

    }*/

}

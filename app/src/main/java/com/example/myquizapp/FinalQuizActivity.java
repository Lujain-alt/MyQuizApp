package com.example.myquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FinalQuizActivity extends AppCompatActivity {
    boolean easy=false;
    boolean mid=false;
    boolean dif=false;

    TextView userName,trueCounter,falseCounter,text_question;
    ImageView userProfile,trueImage,falseImage;
    Button hintBTN,true_btn,false_btn;
    TextView counter;
    int count=0;
    int current_index=0;
    Questions[] questionText;
    Questions[] questions;
    final int max=8,min=0;
    boolean valdite,sheat=false;
    int random;
   int trueScoreVal=0;
   int falseScoreVal=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_quiz);

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
        boolean genderValue = getIntent().getBooleanExtra("gender", true);

        if (genderValue == true) {

            //userProfile.setImageDrawable(getResources().getDrawable(male));
            //userProfile.setImageDrawable(getResources().getDrawable(male));
            userProfile.setImageResource(R.drawable.male);
            //setImageDrawable(getResources().getDrawable(R.drawable.mm));

        } else if (genderValue == false) {
            userProfile.setImageResource(R.drawable.ff);

        }

        userName.setText(getIntent().getStringExtra("name"));

       Parcelable parcelable[] = getIntent().getParcelableArrayExtra("question");
        questions=new Questions[parcelable.length];
for(int i =0 ; i <parcelable.length;i++)
{
    questions[i]=(Questions)parcelable[i];
}

questionText=new Questions[3];
for(int i =0; i <3;i++)
{valdite=false;
    random= new Random().nextInt((max-min)+1)+min;
    if(questions[random].getType() == 1 && easy==false)
    {
        questionText[i]=questions[random];
        easy=true;
        valdite=true;
    }

    if(questions[random].getType() == 2 && mid==false)
    {
        questionText[i]=questions[random];
        mid=true;
        valdite=true;
    }

    if(questions[random].getType() == 3 && dif==false)
    {
        questionText[i]=questions[random];
        dif=true;
        valdite=true;
    }
    if(valdite==false)
    {
        i--;
    }

}
text_question.setText(questionText[current_index].getTextResTd());

        true_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        false_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        hintBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(FinalQuizActivity.this,"cheat answe is"+questionText[current_index].isAnswerTrue()+"",Toast.LENGTH_LONG).show();
          sheat=true;
            }
        });
        }


        /*--------------------------------------------------------------*/


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

                trueScoreVal++;
                trueCounter.setText(trueScoreVal + "");
                updateQuestion();
            }else
            updateQuestion();
        }
        else

        {
            messageResId=R.string.false_btn;
            falseScoreVal++;
            falseCounter.setText(falseScoreVal+"");
            updateQuestion();
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_LONG) .show();
    }


    private void updateQuestion()
    {
        if(current_index==questionText.length-1)
        {
            Intent i= new Intent(FinalQuizActivity.this,HintActivity.class);
            i.putExtra("score",count+"");
            i.putExtra("trueScore",trueScoreVal+"");
            i.putExtra("falseScore",falseScoreVal+"");
            i.putExtra("wof",count);
            startActivity(i);

        }
        else {
            current_index++;
            text_question.setText(questionText[current_index].getTextResTd());
        }

    }


}


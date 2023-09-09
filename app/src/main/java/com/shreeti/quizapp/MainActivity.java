package com.shreeti.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.color.utilities.Score;

import javax.net.ssl.SSLEngineResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView questionsTV,TotalQuestionsTV;

    Button ansA,ansB,ansC,ansD,submitBtn;

    int Score=0;
    int totalquestion=QuizQuestions.question.length;
    int CurrentQuestionIndex=0;
    String selectedAnswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        questionsTV=findViewById(R.id.questionsTV);
        TotalQuestionsTV=findViewById(R.id.TotalQuestionsTV);
        ansA=findViewById(R.id.ansA);
        ansB=findViewById(R.id.ansB);
        ansC=findViewById(R.id.ansC);
        ansD=findViewById(R.id.ansD);
        submitBtn=findViewById(R.id.submitBtn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        TotalQuestionsTV.setText("Total Question: "+totalquestion);

        loadNewQuestion();



    }

    @Override
    public void onClick(View view) {

        //first the color of all the buttons will be white......

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);


        Button clickedButton=(Button) view;

        if(clickedButton.getId()==R.id.submitBtn){
            if(selectedAnswer.equals(QuizQuestions.correctAns[CurrentQuestionIndex])){
                Score++;
            }
            CurrentQuestionIndex++;
            loadNewQuestion();

        }else{
            //choiced button clicked

            selectedAnswer=clickedButton.getText().toString();
            // after selecting one answer the color of the buttons will turn to magenta
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }

    }

    void loadNewQuestion(){
        if(CurrentQuestionIndex==totalquestion){
            finishQuiz();
            return;
        }

        questionsTV.setText(QuizQuestions.question[CurrentQuestionIndex]);
        ansA.setText(QuizQuestions.answers[CurrentQuestionIndex][0]);
        ansB.setText(QuizQuestions.answers[CurrentQuestionIndex][1]);
        ansC.setText(QuizQuestions.answers[CurrentQuestionIndex][2]);
        ansD.setText(QuizQuestions.answers[CurrentQuestionIndex][3]);

    }

    void finishQuiz(){
        String Status="";
        if(Score>totalquestion*0.60){
            Status="Passed This Quiz Test";
        }else{
            Status="Sorry, You Failed";

        }
        new AlertDialog.Builder(this)
                .setTitle(Status)
                .setMessage("Out of "+totalquestion +" Your score is "+Score)
                .setPositiveButton("Restart",((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }

    void restartQuiz(){
        Score=0;
        CurrentQuestionIndex=0;
        loadNewQuestion();
    }
}
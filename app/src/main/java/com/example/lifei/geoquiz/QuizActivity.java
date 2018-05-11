package com.example.lifei.geoquiz;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity" ;
    private static final String KEY_INDEX ="index";

    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionTextView;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private int mCurretIndex = 0;
    private Question[] mQuestionBank= new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),

    } ;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX,mCurretIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            mCurretIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        Log.d(TAG,"onCreate is called");
        setContentView(R.layout.activity_quiz);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurretIndex = (mCurretIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCurretIndex = (mCurretIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();
        mPrevButton =(ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mCurretIndex = (mCurretIndex - 1) % mQuestionBank.length;
                if (mCurretIndex < 0){
                    mCurretIndex += mQuestionBank.length;
                }
                updateQuestion();
            }
        });
    }

    private void updateQuestion(){

        int question = mQuestionBank[mCurretIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){

        boolean answerIsTrue = mQuestionBank[mCurretIndex].isAnswerTrue();
        int messageResId = 0;

        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        }else{
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this,messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPaused is called");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume is called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop is called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy is called");
    }
}

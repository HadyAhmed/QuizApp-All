package com.example.quizapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivityTag";

    public static final String LIVERPOOL_EXTRA = "liver";
    public static final String BARCELONA_EXTRA = "barcelona";
    public static final String MANCHESTER_EXTRA = "manchester";

    private Button submitBtn;
    private EditText layoutAnswerEt;

    private CheckBox liverpoolB;
    private CheckBox barcelonaB;
    private CheckBox manchesterB;

    private boolean liverpoolCheckState, barcelonaCheckState, manchesterCheckState;

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // TODO: 11/15/2019 check for the restored variables here
        if (savedInstanceState.containsKey(LIVERPOOL_EXTRA)) {
            liverpoolCheckState = savedInstanceState.getBoolean(LIVERPOOL_EXTRA);
            manchesterCheckState = savedInstanceState.getBoolean(MANCHESTER_EXTRA);
            barcelonaCheckState = savedInstanceState.getBoolean(BARCELONA_EXTRA);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitBtn = findViewById(R.id.submit_btn);
        layoutAnswerEt = findViewById(R.id.layout_answer);

        liverpoolB = findViewById(R.id.liverpool_cb);
        barcelonaB = findViewById(R.id.barcelona_cb);
        manchesterB = findViewById(R.id.manchester_cb);

        // TODO: 11/15/2019  setup your restored ui here

        liverpoolB.setChecked(liverpoolCheckState);
        manchesterB.setChecked(manchesterCheckState);
        barcelonaB.setChecked(barcelonaCheckState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(TAG, "onSaveInstanceState: ");
    }

    public void checkAnswers(View view) {
        String ans = layoutAnswerEt.getText().toString();

        boolean isValidAns = false;
        if (ans.contains("scrollview") && ans.length() >= 20) {
            isValidAns = true;
        }

        AlertDialog.Builder answersScore = new AlertDialog.Builder(this);
        answersScore.setTitle("Score")
                .setMessage("Your answer is " + isValidAns)
                .setPositiveButton("Retry", null)
                .setNeutralButton("Exit", null)
                .show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        liverpoolCheckState = liverpoolB.isChecked();
        manchesterCheckState = manchesterB.isChecked();
        barcelonaCheckState = barcelonaB.isChecked();

        outState.putBoolean(LIVERPOOL_EXTRA, liverpoolCheckState);
        outState.putBoolean(MANCHESTER_EXTRA, manchesterCheckState);
        outState.putBoolean(BARCELONA_EXTRA, barcelonaCheckState);
    }
}

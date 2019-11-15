package com.example.quizapp;

import android.os.Bundle;
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

    // constant keys for the bundle
    public static final String LIVERPOOL_EXTRA = "liver";
    public static final String BARCELONA_EXTRA = "barcelona";
    public static final String MANCHESTER_EXTRA = "manchester";

    // getting instance for each view
    private Button submitBtn;
    private EditText layoutAnswerEt;

    private CheckBox liverpoolB;
    private CheckBox barcelonaB;
    private CheckBox manchesterB;

    // this is the variables that hold the check state for the views
    private boolean liverpoolCheckState;
    private boolean barcelonaCheckState;
    private boolean manchesterCheckState;

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        /*
         * here we check for the restored values in the bundle keys
         */
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

        // finding views by ids to use it
        submitBtn = findViewById(R.id.submit_btn);
        liverpoolB = findViewById(R.id.liverpool_cb);
        barcelonaB = findViewById(R.id.barcelona_cb);
        manchesterB = findViewById(R.id.manchester_cb);
        layoutAnswerEt = findViewById(R.id.layout_answer);

        // TODO: 11/15/2019  setup your restored ui here

        // changing the state for the restored variables
        // note: that the default value for the first launch is false
        // so nothing is checked by default
        liverpoolB.setChecked(liverpoolCheckState);
        manchesterB.setChecked(manchesterCheckState);
        barcelonaB.setChecked(barcelonaCheckState);
    }

    /**
     * this is where the action for click listeners of the submit button
     */
    public void checkAnswers(View view) {
        String ans = layoutAnswerEt.getText().toString();

        boolean isValidAns = false;
        // checking for answer input validation
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
        // getting values from view states
        liverpoolCheckState = liverpoolB.isChecked();
        manchesterCheckState = manchesterB.isChecked();
        barcelonaCheckState = barcelonaB.isChecked();

        // attach the variables to the bundle
        outState.putBoolean(LIVERPOOL_EXTRA, liverpoolCheckState);
        outState.putBoolean(MANCHESTER_EXTRA, manchesterCheckState);
        outState.putBoolean(BARCELONA_EXTRA, barcelonaCheckState);
    }
}

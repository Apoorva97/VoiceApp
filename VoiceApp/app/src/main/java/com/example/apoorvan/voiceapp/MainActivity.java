package com.example.apoorvan.voiceapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1234;
    private TextView word;
    private ImageButton imgTEXT;
    float x=-400;
    /**
     * Called with the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_recog);

        imgTEXT = (ImageButton) findViewById(R.id.imageButton);




        word = (TextView) findViewById(R.id.list);
        // Disable button if no recognition service is present
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0)
        {
            imgTEXT.setEnabled(false);
        }
    }

    /**
     * Handle the action of the button being clicked
     */
    public void speakButtonClicked(View v)
    {
        startVoiceRecognitionActivity();
    }

    /**
     * Fire an intent to start the voice recognition activity.
     */
    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice recognition Demo...");
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Handle the results from the voice recognition activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            // Populate the wordsList with the String values the recognition engine thought it heard
            ArrayList<String> matches = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            word.setText(matches.get(0));
            String move= matches.get(0);
            if(move=="right") {
                TranslateAnimation animation = new TranslateAnimation(0.0f, 400.0f,
                        0.0f, 0.0f);
                animation.setDuration(5000);
                animation.setRepeatCount(1);
                animation.setRepeatMode(2);
                animation.setFillAfter(true);
                imgTEXT.startAnimation(animation);

            }
            if(move=="left") {
                TranslateAnimation animation = new TranslateAnimation(0.0f, -400.0f,
                        0.0f, 0.0f);
                animation.setDuration(5000);
                animation.setRepeatCount(1);
                animation.setRepeatMode(2);
                animation.setFillAfter(true);
                imgTEXT.startAnimation(animation);
            }
            if(move=="bottom") {
                TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
                        0.0f, 600.0f);
                animation.setDuration(5000);
                animation.setRepeatCount(1);
                animation.setRepeatMode(2);
                animation.setFillAfter(true);
                imgTEXT.startAnimation(animation);
            }
            if(move=="top") {
                TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -600.0);
                animation.setDuration(5000);
                animation.setRepeatCount(1);
                animation.setRepeatMode(2);
                animation.setFillAfter(true);
                imgTEXT.startAnimation(animation);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
package com.example.david.intervaltimersimplest;

import android.content.Context;
// import android.content.SharedPreferences;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.media.MediaPlayer;

// import java.util.concurrent.TimeUnit;


public class MainActivity extends ActionBarActivity {

    // Declare your view elements
    public EditText countdownTickFrequency;
    public EditText countdownLength;
    public NumberPickerCustom minutePickerInterval;
    public NumberPickerCustom secondPickerInterval;
    public NumberPickerCustom millisecondPickerInterval;

    public static final String ALERT_FREQUENCY = "com.example.david.IntervalTimerSimplest.ALERT_FREQUENCY";
    private float levelOfAccuracy = (float) .01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // loadUserInput();

        // assign your view elements their actual components from the view so we can modify them
        // countdownTickFrequency = (EditText) findViewById(R.id.countdownTickFrequency);
        // countdownLength = (EditText) findViewById(R.id.countdownLength);
        minutePickerInterval = (NumberPickerCustom) findViewById(R.id.minutePickerInterval);
        secondPickerInterval = (NumberPickerCustom) findViewById(R.id.secondPickerInterval);
        millisecondPickerInterval = (NumberPickerCustom) findViewById(R.id.millisecondPickerInterval);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DurationActivity.class);
        // EditText editText = (EditText) findViewById(R.id.editText);
        // String message = editText.getText().toString();
        float message = (minutePickerInterval.getValue() * 60) + (secondPickerInterval.getValue()) + (float) (millisecondPickerInterval.getValue() * levelOfAccuracy);
        intent.putExtra(ALERT_FREQUENCY, message);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public void onPause() {
        super.onPause();

        saveUserInput();

        // save preferences
    }

    public void saveUserInput() {
        SharedPreferences sharedPref = getSharedPreferences("countdownAndBeepInfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("countdownLen", countdownLength.getText().toString());
        editor.putString("beepFrequency", countdownTickFrequency.getText().toString());
        editor.apply();
    }

    public void loadUserInput() {
        SharedPreferences sharedPref = getSharedPreferences("countdownAndBeepInfo", Context.MODE_PRIVATE);

        countdownLength.setText(sharedPref.getString("countdownLen", ""));
        countdownTickFrequency.setText(sharedPref.getString("beepFrequency", ""));

    }*/
}

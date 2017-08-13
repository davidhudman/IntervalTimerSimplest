package com.example.david.intervaltimersimplest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

public class DurationActivity extends ActionBarActivity {

    public NumberPickerCustom minutePickerCountdown, secondPickerCountdown, millisecondPickerCountdown;
    public float levelOfAccuracy = (float) .01;
    public final String[] TIMER_DATA_STRINGS = { "Interval", "CountdownLength" };
    public float[] TIMER_DATA_DATA;
    public Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration);

        minutePickerCountdown = (NumberPickerCustom) findViewById(R.id.minutePickerCountdown);
        secondPickerCountdown = (NumberPickerCustom) findViewById(R.id.secondPickerCountdown);
        millisecondPickerCountdown = (NumberPickerCustom) findViewById(R.id.millisecondPickerCountdown);
        // chronometer = (Chronometer) findViewById(R.id.chronometer);
        // chronometer.start();

        // Get the Intent that started this activity and extract the string
        // Bundle intent = getIntent().getExtras();
        // Float message = intent.getFloat(MainActivity.ALERT_FREQUENCY);
        // TIMER_DATA_DATA[0] = message;

        // Capture the layout's TextView and set the string as its text
        // TextView textView = (TextView) findViewById(R.id.textViewFrequency);
        // textView.setText(message.toString());

    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        // Intent intent = new Intent(this, DurationActivity.class);
        // EditText editText = (EditText) findViewById(R.id.editText);
        // String message = editText.getText().toString();
        // float message = (minutePickerCountdown.getValue() * 60) + (secondPickerInterval.getValue()) + (float) (millisecondPickerInterval.getValue() * levelOfAccuracy);
        // intent.putExtra(ALERT_FREQUENCY, message);
        // startActivity(intent);

        /*Intent intent = new Intent(this, DurationActivity.class);
        float message = (minutePickerCountdown.getValue() * 60) + (secondPickerCountdown.getValue()) + (float) (millisecondPickerCountdown.getValue() * levelOfAccuracy);
        intent.putExtra(TIMER_DATA_STRINGS.toString(), message);
        startActivity(intent);*/
    }
}

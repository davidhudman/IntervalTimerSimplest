package com.example.david.intervaltimersimplest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

public class DurationActivity extends ActionBarActivity {

    public NumberPickerCustom minutePickerCountdown1, secondPickerCountdown1, millisecondPickerCountdown1, minutePickerCountdown2, secondPickerCountdown2, millisecondPickerCountdown2;
    public float levelOfAccuracy = (float) .01;
    public static final String TIMER_DATA_STRINGS = "Interval,CountdownLength";
    public String TIMER_DATA_DATA;
    public Chronometer chronometer;
    public Float receivedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration);

        minutePickerCountdown1 = (NumberPickerCustom) findViewById(R.id.minutePickerCountdown1);
        secondPickerCountdown1 = (NumberPickerCustom) findViewById(R.id.secondPickerCountdown1);
        millisecondPickerCountdown1 = (NumberPickerCustom) findViewById(R.id.millisecondPickerCountdown1);
        minutePickerCountdown2 = (NumberPickerCustom) findViewById(R.id.minutePickerCountdown2);
        secondPickerCountdown2 = (NumberPickerCustom) findViewById(R.id.secondPickerCountdown2);
        millisecondPickerCountdown2 = (NumberPickerCustom) findViewById(R.id.millisecondPickerCountdown2);
        // chronometer = (Chronometer) findViewById(R.id.chronometer);
        // chronometer.start();

        // Get the Intent that started this activity and extract the string
        Bundle intent = getIntent().getExtras();
        Float receivedMessage = intent.getFloat(MainActivity.ALERT_FREQUENCY);
        TIMER_DATA_DATA = receivedMessage.toString() + ",";

        // Capture the layout's TextView and set the string as its text
        // TextView textView = (TextView) findViewById(R.id.textViewFrequency);
        // textView.setText(message.toString());

    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MainTimerUI.class);
        // EditText editText = (EditText) findViewById(R.id.editText);
        // String message = editText.getText().toString();
        float message = (
                (((minutePickerCountdown1.getValue() * 10) + minutePickerCountdown2.getValue() ) * 60)
                + ((secondPickerCountdown1.getValue() * 10) + secondPickerCountdown2.getValue())
                + (float) (((millisecondPickerCountdown1.getValue() * 10) + millisecondPickerCountdown2.getValue()) * levelOfAccuracy));
        TIMER_DATA_DATA += message;
        intent.putExtra(TIMER_DATA_STRINGS, TIMER_DATA_DATA);
        startActivity(intent);

        /*Intent intent = new Intent(this, DurationActivity.class);
        float message = (minutePickerCountdown.getValue() * 60) + (secondPickerCountdown.getValue()) + (float) (millisecondPickerCountdown.getValue() * levelOfAccuracy);
        intent.putExtra(TIMER_DATA_STRINGS.toString(), message);
        startActivity(intent);*/
    }
}

package com.example.david.intervaltimersimplest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainTimerUI extends ActionBarActivity {

    public String TIMER_DATA_STRINGS2 = "Interval,CountdownLength";
    public String TIMER_DATA_DATA2;
    String receivedMessage;
    public TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_timer_ui);

        // textView2
        textView2 = (TextView) findViewById(R.id.textView2);

        // Get the Intent that started this activity and extract the string
        Bundle intent = getIntent().getExtras();
        receivedMessage = intent.getString(DurationActivity.TIMER_DATA_STRINGS);

        textView2.setText(receivedMessage);

    }
}

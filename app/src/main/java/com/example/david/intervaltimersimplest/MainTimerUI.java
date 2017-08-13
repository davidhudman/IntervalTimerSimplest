package com.example.david.intervaltimersimplest;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainTimerUI extends ActionBarActivity {

    public String TIMER_DATA_STRINGS2 = "Interval,CountdownLength";
    public String TIMER_DATA_DATA2;
    String receivedMessage;
    public TextView textView2;

    public Button startCountdown;
    public TextView countdownResults;
    public TextView remainingTimeText, elapsedTimeText;

    public boolean isTimerStarted = false;

    public preciseCountdown countDownTimer;
    public preciseCountdown visualTimer;

    public MediaPlayer bellSound;

    private float countdownLen;
    private float countdownTick;

    private float levelOfAccuracy = (float) .01;

    public Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_timer_ui);

        // textView2
        textView2 = (TextView) findViewById(R.id.textView2);

        // Get the Intent that started this activity and extract the string
        Bundle intent = getIntent().getExtras();
        receivedMessage = intent.getString(DurationActivity.TIMER_DATA_STRINGS);

        String intentStringOutput = "";
        intentStringOutput += receivedMessage.substring(0, receivedMessage.indexOf(','));
        intentStringOutput += "--------";
        intentStringOutput += receivedMessage.substring(receivedMessage.indexOf(',')+1, receivedMessage.length());
        textView2.setText(intentStringOutput);

        countdownTick = Float.parseFloat(receivedMessage.substring(0, receivedMessage.indexOf(',')));
        countdownLen = Float.parseFloat(receivedMessage.substring(receivedMessage.indexOf(',')+1, receivedMessage.length()));

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.stop();
        startCountdown = (Button) findViewById(R.id.startButton);
        countdownResults = (TextView) findViewById(R.id.textView);
        remainingTimeText = (TextView) findViewById(R.id.remainingTimeText);
        elapsedTimeText = (TextView) findViewById(R.id.elapsedTimeText);


        // assign mp3 for music to be played on each tick of the countdown
        bellSound = MediaPlayer.create(this, R.raw.bellsound1secexactly);

        final AudioManager am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        // Request audio focus for playback
        final AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    // screw that other app - they're not getting focus
                }
            }
        };

        // I think there's a risk that this could get fired if the tick time was close to the length of the alert sound. If the alert sound is much shorter, it shouldn't be a problem.
        bellSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                am.abandonAudioFocus(afChangeListener);
            }
        });


        // final AudioManager.OnAudioFocusChangeListener afChangeListener;

        // when the button is clicked, do this
        startCountdown.setOnClickListener(new View.OnClickListener(){


            public void audioTimerPrintThis(final String stringToPrint) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        countdownResults.append(stringToPrint);
                    }
                });
            }

            public void playTheSound() {
                int result = am.requestAudioFocus(afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    if (bellSound.isPlaying()) {
                        bellSound.seekTo(0);
                    } else {
                        bellSound.start();
                    }
                }
            }

            public String millisToColonTimeFormat(long millis) {
                int remainderMillis = (int) (millis % 1000);
                int remainderSeconds = (int) ((millis / (1000)) % 60);
                int remainderMinutes = (int) ((millis / (1000 * 60)) % 60);
                int remainderHours = (int) ((millis / (1000 * 60 * 60)));

                String printMillis = String.format("%03d", remainderMillis);
                String printSeconds = String.format("%02d", remainderSeconds);
                String printMinutes = String.format("%02d", remainderMinutes);
                String printHours = String.format("%02d", remainderHours);

                return printHours + ":" + printMinutes + ":" + printSeconds + ":" + printMillis;
            }

            @Override
            public void onClick(View view){
                if (!isTimerStarted) {                 // check if the timer has already been started
                    isTimerStarted = !isTimerStarted;

                    startCountdown.setText("STOP");


                    if(true) {
                        // countdownTick = 0; // (minutePickerInterval.getValue() * 60) + (secondPickerInterval.getValue()) + (float) (millisecondPickerInterval.getValue() * levelOfAccuracy);
                        // countdownLen = 0; // (minutePickerCountdown.getValue() * 60) + (secondPickerCountdown.getValue()) + (float) (millisecondPickerCountdown.getValue() * levelOfAccuracy);

                        audioTimerPrintThis("\n" + Float.toString(countdownTick) + " : " + Float.toString(countdownLen) + " test");

                        // create countdown timer for the sound alerts
                        countDownTimer = new preciseCountdown((int) (countdownLen * 1000), (int) (countdownTick * 1000), 0) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                audioTimerPrintThis("\n" + (float) (millisUntilFinished * levelOfAccuracy) + " : " + ((countdownLen) - (millisUntilFinished * levelOfAccuracy)));
                                playTheSound();
                            }

                            @Override
                            public void onFinished() {
                                audioTimerPrintThis("\nFinished");
                                playTheSound();
                            }
                        };

                        /**
                         // What: create a visual timer for the view
                         // Why: the reason we need two timers is because the onTick() event can only run on one interval, so we need one for what the
                         **/
                        visualTimer = new preciseCountdown((int) (countdownLen * 1000), 49, 0) {

                            public void visualTimerPrintThis(final long print_MillisUntilFinished, final boolean isFinished) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (!isFinished) {
                                            // update UI with elapsedTime and remainingTime
                                            long elapsedTimeMillis = (long) (countdownLen * 1000) - print_MillisUntilFinished;


                                            // put the data into the view
                                            remainingTimeText.setText(millisToColonTimeFormat(print_MillisUntilFinished));
                                            elapsedTimeText.setText(millisToColonTimeFormat(elapsedTimeMillis));
                                        }
                                        else {
                                            // Update the UI with what the user is supposed to see
                                            remainingTimeText.setText("00:00:00.000");
                                            elapsedTimeText.setText(millisToColonTimeFormat((long)(countdownLen * 1000)));
                                        }


                                    }
                                });
                            }

                            @Override
                            public void onTick(long millisUntilFinished) {
                                final long printMillisUntilFinished = millisUntilFinished;
                                visualTimerPrintThis(printMillisUntilFinished, false);

                            }

                            @Override
                            public void onFinished() {
                                visualTimerPrintThis(0, true);
                            }
                        };

                        countDownTimer.start();
                        visualTimer.start();
                    }
                }
                else {
                    isTimerStarted = !isTimerStarted;

                    countDownTimer.stop();
                    visualTimer.stop();
                    countDownTimer = null;
                    visualTimer = null;

                    startCountdown.setText("START");
                }
            }
        });

        countdownResults.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String displayText = countdownResults.getText().toString();
                countdownResults.setText(displayText.subSequence(displayText.indexOf('\n') + 1, displayText.length()));

            }
        });

        countdownResults.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                // clear text
                countdownResults.setText("");
                return true;
            }
        });

    }

    public void chronometerClick(View view) {
        if (chronometer.isRunning())
            chronometer.stop();

        else
            chronometer.start();
    }

    public void stopChrono(View view) {
        if (chronometer.isRunning())
            chronometer.stop();
    }
}

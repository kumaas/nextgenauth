package ashfox.nextgenauthentication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.view.GestureDetector;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.achartengine.chart.LineChart;
import org.achartengine.chart.PieChart;

import java.util.ArrayList;
import java.util.Random;

import ashfox.nextgenauthentication.util.AuthResult;
import ashfox.nextgenauthentication.util.Authenticator;
import ashfox.nextgenauthentication.util.ImageAdapter;
import ashfox.nextgenauthentication.util.KeyPressAttributes;
import ashfox.nextgenauthentication.util.StatCollector;
import ashfox.nextgenauthentication.util.Storage;

/**

 */
public class LockScreen extends Activity {
    public TextView display, challenge;

    public enum modes {TRAIN, AUTHENTICATE};
    private modes mode = modes.AUTHENTICATE;
    private GestureDetector detector;

    private String TAG = "LockScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        display = (TextView) findViewById(R.id.tv);
        challenge = (TextView) findViewById(R.id.tvChallenge);
        challenge.setText(String.valueOf((getRandomFiveDigits())));

        /* set up the button listeners */
        Button reset = (Button) findViewById(R.id.reset_model);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // reset the model
                Storage.clearCurrent();
                Storage.clearHistory();
                display.setText("");
                Toast.makeText(getApplicationContext(), "Cleared Model", Toast.LENGTH_SHORT).show();
            }
        });

        ToggleButton train = (ToggleButton) findViewById(R.id.train);
        train.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
                if (isChecked) {
                    mode = modes.TRAIN;
                } else {
                    mode = modes.AUTHENTICATE;
                }
                display.setText("");
                challenge.setText(String.valueOf((getRandomFiveDigits())));
            }
        });


        Button enter = (Button) findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // authenticate/train the model
                if (mode == modes.AUTHENTICATE) {
                    if(Storage.getHistory().size() <= 20) {
                        Toast.makeText(getApplicationContext(), "Please train the model!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    StatCollector.addTrial();
                    Authenticator a = new Authenticator();
                    if (a.verify(Storage.getHistory(), Storage.getCurrent()) == AuthResult.PASSED) {
                        new AlertDialog.Builder(LockScreen.this)
                                .setTitle("Authorized!")
                                .setMessage("The model thinks this is the authorized user. Correct?")
                                .setPositiveButton("Correct", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        StatCollector.addSuccess();
                                    }
                                })
                                .setNegativeButton("Incorrect", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        StatCollector.addFalse_pos();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_input_add)
                                .show();
                    } else {
                        new AlertDialog.Builder(LockScreen.this)
                                .setTitle("Unauthorized!")
                                .setMessage("The model thinks this is not the authorized user. Correct?")
                                .setPositiveButton("Correct", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        StatCollector.addSuccess();
                                    }
                                })
                                .setNegativeButton("Incorrect", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        StatCollector.addFalse_neg();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                } else if (mode == modes.TRAIN) {
                    for (KeyPressAttributes kpa : Storage.getCurrent()) {
                        Storage.addHistory(kpa);
                    }
                    Toast.makeText(getApplicationContext(),
                            "Trained: set size = " + String.valueOf(Storage.getHistory().size()),
                            Toast.LENGTH_SHORT).show();
                }
                Storage.clearCurrent();
                display.setText("");
                challenge.setText(String.valueOf((getRandomFiveDigits())));
            }

        });

        Button metrics = (Button) findViewById(R.id.metrics);
        metrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LockScreen.this, Metrics.class);
                LockScreen.this.startActivity(myIntent);
            }
        });



        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        final ListAdapter adp = new ImageAdapter(this);
        gridview.setAdapter(adp);


//        Intent lineChart = new LineChart().execute(getApplicationContext(), null, ???);
//        startActivity(pieChart);

    }

    private long getRandomFiveDigits(){
//        Random r = new Random(System.currentTimeMillis());
//        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        long tmp = mNums[mNumIdx];
        mNumIdx = (mNumIdx + 1) % mNums.length;
        return tmp;
    }

    // Numbers!
    private static int mNumIdx = 0;
    private long[] mNums = {
            98264137, 47026249,
            51457784, 42581236,
            76947571, 52479253,
            42283911
    };
}

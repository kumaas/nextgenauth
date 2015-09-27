package ashfox.nextgenauthentication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.Random;

import ashfox.nextgenauthentication.util.AuthResult;
import ashfox.nextgenauthentication.util.Authenticator;
import ashfox.nextgenauthentication.util.ImageAdapter;
import ashfox.nextgenauthentication.util.KeyPressAttributes;
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
                if(isChecked){
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
                    Authenticator a = new Authenticator();
                    if (a.verify(Storage.getHistory(), Storage.getCurrent()) == AuthResult.PASSED) {
                        Toast.makeText(getApplicationContext(), "Authenticated",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Authentication Failed",
                                Toast.LENGTH_SHORT).show();
                    }
                } else if(mode == modes.TRAIN) {
                    for(KeyPressAttributes kpa : Storage.getCurrent()){
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


        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        final ListAdapter adp = new ImageAdapter(this);
        gridview.setAdapter(adp);
    }

    private long getRandomFiveDigits(){
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    // Numbers!
    private long[] mThumbIds = {
            98264137, 47026249,
            51457784, 42581236,
            76947571, 52479253,
            42283911
    };
}

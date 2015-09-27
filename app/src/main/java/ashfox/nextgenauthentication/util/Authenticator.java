package ashfox.nextgenauthentication.util;

import android.util.Log;

import java.lang.reflect.Array;
import java.security.Key;
import java.util.ArrayList;

/**
 * Created by kumaas on 9/26/15.
 */
public class Authenticator{

        public Authenticator() {

        }

        public AuthResult verify(ArrayList<KeyPressAttributes> train_data, ArrayList<KeyPressAttributes> test_data) {
            PressureBasedScorer prscorer = new PressureBasedScorer();
            //Log.d("behen", String.valueOf(train_data.size()));
            prscorer.train(train_data);
            ArrayList<Double> pressure_scores = prscorer.score(test_data);

            PositionBasedScorer posscorer = new PositionBasedScorer();
            posscorer.train(train_data);
            ArrayList<Double> position_scores = posscorer.score(test_data);

            DurationBasedScorer durscorer = new DurationBasedScorer();
            durscorer.train(train_data);
            ArrayList<Double> duration_scores = durscorer.score(test_data);

            int count = 0;
            for(int i = 0; i < pressure_scores.size(); i++){
                Log.d("Score: ", String.valueOf(test_data.get(i).key_press) + " | "
                        + String.valueOf(pressure_scores.get(i)) + " | "
                        + String.valueOf(position_scores.get(i)) + " | "
                        + String.valueOf(duration_scores.get(i)));
                double score = 0.4 * pressure_scores.get(i) + 0.6 * position_scores.get(i);
                if(score >= 0.40){
                    count++;
                }
            }
            return (count > test_data.size() / 2.0) ? AuthResult.PASSED : AuthResult.FAILED;
        }

        /*public AuthResult verify() {
            ArrayList<Double> static_data = new ArrayList<Double>(){{
                add(5.0);
                add(4.0);
                add(4.0);
                add(3.0);
                add(3.0);
                add(3.0);
                add(2.0);
                add(2.0);
                add(2.0);
                add(2.0);
            }};
            ArrayList<KeyPressAttributes> train_data = new ArrayList<>();
            for(Double sdata : static_data){
                KeyPressAttributes kp = new KeyPressAttributes();
                kp.pressure = sdata;
                kp.key_press = 1;
                train_data.add(kp);
            }
            PressureBasedScorer pscorer = new PressureBasedScorer();
            pscorer.train(train_data);
            KeyPressAttributes kl = new KeyPressAttributes();
            kl.key_press=2;
            kl.pressure = 3;
            train_data.add(kl);
            for(Double score : pscorer.score(train_data)){
                Log.d("xxxxxxxxx  ", String.valueOf(score));
            }
            return AuthResult.PASSED;
        }*/


}

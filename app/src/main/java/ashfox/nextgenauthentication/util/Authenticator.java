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
            PressureBasedScorer pscorer = new PressureBasedScorer();
            Log.d("behen", String.valueOf(train_data.size()));
            pscorer.train(train_data);
            int count = 0;
            for(Double score : pscorer.score(train_data)){
                Log.d("xxxxxxxxx  ", String.valueOf(score));
                if(score >= 0.35){
                    count++;
                }
            }
            return (count >= test_data.size() / 2) ? AuthResult.PASSED : AuthResult.FAILED;
        }

        public AuthResult verify() {
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
        }


}

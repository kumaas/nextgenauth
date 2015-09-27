package ashfox.nextgenauthentication.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by kumaas on 9/26/15.
 */
public class PressureBasedScorer {
    private Hashtable<Integer, GaussianFilter> key2GF;

    public PressureBasedScorer() {
        this.key2GF = new Hashtable<>();
    }

    public boolean train(ArrayList<KeyPressAttributes> data_keys){
        HashMap<Integer, ArrayList<Double>> key2Pr = new HashMap<Integer,ArrayList<Double>>();

        for(KeyPressAttributes data_key : data_keys){
            ArrayList<Double> old_list = key2Pr.get(data_key.key_press);
            if(old_list != null){
                old_list.add(data_key.pressure);
            }
            else{
                ArrayList<Double> new_list = new ArrayList<Double>();
                new_list.add(data_key.pressure);
                key2Pr.put(data_key.key_press, new_list);
            }
        }
        for(Map.Entry<Integer, ArrayList<Double>> entry : key2Pr.entrySet()){
            key2GF.put(new Integer(entry.getKey()), new GaussianFilter(entry.getValue()));
            Log.d("Pres-> ", String.valueOf(entry.getKey()) + " | M: " +
                    String.valueOf(key2GF.get(entry.getKey()).getMean()) + " | V: " +
                    String.valueOf(key2GF.get(entry.getKey()).getStandard_deviation()) + " | S: " +
                    String.valueOf(key2GF.get(entry.getKey()).getNumSamples()));
        }
        return true;
    }

    public ArrayList<Double> score(ArrayList<KeyPressAttributes> data_keys){
        ArrayList<Double> scores = new ArrayList<Double>();
        for(KeyPressAttributes data_key : data_keys){
            GaussianFilter gf = key2GF.get(data_key.key_press);
            if(gf != null){
                scores.add(Math.exp(-1 * gf.varianceLevel(data_key.pressure)));
            }
            else {
                scores.add(new Double(0));
            }
        }
        return scores;
    }
}
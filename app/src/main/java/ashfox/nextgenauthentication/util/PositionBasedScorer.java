package ashfox.nextgenauthentication.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by kumaas on 9/26/15.
 */
public class PositionBasedScorer {
    private Hashtable<Integer, GaussianFilter> xkey2GF;
    private Hashtable<Integer, GaussianFilter> ykey2GF;

    public PositionBasedScorer() {
        this.xkey2GF = new Hashtable<>();
        this.ykey2GF = new Hashtable<>();
    }

    public boolean train(ArrayList<KeyPressAttributes> data_keys){
        HashMap<Integer, ArrayList<Double>> key2Pr = new HashMap<>();

        for(KeyPressAttributes data_key : data_keys){
            ArrayList<Double> old_list = key2Pr.get(data_key.key_press);
            if(old_list != null){
                old_list.add(data_key.x);
            }
            else{
                ArrayList<Double> new_list = new ArrayList<>();
                new_list.add(data_key.x);
                key2Pr.put(data_key.key_press, new_list);
            }
        }
        for(Map.Entry<Integer, ArrayList<Double>> entry : key2Pr.entrySet()){
            xkey2GF.put(new Integer(entry.getKey()), new GaussianFilter(entry.getValue()));
            Log.d("XPos-> ", String.valueOf(entry.getKey()) + " | M: " +
                    String.valueOf(xkey2GF.get(entry.getKey()).getMean()) + " | V: " +
                    String.valueOf(xkey2GF.get(entry.getKey()).getStandard_deviation()) + " | S: " +
                    String.valueOf(xkey2GF.get(entry.getKey()).getNumSamples()));
        }

        HashMap<Integer, ArrayList<Double>> ykey2Pr = new HashMap<>();

        for(KeyPressAttributes data_key : data_keys){
            ArrayList<Double> old_list = ykey2Pr.get(data_key.key_press);
            if(old_list != null){
                old_list.add(data_key.y);
            }
            else{
                ArrayList<Double> new_list = new ArrayList<>();
                new_list.add(data_key.y);
                ykey2Pr.put(data_key.key_press, new_list);
            }
        }
        for(Map.Entry<Integer, ArrayList<Double>> entry : ykey2Pr.entrySet()){
            ykey2GF.put(new Integer(entry.getKey()), new GaussianFilter(entry.getValue()));
            Log.d("YPos-> ", String.valueOf(entry.getKey()) + " | M: " +
                    String.valueOf(ykey2GF.get(entry.getKey()).getMean()) + " | V: " +
                    String.valueOf(ykey2GF.get(entry.getKey()).getStandard_deviation()) + " | S: " +
                    String.valueOf(ykey2GF.get(entry.getKey()).getNumSamples()));
        }

        return true;
    }

    public ArrayList<Double> score(ArrayList<KeyPressAttributes> data_keys){
        ArrayList<Double> scores = new ArrayList<>();
        for(KeyPressAttributes data_key : data_keys){
            GaussianFilter gf = xkey2GF.get(data_key.key_press);
            if(gf != null){
                Double x_score = Math.exp(-1 * gf.varianceLevel(data_key.x));
                GaussianFilter ygf = ykey2GF.get(data_key.key_press);
                Double y_score = Math.exp(-1 * ygf.varianceLevel(data_key.y));
                scores.add((x_score + y_score)/2);
            }
            else {
                scores.add(new Double(0));
            }
        }
        return scores;
    }
}

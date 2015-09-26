package ashfox.nextgenauthentication.util;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by kumaas on 9/26/15.
 */
public class GaussianFilter {
    public double getMean() {
        return mean;
    }

    public double getStandard_deviation() {
        return standard_deviation;
    }

    private double mean;
    private double standard_deviation;

    public GaussianFilter(ArrayList<Double> data_points) {

        double total = 0;
        for (double data_point : data_points) {
            total += data_point;
        }
        mean = total / data_points.size();
//        Log.d("-------",String.valueOf(mean));
        total = 0;
        for (double data_point : data_points) {
            total += Math.pow(data_point - mean, 2);
        }
        total /= data_points.size();
        standard_deviation = Math.sqrt(total);
//        Log.d("-------",String.valueOf(standard_deviation));
    }

    public boolean isWithinVarianceLevel(double data_point, int level) {
        return Math.abs(data_point - mean) <= (level * standard_deviation);
    }
    public double varianceLevel(double data_point){
        double shit = Math.floor(Math.abs(data_point-mean)/standard_deviation);
//        Log.d("~~~~~~~~~~SD",String.valueOf(standard_deviation));
//        Log.d("~~~~~~~~~~DP",String.valueOf(data_point));
//        Log.d("`~~~~~~~~~SH",String.valueOf(shit));
        return shit;
    }
}
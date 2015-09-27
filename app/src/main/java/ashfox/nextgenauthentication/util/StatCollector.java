package ashfox.nextgenauthentication.util;

/**
 * Created by anirudh on 9/27/15.
 */
public class StatCollector {
    private static long trials = 0;
    private static long success = 0;
    private static long false_pos = 0;
    private static long false_neg = 0;

    public static long getSuccess() {
        return success;
    }

    public static void addSuccess() {
        success++;
    }

    public static  long getTrials() {
        return trials;
    }

    public static void addTrial() {
        trials++;
    }

    public static long getFalse_pos() {
        return false_pos;
    }

    public static void addFalse_pos() {
        false_pos++;
    }

    public static long getFalse_neg() {
        return false_neg;
    }

    public static void addFalse_neg() {
        false_neg++;
    }
}

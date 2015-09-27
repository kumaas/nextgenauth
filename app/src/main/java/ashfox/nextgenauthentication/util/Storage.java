package ashfox.nextgenauthentication.util;

import java.util.ArrayList;

public class Storage {
    private static ArrayList<KeyPressAttributes> history = new ArrayList<>();
    private static ArrayList<KeyPressAttributes> current = new ArrayList<>();

    public static void addHistory(int key_press, double x, double y, double pressure, double duration) {
        KeyPressAttributes kpa = new KeyPressAttributes();
        kpa.key_press = key_press;
        kpa.x = x;
        kpa.y = y;
        kpa.pressure = pressure;
        kpa.duration = duration;
        addHistory(kpa);
    }

    public static void addHistory(KeyPressAttributes kpa) {
        if(history.size() >= 1000) {
            history.remove(history.size() - 1);
        }
        history.add(0, kpa);
    }

    public static void addCurrent(int key_press, double x, double y, double pressure, double duration) {
        KeyPressAttributes kpa = new KeyPressAttributes();
        kpa.key_press = key_press;
        kpa.x = x;
        kpa.y = y;
        kpa.pressure = pressure;
        kpa.duration = duration;
        current.add(kpa);
    }

    public static void clearHistory(){
        history.clear();
    }
    public static void clearCurrent(){
        current.clear();
    }
    public static ArrayList<KeyPressAttributes> getHistory(){
        return history;
    }
    public static ArrayList<KeyPressAttributes> getCurrent(){
        return current;
    }
}



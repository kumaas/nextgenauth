package ashfox.nextgenauthentication;

import ashfox.nextgenauthentication.util.PointerView;
import ashfox.nextgenauthentication.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.view.GestureDetector;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class LockScreen extends Activity {
    private GestureDetector detector;
    private String TAG = "LockScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PointerView(this));
    }
    public boolean onTouchEvent(MotionEvent e){
        Log.d(TAG ,"Touch: " + String.valueOf(e.getPointerCount()));
        this.detector.onTouchEvent(e);
        return super.onTouchEvent(e);
    }
}

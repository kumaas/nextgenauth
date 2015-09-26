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
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import ashfox.nextgenauthentication.util.ImageAdapter;

/**

 */
public class LockScreen extends Activity {
    private GestureDetector detector;
    private String TAG = "LockScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        final ListAdapter adp = new ImageAdapter(this);
        gridview.setAdapter(adp);


    }

}

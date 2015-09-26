package ashfox.nextgenauthentication.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PointerView extends SurfaceView  implements SurfaceHolder.Callback {
    private String TAG = "PointerView.java";
    private SurfaceHolder sh;
    private GestureDetector detector;
    private static int bubbleColor = 0;
    public PointerView(Context context) {
        super(context);
        sh = getHolder();
        sh.addCallback(this);
        setLongClickable(false);
        detector = new GestureDetector(context, new GestureListener());
    }
    // also implement surfaceChanged() and surfaceDestroyed() (can be blank)

    public boolean onTouchEvent(MotionEvent e){
        this.detector.onTouchEvent(e);
        super.onTouchEvent(e);
        return true;
    }

    class GestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
        private GestureDetector detector;
        GestureListener(){
            detector = new GestureDetector(this);
        }
        public boolean onDown(MotionEvent e) {
            return true;
        }
        public void onLongPress(MotionEvent e) {}

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return true;
        }

        public void onShowPress(MotionEvent e) {}
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return true;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub

    }
}

package ashfox.nextgenauthentication.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ButtonView extends ImageView {
    public ButtonView(Context context) {
        super(context);
    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Context context = getContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        return super.dispatchTouchEvent(event);
    }
}

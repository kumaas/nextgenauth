package ashfox.nextgenauthentication.util;

/**
 * Created by anirudh on 9/26/15.
 */
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ShapeDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Hashtable;

import ashfox.nextgenauthentication.LockScreen;
import ashfox.nextgenauthentication.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private static long LastKeyDownTime;

    public ImageAdapter(Context c) {
        mContext = c;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return 10;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int elementIndex = (position + 1) % 10;

        final ViewHolder holder;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item, null);
            holder.imageview = (ImageView) convertView.findViewById(R.id.thumbImage);
            holder.imageview.setImageResource(mThumbIds[elementIndex]);
            holder.textView = (TextView) convertView.findViewById(R.id.textView1);
            holder.textView.setText(String.valueOf(elementIndex));
            holder.imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.imageview.setPadding(8, 8, 8, 8);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        convertView.setId(position);
        holder.imageview.setId(position);
        holder.id = position;

        //draw border
        ShapeDrawable rectShapeDrawable = new ShapeDrawable(); // pre defined class
        Paint paint = rectShapeDrawable.getPaint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(1); // you can change the value of 5
        convertView.setBackgroundDrawable(rectShapeDrawable);

        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    LastKeyDownTime = System.currentTimeMillis();
                    return true;
                }

                long eventDuration = 0;
                /* we now record the event*/
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    eventDuration = System.currentTimeMillis() - LastKeyDownTime;
                    // have same code as onTouchEvent() (for the Activity) above
                    Storage.addCurrent(elementIndex, event.getX(), event.getY(), event.getPressure(), eventDuration);
                    ((LockScreen) mContext).display.append(String.valueOf(elementIndex));
                    return true;
                }
                return false;
            }
        });

        return convertView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.a0, R.drawable.a1, R.drawable.a2, R.drawable.a3,
            R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7,
            R.drawable.a8, R.drawable.a9
    };
}

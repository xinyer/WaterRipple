package com.wx.waterripple;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RipplesActivity extends BaseActivity implements OnTouchListener {
    private RipplesRenderer mRenderer;
    private Point mScreenSize;

    private int[] bitmapResource = new int[] {
      R.drawable.image_0, R.drawable.image_1, R.drawable.image_2
    };

    private int index = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRenderer = new RipplesRenderer(this);
        mRenderer.setSurfaceView(mSurfaceView);
        super.setRenderer(mRenderer);

        mSurfaceView.setOnTouchListener(this);

        Display display = getWindowManager().getDefaultDisplay();
        mScreenSize = new Point();
        mScreenSize.x = display.getWidth();
        mScreenSize.y = display.getHeight();
//
//        LinearLayout ll = new LinearLayout(this);
//        ll.setOrientation(LinearLayout.VERTICAL);
//        ll.setGravity(Gravity.BOTTOM);
//
//        TextView tv = new TextView(this);
//        tv.setText("Touch Me.");
//        tv.setTextColor(0xffffffff);
//        ll.addView(tv);
//        mLayout.addView(ll);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            index ++;
            index %= bitmapResource.length;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), bitmapResource[index]);
            mRenderer.setTouch(event.getX() / mScreenSize.x, 1.0f - (event.getY() / mScreenSize.y), bitmap);
        }
        return super.onTouchEvent(event);
    }
}
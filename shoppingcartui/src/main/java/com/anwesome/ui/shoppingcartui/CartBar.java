package com.anwesome.ui.shoppingcartui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 01/06/17.
 */

public class CartBar extends View {
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    private float scale = 0;
    public CartBar(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.white_cart);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            bitmap = Bitmap.createScaledBitmap(bitmap,h/2,h/2,true);
        }
        paint.setColor(Color.parseColor("#00BCD4"));
        canvas.drawBitmap(bitmap,4*w/5-h/2,h/4,paint);
        canvas.save();
        canvas.translate(4*w/5,h/2);
        canvas.scale(scale,scale);
        paint.setColor(Color.argb(150,0,0,0));
        canvas.drawCircle(0,0,h/4,paint);
        canvas.restore();
        paint.setColor(Color.RED);
        canvas.drawCircle(4*w/5+h/2,h/4,h/8,paint);
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    public void update(float factor) {
        scale = factor;
        postInvalidate();
    }
}

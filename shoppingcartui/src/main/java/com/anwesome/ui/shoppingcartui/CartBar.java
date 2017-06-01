package com.anwesome.ui.shoppingcartui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.ConcurrentLinkedQueue;

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
        Constants.viewAnimatore.setCartBar(this);
        Constants.itemContainer.subscribe(this);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            bitmap = Bitmap.createScaledBitmap(bitmap,h/2,h/2,true);
        }
        canvas.drawColor(Color.parseColor("#00BCD4"));
        canvas.drawBitmap(bitmap,4*w/5-h/4,h/4,paint);
        canvas.save();
        canvas.translate(4*w/5,h/2);
        canvas.scale(scale,scale);
        paint.setColor(Color.argb(150,0,0,0));
        canvas.drawCircle(0,0,h/4,paint);
        canvas.restore();
        ConcurrentLinkedQueue<ShoppingCartItem> items = Constants.itemContainer.getSelectedItems();
        paint.setColor(Color.RED);
        canvas.drawCircle(4 * w / 5 + h/4, h / 4, h / 5, paint);
        paint.setTextSize(h/5);
        paint.setColor(Color.WHITE);
        canvas.drawText(""+items.size(),4*w/5+h/4-paint.measureText(""+items.size())/2,h/5+paint.getTextSize()/2,paint);
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            Constants.viewAnimatore.start();
        }
        return true;
    }
    public void update(float factor) {
        scale = factor;
        postInvalidate();
    }
}

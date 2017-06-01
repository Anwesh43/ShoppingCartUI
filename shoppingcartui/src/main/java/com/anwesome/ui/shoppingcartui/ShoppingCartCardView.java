package com.anwesome.ui.shoppingcartui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 01/06/17.
 */

public class ShoppingCartCardView extends View{
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int time = 0,w,h;
    public ShoppingCartCardView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class CardButton {
        private float x,y,scale = 0,size,dir = 0;
        public CardButton(float x) {
            this.x = x;
            this.y = 4*h/5;
            size = w/12;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.save();
            canvas.scale(scale,scale);
            canvas.restore();
            canvas.restore();
        }
        public void update() {
            scale+=0.2f*dir;
            if(scale>1) {
                dir *= -1;
            }
            if(scale < 0) {
                dir = 0;
                scale = 0;
            }
        }
        public boolean stopped() {
            return this.dir == 0;
        }
        public boolean handleTap(float x,float y) {
            boolean condition = x>=this.x-size && x<=this.x+size && y>=this.y-size && y<=this.y+size && dir == 0;
            if(condition) {
                dir = 1;
            }
            return condition;
        }
        public int hashCode() {
            return (int)(x+dir);
        }
    }
    private class AnimationHandler {
        private ConcurrentLinkedQueue<CardButton> cardButtons = new ConcurrentLinkedQueue<>();
        private boolean isAnimated = false;
        public void animate() {
            if(isAnimated) {
                for(CardButton cardButton:cardButtons) {
                    cardButton.update();
                    if(cardButton.stopped()) {
                        cardButtons.remove(cardButton);
                        if(cardButtons.size() == 0) {
                            isAnimated = false;
                        }
                    }
                }
                try {
                    Thread.sleep(50);
                    invalidate();
                }
                catch (Exception ex) {

                }
            }
        }
        public void handleTapForButtons(float x,float y) {
            for(CardButton cardButton:cardButtons) {
                if(cardButton.handleTap(x,y)) {
                    cardButtons.add(cardButton);
                    if(cardButtons.size() == 1) {
                        isAnimated = true;
                        postInvalidate();
                    }
                }
            }
        }

    }
}

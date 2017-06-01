package com.anwesome.ui.shoppingcartui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
    private ShoppingCartItem shoppingCartItem;
    public ShoppingCartCardView(Context context,ShoppingCartItem shoppingCartItem) {
        super(context);
        this.shoppingCartItem = shoppingCartItem;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            shoppingCartItem.initBitmap(w,h);
        }
        shoppingCartItem.draw(canvas,paint,w,h);
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }
    private class CardButton {
        private float x,y,scale = 0,size,dir = 0,changeFactor = 1;
        private CardButtonType type;
        public CardButton(CardButtonType type) {
            this.type = type;
            this.y = 9*h/10;
            size = w/20;
            decideAccordingToType();
        }
        private void decideAccordingToType() {
            switch (type) {
                case PLUS:
                    changeFactor = 1;
                    x = 9*w/10-w/9;
                    break;
                case MINUS:
                    changeFactor = -1;
                    x = w/9;
                    break;
                default:
                    break;
            }
        }
        public float getChangeFactor() {
            return changeFactor;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(x,y);
            paint.setColor(Color.parseColor("#BDBDBD"));
            canvas.drawCircle(0,0,size,paint);
            paint.setStrokeWidth(size/5);
            paint.setColor(Color.WHITE);
            if(type == CardButtonType.PLUS) {

                for(int i=0;i<2;i++) {
                    canvas.save();
                    canvas.rotate(i * 90);
                    canvas.drawLine(0,-size/2,0,size/2,paint);
                    canvas.restore();
                }
            }
            else {
                canvas.drawLine(-size/2,0,size/2,0,paint);
            }
            canvas.save();
            canvas.scale(scale,scale);
            paint.setColor(Color.argb(150,0,0,0));
            canvas.drawCircle(0,0,size,paint);
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
    private enum CardButtonType {
        PLUS,MINUS;
    }
}

package com.anwesome.ui.shoppingcartui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 01/06/17.
 */

public class ShoppingCartItem {
    private Bitmap bitmap;
    private String title,subtitle;
    private int count = 0;
    private float price = 0;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private String tag;
    public ShoppingCartItem(Bitmap bitmap,String title,String subtitle,float price) {
        this.bitmap = bitmap;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void updateItemCount(int factor) {
        count += factor;
        if(count < 0) {
            count = 0;
        }
    }

    public int hashCode() {
        return (int)(price+count)+title.hashCode()+subtitle.hashCode()+bitmap.hashCode();
    }
    public void initBitmap(int w,int h) {
        bitmap = Bitmap.createScaledBitmap(bitmap,9*w/10,3*h/5,true);
    }
    public void draw(Canvas canvas, Paint paint,int w,int h) {
        canvas.drawBitmap(bitmap,w/20,h/20,paint);
        paint.setTextSize(h/12);
        paint.setColor(Color.BLACK);
        canvas.drawText(adjustText(title,7*w/10,paint),w/20,7*h/10+h/24,paint);
        paint.setTextSize(h/15);
        canvas.drawText(adjustText(subtitle,7*w/10,paint),w/20,8*h/10,paint);
        paint.setTextSize(h/10);
        canvas.drawText(""+count,w/2-paint.measureText(""+count)/2,9*h/10,paint);
    }
    private String adjustText(String text,int w,Paint paint) {
        String msg = "";
        for(char ch:text.toCharArray()) {
            if(paint.measureText(msg+ch) < w) {
                msg +=  ch;
            }
            else {
                msg += "..";
            }
        }
        return msg;
    }
}

package com.anwesome.ui.shoppingcartui;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 01/06/17.
 */

public class ShoppingCardContainer {
    private Activity activity;
    private boolean isShown = false;
    private ScrollView scrollView;
    private ListLayout listLayout;
    public ShoppingCardContainer(Activity activity) {
        this.activity = activity;
        scrollView = new ScrollView(activity);
        listLayout = new ListLayout(activity);
        scrollView.addView(listLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void addItem(Bitmap bitmap,String title,String subTitle,float price) {
        listLayout.addItem(bitmap,title,subTitle,price);
    }
    public void show() {
        if(!isShown) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            activity.setContentView(scrollView);
            if(activity instanceof AppCompatActivity) {
                AppCompatActivity appCompatActivity = (AppCompatActivity)activity;
                ActionBar actionBar = appCompatActivity.getSupportActionBar();
                if(actionBar != null) {
                    actionBar.hide();
                }
            }
            else {
                android.app.ActionBar actionBar = activity.getActionBar();
                actionBar.hide();
            }
            isShown = true;
        }
    }
    private class ListLayout extends ViewGroup{
        private int w,h;
        public void onMeasure(int wspec,int hspec) {
            int hMax = h/30;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                measureChild(child,wspec,hspec);
                hMax += (h/30+child.getMeasuredHeight());
            }
            setMeasuredDimension(w,hMax);
        }
        public void onLayout(boolean reloaded,int a,int b,int wa,int ha) {
            int x = w/20,y = h/20;
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                child.layout(x,y,x+child.getMeasuredWidth(),y+child.getMeasuredHeight());
                y += (child.getMeasuredHeight()+h/20);
            }
        }
        public void addItem(Bitmap bitmap,String title,String subTitle,float price) {
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem(bitmap,title,subTitle,price);
            ShoppingCartCardView shoppingCartCardView = new ShoppingCartCardView(getContext(),shoppingCartItem);
            addView(shoppingCartCardView,new LayoutParams(9*w/10,9*w/10));
            requestLayout();
        }
        public ListLayout(Context context) {
            super(context);
            initDimension(context);
        }
        private void initDimension(Context context) {
            DisplayManager displayManager = (DisplayManager)context.getSystemService(Context.DISPLAY_SERVICE);
            Display display = displayManager.getDisplay(0);
            if(display != null) {
                Point size = new Point();
                display.getRealSize(size);
                w = size.x;
                h = size.y;
            }
        }
    }
}

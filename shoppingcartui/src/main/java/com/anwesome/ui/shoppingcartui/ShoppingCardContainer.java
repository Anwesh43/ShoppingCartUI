package com.anwesome.ui.shoppingcartui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 01/06/17.
 */

public class ShoppingCardContainer {
    private Activity activity;
    private boolean isShown = false;
    private ScrollView scrollView;
    public ShoppingCardContainer(Activity activity) {
        this.activity = activity;
        scrollView = new ScrollView(activity);
    }
    public void addItem(Bitmap bitmap,String title,String subTitle,float price) {
        
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
            isShown = true;
        }
    }
}

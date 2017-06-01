package com.anwesome.ui.shoppingcartui;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 01/06/17.
 */

public class CheckoutContainer extends ViewGroup{
    private LinearLayout linearLayout;
    private ScrollView scrollView;
    public CheckoutContainer(Context context) {
        super(context);
        linearLayout = new LinearLayout(context);
        scrollView = new ScrollView(context);
    }
    public void onMeasure(int wspec,int hspec) {

    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {

    }
}

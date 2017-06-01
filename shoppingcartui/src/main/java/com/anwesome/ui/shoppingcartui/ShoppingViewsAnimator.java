package com.anwesome.ui.shoppingcartui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;

/**
 * Created by anweshmishra on 01/06/17.
 */

public class ShoppingViewsAnimator extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
    private int mode = 0;
    private ValueAnimator startAnim = ValueAnimator.ofFloat(0,1),endAnim = ValueAnimator.ofFloat(1,0);
    private CartBar cartBar;
    private boolean isAnimating = false;

    public static ShoppingViewsAnimator getInstance() {
        return new ShoppingViewsAnimator();
    }
    public void setCartBar(CartBar cartBar) {
        this.cartBar = cartBar;
    }
    private ShoppingViewsAnimator() {

        startAnim.setDuration(200);
        endAnim.setDuration(200);
        startAnim.addUpdateListener(this);
        endAnim.addUpdateListener(this);
        startAnim.addListener(this);
        endAnim.addListener(this);
    }
    public void onAnimationEnd(Animator animator) {
        if(isAnimating) {
            if(mode == 0) {
                mode = 1;
                start();
            }
            else if(mode == 1) {
                mode = 2;
                start();
            }
            else if(mode == 2) {
                mode = 3;
            }
            isAnimating = false;
        }
    }
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float factor = (float)valueAnimator.getAnimatedValue();
        if(mode == 0 || mode == 1) {
            cartBar.update(factor);
        }
        else if(mode == 2 || mode == 3) {

        }
    }
    public void start() {
        if(!isAnimating) {
            if (mode == 0 || mode == 2) {
                startAnim.start();
            }
            if (mode == 1 || mode == 3) {
                endAnim.start();
            }
            isAnimating = true;
        }
    }
}

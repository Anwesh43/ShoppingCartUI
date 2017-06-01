package com.anwesome.ui.shoppingcartui;

import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 01/06/17.
 */

public class SelectedItemContainer {
    private SelectedItemContainer() {

    }
    public static SelectedItemContainer getInstance() {
        return new SelectedItemContainer();
    }
    private ConcurrentLinkedQueue<ShoppingCartItem> selectedItems = new ConcurrentLinkedQueue<>();
    private List<View> subscribers = new ArrayList<>();
    private ConcurrentLinkedQueue<ShoppingCartItem> getSelectedItems() {
        return selectedItems;
    }
    public void subscribe(View view) {
        subscribers.add(view);
    }
    public void addItem(ShoppingCartItem item) {
        boolean itemAlreadyAdded = false;
        for(ShoppingCartItem shoppingCartItem:selectedItems) {
            if(shoppingCartItem.equals(item)) {
                itemAlreadyAdded = true;
                break;
            }
        }
        if(itemAlreadyAdded) {
            if(item.getCount() == 0) {
                selectedItems.remove(item);
            }
        }
        else {
            if(item.getCount() == 1) {
                selectedItems.add(item);
            }
        }
        for(View subscriber:subscribers) {
            subscriber.postInvalidate();
        }
    }
}

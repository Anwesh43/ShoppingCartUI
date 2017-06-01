package com.anwesome.ui.shoppingcartdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.anwesome.ui.shoppingcartui.ShoppingCartCardView;
import com.anwesome.ui.shoppingcartui.ShoppingCartItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.stp);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(bitmap,"Item 1","This item is dope",2000);
        ShoppingCartCardView shoppingCartCardView = new ShoppingCartCardView(this,shoppingCartItem);
        addContentView(shoppingCartCardView,new ViewGroup.LayoutParams(1080,1080));

    }
}

package com.anwesome.ui.shoppingcartdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.anwesome.ui.shoppingcartui.ShoppingCardContainer;
import com.anwesome.ui.shoppingcartui.ShoppingCartCardView;
import com.anwesome.ui.shoppingcartui.ShoppingCartItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.stp);
        ShoppingCardContainer shoppingCardContainer = new ShoppingCardContainer(this);
        for(int i=0;i<8;i++) {
            shoppingCardContainer.addItem(bitmap,"Title"+(i+1),"A more concrete description "+(i+1),2000);
        }
        shoppingCardContainer.show();
    }
}

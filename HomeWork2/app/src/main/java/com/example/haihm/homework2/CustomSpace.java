package com.example.haihm.homework2;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by haihm on 9/29/2017.
 */

public class CustomSpace extends View {


    public CustomSpace(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(15);
        this.setMeasuredDimension(parentWidth, parentHeight);
        this.setLayoutParams(new LinearLayout.LayoutParams(parentWidth, parentHeight));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

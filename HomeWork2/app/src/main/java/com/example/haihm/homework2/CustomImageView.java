package com.example.haihm.homework2;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by haihm on 9/29/2017.
 */

public class CustomImageView extends android.support.v7.widget.AppCompatImageView {

    public CustomImageView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentWidth/2);
        this.setLayoutParams(new LinearLayout.LayoutParams(parentWidth, parentWidth/2));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



}

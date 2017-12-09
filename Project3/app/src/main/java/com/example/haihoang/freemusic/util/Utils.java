package com.example.haihoang.freemusic.util;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by haihm on 11/22/2017.
 */

public class Utils {
    public static void openFragment(FragmentManager fragmentManager, int layoutId, Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void rotateAnimation(ImageView imageView, boolean isRotate){
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f );
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(10000);

        if(isRotate){
            if(imageView.getAnimation() == null)
            imageView.startAnimation(rotateAnimation);
        }else {
            imageView.setAnimation(null);
        }

    }

    public static String convertTime(int time){
        int min = time/60000;
        int sec = (time - min*60000)/1000;

        return String.format("%02d:%02d", min, sec);
    }


}

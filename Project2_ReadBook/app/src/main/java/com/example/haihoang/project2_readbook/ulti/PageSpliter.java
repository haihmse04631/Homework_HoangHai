package com.example.haihoang.project2_readbook.ulti;

import android.content.Context;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haihoang on 10/18/17.
 */

public class PageSpliter {
    private static int height, width;
    private static String content;

    public PageSpliter(int height, int width, String content) {
        this.height = height;
        this.width = width;
        this.content = content;
    }

    public static StaticLayout createrStaticLayout(String content) {

        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(40);
        textPaint.setAntiAlias(true);

        StaticLayout staticLayout = new StaticLayout(content, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 10f, true);
        return staticLayout;
    }

    public static List<String> splitPage(StaticLayout staticLayout){
        List<String> listPage = new ArrayList<>();
        int startLineNumber = 0;

        while (startLineNumber < staticLayout.getLineCount()){
            int startLineCoordinator = staticLayout.getLineTop(startLineNumber);
            int endLineNumber = staticLayout.getLineForVertical(startLineCoordinator + height);
            int startIndex = staticLayout.getLineStart(startLineNumber);

            int endLineCoordinator = staticLayout.getLineBottom(endLineNumber);
            int rightEndLineNumber;
            if(endLineCoordinator > startLineCoordinator + height){
                rightEndLineNumber = endLineNumber - 1;
            } else {
              rightEndLineNumber = endLineNumber;
            }

            int endIndex = staticLayout.getLineEnd(rightEndLineNumber);

            String page = content.substring(startIndex, endIndex);
            startLineNumber = rightEndLineNumber + 1;

            listPage.add(page);
        }

        return listPage;
    }

    public static int getWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }

}

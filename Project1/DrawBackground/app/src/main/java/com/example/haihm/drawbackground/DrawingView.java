package com.example.haihm.drawbackground;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by haihm on 9/27/2017.
 */

public class DrawingView extends View {

    private Canvas canvas;
    private Paint paint, cameraPaint;
    private Path path;
    private Bitmap canvasBitmap;

    public DrawingView(Context context, Bitmap bitmap ) {
        super(context);
        path = new Path();
        this.canvasBitmap = bitmap;

        paint = new Paint();
        paint.setColor(DrawWhiteBackgroundActivity.currentColor);
        paint.setStrokeWidth(DrawWhiteBackgroundActivity.currentSize);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);

        cameraPaint = new Paint(Paint.DITHER_FLAG);

        Log.e("checkView", "DrawingView ");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if(canvasBitmap == null){
            canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            canvasBitmap.eraseColor(Color.WHITE);
        }
        canvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(canvasBitmap == null){
            canvas.drawBitmap(canvasBitmap, 0, 0, paint);
        }else{
            canvas.drawBitmap(canvasBitmap, new Matrix(), cameraPaint);
        }
       // canvas.drawBitmap(canvasBitmap, 0, 0, paint);
        canvas.drawPath(path, paint);
        Log.e("checkAction", "onDraw");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX() , touchY = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                Log.e("checkView", "down ");
                paint.setColor(DrawWhiteBackgroundActivity.currentColor);
                paint.setStrokeWidth(DrawWhiteBackgroundActivity.currentSize);
                path.moveTo(touchX, touchY);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                Log.e("checkView", "move ");
                path.lineTo(touchX, touchY);
                break;
            }
            case MotionEvent.ACTION_UP: {
                canvas.drawPath(path, paint);
                path.reset();
                paint.setXfermode(null);
                Log.e("checkView", "up ");
                break;
            }
        }
        invalidate();
        return true;
    }
}

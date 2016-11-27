package com.wedotech.simplechart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by YangYan on 2016/11/27.
 */

public class PathTestView extends View {

    private Path path;
    private RectF rectF;
    private Paint paint;
    private Path mPath = new Path();
    private float mPreX;
    private float mPreY;

    public PathTestView(Context context) {
        super(context);
        intPath();
    }

    public PathTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        intPath();
    }

    public PathTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intPath();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PathTestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        intPath();
    }

    private void intPath() {
        path = new Path();
        rectF = new RectF(100, 100, 300, 300);
        //path.addRect(rectF, Path.Direction.CW);
        paint = new Paint();
        paint.setColor(Color.parseColor("#ff0000"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
    }

    /*    @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    mPath.moveTo(event.getX(), event.getY());
                    return true;
                }
                case MotionEvent.ACTION_MOVE:
                    mPath.lineTo(event.getX(), event.getY());
                    invalidate();
                    break;
                default:
                    break;
            }
            return super.onTouchEvent(event);
        }*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mPreX + event.getX()) / 2;
                float endY = (mPreY + event.getY()) / 2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, paint);
    }

    public void reset() {
        mPath.reset();
        invalidate();
    }
  /*  @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
      *//*  paint.setColor(Color.parseColor("#ff0000"));
        RectF rectF = new RectF(100, 100, 400, 300);
        path.addRoundRect(rectF, 20, 20, Path.Direction.CW);
        canvas.drawPath(path, paint);*//*
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(20);

        Path path = new Path();
        path.moveTo(100,300);
        path.quadTo(200,200,300,300);
        path.quadTo(400,400,500,300);

        canvas.drawPath(path,paint);
    }*/
}

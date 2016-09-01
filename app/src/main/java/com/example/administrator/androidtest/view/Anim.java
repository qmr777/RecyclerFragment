package com.example.administrator.androidtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.androidtest.R;

/**
 * Created by qmr on 2016/8/30.
 */
public class Anim extends View {

    private int maxHeight;
    private int maxWidth;
    float percentage;

    boolean b,isRefreshing = false;

    private Paint mBlackPaint;
    private Paint mGreyPaint;

    public Anim(Context context) {
        super(context);
    }

    public Anim(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Anim(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Anim(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        maxHeight = MeasureSpec.getSize(heightMeasureSpec);
        maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawRect(0,0,(1-percentage)*maxWidth*0.5f,maxHeight, b?getBlackPaint():getGreyPaint());

        canvas.drawRect((1f-percentage)*maxWidth*0.5f,0,(1+percentage)*maxWidth,maxHeight, !b?getBlackPaint():getGreyPaint());

        canvas.drawRect((1+percentage)*maxWidth*.5f,0,maxWidth,maxHeight, b?getBlackPaint():getGreyPaint());

        resetHeight();
        if(isRefreshing)
            postInvalidateDelayed(15);
    }

    void setHeight(float height){
        percentage = height/maxHeight;
        invalidate();
    }

    void resetHeight(){
        percentage +=0.02;
        if(percentage>1f) {
            b = !b;
            percentage = percentage % 1;
        }
    }



    private Paint getBlackPaint(){
        if(mBlackPaint ==null) {
            mBlackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBlackPaint.setStyle(Paint.Style.FILL);
            mBlackPaint.setColor(Color.BLACK);
        }
        return mBlackPaint;
    }

    private Paint getGreyPaint(){
        if(mGreyPaint == null){
            mGreyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mGreyPaint.setStyle(Paint.Style.FILL);
            mGreyPaint.setColor(Color.GRAY);
        }
        return mGreyPaint;
    }


    private Paint getBlackPaint(int color){
        if(mBlackPaint ==null) {
            mBlackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBlackPaint.setStyle(Paint.Style.FILL);
            mBlackPaint.setColor(color);
        }
        return mBlackPaint;
    }
}

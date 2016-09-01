package com.example.administrator.androidtest.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

/**
 * Created by qmr on 2016/9/1.
 */
public class Abc extends ViewGroup implements NestedScrollingParent{

    NestedScrollingParentHelper nestedScrollingParentHelper = new NestedScrollingParentHelper(this);

    private View mTarget;

    private Anim animTop;

    private Anim animBottom;

    private int mTotalUnConsumed;//Spinner的下滑距离

    private static final int REFRESH_DIST = 200;//最短下拉刷新距离

    private static final int REFRESHING_DIST = 50;//停止播放动画高度

    private boolean isRefreshing = false;

    private boolean onNestedScrolling = false;

    public Abc(Context context) {
        super(context);
    }

    public Abc(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Abc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Abc(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(mTarget == null)
            ensureTarget();
        if(mTarget == null)
            return;

        mTarget.measure(MeasureSpec.makeMeasureSpec(
                getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), MeasureSpec.EXACTLY));

        animTop.measure(MeasureSpec.makeMeasureSpec(
                getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(REFRESH_DIST - getPaddingTop(),MeasureSpec.EXACTLY));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(mTarget == null)
            ensureTarget();
        if(mTarget == null)
            return;
        final int w = getMeasuredWidth();
        final int h = getMeasuredHeight();
        mTarget.layout(getPaddingLeft(),getPaddingTop(),w - getPaddingRight() , h - getPaddingBottom() );
        animTop.layout(getPaddingLeft(),-getPaddingTop(),w - getPaddingRight() , 0);
    }

    void ensureTarget(){
        if(mTarget == null){
            int childCount = getChildCount();
            for(int i = 0; i<childCount;i++){
                if(getChildAt(i).equals(animTop)) {
                    mTarget = getChildAt(i);
                    break;
                }
            }
        }
    }


    public boolean canChildScrollUp() {

        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mTarget instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mTarget;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mTarget, -1) || mTarget.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mTarget, -1);
        }
    }

    public boolean canChildScrollDown(){//mTarget能继续下拉
        if(Build.VERSION.SDK_INT < 14){
            if(mTarget instanceof AbsListView){//子布局是listView的情况
                AbsListView absListView = (AbsListView) mTarget;
                return absListView.getChildCount()>0 //至少有一个子布局
                        && (absListView.getLastVisiblePosition() < (absListView.getChildCount() - 1)
                        ||absListView.getChildAt(getChildCount()-1).getBottom() >
                        absListView.getBottom() - absListView.getPaddingBottom());//最后一个布局的底部在absListView之下
            } else {//sdk<14 子布局不是absListView
                //滚动过的高度小于mTarget的高，absListView好像一直是0?
                return ViewCompat.canScrollVertically(mTarget,0) || getScrollY() < mTarget.getHeight();
            }
        } else {//sdk版本大于14
            //direction<0判断是否能上划，else是否能下拉
            return ViewCompat.canScrollVertically(mTarget,0);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isRefreshing || !onNestedScrolling;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    //NestedScrollingParent

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        if(nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL) {
            onNestedScrolling = true;
            return true;
        }
        return false;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        super.onNestedScrollAccepted(child, target, axes);
        mTotalUnConsumed = 0;
        nestedScrollingParentHelper.onNestedScrollAccepted(child,target,axes);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(View child) {
        super.onStopNestedScroll(child);
        nestedScrollingParentHelper.onStopNestedScroll(child);
        onNestedScrolling = false;
    }
}

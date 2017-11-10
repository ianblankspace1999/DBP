/*
 * Copyright 2012 Roman Nurik
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package utils.customview;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import com.biz.pmti.dbp.R;


public class StepPagerStrip extends View {
    private static final String XML_NS = "http://schemas.android.com/apk/res/android";
    private static final int[] ATTRS = new int[]{
            android.R.attr.gravity
    };
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private int mOrientation = HORIZONTAL;

    private int mPageCount;
    private int mCurrentPage;

    private int mGravity = Gravity.LEFT | Gravity.TOP;
    private float mTabWidth;
    private float mTabHeight;
    private float mTabSpacing;

    private Paint mPrevTabPaint;
    private Paint mSelectedTabPaint;
    private Paint mSelectedLastTabPaint;
    private Paint mNextTabPaint;

    private RectF mTempRectF = new RectF();

    //private Scroller mScroller;

    private OnPageSelectedListener mOnPageSelectedListener;
    private int mReviewPage;
    @SuppressWarnings("unused")
    private int mDonePage;

    public StepPagerStrip(Context context) {
        this(context, null, 0);
    }

    public StepPagerStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepPagerStrip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        final TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);
        mGravity = a.getInteger(0, mGravity);
        a.recycle();

        //We default to horizontal, only change if a value is explicitly specified
        int orientation = attrs.getAttributeIntValue(XML_NS, "orientation", -1);
        if (orientation != -1) {
            setOrientation(orientation);
        }

        final Resources res = getResources();
        if (mOrientation == HORIZONTAL) {
            //mTabWidth = dpToPx(32);
            mTabWidth = dpToPx(128);
            mTabHeight = dpToPx(6);
            // mTabSpacing = dpToPx(1);
            mTabSpacing = dpToPx(15);
        } else {
            mTabWidth = dpToPx(2);
            mTabHeight = dpToPx(32);
            mTabSpacing = dpToPx(4);
        }

        mPrevTabPaint = new Paint();
        mPrevTabPaint.setColor(Color.parseColor("#4433b5e5"));

        mSelectedTabPaint = new Paint();
        //mSelectedTabPaint.setColor(Color.parseColor("#ff0099cc"));
        mSelectedTabPaint.setColor(ContextCompat.getColor(context, R.color.cl_blue));

        mSelectedLastTabPaint = new Paint();
        //mSelectedLastTabPaint.setColor(Color.parseColor("#ff669900"));
        mSelectedLastTabPaint.setColor(ContextCompat.getColor(context, R.color.cl_red));

        mNextTabPaint = new Paint();
        mNextTabPaint.setColor(Color.parseColor("#10000000"));
        //mNextTabPaint.setColor(ContextCompat.getColor(context, R.color.cl_red));

    }

    public int getOrientation() {
        return mOrientation;
    }

    public void setOrientation(int orientation) {
        switch (orientation) {
            case HORIZONTAL:
            case VERTICAL:
                break;

            default:
                throw new IllegalArgumentException("Only HORIZONTAL and VERTICAL are valid orientations.");
        }

        if (orientation == mOrientation) {
            return;
        }

        //Adjust scroll for new orientation
        mOrientation = orientation;
        final Resources res = getResources();
        if (orientation == HORIZONTAL) {
            //mTabWidth = dpToPx(32);
            mTabWidth = dpToPx(128);
            mTabHeight = dpToPx(6);
            //mTabSpacing = dpToPx(1);
            mTabSpacing = dpToPx(15);
        } else {
            mTabWidth = dpToPx(3);
            mTabHeight = dpToPx(32);
            mTabSpacing = dpToPx(4);
        }

        requestLayout();
    }


    public void setOnPageSelectedListener(OnPageSelectedListener onPageSelectedListener) {
        mOnPageSelectedListener = onPageSelectedListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mPageCount == 0) {
            return;
        }

        if (mOrientation == HORIZONTAL) {
            onDrawX(canvas);
        } else {
            onDrawY(canvas);
        }
    }

    protected void onDrawX(Canvas canvas) {
        float totalWidth = mPageCount * (mTabWidth + mTabSpacing) - mTabSpacing;
        float totalLeft;
        boolean fillHorizontal = false;

        switch (mGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
            case Gravity.CENTER_HORIZONTAL:
                totalLeft = (getWidth() - totalWidth) / 2;
                break;
            case Gravity.RIGHT:
                totalLeft = getWidth() - getPaddingRight() - totalWidth;
                break;
            case Gravity.FILL_HORIZONTAL:
                totalLeft = getPaddingLeft();
                fillHorizontal = true;
                break;
            default:
                totalLeft = getPaddingLeft();
        }

        switch (mGravity & Gravity.VERTICAL_GRAVITY_MASK) {
            case Gravity.CENTER_VERTICAL:
                mTempRectF.top = (int) (getHeight() - mTabHeight) / 2;
                break;
            case Gravity.BOTTOM:
                mTempRectF.top = getHeight() - getPaddingBottom() - mTabHeight;
                break;
            default:
                mTempRectF.top = getPaddingTop();
        }

        mTempRectF.bottom = mTempRectF.top + mTabHeight;

        float tabWidth = mTabWidth;
        if (fillHorizontal) {
            tabWidth = (getWidth() - getPaddingRight() - getPaddingLeft()
                    - (mPageCount - 1) * mTabSpacing) / mPageCount;
        }

        for (int i = 0; i < mPageCount; i++) {
            mTempRectF.left = totalLeft + (i * (tabWidth + mTabSpacing));
            mTempRectF.right = mTempRectF.left + tabWidth;
            Paint paint; /*= i < mCurrentPage
                    ? mPrevTabPaint
                    : (i > mCurrentPage
                            ? mNextTabPaint
                            : (i == mReviewPage
                                    ? mSelectedLastTabPaint
                                    : mSelectedTabPaint));*/
            if (i < mCurrentPage) {
                paint = mPrevTabPaint;
            } else {
                if (i > mCurrentPage) {
                    paint = mNextTabPaint;
                } else {
                    if (i == mReviewPage) {
                        paint = mSelectedLastTabPaint;
                    } else {
                        paint = mSelectedTabPaint;
                    }
                }
            }
            canvas.drawRect(mTempRectF, paint);
        }
    }

    protected void onDrawY(Canvas canvas) {
        if (mPageCount == 0) {
            return;
        }

        float totalHeight = mPageCount * (mTabHeight + mTabSpacing) - mTabSpacing;
        float totalTop;
        boolean fillHorizontal = false;

        switch (mGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
            case Gravity.CENTER_HORIZONTAL:
                totalTop = (getHeight() - totalHeight) / 2;
                break;
            case Gravity.RIGHT:
                totalTop = getHeight() - getPaddingBottom() - totalHeight;
                break;
            case Gravity.FILL_HORIZONTAL:
                totalTop = getPaddingTop();
                //fillHorizontal = true;

                fillHorizontal = true;
                break;
            default:
                totalTop = getPaddingTop();
        }

        switch (mGravity & Gravity.VERTICAL_GRAVITY_MASK) {
            case Gravity.CENTER_VERTICAL:
                mTempRectF.left = (int) (getWidth() - mTabWidth) / 2;
                break;
            case Gravity.BOTTOM:
                mTempRectF.left = getWidth() - getPaddingRight() - mTabWidth;
                break;
            default:
                mTempRectF.left = getPaddingLeft();
        }

        mTempRectF.right = mTempRectF.left + mTabWidth;

        float tabHeight = mTabHeight;
        if (fillHorizontal) {
            tabHeight = (getWidth() - getPaddingRight() - getPaddingLeft()
                    - (mPageCount - 1) * mTabSpacing) / mPageCount;
        }

        for (int i = 0; i < mPageCount; i++) {
            mTempRectF.top = totalTop + (i * (tabHeight + mTabSpacing));
            mTempRectF.bottom = mTempRectF.top + tabHeight;
            Paint paint = i < mCurrentPage
                    ? mPrevTabPaint
                    : (i > mCurrentPage
                    ? mNextTabPaint
                    : (i == mReviewPage
                    ? mSelectedLastTabPaint
                    : mSelectedTabPaint));
            canvas.drawRect(mTempRectF, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mOrientation == HORIZONTAL) {
            setMeasuredDimension(
                    View.resolveSize(
                            (int) (mPageCount * (mTabWidth + mTabSpacing) - mTabSpacing)
                                    + getPaddingLeft() + getPaddingRight(),
                            widthMeasureSpec),
                    View.resolveSize(
                            (int) mTabHeight
                                    + getPaddingTop() + getPaddingBottom(),
                            heightMeasureSpec));
        } else {
            setMeasuredDimension(
                    View.resolveSize(
                            (int) mTabWidth
                                    + getPaddingLeft() + getPaddingRight(),
                            widthMeasureSpec),
                    View.resolveSize(
                            (int) (mPageCount * (mTabHeight + mTabSpacing) - mTabSpacing)
                                    + getPaddingTop() + getPaddingBottom(),
                            heightMeasureSpec));
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        scrollCurrentPageIntoView();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mOnPageSelectedListener != null) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    int position;
                    if (mOrientation == HORIZONTAL) {
                        position = hitTestX(event.getX());
                    } else {
                        position = hitTestY(event.getY());
                    }

                    if (position >= 0) {
                        mOnPageSelectedListener.onPageStripSelected(position);
                    }
                    return true;
            }
        }
        return super.onTouchEvent(event);
    }

    private int hitTestX(float x) {
        if (mPageCount == 0) {
            return -1;
        }

        float totalWidth = mPageCount * (mTabWidth + mTabSpacing) - mTabSpacing;
        float totalLeft;
        boolean fillHorizontal = false;

        switch (mGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
            case Gravity.CENTER_HORIZONTAL:
                totalLeft = (getWidth() - totalWidth) / 2;
                break;
            case Gravity.RIGHT:
                totalLeft = getWidth() - getPaddingRight() - totalWidth;
                break;
            case Gravity.FILL_HORIZONTAL:
                totalLeft = getPaddingLeft();
                fillHorizontal = true;
                break;
            default:
                totalLeft = getPaddingLeft();
        }

        float tabWidth = mTabWidth;
        if (fillHorizontal) {
            tabWidth = (getWidth() - getPaddingRight() - getPaddingLeft()
                    - (mPageCount - 1) * mTabSpacing) / mPageCount;
        }

        float totalRight = totalLeft + (mPageCount * (tabWidth + mTabSpacing));
        if (x >= totalLeft && x <= totalRight && totalRight > totalLeft) {
            return (int) (((x - totalLeft) / (totalRight - totalLeft)) * mPageCount);
        } else {
            return -1;
        }
    }

    private int hitTestY(float y) {
        if (mPageCount == 0) {
            return -1;
        }

        float totalHeight = mPageCount * (mTabHeight + mTabSpacing) - mTabSpacing;
        float totalTop;
        boolean fillHorizontal = false;

        switch (mGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
            case Gravity.CENTER_HORIZONTAL:
                totalTop = (getHeight() - totalHeight) / 2;
                break;
            case Gravity.RIGHT:
                totalTop = getHeight() - getPaddingBottom() - totalHeight;
                break;
            case Gravity.FILL_HORIZONTAL:
                totalTop = getPaddingTop();
                //fillHorizontal = true;

                fillHorizontal = true;
                break;
            default:
                totalTop = getPaddingTop();
        }

        float tabWidth = mTabHeight;
        if (fillHorizontal) {
            tabWidth = (getWidth() - getPaddingRight() - getPaddingLeft()
                    - (mPageCount - 1) * mTabSpacing) / mPageCount;
        }

        float totalBottom = totalTop + (mPageCount * (tabWidth + mTabSpacing));
        if (y >= totalTop && y <= totalBottom && totalBottom > totalTop) {
            return (int) (((y - totalTop) / (totalBottom - totalTop)) * mPageCount);
        } else {
            return -1;
        }
    }

    public void setReviewPagePosition(int page) {
        mReviewPage = page;
    }

    public void setDonePagePosition(int page) {
        mDonePage = page;
    }

    public void setCurrentPage(int currentPage) {
        mCurrentPage = currentPage;
        invalidate();
        scrollCurrentPageIntoView();

        // TODO: Set content description appropriately
    }

    private void scrollCurrentPageIntoView() {
        // TODO: only works with left gravity for now
//
//        float widthToActive = getPaddingLeft() + (mCurrentPage + 1) * (mTabWidth + mTabSpacing)
//                - mTabSpacing;
//        int viewWidth = getWidth();
//
//        int startScrollX = getScrollX();
//        int destScrollX = (widthToActive > viewWidth) ? (int) (widthToActive - viewWidth) : 0;
//
//        if (mScroller == null) {
//            mScroller = new Scroller(getContext());
//        }
//
//        mScroller.abortAnimation();
//        mScroller.startScroll(startScrollX, 0, destScrollX - startScrollX, 0);
//        postInvalidate();
    }

    public void setPageCount(int count) {
        mPageCount = count;
        invalidate();

        // TODO: Set content description appropriately
    }

    public void setViewPager(ViewPager viewPager) {
        int maxval = viewPager.getAdapter().getCount();
        this.setPageCount(maxval);
        this.setOrientation(0);
        this.setCurrentPage(viewPager.getCurrentItem());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        this.setReviewPagePosition(maxval - 1);
    }

    public static interface OnPageSelectedListener {
        void onPageStripSelected(int position);
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

//
//    @Override
//    public void computeScroll() {
//        super.computeScroll();
//        if (mScroller.computeScrollOffset()) {
//            setScrollX(mScroller.getCurrX());
//        }
//    }
}

package com.plugin.gridlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by chaoyang on 2017/9/29.
 */

public class GridLayoutForJava extends ViewGroup {

    public int pendding;
    private int windowWidth;
    private int windowHeight;
    private List<Integer> mImagesRes;

    public GridLayoutForJava(Context context) {
        this(context, null);
    }

    public GridLayoutForJava(Context context, AttributeSet attrs) {
        super(context, attrs);
        //TypedArray typedArray = context.obtainStyledAttributes(R.styleable.gridlayout);
        //pendding = typedArray.getInteger(R.attr.GridPending, 1);
        //typedArray.recycle();
        initData();
    }

    private void initData() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowWidth = wm.getDefaultDisplay().getWidth();
        windowHeight = wm.getDefaultDisplay().getHeight();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = measureWidth(widthMeasureSpec);
        int measureHeight = measureHeight(heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);
    }


    private int measureWidth(int widthMeasureSpec) {
        int widthresult = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            //精确模式
            widthresult = widthSize;
        } else if (widthMeasureSpec == MeasureSpec.AT_MOST) {
            //最大模式
            widthresult = windowHeight;
        } else if (widthMode == MeasureSpec.UNSPECIFIED) {
            //wrapcontent
            widthresult = windowHeight;
        }
        return widthresult;
    }


    private int measureHeight(int heightMeasureSpec) {
        int heightResult = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            heightResult = heightSize;
        } else {
            int childCount = getChildCount();
            switch (childCount) {
                case 1:
                    heightResult = windowWidth;
                    break;
                case 2:
                    heightResult = windowWidth / 2;
                    break;
                case 3:
                    heightResult = windowWidth / 3;
                    break;
                case 4:
                    heightResult = windowWidth;
                    break;
                case 5:
                    heightResult = windowWidth / 2 + windowWidth / 3;
                    break;
                case 6:
                    heightResult = windowWidth / 3 + windowWidth / 3;
                    break;
                case 7:
                    heightResult = windowWidth / 3 + windowWidth / 2 + windowWidth / 2;
                    break;
                case 8:
                    heightResult = windowWidth / 3 + windowWidth / 3 + windowWidth / 2;
                    break;
                case 9:
                    heightResult = windowWidth / 3 + windowWidth / 3 + windowWidth / 3;
                    break;
                default:

                    break;
            }
        }

        return heightResult;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            switch (childCount) {
                case 1:
                    childView.layout(0 + pendding, 0 + pendding, windowWidth - pendding, windowWidth - pendding);
                    break;
                case 2:
                    if (i == 0) {
                        childView.layout(0 + pendding, 0 + pendding, windowWidth / 2 - pendding, windowWidth / 2 - pendding);
                    } else {
                        childView.layout(windowWidth / 2 + pendding, 0 + pendding, windowWidth - pendding, windowWidth - pendding);
                    }
                    break;
                case 3:
                    if (i == 0) {
                        childView.layout(0 + pendding, 0 + pendding, windowWidth / 3 - pendding, windowWidth / 3 - pendding);

                    } else if (i == 1) {
                        childView.layout(windowWidth / 3 + pendding, 0 + pendding, windowWidth / 3 * 2 - pendding, windowWidth / 3 * 2 - pendding);

                    } else {
                        childView.layout(windowWidth / 3 * 2 + pendding, 0 + pendding, windowWidth - pendding, windowWidth - pendding);
                    }

                    break;
                case 4:
                    if (i == 0) {
                        childView.layout(0 + pendding, 0 + pendding, windowWidth / 2 - pendding, windowWidth / 2 - pendding);
                    } else if (i == 1) {
                        childView.layout(windowWidth / 2 + pendding, 0 + pendding, windowWidth - pendding, windowWidth / 2 - pendding);
                    } else if (i == 2) {
                        childView.layout(0 + pendding, windowWidth / 2 + pendding, windowWidth / 2 - pendding, windowWidth - pendding);
                    } else {
                        childView.layout(windowWidth / 2 + pendding, windowWidth / 2 + pendding, windowWidth - pendding, windowWidth - pendding);
                    }
                    break;
                case 5:

                    if (i == 0) {
                        childView.layout(0 + pendding, 0 + pendding, windowWidth / 2 - pendding, windowWidth / 2 - pendding);
                    } else if (i == 1) {
                        childView.layout(windowWidth / 2 + pendding, 0 + pendding, windowWidth - pendding, windowWidth / 2 - pendding);
                    } else if (i == 2) {
                        childView.layout(0 + pendding, windowWidth / 2 + pendding, windowWidth / 3 - pendding, windowWidth / 2 + windowWidth / 3 - pendding);
                    } else if (i == 3) {
                        childView.layout(windowWidth / 3 + pendding, windowWidth / 2 + pendding, windowWidth / 3 * 2 - pendding, windowWidth / 3 + windowWidth / 2 - pendding);
                    } else {
                        childView.layout(windowWidth / 3 * 2 + pendding, windowWidth / 2 + pendding, windowWidth - pendding, windowWidth / 2 + windowWidth / 3 - pendding);
                    }

                    break;
                case 6:

                    if (i == 0) {
                        childView.layout(0 + pendding, 0 + pendding, windowWidth / 3 - pendding, windowWidth / 3 - pendding);
                    } else if (i == 1) {
                        childView.layout(windowWidth / 3 + pendding, 0 + pendding, windowWidth / 3 * 2 - pendding, windowWidth / 3 - pendding);
                    } else if (i == 2) {
                        childView.layout(windowWidth / 3 * 2 + pendding, 0 + pendding, windowWidth - pendding, windowWidth / 3 - pendding);
                    } else if (i == 3) {
                        childView.layout(0 + pendding, windowWidth / 3 + pendding, windowWidth / 3 - pendding, windowWidth / 3 * 2 - pendding);
                    } else if (i == 4) {
                        childView.layout(windowWidth / 3 + pendding, windowWidth / 3 + pendding, windowWidth / 3 * 2 - pendding, windowWidth / 3 * 2 - pendding);
                    } else {
                        childView.layout(windowWidth / 3 * 2 + pendding, windowWidth / 3 + pendding, windowWidth - pendding, windowWidth / 3 * 2 - pendding);
                    }

                    break;
                case 7:
                    if (i == 0) {
                        childView.layout(0 + pendding, 0 + pendding, windowWidth / 2 - pendding, windowWidth / 2 - pendding);
                    } else if (i == 1) {
                        childView.layout(windowWidth / 2 + pendding, 0 + pendding, windowWidth - pendding, windowWidth / 2 - pendding);
                    } else if (i == 2) {
                        childView.layout(0 + pendding, windowWidth / 2 + pendding, windowWidth / 3 - pendding, windowWidth / 2 + windowWidth / 3 - pendding);
                    } else if (i == 3) {
                        childView.layout(windowWidth / 3 + pendding, windowWidth / 2 + pendding, windowWidth / 3 + windowWidth / 2 - pendding, windowWidth / 2 + windowWidth / 3 - pendding);
                    } else if (i == 4) {
                        childView.layout(windowWidth / 3 * 2 + pendding, windowWidth / 2 + pendding, windowWidth - pendding, windowWidth / 2 + windowWidth / 3 - pendding);
                    } else if (i == 5) {
                        childView.layout(0 + pendding, windowWidth / 2 + windowWidth / 3 + pendding, windowWidth / 2 - pendding, windowWidth + windowWidth / 3 - pendding);
                    } else {
                        childView.layout(windowWidth / 2 + pendding, windowWidth / 2 + windowWidth / 3 + pendding, windowWidth - pendding, windowWidth + windowWidth / 3 - pendding);

                    }
                    break;
                case 8:
                    if (i == 0) {
                        childView.layout(0 + pendding, 0 + pendding, windowWidth / 3 - pendding, windowWidth / 3 - pendding);
                    } else if (i == 1) {
                        childView.layout(windowWidth / 3 + pendding, 0 + pendding, windowWidth / 3 * 2 - pendding, windowWidth / 3 - pendding);
                    } else if (i == 2) {
                        childView.layout(windowWidth / 3 * 2 + pendding, 0 + pendding, windowWidth - pendding, windowWidth / 3 - pendding);
                    } else if (i == 3) {
                        childView.layout(0 + pendding, windowWidth / 3 + pendding, windowWidth / 2 - pendding, windowWidth / 2 + windowWidth / 2 - pendding);
                    } else if (i == 4) {
                        childView.layout(windowWidth / 2 + pendding, windowWidth / 3 + pendding, windowWidth - pendding, windowWidth / 3 + windowWidth / 2 - pendding);
                    } else if (i == 5) {
                        childView.layout(0 + pendding, windowWidth / 3 + windowWidth / 2 + pendding, windowWidth / 3 - pendding, windowWidth / 3 * 2 + windowWidth / 2 - pendding);
                    } else if (i == 6) {
                        childView.layout(windowWidth / 3 + pendding, windowWidth / 3 + windowWidth / 2 + pendding, windowWidth / 3 * 2 - pendding, windowWidth / 3 * 2 + windowWidth / 2 - pendding);
                    } else {
                        childView.layout(windowWidth / 3 * 2 + pendding, windowWidth / 3 + windowWidth / 2 + pendding, windowWidth - pendding, windowWidth / 3 * 2 + windowWidth / 2 - pendding);
                    }
                    break;
                case 9:
                    if (i == 0) {
                        childView.layout(0 + pendding, 0 + pendding, windowWidth / 3 - pendding, windowWidth / 3 - pendding);
                    } else if (i == 1) {
                        childView.layout(windowWidth / 3 + pendding, 0 + pendding, windowWidth / 3 * 2 - pendding, windowWidth / 3 - pendding);
                    } else if (i == 2) {
                        childView.layout(windowWidth / 3 * 2 + pendding, 0 + pendding, windowWidth - pendding, windowWidth / 3 - pendding);
                    } else if (i == 3) {
                        childView.layout(0 + pendding, windowWidth / 3 + pendding, windowWidth / 3 - pendding, windowWidth / 3 * 2 - pendding);
                    } else if (i == 4) {
                        childView.layout(windowWidth / 3 + pendding, windowWidth / 3 + pendding, windowWidth / 3 * 2 - pendding, windowWidth / 3 * 2 - pendding);
                    } else if (i == 5) {
                        childView.layout(windowWidth / 3 * 2 + pendding, windowWidth / 3 + pendding, windowWidth - pendding, windowWidth / 3 * 2 - pendding);
                    } else if (i == 6) {
                        childView.layout(0 + pendding, windowWidth / 3 * 2 + pendding, windowWidth / 3 + pendding, windowWidth - pendding);
                    } else if (i == 7) {
                        childView.layout(windowWidth / 3 + pendding, windowWidth / 3 * 2 + pendding, windowWidth / 3 * 2 - pendding, windowWidth - pendding);
                    } else {
                        childView.layout(windowWidth / 3 * 2 + pendding, windowWidth / 3 * 2 + pendding, windowWidth - pendding, windowWidth - pendding);
                    }
                    break;
            }
        }
    }

    public void setGridLayout(List<Integer> imagesRes) {
        mImagesRes = imagesRes;
        removeAllViews();
        for (int i = 0; i < imagesRes.size(); i++) {
            ImageView iv = new ImageView(getContext());
            iv.setImageResource(imagesRes.get(i));
//            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(iv);
        }
        requestLayout();
    }

}

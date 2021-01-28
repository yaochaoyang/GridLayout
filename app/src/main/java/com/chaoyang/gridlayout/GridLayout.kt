package com.chaoyang.gridlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView

/**
 * @ClassName:      GridLayout
 * @Author:         chaoyang
 */
class GridLayout constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewGroup(context, attrs) {
    private lateinit var mImages: List<Int>
    private var windowHeight = 0
    private var windowWidth = 0
    var gridPending: Int = 0

    init {
//        val typedArray = context.obtainStyledAttributes(R.styleable.gridlayout)
//        gridPending = typedArray.getInteger(R.attr.GridPending, 0)
//        typedArray.recycle()
        initData()
    }

    private fun initData() {

        windowWidth = resources.displayMetrics.widthPixels
        windowHeight = resources.displayMetrics.heightPixels
        println("chaoyang - windowWidth = $windowWidth , windowHeight = $windowHeight")

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measuredWidth = measuredWidth(widthMeasureSpec)
        val measuredHeight = measuredHeight(heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    private fun measuredWidth(widthMeasureSpec: Int): Int {
        var widthResult = 0
        val mode = MeasureSpec.getMode(widthMeasureSpec)
        val size = MeasureSpec.getSize(widthMeasureSpec)
        when (mode) {
            MeasureSpec.EXACTLY -> {
                widthResult = size
            }
            MeasureSpec.AT_MOST -> {
                widthResult = windowWidth
            }
            MeasureSpec.UNSPECIFIED -> {
                widthResult = windowWidth
            }
        }
        return widthResult
    }

    private fun measuredHeight(heightMeasureSpec: Int): Int {
        var heightResult = 0
        val mode = MeasureSpec.getMode(heightMeasureSpec)
        val size = MeasureSpec.getSize(heightMeasureSpec)
        if (mode == MeasureSpec.EXACTLY) {
            heightResult = size
        } else {
            when (childCount) {
                1 -> {
                    heightResult = windowWidth
                }
                2 -> {
                    heightResult = windowWidth / 2
                }
                3 -> {
                    heightResult = windowWidth / 3
                }
                4 -> {
                    heightResult = windowWidth
                }
                5 -> {
                    heightResult = windowWidth / 2 + windowWidth / 3
                }
                6 -> {
                    heightResult = windowWidth / 3 * 2
                }
                7 -> {
                    heightResult = windowWidth / 3 + windowWidth
                }
                8 -> {
                    heightResult = windowWidth / 3 * 2 + windowWidth / 2
                }
                9 -> {
                    heightResult = windowWidth
                }
            }
        }
        return heightResult
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            requestChildLayout(i)
        }
    }

    private fun requestChildLayout(position: Int) {
        val childView = getChildAt(position)
        when (childCount) {
            1 -> {
                layoutOne(childView)
            }
            2 -> {
                layoutTow(childView, position)
            }
            3 -> {
                layoutThree(childView, position)
            }
            4 -> {
                layoutFour(childView, position)
            }
            5 -> {
                layoutFive(childView, position)
            }
            6 -> {
                layoutSize(childView, position)
            }
            7 -> {
                layoutSeven(childView, position)
            }
            8 -> {
                layoutEighty(childView, position)
            }
            9 -> {
                layoutNine(childView, position)
            }
        }
    }

    fun setGridLayout(images: List<Int>) {
        this.mImages = images
        removeAllViews()
        images.forEach {
            val imageView = ImageView(this.context)
            imageView.setImageResource(it)
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            addView(imageView)
        }
        requestLayout()
    }

    private fun layoutOne(childView: View) {
        childView.layout(
            0 + gridPending,
            0 + gridPending,
            windowWidth - gridPending,
            windowWidth - gridPending
        )
    }

    private fun layoutTow(childView: View, position: Int) {
        if (position == 0) {
            childView.layout(
                0 + gridPending,
                0 + gridPending,
                windowWidth / 2 - gridPending,
                windowWidth / 2 - gridPending
            )
        } else {
            childView.layout(
                windowWidth / 2 + gridPending,
                0 + gridPending,
                windowWidth - gridPending,
                windowWidth - gridPending
            )
        }
    }

    private fun layoutThree(childView: View, position: Int) {
        when (position) {
            0 -> {
                childView.layout(
                    0 + gridPending,
                    0 + gridPending,
                    windowWidth / 3 - gridPending,
                    windowWidth / 3 - gridPending
                )
            }
            1 -> {
                childView.layout(
                    windowWidth / 3 + gridPending,
                    0 + gridPending,
                    windowWidth / 3 * 2 - gridPending,
                    windowWidth / 3 * 2 - gridPending
                )
            }
            else -> {
                childView.layout(
                    windowWidth / 3 * 2 + gridPending,
                    0 + gridPending,
                    windowWidth - gridPending,
                    windowWidth - gridPending
                )
            }
        }
    }

    private fun layoutFour(childView: View, position: Int) {
        when (position) {
            0 -> {
                childView.layout(
                    0 + gridPending,
                    0 + gridPending,
                    windowWidth / 2 - gridPending,
                    windowWidth / 2 - gridPending
                )
            }
            1 -> {
                childView.layout(
                    windowWidth / 2 + gridPending,
                    0 + gridPending,
                    windowWidth - gridPending,
                    windowWidth / 2 - gridPending
                )
            }
            2 -> {
                childView.layout(
                    0 + gridPending,
                    windowWidth / 2 + gridPending,
                    windowWidth / 2 - gridPending,
                    windowWidth - gridPending
                )
            }
            else -> {
                childView.layout(
                    windowWidth / 2 + gridPending,
                    windowWidth / 2 + gridPending,
                    windowWidth - gridPending,
                    windowWidth - gridPending
                )
            }
        }
    }

    private fun layoutFive(childView: View, position: Int) {
        when (position) {
            0 -> {
                childView.layout(
                    0 + gridPending,
                    0 + gridPending,
                    windowWidth / 2 - gridPending,
                    windowWidth / 2 - gridPending
                )
            }
            1 -> {
                childView.layout(
                    windowWidth / 2 + gridPending,
                    0 + gridPending,
                    windowWidth - gridPending,
                    windowWidth / 2 - gridPending
                )
            }
            2 -> {
                childView.layout(
                    0 + gridPending,
                    windowWidth / 2 + gridPending,
                    windowWidth / 3 - gridPending,
                    windowWidth / 2 + windowWidth / 3 - gridPending
                )
            }
            3 -> {
                childView.layout(
                    windowWidth / 3 + gridPending,
                    windowWidth / 2 + gridPending,
                    windowWidth / 3 * 2 - gridPending,
                    windowWidth / 3 + windowWidth / 2 - gridPending
                )
            }
            else -> {
                childView.layout(
                    windowWidth / 3 * 2 + gridPending,
                    windowWidth / 2 + gridPending,
                    windowWidth - gridPending,
                    windowWidth / 2 + windowWidth / 3 - gridPending
                )
            }
        }
    }

    private fun layoutSize(childView: View, position: Int) {
        when (position) {
            0 -> {
                childView.layout(
                    0 + gridPending,
                    0 + gridPending,
                    windowWidth / 3 - gridPending,
                    windowWidth / 3 - gridPending
                )
            }
            1 -> {
                childView.layout(
                    windowWidth / 3 + gridPending,
                    0 + gridPending,
                    windowWidth / 3 * 2 - gridPending,
                    windowWidth / 3 - gridPending
                )
            }
            2 -> {
                childView.layout(
                    windowWidth / 3 * 2 + gridPending,
                    0 + gridPending,
                    windowWidth - gridPending,
                    windowWidth / 3 - gridPending
                )
            }
            3 -> {
                childView.layout(
                    0 + gridPending,
                    windowWidth / 3 + gridPending,
                    windowWidth / 3 - gridPending,
                    windowWidth / 3 * 2 - gridPending
                )
            }
            4 -> {
                childView.layout(
                    windowWidth / 3 + gridPending,
                    windowWidth / 3 + gridPending,
                    windowWidth / 3 * 2 - gridPending,
                    windowWidth / 3 * 2 - gridPending
                )
            }
            else -> {
                childView.layout(
                    windowWidth / 3 * 2 + gridPending,
                    windowWidth / 3 + gridPending,
                    windowWidth - gridPending,
                    windowWidth / 3 * 2 - gridPending
                )
            }
        }

    }

    private fun layoutSeven(childView: View, position: Int) {
        when (position) {
            0 -> {
                childView.layout(
                    0 + gridPending,
                    0 + gridPending,
                    windowWidth / 2 - gridPending,
                    windowWidth / 2 - gridPending
                )
            }
            1 -> {
                childView.layout(
                    windowWidth / 2 + gridPending,
                    0 + gridPending,
                    windowWidth - gridPending,
                    windowWidth / 2 - gridPending
                )
            }
            2 -> {
                childView.layout(
                    0 + gridPending,
                    windowWidth / 2 + gridPending,
                    windowWidth / 3 - gridPending,
                    windowWidth / 2 + windowWidth / 3 - gridPending
                )
            }
            3 -> {
                childView.layout(
                    windowWidth / 3 + gridPending,
                    windowWidth / 2 + gridPending,
                    windowWidth / 3 + windowWidth / 2 - gridPending,
                    windowWidth / 2 + windowWidth / 3 - gridPending
                )
            }
            4 -> {
                childView.layout(
                    windowWidth / 3 * 2 + gridPending,
                    windowWidth / 2 + gridPending,
                    windowWidth - gridPending,
                    windowWidth / 2 + windowWidth / 3 - gridPending
                )
            }
            5 -> {
                childView.layout(
                    0 + gridPending,
                    windowWidth / 2 + windowWidth / 3 + gridPending,
                    windowWidth / 2 - gridPending,
                    windowWidth + windowWidth / 3 - gridPending
                )
            }
            else -> {
                childView.layout(
                    windowWidth / 2 + gridPending,
                    windowWidth / 2 + windowWidth / 3 + gridPending,
                    windowWidth - gridPending,
                    windowWidth + windowWidth / 3 - gridPending
                )
            }
        }
    }

    private fun layoutEighty(childView: View, position: Int) {
        when (position) {
            0 -> {
                childView.layout(
                    0 + gridPending,
                    0 + gridPending,
                    windowWidth / 3 - gridPending,
                    windowWidth / 3 - gridPending
                )
            }
            1 -> {
                childView.layout(
                    windowWidth / 3 + gridPending,
                    0 + gridPending,
                    windowWidth / 3 * 2 - gridPending,
                    windowWidth / 3 - gridPending
                )
            }
            2 -> {
                childView.layout(
                    windowWidth / 3 * 2 + gridPending,
                    0 + gridPending,
                    windowWidth - gridPending,
                    windowWidth / 3 - gridPending
                )
            }
            3 -> {
                childView.layout(
                    0 + gridPending,
                    windowWidth / 3 + gridPending,
                    windowWidth / 2 - gridPending,
                    windowWidth / 2 + windowWidth / 2 - gridPending
                )
            }
            4 -> {
                childView.layout(
                    windowWidth / 2 + gridPending,
                    windowWidth / 3 + gridPending,
                    windowWidth - gridPending,
                    windowWidth / 3 + windowWidth / 2 - gridPending
                )
            }
            5 -> {
                childView.layout(
                    0 + gridPending,
                    windowWidth / 3 + windowWidth / 2 + gridPending,
                    windowWidth / 3 - gridPending,
                    windowWidth / 3 * 2 + windowWidth / 2 - gridPending
                )
            }
            6 -> {
                childView.layout(
                    windowWidth / 3 + gridPending,
                    windowWidth / 3 + windowWidth / 2 + gridPending,
                    windowWidth / 3 * 2 - gridPending,
                    windowWidth / 3 * 2 + windowWidth / 2 - gridPending
                )
            }
            else -> {
                childView.layout(
                    windowWidth / 3 * 2 + gridPending,
                    windowWidth / 3 + windowWidth / 2 + gridPending,
                    windowWidth - gridPending,
                    windowWidth / 3 * 2 + windowWidth / 2 - gridPending
                )
            }
        }
    }

    private fun layoutNine(childView: View, position: Int) {
        when (position) {
            0 -> {
                childView.layout(
                    0 + gridPending,
                    0 + gridPending,
                    windowWidth / 3 - gridPending,
                    windowWidth / 3 - gridPending
                )
            }
            1 -> {
                childView.layout(
                    windowWidth / 3 + gridPending,
                    0 + gridPending,
                    windowWidth / 3 * 2 - gridPending,
                    windowWidth / 3 - gridPending
                )
            }
            2 -> {
                childView.layout(
                    windowWidth / 3 * 2 + gridPending,
                    0 + gridPending,
                    windowWidth - gridPending,
                    windowWidth / 3 - gridPending
                )
            }
            3 -> {
                childView.layout(
                    0 + gridPending,
                    windowWidth / 3 + gridPending,
                    windowWidth / 3 - gridPending,
                    windowWidth / 3 * 2 - gridPending
                )
            }
            4 -> {
                childView.layout(
                    windowWidth / 3 + gridPending,
                    windowWidth / 3 + gridPending,
                    windowWidth / 3 * 2 - gridPending,
                    windowWidth / 3 * 2 - gridPending
                )
            }
            5 -> {
                childView.layout(
                    windowWidth / 3 * 2 + gridPending,
                    windowWidth / 3 + gridPending,
                    windowWidth - gridPending,
                    windowWidth / 3 * 2 - gridPending
                )
            }
            6 -> {
                childView.layout(
                    0 + gridPending,
                    windowWidth / 3 * 2 + gridPending,
                    windowWidth / 3 + gridPending,
                    windowWidth - gridPending
                )
            }
            7 -> {
                childView.layout(
                    windowWidth / 3 + gridPending,
                    windowWidth / 3 * 2 + gridPending,
                    windowWidth / 3 * 2 - gridPending,
                    windowWidth - gridPending
                )
            }
            else -> {
                childView.layout(
                    windowWidth / 3 * 2 + gridPending,
                    windowWidth / 3 * 2 + gridPending,
                    windowWidth - gridPending,
                    windowWidth - gridPending
                )
            }
        }
    }
}
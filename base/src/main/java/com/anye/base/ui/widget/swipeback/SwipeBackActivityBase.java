package com.anye.base.ui.widget.swipeback;


/**
 *interface for {@link com.anye.base.ui.activity.AbsSwipeBackActivity}
 */
public interface SwipeBackActivityBase {
    /**
     *the SwipeBackLayout associated with this activity.
     */
    public abstract SwipeBackLayout getSwipeBackLayout();


    public abstract void setSwipeBackEnable(boolean enable);

    /**
     * Scroll out contentView and finish the activity
     */
    public abstract void scrollToFinishActivity();

}

package com.anye.base.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anye.base.R;

/**
 * 自定义titlebar
 * Created by lwz on 2016/3/29.
 */
public class AnyeTitleBar extends RelativeLayout {

    protected RelativeLayout leftLayout;
    protected ImageView leftImage;
    protected TextView leftTv;
    protected RelativeLayout rightLayout;
    protected ImageView rightImage;
    protected TextView rightTv;
    protected TextView titleView;
    protected RelativeLayout titleLayout;

    public AnyeTitleBar(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public AnyeTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AnyeTitleBar(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.anye_widget_title_bar, this);
        leftLayout = (RelativeLayout) findViewById(R.id.left_layout);
        leftImage = (ImageView) findViewById(R.id.left_image);
        rightLayout = (RelativeLayout) findViewById(R.id.right_layout);
        rightImage = (ImageView) findViewById(R.id.right_image);
        rightTv = (TextView) findViewById(R.id.right_tv);
        titleView = (TextView) findViewById(R.id.title);
        titleLayout = (RelativeLayout) findViewById(R.id.root);

        parseStyle(context, attrs);
    }

    private void parseStyle(Context context, AttributeSet attrs){
        if(attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AnyeTitleBar);
            String title = ta.getString(R.styleable.AnyeTitleBar_titleBarTitle);
            titleView.setText(title);

            Drawable leftDrawable = ta.getDrawable(R.styleable.AnyeTitleBar_titleBarLeftImage);
            if (null != leftDrawable) {
                leftImage.setImageDrawable(leftDrawable);
            }
            Drawable rightDrawable = ta.getDrawable(R.styleable.AnyeTitleBar_titleBarRightImage);
            if (null != rightDrawable) {
                rightImage.setImageDrawable(rightDrawable);
            }

            Drawable background = ta.getDrawable(R.styleable.AnyeTitleBar_titleBarBackground);
            if(null != background) {
                titleLayout.setBackgroundDrawable(background);
            }

            ta.recycle();
        }
    }

    public void setLeftImageResource(int resId) {
        leftImage.setImageResource(resId);
    }

    public void setRightImageResource(int resId) {
        rightImage.setVisibility(View.VISIBLE);
        rightImage.setImageResource(resId);
    }

    public void setLeftLayoutClickListener(OnClickListener listener){
        leftLayout.setOnClickListener(listener);
    }

    public void setRightLayoutClickListener(OnClickListener listener){
        rightLayout.setOnClickListener(listener);
    }

    public void setLeftLayoutVisibility(int visibility){
        leftLayout.setVisibility(visibility);
    }

    public void setRightLayoutVisibility(int visibility){
        rightLayout.setVisibility(visibility);
    }

    public void setTitle(String title){
        titleView.setText(title);
    }

    public void setRightTitle(String rightTitle){
        if (!TextUtils.isEmpty(rightTitle)) {
            rightTv.setVisibility(View.VISIBLE);
            rightTv.setText(rightTitle);
        }
    }

    public void setLeftTitle(String leftTitle){
        if (!TextUtils.isEmpty(leftTitle)) {
            leftImage.setVisibility(View.GONE);
            leftTv.setVisibility(View.VISIBLE);
            leftTv.setText(leftTitle);
        }
    }

    public void setLeftPicTitle(String leftTitle,Drawable img){
        if (!TextUtils.isEmpty(leftTitle)) {
            leftImage.setVisibility(View.GONE);
            leftTv.setVisibility(View.VISIBLE);
            leftTv.setText(leftTitle);
            leftTv.setCompoundDrawables(img,null,null,null);
            leftTv.setCompoundDrawablePadding(10);
        }
    }

    public void setBackgroundColor(int color){
        titleLayout.setBackgroundColor(color);
    }

    public RelativeLayout getLeftLayout(){
        return leftLayout;
    }

    public RelativeLayout getRightLayout(){
        return rightLayout;
    }
}

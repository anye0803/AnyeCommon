package com.anye.base.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 各种结构的头像展示
 * Created by lwz on 2016/7/7.
 */

public class AvatarImageView  extends ImageView {
    /**
     * 用于图片背景
     */
    private Bitmap backgroundBitmap;
    /**
     * 显示的图片
     */
    private Bitmap mBitmap;
    /**
     * view 的宽度
     */
    private int viewWidth;
    /**
     * view 的高度
     */
    private int viewHeight;

    /**
     * 构造函数
     */
    public AvatarImageView(Context context) {
        this(context,null,0);
    }
    public AvatarImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public AvatarImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        /**
         * 获得控件的宽高，默认MeasureSpec.EXACTLY （ match_parent , accurate ）
         * 并且布局文件中应该设置 控件的宽高相等
         */
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBitmap!=null && backgroundBitmap!=null){
            /**
             * 对图片给进行缩放
             */
            int min = Math.min(viewWidth, viewHeight);
            backgroundBitmap = Bitmap.createScaledBitmap(backgroundBitmap, min, min, false);
            mBitmap = Bitmap.createScaledBitmap(mBitmap, min, min, false);
            /**
             * 把最后的bitmap画上去
             */
            canvas.drawBitmap(createImage(), 0, 0, null);
        }
    }
    private Bitmap createImage()
    {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap finalBmp = Bitmap.createBitmap(viewWidth,viewHeight, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(finalBmp);
        /**
         * 首先背景图片
         */
        canvas.drawBitmap(backgroundBitmap, 0, 0, paint);
        /**
         * 使用SRC_IN，取两层绘制交集，显示上层。
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制前景图片
         */
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        return finalBmp;
    }
    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
        setBitmaps();
    }

    /**
     * 会调用这个方法设置前景 src
     */
    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = getBitmapFromDrawable(drawable);
        setBitmaps();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setBitmaps();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setBitmaps();
    }
    private void setBitmaps(){
        if(null==getBackground()){
            throw new IllegalArgumentException(String.format("background is null."));
        }else{
            backgroundBitmap = getBitmapFromDrawable(getBackground());
            invalidate();
        }
    }
    /**
     * Drawable转Bitmap
     */
    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        super.setScaleType(ScaleType.CENTER_CROP);
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap bitmap;
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
}

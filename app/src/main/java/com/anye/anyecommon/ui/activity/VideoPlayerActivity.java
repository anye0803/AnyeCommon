package com.anye.anyecommon.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tedcoder.wkvideoplayer.dlna.engine.DLNAContainer;
import com.android.tedcoder.wkvideoplayer.dlna.service.DLNAService;
import com.android.tedcoder.wkvideoplayer.model.Video;
import com.android.tedcoder.wkvideoplayer.model.VideoUrl;
import com.android.tedcoder.wkvideoplayer.util.DensityUtil;
import com.android.tedcoder.wkvideoplayer.view.MediaController;
import com.android.tedcoder.wkvideoplayer.view.SuperVideoPlayer;
import com.anye.anyecommon.R;
import com.anye.base.ui.activity.BaseActivity;
import com.anye.base.ui.widget.imageloader.ImageLoader;
import com.anye.base.ui.widget.imageloader.ImageLoaderUtil;
import com.anye.base.util.ToastUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 视频播放详情页
 * Created by lwz on 2016/7/1.
 */

public class VideoPlayerActivity extends BaseActivity {

    @Bind(R.id.play_btn)
    ImageView mPlayBtnView;
    @Bind(R.id.video_player_item_1)
    SuperVideoPlayer mSuperVideoPlayer;
    @Bind(R.id.id_video_player)
    FrameLayout mVideoPlayer;
    @Bind(R.id.id_video_intro_title)
    TextView mTitle;
    @Bind(R.id.id_video_intro_description)
    TextView mDescription;
    @Bind(R.id.id_video_intro_create_time)
    TextView mCreateTime;
    @Bind(R.id.id_video_intro_update_time)
    TextView mUpdateTime;
    @Bind(R.id.play_bg)
    ImageView mVideoBg;
    @Bind(R.id.id_video_intro_layout)
    LinearLayout mINtroLayout;

    private Video video;
    @Override
    protected void initViewsAndEvents() {
        video = (Video) getIntent().getSerializableExtra("video");
        mTitle.setText(video.getVideoTitle());
        mDescription.setText(video.getVideoDesc());
        mCreateTime.setText(video.getCreatedAt());
        mUpdateTime.setText(video.getUpdatedAt());
        ImageLoader imageLoader = new ImageLoader.Builder().url(video.getVideoPhotoUrl()).imgView(mVideoBg).build();
        ImageLoaderUtil.getInstance().loadImage(mContext, imageLoader);

        mSuperVideoPlayer.setVideoPlayCallback(mVideoPlayCallback);
        startDLNAService();
    }

    @Override
    protected int getContentViewID() {
        return R.layout.act_video_player;
    }

    @OnClick(R.id.play_btn)
    void startPlay(){
        ToastUtils.getInstance().showToast("开始播放。。。");
        mPlayBtnView.setVisibility(View.GONE);
        mSuperVideoPlayer.setVisibility(View.VISIBLE);
        mSuperVideoPlayer.setAutoHideController(false);

        VideoUrl videoUrl1 = new VideoUrl();
        videoUrl1.setFormatName("720P");
        videoUrl1.setFormatUrl("http://192.168.224.2/testvideo.mp4");
//        videoUrl1.setFormatUrl(video.getVideoUrl());


        ArrayList<VideoUrl> arrayList1 = new ArrayList<>();
        arrayList1.add(videoUrl1);
        video.setmVideoName("测试视频一");
        video.setmVideoUrlList(arrayList1);
        ArrayList<Video> videoArrayList = new ArrayList<>();
        videoArrayList.add(video);
        mSuperVideoPlayer.loadMultipleVideo(videoArrayList,0,0,0);
    }

    private SuperVideoPlayer.VideoPlayCallbackImpl mVideoPlayCallback = new SuperVideoPlayer.VideoPlayCallbackImpl() {
        @Override
        public void onCloseVideo() {
            mSuperVideoPlayer.close();
            mPlayBtnView.setVisibility(View.VISIBLE);
            mSuperVideoPlayer.setVisibility(View.GONE);
            resetPageToPortrait();
        }

        @Override
        public void onSwitchPageType() {
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                mSuperVideoPlayer.setPageType(MediaController.PageType.EXPAND);
            }
        }

        @Override
        public void onPlayFinish() {

        }
    };

    /***
     * 恢复屏幕至竖屏
     */
    private void resetPageToPortrait() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
        }
//        mINtroLayout.setVisibility(View.VISIBLE);
    }

    /***
     * 旋转屏幕之后回调
     * @param newConfig newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (null == mSuperVideoPlayer) return;

//        mINtroLayout.setVisibility(View.GONE);
        /***
         * 根据屏幕方向重新设置播放器的大小
         */
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().invalidate();
            float height = DensityUtil.getWidthInPx(this);
            float width = DensityUtil.getHeightInPx(this);
            mSuperVideoPlayer.getLayoutParams().height = (int) width;
            mSuperVideoPlayer.getLayoutParams().width = (int) height;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            final WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            float width = DensityUtil.getWidthInPx(this);
            float height = DensityUtil.dip2px(this, 200.f);
            mSuperVideoPlayer.getLayoutParams().height = (int) height;
            mSuperVideoPlayer.getLayoutParams().width = (int) width;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopDLNAService();
    }

    private void startDLNAService() {
        // Clear the device container.
        DLNAContainer.getInstance().clear();
        Intent intent = new Intent(getApplicationContext(), DLNAService.class);
        startService(intent);
    }

    private void stopDLNAService() {
        Intent intent = new Intent(getApplicationContext(), DLNAService.class);
        stopService(intent);
    }
}

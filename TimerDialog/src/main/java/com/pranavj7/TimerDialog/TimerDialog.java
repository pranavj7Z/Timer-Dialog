package com.pranavj7.TimerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.pranavj7.TimerDialog.util.ProgressBar;
import com.pranavj7.TimerDialog.util.AnimationLoader;
import com.pranavj7.TimerDialog.util.DisplayUtil;

/**
 * name : pranav jayaraj
 * date : 18/4/16 8:55
 * e-mail : pranavjayaraj7@gmail.com
 */
public class TimerDialog extends Dialog implements View.OnClickListener {

    private ImageView mContentIv;

    private ProgressDialog p;

    private Bitmap mContentBitmap;

    private View mBtnGroupView, mDividerView, mBkgView, mDialogView;

    private TextView mTitleTv, mContentTv, mPositiveBtn, mNegativeBtn;

    private Drawable mDrawable;

    private AnimationSet mAnimIn, mAnimOut;


    private int mResId, mBackgroundColor, mTitleTextColor, mContentTextColor;

    private OnPositiveListener mPositiveListener;

    private OnNegativeListener mNegativeListener;

    private CharSequence mTitleText, mContentText, mPositiveText, mNegativeText;

    private MyCountDownTimer myCountDownTimer;

    private MyCountDownTimer0 myCountDownTimer0;

private ProgressBar cd;

    private boolean mIsShowAnim;

    public TimerDialog(Context context) {
        this(context, 0);
    }

    public TimerDialog(Context context, int theme) {
        super(context, R.style.color_dialog);
        init();
    }

    private void callDismiss() {
        super.dismiss();
    }

    private void init() {
        mAnimIn = AnimationLoader.getInAnimation(getContext());
        mAnimOut = AnimationLoader.getOutAnimation(getContext());
        initAnimListener();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitleText = title;
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getContext().getText(titleId));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = View.inflate(getContext(), R.layout.layout_colordialog, null);
        setContentView(contentView);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mBkgView = contentView.findViewById(R.id.llBkg);
        mTitleTv = (TextView) contentView.findViewById(R.id.tvTitle);
        mContentTv = (TextView) contentView.findViewById(R.id.tvContent);
        mContentIv = (ImageView) contentView.findViewById(R.id.ivContent);
        cd = (ProgressBar) findViewById(R.id.ivContent1);
        cd.setAnimateProgress(true);
        cd.setMaximum(100);
        cd.setProgress(0);
        //cd.setProgress(0);
// you can set max and current progress values individually
        //cd.setMaxProgress(10000);
        //cd.setCurrentProgress(0);
// or all at once
        mPositiveBtn = (TextView) contentView.findViewById(R.id.btnPositive);
        mNegativeBtn = (TextView) contentView.findViewById(R.id.btnNegative);

        mDividerView = contentView.findViewById(R.id.divider);
        mBtnGroupView = contentView.findViewById(R.id.llBtnGroup);

        mPositiveBtn.setOnClickListener(this);
        mNegativeBtn.setOnClickListener(this);

        mTitleTv.setText(mTitleText);
        mContentTv.setText(mContentText);
        mPositiveBtn.setText(mPositiveText);
        mNegativeBtn.setText(mNegativeText);

        if (null == mPositiveListener && null == mNegativeListener) {
            mBtnGroupView.setVisibility(View.GONE);
        } else if (null == mPositiveListener && null != mNegativeListener) {
            mPositiveBtn.setVisibility(View.GONE);
            mDividerView.setVisibility(View.GONE);
            mNegativeBtn.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.sel_def_gray));
        } else if (null != mPositiveListener && null == mNegativeListener ) {
            mNegativeBtn.setVisibility(View.GONE);
            mDividerView.setVisibility(View.GONE);
            mPositiveBtn.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.sel_def_gray));
        }

        if (null != mDrawable) {
            mContentIv.setBackgroundDrawable(mDrawable);
        }

        if (null != mContentBitmap) {
            mContentIv.setImageBitmap(mContentBitmap);
        }

        if (0 != mResId) {
            mContentIv.setBackgroundResource(mResId);
        }
        setTextColor();
        setBackgroundColor();
        setContentMode();
    }

    private int mDuration;


    @Override
    protected void onStart() {
        super.onStart();
        startWithAnimation(mIsShowAnim);
        //cd.setMaximum(mDuration/100);
        //cd.setProgress(100);
        cd.setMaximum(100);
        myCountDownTimer0= new MyCountDownTimer0(1000, 500);
        myCountDownTimer0.start();
    }


    @Override
    public void dismiss() {
        dismissWithAnimation(mIsShowAnim);
    }

    private void startWithAnimation(boolean showInAnimation) {
        if (showInAnimation) {
            mDialogView.startAnimation(mAnimIn);
        }
    }

    private void dismissWithAnimation(boolean showOutAnimation) {
        if (showOutAnimation) {
            mDialogView.startAnimation(mAnimOut);
        } else {
            super.dismiss();
        }
    }

    private void initAnimListener() {
        mAnimOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        callDismiss();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void setBackgroundColor() {

        if (0 == mBackgroundColor) {
            return;
        }

        int radius = DisplayUtil.dp2px(getContext(), 6);
        float[] outerRadii = new float[] { radius, radius, radius, radius, 0, 0, 0, 0 };
        RoundRectShape roundRectShape = new RoundRectShape(outerRadii, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
        shapeDrawable.getPaint().setColor(mBackgroundColor);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        mBkgView.setBackgroundDrawable(shapeDrawable);
    }

    private void setTextColor() {

        if (0 != mTitleTextColor) {
            mTitleTv.setTextColor(mTitleTextColor);
        }

        if (0 != mContentTextColor) {
            mContentTv.setTextColor(mContentTextColor);
        }

    }

    private void setContentMode() {
        boolean isImageMode = (null != mDrawable | null != mContentBitmap | 0 != mResId);
        boolean isTextMode = (!TextUtils.isEmpty(mContentText));

        if (isImageMode && isTextMode) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mContentTv.getLayoutParams();
            params.gravity = Gravity.BOTTOM;
            mContentTv.setLayoutParams(params);
            mContentTv.setBackgroundColor(Color.BLACK);
            mContentTv.getBackground().setAlpha(0x28);
            mContentTv.setVisibility(View.VISIBLE);
            mContentIv.setVisibility(View.VISIBLE);
            return;
        }

        if (isTextMode) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mContentTv.getLayoutParams();
            params.gravity = Gravity.NO_GRAVITY;
            mContentTv.setLayoutParams(params);
            mContentIv.setVisibility(View.GONE);
            mContentTv.setVisibility(View.VISIBLE);
            return;
        }

        if (isImageMode) {
            mContentTv.setVisibility(View.GONE);
            mContentIv.setVisibility(View.VISIBLE);
            return;
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (R.id.btnPositive == id) {
            mPositiveListener.onClick(this);
        } else if (R.id.btnNegative == id) {
            mNegativeListener.onClick(this);
        } else {
        }
    }

    public TimerDialog setAnimationEnable(boolean enable) {
        mIsShowAnim = enable;
        return this;
    }

    public TimerDialog setAnimationIn(AnimationSet animIn) {
        mAnimIn = animIn;
        return this;
    }

    public TimerDialog setAnimationOut(AnimationSet animOut) {
        mAnimOut = animOut;
        initAnimListener();
        return this;
    }

    public TimerDialog setDuration(int duration)
    {
        mDuration = duration;
    return this;
    }


    public TimerDialog setColor(int color) {
        mBackgroundColor = color;
        return this;
    }

    public TimerDialog setColor(String color) {
        try {
            setColor(Color.parseColor(color));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    public TimerDialog setTitleTextColor(int color) {
        mTitleTextColor = color;
        return this;
    }

    public TimerDialog setTitleTextColor(String color) {
        try {
            setTitleTextColor(Color.parseColor(color));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    public TimerDialog setContentTextColor(int color) {
        mContentTextColor = color;
        return this;
    }

    public TimerDialog setContentTextColor(String color) {
        try {
            setContentTextColor(Color.parseColor(color));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return this;
    }


    public TimerDialog setPositiveListener(CharSequence text, OnPositiveListener l) {
        mPositiveText = text;
        mPositiveListener = l;
        return this;
    }

    public TimerDialog setPositiveListener(int textId, OnPositiveListener l) {
        return setPositiveListener(getContext().getText(textId), l);
    }

    public TimerDialog setNegativeListener(CharSequence text, OnNegativeListener l) {
        mNegativeText = text;
        mNegativeListener = l;
        return this;
    }

    public TimerDialog setNegativeListener(int textId, OnNegativeListener l) {
        return setNegativeListener(getContext().getText(textId), l);
    }

    public TimerDialog setContentText(CharSequence text) {
        mContentText = text;
        return this;
    }

    public TimerDialog setContentText(int textId) {
        return setContentText(getContext().getText(textId));
    }

    public TimerDialog setContentImage(Drawable drawable) {
        mDrawable = drawable;
        return this;
    }

    public TimerDialog setContentImage(Bitmap bitmap) {
        mContentBitmap = bitmap;
        return this;
    }

    public TimerDialog setContentImage(int resId) {
        mResId = resId;
        return this;
    }

    public CharSequence getContentText() {
        return mContentText;
    }

    public CharSequence getTitleText() {
        return mTitleText;
    }

    public CharSequence getPositiveText() {
        return mPositiveText;
    }

    public CharSequence getNegativeText() {
        return mNegativeText;
    }

    public interface OnPositiveListener {
        void onClick(TimerDialog dialog);
    }

    public interface OnNegativeListener {
        void onClick(TimerDialog dialog);
    }
    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished/100);
            cd.setProgress(progress-10);
            cd.onVisibilityAggregated(true);
        }

        @Override
        public void onFinish()
        {
            dismissWithAnimation(mIsShowAnim);
        }
    }

    public class MyCountDownTimer0 extends CountDownTimer {

        public MyCountDownTimer0(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
           // int progress = (int) (millisUntilFinished/100);
            cd.setProgress(100);
        }

        @Override
        public void onFinish()
        {
            cd.onVisibilityAggregated(false);
            cd.setMaximum(mDuration/100);
            myCountDownTimer = new MyCountDownTimer(mDuration+100, 1000);
            myCountDownTimer.start();
        }
    }
}



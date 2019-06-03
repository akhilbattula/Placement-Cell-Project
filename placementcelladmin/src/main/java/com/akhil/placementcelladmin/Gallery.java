package com.akhil.placementcelladmin;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class Gallery extends AppCompatActivity {

    private GridView gv;
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;
    private int j = 0;

    private GestureDetector detector;
    private static final int SWIPE_MIN_DISTANCE = 80;
    private static final int SWIPE_THRESHOLD_VELOCITY = 150;

    private int thumb[] = {

            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p8,
            R.drawable.p9,
            R.drawable.p10,
            R.drawable.p11,
            R.drawable.p14,
            R.drawable.p15,
            R.drawable.p16,
            R.drawable.p17,
            R.drawable.p100,
            R.drawable.p101
    };
    private ImageView expandedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        detector = new GestureDetector(this, new SwipeGestureDetector());



        gv = (GridView) findViewById(R.id.grid_view);
        gv.setAdapter(new ImageAdapter(this));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos,
                                    long id) {
                j = pos;
                zoomImageFromThumb(v, thumb[pos]);
            }
        });

        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
    }

    class ImageAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public ImageAdapter(Gallery activity) {
            layoutInflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return thumb.length;
        }

        @Override
        public Object getItem(int pos) {
            return pos;
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @SuppressLint("InflateParams") @Override
        public View getView(int pos, View convertView, ViewGroup parent) {

            View listItem = convertView;
            int p = pos;

            if(listItem == null) {
                listItem = layoutInflater.inflate(R.layout.single_grid_item, null);

            }

            ImageView iv = (ImageView) listItem.findViewById(R.id.thumbnail);

            iv.setBackgroundResource(thumb[p]);

            return listItem;
        }
    }

    @SuppressLint("ClickableViewAccessibility") private void zoomImageFromThumb(final View thumbView, int imageResId) {

        if(mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        expandedImageView = (ImageView) findViewById(R.id.expanded_image);

        expandedImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(detector.onTouchEvent(event)){
                    return true;
                } else {
                    return false;
                }
            }
        });

        expandedImageView.setImageResource(imageResId);

        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);

        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;

        if ((float) finalBounds.width() / finalBounds.height() > (float) startBounds
                .width() / startBounds.height()) {

            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;

        } else {

            startScale = (float) startBounds.width() / finalBounds.width();
            @SuppressWarnings("unused")
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight = startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        AnimatorSet set = new AnimatorSet();

        set.play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f));

        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                AnimatorSet set = new AnimatorSet();

                set.play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                        .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScaleFinal));

                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }

    private class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {

            try {

                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){

                    if(thumb.length > j) {
                        j++;

                        if(j < thumb.length) {
                            expandedImageView.setImageResource(thumb[j]);
                            return true;
                        } else {
                            j = 0;
                            expandedImageView.setImageResource(thumb[j]);
                            return true;
                        }
                    }
                } else if(e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                    if(j > 0) {
                        j--;
                        expandedImageView.setImageResource(thumb[j]);
                        return true;
                    } else {
                        j = thumb.length - 1;
                        expandedImageView.setImageResource(thumb[j]);
                        return true;
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }
}

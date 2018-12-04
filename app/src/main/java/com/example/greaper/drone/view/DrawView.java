package com.example.greaper.drone.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class DrawView extends View {
    private Paint mPaint;
    private Bitmap mDrawBit; // bit map of draw
    private Paint mEraserPaint;

    private Canvas mPaintCanvas = null;

    private float last_x;
    private float last_y;
    private boolean eraser = false;

    private boolean canDraw;
    private int width, height;

    private DrawViewListener drawViewListener;

    public DrawView(Context context) {
        super(context);
        init(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        canDraw = true;

        mEraserPaint = new Paint();
        mEraserPaint.setAlpha(0);
        mEraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mEraserPaint.setAntiAlias(true);
        mEraserPaint.setDither(true);
        mEraserPaint.setStyle(Paint.Style.STROKE);
        mEraserPaint.setStrokeJoin(Paint.Join.ROUND);
        mEraserPaint.setStrokeCap(Paint.Cap.ROUND);
        mEraserPaint.setStrokeWidth(40);
    }

    private void generatorBit() {
        mDrawBit = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mPaintCanvas = new Canvas(mDrawBit);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDrawBit != null) {
            canvas.drawBitmap(mDrawBit, 0, 0, null);
        }
    }

    public void clear() {
        mPaintCanvas.drawRect(0,0, width, height, mEraserPaint);
        canDraw = true;
        this.postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean ret = super.onTouchEvent(event);
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ret = true;
                last_x = x;
                last_y = y;
                break;
            case MotionEvent.ACTION_MOVE:
                ret = true;
                if (canDraw) {
                    mPaintCanvas.drawLine(last_x, last_y, x, y, eraser ? mEraserPaint : mPaint);
                }
                last_x = x;
                last_y = y;
                this.postInvalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                ret = false;
                canDraw = false;
                drawViewListener.onActionUp();
                break;
        }
        return ret;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        generatorBit();
    }

    public Bitmap getPaintBit() {
        return mDrawBit;
    }

    public interface DrawViewListener {
        void onActionUp();
    }

    public void setDrawViewListener(DrawViewListener drawViewListener) {
        this.drawViewListener = drawViewListener;
    }
}

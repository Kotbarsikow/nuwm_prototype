package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;

/* loaded from: classes2.dex */
public class ZoomableRelativeLayout extends FrameLayout {
    private Bitmap ch;
    private long lastTapTime;
    private float lastd;
    private float lastdx1;
    private float lastdx2;
    private float lastdy1;
    private float lastdy2;
    ZoomViewListener listener;
    private final Matrix m;
    float maxZoom;
    private String miniMapCaption;
    private int miniMapCaptionColor;
    private float miniMapCaptionSize;
    private int miniMapColor;
    private int miniMapHeight;
    private final Paint p;
    private boolean pinching;
    private boolean scrolling;
    private boolean showMinimap;
    float smoothZoom;
    float smoothZoomX;
    float smoothZoomY;
    private float startd;
    private float touchLastX;
    private float touchLastY;
    private float touchStartX;
    private float touchStartY;
    float zoom;
    float zoomX;
    float zoomY;

    public interface ZoomViewListener {
        void onZoomEnded(float zoom, float zoomx, float zoomy);

        void onZoomStarted(float zoom, float zoomx, float zoomy);

        void onZooming(float zoom, float zoomx, float zoomy);
    }

    private float lerp(final float a, final float b, final float k) {
        return a + ((b - a) * k);
    }

    public ZoomableRelativeLayout(final Context context) {
        this(context, null, 0);
    }

    public ZoomableRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.zoom = 1.0f;
        this.maxZoom = 2.0f;
        this.smoothZoom = 1.0f;
        this.showMinimap = false;
        this.miniMapColor = ViewCompat.MEASURED_STATE_MASK;
        this.miniMapHeight = -1;
        this.miniMapCaptionSize = 10.0f;
        this.miniMapCaptionColor = -1;
        this.m = new Matrix();
        this.p = new Paint();
    }

    public float getZoom() {
        return this.zoom;
    }

    public float getMaxZoom() {
        return this.maxZoom;
    }

    public void setMaxZoom(final float maxZoom) {
        if (maxZoom < 1.0f) {
            return;
        }
        this.maxZoom = maxZoom;
    }

    public void setMiniMapEnabled(final boolean showMiniMap) {
        this.showMinimap = showMiniMap;
    }

    public boolean isMiniMapEnabled() {
        return this.showMinimap;
    }

    public void setMiniMapHeight(final int miniMapHeight) {
        if (miniMapHeight < 0) {
            return;
        }
        this.miniMapHeight = miniMapHeight;
    }

    public int getMiniMapHeight() {
        return this.miniMapHeight;
    }

    public void setMiniMapColor(final int color) {
        this.miniMapColor = color;
    }

    public int getMiniMapColor() {
        return this.miniMapColor;
    }

    public String getMiniMapCaption() {
        return this.miniMapCaption;
    }

    public void setMiniMapCaption(final String miniMapCaption) {
        this.miniMapCaption = miniMapCaption;
    }

    public float getMiniMapCaptionSize() {
        return this.miniMapCaptionSize;
    }

    public void setMiniMapCaptionSize(final float size) {
        this.miniMapCaptionSize = size;
    }

    public int getMiniMapCaptionColor() {
        return this.miniMapCaptionColor;
    }

    public void setMiniMapCaptionColor(final int color) {
        this.miniMapCaptionColor = color;
    }

    public void zoomTo(final float zoom, final float x, final float y) {
        float min = Math.min(zoom, this.maxZoom);
        this.zoom = min;
        this.zoomX = x;
        this.zoomY = y;
        smoothZoomTo(min, x, y);
    }

    public void smoothZoomTo(final float zoom, final float x, final float y) {
        float clamp = clamp(1.0f, zoom, this.maxZoom);
        this.smoothZoom = clamp;
        this.smoothZoomX = x;
        this.smoothZoomY = y;
        ZoomViewListener zoomViewListener = this.listener;
        if (zoomViewListener != null) {
            zoomViewListener.onZoomStarted(clamp, x, y);
        }
    }

    public ZoomViewListener getListener() {
        return this.listener;
    }

    public void setListner(final ZoomViewListener listener) {
        this.listener = listener;
    }

    public float getZoomFocusX() {
        return this.zoomX * this.zoom;
    }

    public float getZoomFocusY() {
        return this.zoomY * this.zoom;
    }

    public boolean TouchEvent(final MotionEvent ev) {
        if (ev.getPointerCount() == 1) {
            Log.e("MotionEvent", ev.getAction() + "  x:" + ev.getX() + " y:" + ev.getY());
            processSingleTouchEvent(ev);
        }
        ev.getPointerCount();
        getRootView().invalidate();
        invalidate();
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(final MotionEvent ev) {
        if (ev.getPointerCount() == 1) {
            Log.e("MotionEvent", ev.getAction() + "  x:" + ev.getX() + " y:" + ev.getY());
            processSingleTouchEvent(ev);
        }
        if (ev.getPointerCount() == 2) {
            processDoubleTouchEvent(ev);
        }
        getRootView().invalidate();
        invalidate();
        return true;
    }

    private void processSingleTouchEvent(final MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        boolean z = x >= 10.0f && x <= ((((float) this.miniMapHeight) * ((float) getWidth())) / ((float) getHeight())) + 10.0f && y >= 10.0f && y <= ((float) this.miniMapHeight) + 10.0f;
        if (this.showMinimap && this.smoothZoom > 1.0f && z) {
            processSingleTouchOnMinimap(ev);
        } else {
            processSingleTouchOutsideMinimap(ev);
        }
    }

    private void processSingleTouchOnMinimap(final MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        smoothZoomTo(this.smoothZoom, ((x - 10.0f) / ((this.miniMapHeight * getWidth()) / getHeight())) * getWidth(), ((y - 10.0f) / this.miniMapHeight) * getHeight());
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0035, code lost:
    
        if (r5 != 4) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void processSingleTouchOutsideMinimap(final android.view.MotionEvent r12) {
        /*
            Method dump skipped, instructions count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.m_myr.nuwm.nuwmschedule.ui.view.ZoomableRelativeLayout.processSingleTouchOutsideMinimap(android.view.MotionEvent):void");
    }

    private void processDoubleTouchEvent(final MotionEvent ev) {
        float x = ev.getX(0);
        float f = x - this.lastdx1;
        this.lastdx1 = x;
        float y = ev.getY(0);
        float f2 = y - this.lastdy1;
        this.lastdy1 = y;
        float x2 = ev.getX(1);
        float f3 = x2 - this.lastdx2;
        this.lastdx2 = x2;
        float y2 = ev.getY(1);
        float f4 = y2 - this.lastdy2;
        this.lastdy2 = y2;
        double d = x2 - x;
        double d2 = y2 - y;
        float hypot = (float) Math.hypot(d, d2);
        float f5 = hypot - this.lastd;
        this.lastd = hypot;
        float abs = Math.abs(hypot - this.startd);
        Math.atan2(d2, d);
        int action = ev.getAction();
        if (action == 0) {
            this.startd = hypot;
            this.pinching = false;
        } else if (action == 2) {
            if (this.pinching || abs > 30.0f) {
                this.pinching = true;
                float max = Math.max(1.0f, (this.zoom * hypot) / (hypot - f5));
                float f6 = this.zoomX;
                float f7 = this.zoom;
                smoothZoomTo(max, f6 - (((f + f3) * 0.5f) / f7), this.zoomY - (((f2 + f4) * 0.5f) / f7));
            }
        } else {
            this.pinching = false;
        }
        ev.setAction(3);
        super.dispatchTouchEvent(ev);
    }

    private float clamp(final float min, final float value, final float max) {
        return Math.max(min, Math.min(value, max));
    }

    private float bias(final float a, final float b, final float k) {
        float f = b - a;
        return Math.abs(f) >= k ? a + (k * Math.signum(f)) : b;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(final Canvas canvas) {
        ZoomViewListener zoomViewListener;
        this.zoom = lerp(bias(this.zoom, this.smoothZoom, 0.05f), this.smoothZoom, 0.2f);
        this.smoothZoomX = clamp((getWidth() * 0.5f) / this.smoothZoom, this.smoothZoomX, getWidth() - ((getWidth() * 0.5f) / this.smoothZoom));
        this.smoothZoomY = clamp((getHeight() * 0.5f) / this.smoothZoom, this.smoothZoomY, getHeight() - ((getHeight() * 0.5f) / this.smoothZoom));
        this.zoomX = lerp(bias(this.zoomX, this.smoothZoomX, 0.1f), this.smoothZoomX, 0.35f);
        float lerp = lerp(bias(this.zoomY, this.smoothZoomY, 0.1f), this.smoothZoomY, 0.35f);
        this.zoomY = lerp;
        float f = this.zoom;
        if (f != this.smoothZoom && (zoomViewListener = this.listener) != null) {
            zoomViewListener.onZooming(f, this.zoomX, lerp);
        }
        boolean z = Math.abs(this.zoom - this.smoothZoom) > 1.0E-7f || Math.abs(this.zoomX - this.smoothZoomX) > 1.0E-7f || Math.abs(this.zoomY - this.smoothZoomY) > 1.0E-7f;
        if (getChildCount() == 0) {
            return;
        }
        this.m.setTranslate(getWidth() * 0.5f, getHeight() * 0.5f);
        Matrix matrix = this.m;
        float f2 = this.zoom;
        matrix.preScale(f2, f2);
        this.m.preTranslate(-clamp((getWidth() * 0.5f) / this.zoom, this.zoomX, getWidth() - ((getWidth() * 0.5f) / this.zoom)), -clamp((getHeight() * 0.5f) / this.zoom, this.zoomY, getHeight() - ((getHeight() * 0.5f) / this.zoom)));
        View childAt = getChildAt(0);
        this.m.preTranslate(childAt.getLeft(), childAt.getTop());
        if (z && this.ch == null && isAnimationCacheEnabled()) {
            childAt.setDrawingCacheEnabled(true);
            this.ch = childAt.getDrawingCache();
        }
        if (z && isAnimationCacheEnabled() && this.ch != null) {
            this.p.setColor(-1);
            canvas.drawBitmap(this.ch, this.m, this.p);
        } else {
            this.ch = null;
            canvas.save();
            canvas.concat(this.m);
            childAt.draw(canvas);
            canvas.restore();
        }
        if (this.showMinimap) {
            if (this.miniMapHeight < 0) {
                this.miniMapHeight = getHeight() / 4;
            }
            canvas.translate(10.0f, 10.0f);
            this.p.setColor((this.miniMapColor & 16777215) | Integer.MIN_VALUE);
            float width = (this.miniMapHeight * getWidth()) / getHeight();
            float f3 = this.miniMapHeight;
            canvas.drawRect(0.0f, 0.0f, width, f3, this.p);
            String str = this.miniMapCaption;
            if (str != null && str.length() > 0) {
                this.p.setTextSize(this.miniMapCaptionSize);
                this.p.setColor(this.miniMapCaptionColor);
                this.p.setAntiAlias(true);
                canvas.drawText(this.miniMapCaption, 10.0f, this.miniMapCaptionSize + 10.0f, this.p);
                this.p.setAntiAlias(false);
            }
            this.p.setColor((this.miniMapColor & 16777215) | Integer.MIN_VALUE);
            float width2 = (this.zoomX * width) / getWidth();
            float height = (this.zoomY * f3) / getHeight();
            float f4 = width * 0.5f;
            float f5 = this.zoom;
            float f6 = f3 * 0.5f;
            canvas.drawRect(width2 - (f4 / f5), height - (f6 / f5), width2 + (f4 / f5), height + (f6 / f5), this.p);
            canvas.translate(-10.0f, -10.0f);
        }
        getRootView().invalidate();
        invalidate();
    }
}

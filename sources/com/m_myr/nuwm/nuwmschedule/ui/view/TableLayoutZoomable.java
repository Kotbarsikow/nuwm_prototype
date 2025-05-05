package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.TableLayout;

/* loaded from: classes2.dex */
public class TableLayoutZoomable extends TableLayout {
    final GestureDetector gestureDetector;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor;
    OnZoomListener zoomListener;

    public interface OnZoomListener {
        void onZoom(float scale);
    }

    static /* synthetic */ float access$018(TableLayoutZoomable tableLayoutZoomable, double d) {
        float f = (float) (tableLayoutZoomable.mScaleFactor + d);
        tableLayoutZoomable.mScaleFactor = f;
        return f;
    }

    public void setOnZoomListener(OnZoomListener zoomListener) {
        this.zoomListener = zoomListener;
    }

    public TableLayoutZoomable(Context context) {
        super(context);
        this.mScaleFactor = 1.0f;
        this.gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.TableLayoutZoomable.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent e) {
                TableLayoutZoomable.access$018(TableLayoutZoomable.this, 0.5d);
                if (TableLayoutZoomable.this.zoomListener == null) {
                    return true;
                }
                TableLayoutZoomable.this.zoomListener.onZoom(TableLayoutZoomable.this.mScaleFactor);
                return true;
            }
        });
    }

    public TableLayoutZoomable(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mScaleFactor = 1.0f;
        this.gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.TableLayoutZoomable.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent e) {
                TableLayoutZoomable.access$018(TableLayoutZoomable.this, 0.5d);
                if (TableLayoutZoomable.this.zoomListener == null) {
                    return true;
                }
                TableLayoutZoomable.this.zoomListener.onZoom(TableLayoutZoomable.this.mScaleFactor);
                return true;
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        return this.gestureDetector.onTouchEvent(event);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.gestureDetector.onTouchEvent(event);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        return this.gestureDetector.onTouchEvent(event);
    }
}

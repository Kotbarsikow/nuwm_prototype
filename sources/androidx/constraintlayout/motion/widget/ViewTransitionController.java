package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.motion.widget.ViewTransition;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.SharedValues;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ViewTransitionController {
    ArrayList<ViewTransition.Animate> animations;
    private final MotionLayout mMotionLayout;
    private HashSet<View> mRelatedViews;
    private ArrayList<ViewTransition> viewTransitions = new ArrayList<>();
    private String TAG = "ViewTransitionController";
    ArrayList<ViewTransition.Animate> removeList = new ArrayList<>();

    public ViewTransitionController(MotionLayout layout) {
        this.mMotionLayout = layout;
    }

    public void add(ViewTransition viewTransition) {
        this.viewTransitions.add(viewTransition);
        this.mRelatedViews = null;
        if (viewTransition.getStateTransition() == 4) {
            listenForSharedVariable(viewTransition, true);
        } else if (viewTransition.getStateTransition() == 5) {
            listenForSharedVariable(viewTransition, false);
        }
    }

    void remove(int id) {
        ViewTransition viewTransition;
        Iterator<ViewTransition> it = this.viewTransitions.iterator();
        while (true) {
            if (!it.hasNext()) {
                viewTransition = null;
                break;
            } else {
                viewTransition = it.next();
                if (viewTransition.getId() == id) {
                    break;
                }
            }
        }
        if (viewTransition != null) {
            this.mRelatedViews = null;
            this.viewTransitions.remove(viewTransition);
        }
    }

    private void viewTransition(ViewTransition vt, View... view) {
        int currentState = this.mMotionLayout.getCurrentState();
        if (vt.mViewTransitionMode == 2) {
            vt.applyTransition(this, this.mMotionLayout, currentState, null, view);
            return;
        }
        if (currentState == -1) {
            Log.w(this.TAG, "No support for ViewTransition within transition yet. Currently: " + this.mMotionLayout.toString());
            return;
        }
        ConstraintSet constraintSet = this.mMotionLayout.getConstraintSet(currentState);
        if (constraintSet == null) {
            return;
        }
        vt.applyTransition(this, this.mMotionLayout, currentState, constraintSet, view);
    }

    void enableViewTransition(int id, boolean enable) {
        Iterator<ViewTransition> it = this.viewTransitions.iterator();
        while (it.hasNext()) {
            ViewTransition next = it.next();
            if (next.getId() == id) {
                next.setEnabled(enable);
                return;
            }
        }
    }

    boolean isViewTransitionEnabled(int id) {
        Iterator<ViewTransition> it = this.viewTransitions.iterator();
        while (it.hasNext()) {
            ViewTransition next = it.next();
            if (next.getId() == id) {
                return next.isEnabled();
            }
        }
        return false;
    }

    void viewTransition(int id, View... views) {
        ArrayList arrayList = new ArrayList();
        Iterator<ViewTransition> it = this.viewTransitions.iterator();
        ViewTransition viewTransition = null;
        while (it.hasNext()) {
            ViewTransition next = it.next();
            if (next.getId() == id) {
                for (View view : views) {
                    if (next.checkTags(view)) {
                        arrayList.add(view);
                    }
                }
                if (!arrayList.isEmpty()) {
                    viewTransition(next, (View[]) arrayList.toArray(new View[0]));
                    arrayList.clear();
                }
                viewTransition = next;
            }
        }
        if (viewTransition == null) {
            Log.e(this.TAG, " Could not find ViewTransition");
        }
    }

    void touchEvent(MotionEvent event) {
        ViewTransition viewTransition;
        int currentState = this.mMotionLayout.getCurrentState();
        if (currentState == -1) {
            return;
        }
        if (this.mRelatedViews == null) {
            this.mRelatedViews = new HashSet<>();
            Iterator<ViewTransition> it = this.viewTransitions.iterator();
            while (it.hasNext()) {
                ViewTransition next = it.next();
                int childCount = this.mMotionLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = this.mMotionLayout.getChildAt(i);
                    if (next.matchesView(childAt)) {
                        childAt.getId();
                        this.mRelatedViews.add(childAt);
                    }
                }
            }
        }
        float x = event.getX();
        float y = event.getY();
        Rect rect = new Rect();
        int action = event.getAction();
        ArrayList<ViewTransition.Animate> arrayList = this.animations;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<ViewTransition.Animate> it2 = this.animations.iterator();
            while (it2.hasNext()) {
                it2.next().reactTo(action, x, y);
            }
        }
        if (action == 0 || action == 1) {
            ConstraintSet constraintSet = this.mMotionLayout.getConstraintSet(currentState);
            Iterator<ViewTransition> it3 = this.viewTransitions.iterator();
            while (it3.hasNext()) {
                ViewTransition next2 = it3.next();
                if (next2.supports(action)) {
                    Iterator<View> it4 = this.mRelatedViews.iterator();
                    while (it4.hasNext()) {
                        View next3 = it4.next();
                        if (next2.matchesView(next3)) {
                            next3.getHitRect(rect);
                            if (rect.contains((int) x, (int) y)) {
                                viewTransition = next2;
                                next2.applyTransition(this, this.mMotionLayout, currentState, constraintSet, next3);
                            } else {
                                viewTransition = next2;
                            }
                            next2 = viewTransition;
                        }
                    }
                }
            }
        }
    }

    void addAnimation(ViewTransition.Animate animation) {
        if (this.animations == null) {
            this.animations = new ArrayList<>();
        }
        this.animations.add(animation);
    }

    void removeAnimation(ViewTransition.Animate animation) {
        this.removeList.add(animation);
    }

    void animate() {
        ArrayList<ViewTransition.Animate> arrayList = this.animations;
        if (arrayList == null) {
            return;
        }
        Iterator<ViewTransition.Animate> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().mutate();
        }
        this.animations.removeAll(this.removeList);
        this.removeList.clear();
        if (this.animations.isEmpty()) {
            this.animations = null;
        }
    }

    void invalidate() {
        this.mMotionLayout.invalidate();
    }

    boolean applyViewTransition(int viewTransitionId, MotionController motionController) {
        Iterator<ViewTransition> it = this.viewTransitions.iterator();
        while (it.hasNext()) {
            ViewTransition next = it.next();
            if (next.getId() == viewTransitionId) {
                next.mKeyFrames.addAllFrames(motionController);
                return true;
            }
        }
        return false;
    }

    private void listenForSharedVariable(ViewTransition viewTransition, boolean isSet) {
        ConstraintLayout.getSharedValues().addListener(viewTransition.getSharedValueID(), new SharedValues.SharedValuesListener() { // from class: androidx.constraintlayout.motion.widget.ViewTransitionController.1
            final /* synthetic */ boolean val$isSet;
            final /* synthetic */ int val$listen_for_id;
            final /* synthetic */ int val$listen_for_value;
            final /* synthetic */ ViewTransition val$viewTransition;

            AnonymousClass1(ViewTransition viewTransition2, final int val$isSet, boolean isSet2, final int val$viewTransition) {
                val$listen_for_value = viewTransition2;
                val$isSet = val$isSet;
                val$listen_for_id = isSet2;
                val$viewTransition = val$viewTransition;
            }

            @Override // androidx.constraintlayout.widget.SharedValues.SharedValuesListener
            public void onNewValue(int id, int value, int oldValue) {
                int sharedValueCurrent = val$listen_for_value.getSharedValueCurrent();
                val$listen_for_value.setSharedValueCurrent(value);
                if (val$isSet != id || sharedValueCurrent == value) {
                    return;
                }
                if (val$listen_for_id) {
                    if (val$viewTransition == value) {
                        int childCount = ViewTransitionController.this.mMotionLayout.getChildCount();
                        for (int i = 0; i < childCount; i++) {
                            View childAt = ViewTransitionController.this.mMotionLayout.getChildAt(i);
                            if (val$listen_for_value.matchesView(childAt)) {
                                int currentState = ViewTransitionController.this.mMotionLayout.getCurrentState();
                                ConstraintSet constraintSet = ViewTransitionController.this.mMotionLayout.getConstraintSet(currentState);
                                ViewTransition viewTransition2 = val$listen_for_value;
                                ViewTransitionController viewTransitionController = ViewTransitionController.this;
                                viewTransition2.applyTransition(viewTransitionController, viewTransitionController.mMotionLayout, currentState, constraintSet, childAt);
                            }
                        }
                        return;
                    }
                    return;
                }
                if (val$viewTransition != value) {
                    int childCount2 = ViewTransitionController.this.mMotionLayout.getChildCount();
                    for (int i2 = 0; i2 < childCount2; i2++) {
                        View childAt2 = ViewTransitionController.this.mMotionLayout.getChildAt(i2);
                        if (val$listen_for_value.matchesView(childAt2)) {
                            int currentState2 = ViewTransitionController.this.mMotionLayout.getCurrentState();
                            ConstraintSet constraintSet2 = ViewTransitionController.this.mMotionLayout.getConstraintSet(currentState2);
                            ViewTransition viewTransition3 = val$listen_for_value;
                            ViewTransitionController viewTransitionController2 = ViewTransitionController.this;
                            viewTransition3.applyTransition(viewTransitionController2, viewTransitionController2.mMotionLayout, currentState2, constraintSet2, childAt2);
                        }
                    }
                }
            }
        });
    }

    /* renamed from: androidx.constraintlayout.motion.widget.ViewTransitionController$1 */
    class AnonymousClass1 implements SharedValues.SharedValuesListener {
        final /* synthetic */ boolean val$isSet;
        final /* synthetic */ int val$listen_for_id;
        final /* synthetic */ int val$listen_for_value;
        final /* synthetic */ ViewTransition val$viewTransition;

        AnonymousClass1(ViewTransition viewTransition2, final int val$isSet, boolean isSet2, final int val$viewTransition) {
            val$listen_for_value = viewTransition2;
            val$isSet = val$isSet;
            val$listen_for_id = isSet2;
            val$viewTransition = val$viewTransition;
        }

        @Override // androidx.constraintlayout.widget.SharedValues.SharedValuesListener
        public void onNewValue(int id, int value, int oldValue) {
            int sharedValueCurrent = val$listen_for_value.getSharedValueCurrent();
            val$listen_for_value.setSharedValueCurrent(value);
            if (val$isSet != id || sharedValueCurrent == value) {
                return;
            }
            if (val$listen_for_id) {
                if (val$viewTransition == value) {
                    int childCount = ViewTransitionController.this.mMotionLayout.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childAt = ViewTransitionController.this.mMotionLayout.getChildAt(i);
                        if (val$listen_for_value.matchesView(childAt)) {
                            int currentState = ViewTransitionController.this.mMotionLayout.getCurrentState();
                            ConstraintSet constraintSet = ViewTransitionController.this.mMotionLayout.getConstraintSet(currentState);
                            ViewTransition viewTransition2 = val$listen_for_value;
                            ViewTransitionController viewTransitionController = ViewTransitionController.this;
                            viewTransition2.applyTransition(viewTransitionController, viewTransitionController.mMotionLayout, currentState, constraintSet, childAt);
                        }
                    }
                    return;
                }
                return;
            }
            if (val$viewTransition != value) {
                int childCount2 = ViewTransitionController.this.mMotionLayout.getChildCount();
                for (int i2 = 0; i2 < childCount2; i2++) {
                    View childAt2 = ViewTransitionController.this.mMotionLayout.getChildAt(i2);
                    if (val$listen_for_value.matchesView(childAt2)) {
                        int currentState2 = ViewTransitionController.this.mMotionLayout.getCurrentState();
                        ConstraintSet constraintSet2 = ViewTransitionController.this.mMotionLayout.getConstraintSet(currentState2);
                        ViewTransition viewTransition3 = val$listen_for_value;
                        ViewTransitionController viewTransitionController2 = ViewTransitionController.this;
                        viewTransition3.applyTransition(viewTransitionController2, viewTransitionController2.mMotionLayout, currentState2, constraintSet2, childAt2);
                    }
                }
            }
        }
    }
}

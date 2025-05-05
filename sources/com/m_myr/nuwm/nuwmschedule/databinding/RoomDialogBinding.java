package com.m_myr.nuwm.nuwmschedule.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public final class RoomDialogBinding implements ViewBinding {
    public final CardView contentInside;
    public final Button onClickDetails;
    public final LinearLayout realContent;
    private final FrameLayout rootView;
    public final SwitchCompat switch3D;
    public final TextView textRoom;
    public final WebView webview;

    private RoomDialogBinding(FrameLayout rootView, CardView contentInside, Button onClickDetails, LinearLayout realContent, SwitchCompat switch3D, TextView textRoom, WebView webview) {
        this.rootView = rootView;
        this.contentInside = contentInside;
        this.onClickDetails = onClickDetails;
        this.realContent = realContent;
        this.switch3D = switch3D;
        this.textRoom = textRoom;
        this.webview = webview;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static RoomDialogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static RoomDialogBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View inflate = inflater.inflate(R.layout.room_dialog, parent, false);
        if (attachToParent) {
            parent.addView(inflate);
        }
        return bind(inflate);
    }

    public static RoomDialogBinding bind(View rootView) {
        int i = R.id.contentInside;
        CardView cardView = (CardView) ViewBindings.findChildViewById(rootView, R.id.contentInside);
        if (cardView != null) {
            i = R.id.onClickDetails;
            Button button = (Button) ViewBindings.findChildViewById(rootView, R.id.onClickDetails);
            if (button != null) {
                i = R.id.realContent;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(rootView, R.id.realContent);
                if (linearLayout != null) {
                    i = R.id.switch3D;
                    SwitchCompat switchCompat = (SwitchCompat) ViewBindings.findChildViewById(rootView, R.id.switch3D);
                    if (switchCompat != null) {
                        i = R.id.textRoom;
                        TextView textView = (TextView) ViewBindings.findChildViewById(rootView, R.id.textRoom);
                        if (textView != null) {
                            i = R.id.webview;
                            WebView webView = (WebView) ViewBindings.findChildViewById(rootView, R.id.webview);
                            if (webView != null) {
                                return new RoomDialogBinding((FrameLayout) rootView, cardView, button, linearLayout, switchCompat, textView, webView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView.getResources().getResourceName(i)));
    }
}

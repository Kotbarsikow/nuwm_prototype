package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;

/* loaded from: classes2.dex */
public class HintHelpView extends MaterialCardView {
    private TextView mHint;
    private MaterialButton mHintOk;
    private ImageView mImage;

    public HintHelpView(Context context) {
        this(context, null);
    }

    public HintHelpView(Context context, AttributeSet attrs) {
        this(context, attrs, 2131952649);
    }

    public HintHelpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(dpToPx(18.0f), dpToPx(6.0f), dpToPx(18.0f), dpToPx(16.0f));
        setLayoutParams(layoutParams);
        setRadius(dpToPx(10.0f));
        setCardElevation(dpToPx(3.5f));
    }

    public void attachHint(final String hintId, String stringText, int iconRes) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.hint_info, (ViewGroup) this, true);
        this.mImage = (ImageView) inflate.findViewById(R.id.image);
        this.mHint = (TextView) inflate.findViewById(R.id.hint);
        this.mHintOk = (MaterialButton) inflate.findViewById(R.id.hint_ok);
        this.mImage.setImageResource(iconRes);
        this.mHint.setText(stringText);
        this.mHintOk.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.HintHelpView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                HintHelpView.this.setVisibility(8);
                AppDataManager.getInstance().setHideHint(hintId);
            }
        });
    }

    public void attachHint(String hintId, int stringTextRes, int iconRes) {
        attachHint(hintId, getResources().getString(stringTextRes), iconRes);
    }

    public int dpToPx(float dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}

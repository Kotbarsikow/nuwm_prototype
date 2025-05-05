package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import com.m_myr.nuwm.nuwmschedule.R;

/* loaded from: classes2.dex */
public class StyleCallback implements ActionMode.Callback {
    EditText editText;

    @Override // android.view.ActionMode.Callback
    public void onDestroyActionMode(ActionMode mode) {
    }

    @Override // android.view.ActionMode.Callback
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    public StyleCallback(EditText editText) {
        this.editText = editText;
    }

    public StyleCallback(final EditText editText, final View icon) {
        this.editText = editText;
        editText.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.StyleCallback.1
            @Override // android.view.View.AccessibilityDelegate
            public void sendAccessibilityEvent(View host, int eventType) {
                super.sendAccessibilityEvent(host, eventType);
                if (eventType == 8192) {
                    if (editText.getSelectionEnd() - editText.getSelectionStart() > 0) {
                        icon.setAlpha(1.0f);
                        editText.setClickable(true);
                    } else {
                        icon.setAlpha(0.46f);
                        editText.setClickable(false);
                    }
                }
            }
        });
        icon.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.StyleCallback$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StyleCallback.this.m219lambda$new$1$comm_myrnuwmnuwmscheduleuiviewStyleCallback(icon, view);
            }
        });
    }

    /* renamed from: lambda$new$1$com-m_myr-nuwm-nuwmschedule-ui-view-StyleCallback, reason: not valid java name */
    /* synthetic */ void m219lambda$new$1$comm_myrnuwmnuwmscheduleuiviewStyleCallback(View view, View view2) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.style, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.StyleCallback$$ExternalSyntheticLambda1
            @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return StyleCallback.this.m218lambda$new$0$comm_myrnuwmnuwmscheduleuiviewStyleCallback(menuItem);
            }
        });
        popupMenu.show();
    }

    /* renamed from: lambda$new$0$com-m_myr-nuwm-nuwmschedule-ui-view-StyleCallback, reason: not valid java name */
    /* synthetic */ boolean m218lambda$new$0$comm_myrnuwmnuwmscheduleuiviewStyleCallback(MenuItem menuItem) {
        return onActionItemClicked(null, menuItem);
    }

    @Override // android.view.ActionMode.Callback
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.style, menu);
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        final int selectionStart = this.editText.getSelectionStart();
        final int selectionEnd = this.editText.getSelectionEnd();
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.editText.getText());
        int i = 0;
        switch (item.getItemId()) {
            case R.id.bold /* 2131361972 */:
                spannableStringBuilder.setSpan(new StyleSpan(1), selectionStart, selectionEnd, 1);
                this.editText.setText(spannableStringBuilder);
                return true;
            case R.id.clear /* 2131362042 */:
                EditText editText = this.editText;
                editText.setText(editText.getText().toString());
                return true;
            case R.id.color_background /* 2131362058 */:
                AlertDialog.Builder builder = new AlertDialog.Builder(this.editText.getContext());
                builder.setTitle("Колір фону");
                ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.editText.getContext()).inflate(R.layout.color_selector, (ViewGroup) null, false);
                builder.setView(viewGroup);
                final AlertDialog show = builder.show();
                View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.StyleCallback.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        spannableStringBuilder.setSpan(new BackgroundColorSpan(((CircleColorView) v).getColor()), selectionStart, selectionEnd, 1);
                        StyleCallback.this.editText.setText(spannableStringBuilder);
                        show.dismiss();
                    }
                };
                while (i < viewGroup.getChildCount()) {
                    viewGroup.getChildAt(i).setOnClickListener(onClickListener);
                    i++;
                }
                return true;
            case R.id.color_foreground /* 2131362059 */:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this.editText.getContext());
                builder2.setTitle("Колір тексту");
                ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(this.editText.getContext()).inflate(R.layout.color_selector, (ViewGroup) null, false);
                builder2.setView(viewGroup2);
                final AlertDialog show2 = builder2.show();
                View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.StyleCallback.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(((CircleColorView) v).getColor()), selectionStart, selectionEnd, 1);
                        StyleCallback.this.editText.setText(spannableStringBuilder);
                        show2.dismiss();
                    }
                };
                while (i < viewGroup2.getChildCount()) {
                    viewGroup2.getChildAt(i).setOnClickListener(onClickListener2);
                    i++;
                }
                return true;
            case R.id.italic /* 2131362297 */:
                spannableStringBuilder.setSpan(new StyleSpan(2), selectionStart, selectionEnd, 1);
                this.editText.setText(spannableStringBuilder);
                return true;
            case R.id.large /* 2131362309 */:
                spannableStringBuilder.setSpan(new RelativeSizeSpan(1.43f), selectionStart, selectionEnd, 1);
                this.editText.setText(spannableStringBuilder);
                return true;
            case R.id.small /* 2131362635 */:
                spannableStringBuilder.setSpan(new RelativeSizeSpan(0.63f), selectionStart, selectionEnd, 1);
                this.editText.setText(spannableStringBuilder);
                return true;
            case R.id.underline /* 2131362842 */:
                spannableStringBuilder.setSpan(new UnderlineSpan(), selectionStart, selectionEnd, 1);
                this.editText.setText(spannableStringBuilder);
                return true;
            case R.id.url /* 2131362848 */:
                return true;
            default:
                return false;
        }
    }
}

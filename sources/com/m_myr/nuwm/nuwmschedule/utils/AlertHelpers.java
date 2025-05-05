package com.m_myr.nuwm.nuwmschedule.utils;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import com.m_myr.nuwm.nuwmschedule.utils.AlertHelpers;

/* loaded from: classes2.dex */
public class AlertHelpers {

    public interface DialogEditInterface {
        void onClick(EditText editText);
    }

    public static AlertDialog createInfoAlert(Context context, String message) {
        return createInfoAlert(context, "Інформація", message);
    }

    public static AlertDialog createInfoAlert(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null);
        return builder.create();
    }

    public static AlertDialog createEditAlert(Context context, String title, String message, final DialogEditInterface listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LinearLayout linearLayout = new LinearLayout(context);
        final EditText editText = new EditText(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(com.m_myr.nuwm.nuwmschedule.R.dimen.activity_horizontal_margin);
        layoutParams.setMargins(dimensionPixelSize, 0, dimensionPixelSize, 0);
        editText.setLayoutParams(layoutParams);
        linearLayout.addView(editText);
        builder.setView(linearLayout);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Надіслати", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.utils.AlertHelpers$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AlertHelpers.DialogEditInterface.this.onClick(editText);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.utils.AlertHelpers$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }
}

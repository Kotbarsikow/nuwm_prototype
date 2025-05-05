package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.hootsuite.nachos.chip.ChipInfo;
import com.hootsuite.nachos.validator.ChipifyingNachoValidator;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Recipient;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class AlertSender extends AlertDialog implements DialogInterface.OnClickListener {
    private NachoTextViewCustom mMessageRecipients;
    private ProgressBar mProgress;
    private TextInputLayout mTextInputLayout;

    public AlertSender(Context context) {
        super(context);
        View inflate = getLayoutInflater().inflate(R.layout.alert_sender_content, (ViewGroup) null);
        setView(inflate);
        setContentView(R.layout.alert_sender_content);
        setCancelable(false);
        this.mMessageRecipients = (NachoTextViewCustom) inflate.findViewById(R.id.message_recipients);
        this.mTextInputLayout = (TextInputLayout) inflate.findViewById(R.id.text_input_layout);
        this.mProgress = (ProgressBar) inflate.findViewById(R.id.progress);
        this.mMessageRecipients.setNachoValidator(new ChipifyingNachoValidator());
        this.mMessageRecipients.setIllegalCharacters(',', ';', '.');
        setButton(-1, "Надіслати", this);
        setButton(-2, "Скасувати", this);
        this.mMessageRecipients.addTextChangedListener(new TextWatcher() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.AlertSender.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (AlertSender.this.mMessageRecipients.getChipValues().size() > 5) {
                    AlertSender.this.mTextInputLayout.setErrorEnabled(true);
                    AlertSender.this.mTextInputLayout.setError("Кількість тимчасово обмежена 5");
                } else {
                    AlertSender.this.mTextInputLayout.setErrorEnabled(false);
                }
            }
        });
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        if (which != -2) {
            return;
        }
        hide();
    }

    public void setRecipient(List<String> selectedRecipient, ArrayList<Recipient> recipients) {
        this.mMessageRecipients.setAdapter(new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, recipients));
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = selectedRecipient.iterator();
        while (it.hasNext()) {
            String lowerCase = it.next().toLowerCase();
            Iterator<Recipient> it2 = recipients.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Recipient next = it2.next();
                    if (next.equalsTopicName(lowerCase)) {
                        arrayList.add(new ChipInfo(next.getTitle(), next));
                        it.remove();
                        break;
                    }
                }
            }
        }
        if (selectedRecipient.size() > 0) {
            Toast.makeText(getContext(), "Не вдалося знайти отримувачів:" + Utils.StringUtils.joinString(",", selectedRecipient), 1).show();
        }
        this.mMessageRecipients.setTextWithChips(arrayList);
    }
}

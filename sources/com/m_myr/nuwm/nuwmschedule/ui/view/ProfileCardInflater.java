package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.net.MailTo;
import androidx.fragment.app.Fragment;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.button.MaterialButton;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.ProfileCard;
import com.m_myr.nuwm.nuwmschedule.data.models.ProfileCardButton;
import com.m_myr.nuwm.nuwmschedule.data.repositories.FastRepository;
import com.m_myr.nuwm.nuwmschedule.domain.InternalActionExecutor;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.EntityFlagsInt;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback;
import com.m_myr.nuwm.nuwmschedule.network.models.ActionResult;
import com.m_myr.nuwm.nuwmschedule.utils.AlertHelpers;
import com.m_myr.nuwm.nuwmschedule.utils.HtmlHttpImageGetter;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class ProfileCardInflater {
    private List<ProfileCard> cards;
    private final ViewGroup container;
    private final Context context;
    final float dpRaw = Utils.dpToPxRaw(1.0f);
    final int horizontalMargin;

    private ProfileCardInflater(Context context, ViewGroup container) {
        this.context = context;
        this.container = container;
        this.horizontalMargin = context.getResources().getDimensionPixelSize(R.dimen.horizontal_margin_large);
    }

    public static ProfileCardInflater from(Context context, ViewGroup container) {
        return new ProfileCardInflater(context, container);
    }

    public static ProfileCardInflater from(Activity activity, int container) {
        return new ProfileCardInflater(activity, (ViewGroup) activity.findViewById(container));
    }

    public static ProfileCardInflater from(Fragment fragment, int container) {
        return new ProfileCardInflater(fragment.getContext(), (ViewGroup) fragment.getView().findViewById(container));
    }

    public void inflate(ProfileCard card) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(card);
        inflate(arrayList);
    }

    public void inflate(List<ProfileCard> cards) {
        this.cards = cards;
        if (cards != null) {
            Iterator<ProfileCard> it = cards.iterator();
            while (it.hasNext()) {
                inflateCard(it.next());
            }
        }
    }

    private void inflateCard(ProfileCard card) {
        View inflateButtons;
        if (card.isDivider()) {
            this.container.addView(addDecorator());
        }
        if (card.getTitle() != null) {
            this.container.addView(inflateTitle(card.getTitle(), card));
        }
        if (card.getBody() != null) {
            this.container.addView(inflateBody(card.getBody()));
        }
        if (card.getCardButtons() == null || card.getCardButtons().isEmpty() || (inflateButtons = inflateButtons(card.getCardButtons(), this.container)) == null) {
            return;
        }
        this.container.addView(inflateButtons);
    }

    private View inflateButtons(ArrayList<ProfileCardButton> cardButtons, ViewGroup container) {
        Iterator<ProfileCardButton> it = cardButtons.iterator();
        FlexboxLayout flexboxLayout = null;
        while (it.hasNext()) {
            ProfileCardButton next = it.next();
            MaterialButton materialButton = (MaterialButton) LayoutInflater.from(this.context).inflate(next.getTheme(), this.container, false);
            materialButton.setEnabled(next.isEnabled());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            int i = this.horizontalMargin;
            float f = this.dpRaw;
            layoutParams.setMargins(i, (int) f, i, (int) f);
            layoutParams.weight = 1.0f;
            layoutParams.gravity = next.getGravity();
            materialButton.setText(next.getText());
            if (next.getTintColor() != 0) {
                materialButton.setBackgroundTintMode(PorterDuff.Mode.SRC_OVER);
                materialButton.setBackgroundTintList(ColorStateList.valueOf(next.getBackgroundTintColor()));
                materialButton.setTextColor(next.getTextTintColor());
            }
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.ProfileCardInflater$$ExternalSyntheticLambda1
                public final /* synthetic */ ProfileCardButton f$1;

                public /* synthetic */ ProfileCardInflater$$ExternalSyntheticLambda1(ProfileCardButton next2) {
                    r2 = next2;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ProfileCardInflater.this.m216x67ba87e6(r2, view);
                }
            });
            if (next2.isChainGravity()) {
                if (flexboxLayout == null) {
                    flexboxLayout = new FlexboxLayout(this.context);
                    flexboxLayout.setLayoutParams(layoutParams);
                    flexboxLayout.setFlexWrap(next2.getFlexWrap());
                }
                flexboxLayout.addView(materialButton, layoutParams);
            } else {
                materialButton.setLayoutParams(layoutParams);
                container.addView(materialButton);
            }
        }
        return flexboxLayout;
    }

    /* renamed from: lambda$inflateButtons$0$com-m_myr-nuwm-nuwmschedule-ui-view-ProfileCardInflater */
    /* synthetic */ void m216x67ba87e6(ProfileCardButton profileCardButton, View view) {
        onActionClick(profileCardButton);
    }

    private View addDecorator() {
        View view = new View(this.context);
        view.setAlpha(0.66f);
        view.setBackgroundColor(ResourcesHelper.getAttrColor(this.context, R.attr.colorBackgroundSub));
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, dp(8)));
        return view;
    }

    private int dp(int dp) {
        return Math.round(dp * this.dpRaw);
    }

    private View inflateTitle(String title, EntityFlagsInt style) {
        AppCompatTextView appCompatTextView = new AppCompatTextView(this.context);
        appCompatTextView.setTextAppearance(this.context, R.style.BaseTextAppearance_Title_Bold);
        appCompatTextView.setText(Html.fromHtml(title));
        appCompatTextView.setPadding(this.horizontalMargin, style.checkAttribute(1) ? dp(10) : 0, this.horizontalMargin, style.checkAttribute(2) ? dp(10) : 0);
        return appCompatTextView;
    }

    private AppCompatTextView inflateBody(String body) {
        AppCompatTextView appCompatTextView = new AppCompatTextView(this.context);
        appCompatTextView.setTextAppearance(this.context, R.style.BaseTextAppearance);
        int i = this.horizontalMargin;
        appCompatTextView.setPadding(i, 0, i, 0);
        appCompatTextView.setAutoLinkMask(15);
        appCompatTextView.setLinksClickable(true);
        appCompatTextView.setTextIsSelectable(true);
        appCompatTextView.setClickable(true);
        setTextViewHTML(appCompatTextView, body);
        return appCompatTextView;
    }

    protected void setTextViewHTML(TextView text, String content) {
        Spanned fromHtml = HtmlImproved.fromHtml(content, new HtmlHttpImageGetter(text, "http://nuwm.edu.ua/", false));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(fromHtml);
        for (URLSpan uRLSpan : (URLSpan[]) spannableStringBuilder.getSpans(0, fromHtml.length(), URLSpan.class)) {
            makeLinkClickable(spannableStringBuilder, uRLSpan);
        }
        text.setText(spannableStringBuilder);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }

    protected void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span) {
        strBuilder.setSpan(new ClickableSpan() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.ProfileCardInflater.1
            final /* synthetic */ URLSpan val$span;

            AnonymousClass1(final URLSpan span2) {
                val$span = span2;
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                LinksResolver.ActionView(ProfileCardInflater.this.context, val$span.getURL());
            }
        }, strBuilder.getSpanStart(span2), strBuilder.getSpanEnd(span2), strBuilder.getSpanFlags(span2));
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.view.ProfileCardInflater$1 */
    class AnonymousClass1 extends ClickableSpan {
        final /* synthetic */ URLSpan val$span;

        AnonymousClass1(final URLSpan span2) {
            val$span = span2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            LinksResolver.ActionView(ProfileCardInflater.this.context, val$span.getURL());
        }
    }

    private void onActionClick(ProfileCardButton cardButton) {
        FastRepository.call(APIMethods.sendAction(cardButton.getId(), "@clicked")).detach().start();
        try {
            int action = cardButton.getAction();
            if (action == 1) {
                Intent intent = new Intent("android.intent.action.DIAL");
                intent.setData(Uri.parse(cardButton.getActionIntent()));
                this.context.startActivity(intent);
            } else if (action == 2) {
                String str = MailTo.MAILTO_SCHEME + cardButton.getActionIntent() + "?subject=" + Uri.encode(cardButton.getActionText()) + "&body=";
                Intent intent2 = new Intent("android.intent.action.SENDTO");
                intent2.setData(Uri.parse(str));
                this.context.startActivity(intent2);
            } else if (action == 3) {
                FastRepository.call(APIMethods.sendAction(cardButton.getId(), cardButton.getActionIntent())).into(new RequestObjectCallback<ActionResult>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.ProfileCardInflater.2
                    AnonymousClass2() {
                    }

                    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
                    public void onError(ErrorResponse response) {
                        Toast.makeText(ProfileCardInflater.this.context, "Неможливо виконати цю дію+\n" + response.getMessage(), 0).show();
                    }

                    @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
                    public void onSuccessData(ActionResult data) {
                        AlertHelpers.createInfoAlert(ProfileCardInflater.this.context, data.getTitle(), data.getMessage()).show();
                    }
                }).start();
            } else if (action == 4) {
                AlertHelpers.createEditAlert(this.context, cardButton.getActionIntent(), cardButton.getActionText(), new AlertHelpers.DialogEditInterface() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.ProfileCardInflater$$ExternalSyntheticLambda0
                    public final /* synthetic */ ProfileCardButton f$1;

                    public /* synthetic */ ProfileCardInflater$$ExternalSyntheticLambda0(ProfileCardButton cardButton2) {
                        r2 = cardButton2;
                    }

                    @Override // com.m_myr.nuwm.nuwmschedule.utils.AlertHelpers.DialogEditInterface
                    public final void onClick(EditText editText) {
                        ProfileCardInflater.this.m217x722985a6(r2, editText);
                    }
                }).show();
            } else if (action == 5) {
                LinksResolver.ActionView(this.context, cardButton2.getActionIntent());
            } else if (action == 6) {
                LinksResolver.startOnChrome(this.context, cardButton2.getActionIntent());
            } else if (action == 7) {
                LinksResolver.openById(this.context, Utils.StringUtils.safeToInt(cardButton2.getActionIntent(), 0));
            } else if (action == 8) {
                InternalActionExecutor.execute(this.context, cardButton2.getActionIntent(), cardButton2.getActionText());
            }
        } catch (Exception unused) {
            Toast.makeText(this.context, "Неможливо виконати цю дію", 0).show();
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.view.ProfileCardInflater$2 */
    class AnonymousClass2 implements RequestObjectCallback<ActionResult> {
        AnonymousClass2() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onError(ErrorResponse response) {
            Toast.makeText(ProfileCardInflater.this.context, "Неможливо виконати цю дію+\n" + response.getMessage(), 0).show();
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onSuccessData(ActionResult data) {
            AlertHelpers.createInfoAlert(ProfileCardInflater.this.context, data.getTitle(), data.getMessage()).show();
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.view.ProfileCardInflater$3 */
    class AnonymousClass3 implements RequestObjectCallback<ActionResult> {
        AnonymousClass3() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onError(ErrorResponse response) {
            Toast.makeText(ProfileCardInflater.this.context, "Неможливо виконати цю дію+\n" + response.getMessage(), 0).show();
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onSuccessData(ActionResult data) {
            AlertHelpers.createInfoAlert(ProfileCardInflater.this.context, data.getTitle(), data.getMessage()).show();
        }
    }

    /* renamed from: lambda$onActionClick$1$com-m_myr-nuwm-nuwmschedule-ui-view-ProfileCardInflater */
    /* synthetic */ void m217x722985a6(ProfileCardButton profileCardButton, EditText editText) {
        FastRepository.call(APIMethods.sendAction(profileCardButton.getId(), profileCardButton.getActionIntent(), editText.getText().toString())).into(new RequestObjectCallback<ActionResult>() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.ProfileCardInflater.3
            AnonymousClass3() {
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                Toast.makeText(ProfileCardInflater.this.context, "Неможливо виконати цю дію+\n" + response.getMessage(), 0).show();
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(ActionResult data) {
                AlertHelpers.createInfoAlert(ProfileCardInflater.this.context, data.getTitle(), data.getMessage()).show();
            }
        }).start();
    }
}

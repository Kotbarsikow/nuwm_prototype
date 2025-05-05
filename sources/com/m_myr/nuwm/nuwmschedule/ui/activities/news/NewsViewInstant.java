package com.m_myr.nuwm.nuwmschedule.ui.activities.news;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Document;
import com.m_myr.nuwm.nuwmschedule.data.models.NewsViewItem;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.ui.view.HtmlImproved;
import com.m_myr.nuwm.nuwmschedule.ui.view.MaterialDocumentHolderItem;
import com.m_myr.nuwm.nuwmschedule.utils.HtmlHttpImageGetter;
import com.m_myr.nuwm.nuwmschedule.utils.LinksResolver;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import org.mortbay.jetty.MimeTypes;

/* loaded from: classes2.dex */
public class NewsViewInstant extends AppCompatActivity {
    LinearLayout attachment;
    ImageView imageView;
    FlexboxLayout layout_images;
    NewsViewItem newsViewItem;
    TextView textTitle;
    TextView text_date;
    TextView text_news;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(App.getInstance().getAppTheme());
        setContentView(R.layout.layout_news_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.news.NewsViewInstant.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                NewsViewInstant.this.supportFinishAfterTransition();
            }
        });
        this.imageView = (ImageView) findViewById(R.id.image);
        this.textTitle = (TextView) findViewById(R.id.title);
        this.text_news = (TextView) findViewById(R.id.text_news);
        this.text_date = (TextView) findViewById(R.id.text_date);
        if (getIntent().getBooleanExtra("load", false)) {
            return;
        }
        this.newsViewItem = (NewsViewItem) getIntent().getSerializableExtra("news");
        initData();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_option) {
            LinksResolver.startOnChrome(this, this.newsViewItem.getUrl());
        } else if (itemId == R.id.action_share) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType(MimeTypes.TEXT_PLAIN);
            String str = this.newsViewItem.getDesc() + "\n\nДетальніше:" + this.newsViewItem.getUrl();
            intent.putExtra("android.intent.extra.SUBJECT", "Subject here");
            intent.putExtra("android.intent.extra.TEXT", str);
            startActivity(Intent.createChooser(intent, "Поділитися "));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        Glide.with((FragmentActivity) this).load(this.newsViewItem.getImage()).into(this.imageView);
        this.textTitle.setText(this.newsViewItem.getTitle());
        setTextViewHTML(this.text_news);
        this.text_news.setLinksClickable(true);
        this.text_news.setMovementMethod(LinkMovementMethod.getInstance());
        this.text_date.setText(this.newsViewItem.getDate());
        if (this.newsViewItem.getDetailed().getG_images() != null && this.newsViewItem.getDetailed().getG_images().length > 0) {
            FlexboxLayout flexboxLayout = (FlexboxLayout) findViewById(R.id.photo_holder);
            this.layout_images = flexboxLayout;
            flexboxLayout.setVisibility(0);
            this.layout_images.post(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.news.NewsViewInstant.2
                @Override // java.lang.Runnable
                public void run() {
                    NewsViewInstant.this.initPhoto();
                }
            });
        }
        if (this.newsViewItem.getDetailed().haveDoc()) {
            this.attachment = (LinearLayout) findViewById(R.id.attachment);
            findViewById(R.id.space).setVisibility(0);
            this.attachment.setVisibility(0);
            initDoc();
        }
    }

    private void initDoc() {
        LayoutInflater from = LayoutInflater.from(this);
        for (Document document : this.newsViewItem.getDetailed().getDoc()) {
            this.attachment.addView(new MaterialDocumentHolderItem(from, this.attachment, R.layout.file_attach_material_fill).initDocument(document).getView());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPhoto() {
        Utils.calculateNoOfColumns(this);
        this.layout_images.getMeasuredWidth();
        int i = (int) (getResources().getDisplayMetrics().density * 1.0f);
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        int length = this.newsViewItem.getDetailed().getG_images().length;
        for (int i2 = 0; i2 < length; i2++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setClickable(true);
            imageView.setBackgroundResource(typedValue.resourceId);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            imageView.setPadding(i, i, i, i);
            imageView.setLayoutParams(layoutParams);
            this.layout_images.addView(imageView);
            Glide.with((FragmentActivity) this).load(this.newsViewItem.getDetailed().getG_images()[i2]).error(R.drawable.ic_numw).placeholder(R.drawable.ic_numw).into(imageView);
            imageView.setTag(String.valueOf(i2));
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.news.NewsViewInstant.3
                @Override // android.view.View.OnClickListener
                public void onClick(final View v) {
                }
            });
        }
    }

    protected void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span) {
        strBuilder.setSpan(new ClickableSpan() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.news.NewsViewInstant.4
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
            }
        }, strBuilder.getSpanStart(span), strBuilder.getSpanEnd(span), strBuilder.getSpanFlags(span));
    }

    protected void setTextViewHTML(TextView text) {
        Spanned fromHtml = HtmlImproved.fromHtml(this.newsViewItem.getDetailed().getContent(), new HtmlHttpImageGetter(this.text_news, "https://nuwm.edu.ua/", false), null, this);
        Spannable spannable = (Spannable) fromHtml;
        for (final ImageSpan imageSpan : (ImageSpan[]) spannable.getSpans(0, spannable.length(), ImageSpan.class)) {
            spannable.setSpan(new URLSpan(imageSpan.getSource()) { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.news.NewsViewInstant.5
                @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
                public void onClick(View v) {
                    Log.e("onClick", "onClick");
                    LinksResolver.ActionView(NewsViewInstant.this, imageSpan.getSource());
                }
            }, spannable.getSpanStart(imageSpan), spannable.getSpanEnd(imageSpan), spannable.getSpanFlags(imageSpan));
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(fromHtml);
        for (URLSpan uRLSpan : (URLSpan[]) spannableStringBuilder.getSpans(0, fromHtml.length(), URLSpan.class)) {
            makeLinkClickable(spannableStringBuilder, uRLSpan);
        }
        text.setText(spannableStringBuilder);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }
}

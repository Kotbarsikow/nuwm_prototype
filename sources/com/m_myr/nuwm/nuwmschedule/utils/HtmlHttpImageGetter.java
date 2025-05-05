package com.m_myr.nuwm.nuwmschedule.utils;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.Attributes;

/* loaded from: classes2.dex */
public class HtmlHttpImageGetter implements Html.ImageGetter {
    URI baseUri;
    TextView container;
    boolean matchParentWidth;

    public HtmlHttpImageGetter(TextView textView) {
        this.container = textView;
        this.matchParentWidth = false;
    }

    public HtmlHttpImageGetter(TextView textView, String baseUrl) {
        this.container = textView;
        if (baseUrl != null) {
            this.baseUri = URI.create(baseUrl);
        }
    }

    public HtmlHttpImageGetter(TextView textView, String baseUrl, boolean matchParentWidth) {
        this.container = textView;
        this.matchParentWidth = matchParentWidth;
        if (baseUrl != null) {
            this.baseUri = URI.create(baseUrl);
        }
    }

    @Override // android.text.Html.ImageGetter
    public Drawable getDrawable(String source) {
        return getDrawable(source, null);
    }

    public Drawable getDrawable(String source, Attributes attributes) {
        UrlDrawable urlDrawable = new UrlDrawable();
        new ImageGetterAsyncTask(urlDrawable, this, this.container, this.matchParentWidth, attributes, this.baseUri).execute(source);
        return urlDrawable;
    }

    private static class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
        private final WeakReference<Attributes> attributes;
        private URI base;
        private final WeakReference<View> containerReference;
        private final WeakReference<UrlDrawable> drawableReference;
        private final WeakReference<HtmlHttpImageGetter> imageGetterReference;
        private boolean matchParentWidth;
        private float maxWidthParent;
        private float needSizePxWidth;
        private final WeakReference<Resources> resources;
        private float scale = 1.0f;
        private String source;

        public ImageGetterAsyncTask(UrlDrawable d, HtmlHttpImageGetter imageGetter, View container, boolean matchParentWidth, Attributes attributes, URI base) {
            this.needSizePxWidth = 0.0f;
            this.drawableReference = new WeakReference<>(d);
            this.imageGetterReference = new WeakReference<>(imageGetter);
            this.containerReference = new WeakReference<>(container);
            this.resources = new WeakReference<>(container.getResources());
            this.attributes = new WeakReference<>(attributes);
            this.matchParentWidth = matchParentWidth;
            this.base = base;
            if (attributes != null) {
                if (attributes.getValue("width") != null) {
                    this.needSizePxWidth = Utils.dpToPxRaw(Utils.StringUtils.safeToFloat(attributes.getValue("width")));
                    return;
                }
                Matcher matcher = Pattern.compile("(?:\\s+|\\A)width\\s*:\\s*([0-9.]{1,})([a-zA-Z%]{1,3})(\\S*)").matcher(attributes.getValue("", "style"));
                if (matcher.find()) {
                    float safeToFloat = Utils.StringUtils.safeToFloat(matcher.group(1));
                    if ("%".equals(matcher.group(2))) {
                        this.needSizePxWidth = -safeToFloat;
                        return;
                    } else if ("px".equals(matcher.group(2))) {
                        this.needSizePxWidth = safeToFloat;
                        return;
                    } else {
                        this.needSizePxWidth = Utils.dpToPxRaw(safeToFloat);
                        return;
                    }
                }
                this.needSizePxWidth = 0.0f;
            }
        }

        @Override // android.os.AsyncTask
        public Drawable doInBackground(String... params) {
            this.source = params[0];
            if (this.resources.get() != null) {
                return fetchDrawable(this.resources.get(), this.source);
            }
            return null;
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Drawable result) {
            if (result == null) {
                Log.e("HtmlHttpImageGetter", "Drawable result is null! (source: " + this.source + ")");
                return;
            }
            UrlDrawable urlDrawable = this.drawableReference.get();
            if (urlDrawable == null) {
                return;
            }
            urlDrawable.setBounds(0, 0, (int) (result.getIntrinsicWidth() * this.scale), (int) (result.getIntrinsicHeight() * this.scale));
            urlDrawable.drawable = result;
            HtmlHttpImageGetter htmlHttpImageGetter = this.imageGetterReference.get();
            if (htmlHttpImageGetter == null) {
                return;
            }
            htmlHttpImageGetter.container.invalidate();
            htmlHttpImageGetter.container.setText(htmlHttpImageGetter.container.getText());
        }

        public Drawable fetchDrawable(Resources res, String urlString) {
            if (!urlString.contains("http")) {
                urlString = this.base.toString() + urlString;
            }
            String replace = urlString.replace("http://nuwm.edu.ua/", "https://nuwm.edu.ua/");
            Log.e("HtmlHttpImageGetter", "fetchDrawable (source: " + replace + ")");
            try {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(res, fetch(replace));
                this.scale = getScale(bitmapDrawable);
                bitmapDrawable.setBounds(0, 0, (int) (bitmapDrawable.getIntrinsicWidth() * this.scale), (int) (bitmapDrawable.getIntrinsicHeight() * this.scale));
                return bitmapDrawable;
            } catch (Exception unused) {
                return null;
            }
        }

        private float getScale(Drawable drawable) {
            float f;
            int intrinsicWidth;
            View view = this.containerReference.get();
            if (view == null) {
                return 1.0f;
            }
            float width = (view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight();
            this.maxWidthParent = width;
            float min = Math.min(this.needSizePxWidth, width);
            this.needSizePxWidth = min;
            if (this.matchParentWidth) {
                f = this.maxWidthParent;
                intrinsicWidth = drawable.getIntrinsicWidth();
            } else {
                if (min < 0.0f) {
                    this.needSizePxWidth = (this.maxWidthParent / 100.0f) * min * (-1.0f);
                }
                f = this.needSizePxWidth;
                if (f == 0.0f) {
                    float intrinsicWidth2 = drawable.getIntrinsicWidth();
                    float f2 = this.maxWidthParent;
                    if (intrinsicWidth2 > f2) {
                        return f2 / drawable.getIntrinsicWidth();
                    }
                    return 1.0f;
                }
                intrinsicWidth = drawable.getIntrinsicWidth();
            }
            return f / intrinsicWidth;
        }

        private InputStream fetch(String urlString) throws IOException {
            URL url;
            HtmlHttpImageGetter htmlHttpImageGetter = this.imageGetterReference.get();
            if (htmlHttpImageGetter == null) {
                return null;
            }
            if (htmlHttpImageGetter.baseUri != null) {
                url = htmlHttpImageGetter.baseUri.resolve(urlString).toURL();
            } else {
                url = URI.create(urlString).toURL();
            }
            return (InputStream) url.getContent();
        }
    }

    public class UrlDrawable extends BitmapDrawable {
        protected Drawable drawable;

        public UrlDrawable() {
        }

        @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            Drawable drawable = this.drawable;
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }
    }
}

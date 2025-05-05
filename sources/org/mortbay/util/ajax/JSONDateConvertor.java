package org.mortbay.util.ajax;

import j$.util.DesugarTimeZone;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.mortbay.log.Log;
import org.mortbay.util.DateCache;
import org.mortbay.util.ajax.JSON;

/* loaded from: classes3.dex */
public class JSONDateConvertor implements JSON.Convertor {
    DateCache _dateCache;
    SimpleDateFormat _format;
    private boolean _fromJSON;

    public JSONDateConvertor() {
        this(false);
    }

    public JSONDateConvertor(boolean z) {
        this(DateCache.DEFAULT_FORMAT, DesugarTimeZone.getTimeZone("GMT"), z);
    }

    public JSONDateConvertor(String str, TimeZone timeZone, boolean z) {
        DateCache dateCache = new DateCache(str);
        this._dateCache = dateCache;
        dateCache.setTimeZone(timeZone);
        this._fromJSON = z;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        this._format = simpleDateFormat;
        simpleDateFormat.setTimeZone(timeZone);
    }

    public JSONDateConvertor(String str, TimeZone timeZone, boolean z, Locale locale) {
        DateCache dateCache = new DateCache(str, locale);
        this._dateCache = dateCache;
        dateCache.setTimeZone(timeZone);
        this._fromJSON = z;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, new DateFormatSymbols(locale));
        this._format = simpleDateFormat;
        simpleDateFormat.setTimeZone(timeZone);
    }

    @Override // org.mortbay.util.ajax.JSON.Convertor
    public Object fromJSON(Map map) {
        Object parseObject;
        if (!this._fromJSON) {
            throw new UnsupportedOperationException();
        }
        try {
            synchronized (this._format) {
                parseObject = this._format.parseObject((String) map.get("value"));
            }
            return parseObject;
        } catch (Exception e) {
            Log.warn(e);
            return null;
        }
    }

    @Override // org.mortbay.util.ajax.JSON.Convertor
    public void toJSON(Object obj, JSON.Output output) {
        String format = this._dateCache.format((Date) obj);
        if (this._fromJSON) {
            output.addClass(obj.getClass());
            output.add("value", format);
        } else {
            output.add(format);
        }
    }
}

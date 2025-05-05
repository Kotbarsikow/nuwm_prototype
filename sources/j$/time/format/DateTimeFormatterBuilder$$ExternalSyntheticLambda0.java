package j$.time.format;

import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalQuery;

/* loaded from: classes4.dex */
public final /* synthetic */ class DateTimeFormatterBuilder$$ExternalSyntheticLambda0 implements TemporalQuery {
    @Override // j$.time.temporal.TemporalQuery
    public final Object queryFrom(TemporalAccessor temporalAccessor) {
        ZoneId zoneId = (ZoneId) temporalAccessor.query(Temporal.CC.zoneId());
        if (zoneId == null || (zoneId instanceof ZoneOffset)) {
            return null;
        }
        return zoneId;
    }
}

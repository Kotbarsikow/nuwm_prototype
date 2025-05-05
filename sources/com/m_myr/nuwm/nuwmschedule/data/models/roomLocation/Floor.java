package com.m_myr.nuwm.nuwmschedule.data.models.roomLocation;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Floor.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010-\u001a\u0004\u0018\u00010\u00142\u0006\u0010.\u001a\u00020\rR\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR \u0010\f\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R&\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00138\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0006\"\u0004\b\u001b\u0010\bR&\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00138\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R \u0010\u001f\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000f\"\u0004\b!\u0010\u0011R \u0010\"\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u000f\"\u0004\b$\u0010\u0011R2\u0010%\u001a\u0016\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\u00130\u0013\u0018\u00010\u00138\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0016\"\u0004\b(\u0010\u0018R&\u0010)\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010\u00138\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0016\"\u0004\b,\u0010\u0018¨\u0006/"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/data/models/roomLocation/Floor;", "", "()V", "_id", "", "get_id", "()I", "set_id", "(I)V", "area", "getArea", "setArea", "brief", "", "getBrief", "()Ljava/lang/String;", "setBrief", "(Ljava/lang/String;)V", "funcAreas", "", "Lcom/m_myr/nuwm/nuwmschedule/data/models/roomLocation/FuncArea;", "getFuncAreas", "()Ljava/util/List;", "setFuncAreas", "(Ljava/util/List;)V", "high", "getHigh", "setHigh", "imageLayer", "getImageLayer", "setImageLayer", AppMeasurementSdk.ConditionalUserProperty.NAME, "getName", "setName", "name_en", "getName_en", "setName_en", "outline", "", "getOutline", "setOutline", "pubPoint", "Lcom/m_myr/nuwm/nuwmschedule/data/models/roomLocation/PubPoint;", "getPubPoint", "setPubPoint", "find", "roomName", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Floor {

    @SerializedName("_id")
    private int _id;

    @SerializedName("Area")
    private int area;

    @SerializedName("Brief")
    private String brief;

    @SerializedName("FuncAreas")
    private List<? extends FuncArea> funcAreas;

    @SerializedName("High")
    private int high;

    @SerializedName("ImageLayer")
    private List<? extends Object> imageLayer;

    @SerializedName("Name")
    private String name;

    @SerializedName("Name_en")
    private String name_en;

    @SerializedName("Outline")
    private List<? extends List<? extends List<Float>>> outline;

    @SerializedName("PubPoint")
    private List<? extends PubPoint> pubPoint;

    public final String getBrief() {
        return this.brief;
    }

    public final void setBrief(String str) {
        this.brief = str;
    }

    public final List<Object> getImageLayer() {
        return this.imageLayer;
    }

    public final void setImageLayer(List<? extends Object> list) {
        this.imageLayer = list;
    }

    public final int getArea() {
        return this.area;
    }

    public final void setArea(int i) {
        this.area = i;
    }

    public final List<PubPoint> getPubPoint() {
        return this.pubPoint;
    }

    public final void setPubPoint(List<? extends PubPoint> list) {
        this.pubPoint = list;
    }

    public final int getHigh() {
        return this.high;
    }

    public final void setHigh(int i) {
        this.high = i;
    }

    public final String getName_en() {
        return this.name_en;
    }

    public final void setName_en(String str) {
        this.name_en = str;
    }

    public final int get_id() {
        return this._id;
    }

    public final void set_id(int i) {
        this._id = i;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final List<List<List<Float>>> getOutline() {
        return this.outline;
    }

    public final void setOutline(List<? extends List<? extends List<Float>>> list) {
        this.outline = list;
    }

    public final List<FuncArea> getFuncAreas() {
        return this.funcAreas;
    }

    public final void setFuncAreas(List<? extends FuncArea> list) {
        this.funcAreas = list;
    }

    public final FuncArea find(String roomName) {
        String str;
        Intrinsics.checkNotNullParameter(roomName, "roomName");
        String lowerCase = roomName.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        List<? extends FuncArea> list = this.funcAreas;
        Object obj = null;
        if (list == null) {
            return null;
        }
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            String nameNotNull = ((FuncArea) next).getNameNotNull();
            if (nameNotNull != null) {
                Intrinsics.checkNotNull(nameNotNull);
                str = nameNotNull.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase()");
            } else {
                str = null;
            }
            if (StringsKt.equals$default(str, lowerCase, false, 2, null)) {
                obj = next;
                break;
            }
        }
        return (FuncArea) obj;
    }
}

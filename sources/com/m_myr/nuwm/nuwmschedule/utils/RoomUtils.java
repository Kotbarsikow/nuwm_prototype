package com.m_myr.nuwm.nuwmschedule.utils;

import androidx.exifinterface.media.ExifInterface;
import com.google.gson.Gson;
import com.m_myr.nuwm.nuwmschedule.data.models.roomLocation.Floor;
import com.m_myr.nuwm.nuwmschedule.data.models.roomLocation.FuncArea;
import com.m_myr.nuwm.nuwmschedule.data.models.roomLocation.JsonFile;
import com.m_myr.nuwm.nuwmschedule.data.models.roomLocation.JsonFileBuilding;
import com.m_myr.nuwm.nuwmschedule.data.models.roomLocation.RoomLocationInfo;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: RoomUtils.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nJ\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0002¨\u0006\u000e"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/utils/RoomUtils;", "", "()V", "findRoomBuilding", "Lcom/m_myr/nuwm/nuwmschedule/data/models/roomLocation/RoomLocationInfo;", "o", "", "generate3DRoomHtml", "room", "b", "", "getBuilding", "filename", "roomName", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RoomUtils {
    public final String generate3DRoomHtml(RoomLocationInfo room, boolean b) {
        Intrinsics.checkNotNullParameter(room, "room");
        List<Floor> floors = room.getData().floors;
        Intrinsics.checkNotNullExpressionValue(floors, "floors");
        Iterator<T> it = floors.iterator();
        while (it.hasNext()) {
            List<FuncArea> funcAreas = ((Floor) it.next()).getFuncAreas();
            if (funcAreas != null) {
                for (FuncArea funcArea : funcAreas) {
                    String nameNotNull = funcArea.getNameNotNull();
                    FuncArea room2 = room.getRoom();
                    if (Intrinsics.areEqual(nameNotNull, room2 != null ? room2.getNameNotNull() : null)) {
                        funcArea.category = 108;
                    }
                }
            }
        }
        return "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 maximum-scale=1.0 user-scalable=no\">\n    <title></title>\n</head>\n<body>\n<script src=\"js/three.min.js\"></script>\n<script src=\"js/Detector.js\"></script>\n<script src=\"js/OrbitControls.js\"></script>\n<script src=\"js/IndoorMap.js\"></script>\n<script src=\"js/Projector.js\"></script>\n<script src=\"js/stats.min.js\"></script>\n<script src=\"js/IndoorMap2d.js\"></script>\n<script src=\"js/IndoorMap3d.js\"></script>\n<script src=\"js/Theme.js\"></script>\n<script type=\"application/json\" id=\"map_json\">\n" + new Gson().toJson(new JsonFile(room.getData())) + "  \n</script>\n\n\n<link href=\"css/indoor3D.css\" rel=\"stylesheet\">\n\n\n<div id=\"indoor3d\" style=\"width: 100%; height:100%; top:0px; left: 0px; position: absolute\"></div>\n<script>\n\n    var params = {\n        mapDiv:\"indoor3d\",\n        dim:\"3d\"\n    }\n   var map = IndoorMap(params);\n    map.loadFromJson(document.getElementById('map_json').innerHTML, function(){ \n        map.showAreaNames(true).setSelectable(true);\n        var ul = IndoorMap.getUI(map);\n        document.body.appendChild(ul);\n    });\n\n</script>\n \n</body>\n</html>";
    }

    public final RoomLocationInfo findRoomBuilding(String o) {
        Intrinsics.checkNotNullParameter(o, "o");
        if (StringsKt.isBlank(o)) {
            return null;
        }
        String lowerCase = o.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        String obj = StringsKt.trim((CharSequence) lowerCase).toString();
        char charAt = obj.charAt(0);
        if (charAt == '0') {
            return null;
        }
        if (charAt == '1') {
            return getBuilding("1", obj);
        }
        if (charAt == '2') {
            return getBuilding(ExifInterface.GPS_MEASUREMENT_2D, obj);
        }
        return null;
    }

    private final RoomLocationInfo getBuilding(String filename, String roomName) {
        List<Floor> list;
        InputStream open = App.getInstance().getAssets().open("indoor3D/data/" + filename + ".json");
        Intrinsics.checkNotNullExpressionValue(open, "open(...)");
        Reader inputStreamReader = new InputStreamReader(open, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            String readText = TextStreamsKt.readText(bufferedReader);
            RoomLocationInfo roomLocationInfo = null;
            CloseableKt.closeFinally(bufferedReader, null);
            JsonFile jsonFile = (JsonFile) new Gson().fromJson(readText, JsonFile.class);
            JsonFileBuilding jsonFileBuilding = jsonFile != null ? jsonFile.data : null;
            if (jsonFileBuilding != null && (list = jsonFileBuilding.floors) != null && !list.isEmpty()) {
                if (roomName != null && StringsKt.contains$default((CharSequence) roomName, (CharSequence) "корпус", false, 2, (Object) null)) {
                    return new RoomLocationInfo(jsonFileBuilding, null);
                }
                if (roomName == null) {
                    return new RoomLocationInfo(jsonFileBuilding, null);
                }
                List<Floor> floors = jsonFileBuilding.floors;
                Intrinsics.checkNotNullExpressionValue(floors, "floors");
                for (Floor floor : floors) {
                    FuncArea find = floor.find(roomName);
                    if (find != null) {
                        roomLocationInfo = new RoomLocationInfo(jsonFileBuilding, find);
                        FuncArea room = roomLocationInfo.getRoom();
                        Intrinsics.checkNotNull(room);
                        room.floor = floor.getName();
                    }
                }
            }
            return roomLocationInfo;
        } finally {
        }
    }
}

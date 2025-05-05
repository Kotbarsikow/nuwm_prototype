package com.m_myr.nuwm.nuwmschedule.utils.gson;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.m_myr.nuwm.nuwmschedule.data.models.search.BaseSearchResult;
import com.m_myr.nuwm.nuwmschedule.data.models.search.DepartmentSearchItem;
import com.m_myr.nuwm.nuwmschedule.data.models.search.EmployerSearchItem;
import com.m_myr.nuwm.nuwmschedule.data.models.search.GroupSearchItem;
import com.m_myr.nuwm.nuwmschedule.data.models.search.RepositorySearchItem;
import com.m_myr.nuwm.nuwmschedule.data.models.search.StudentSearchItem;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class JsonSearchResponseDeserialize implements JsonDeserializer<ArrayList<BaseSearchResult>> {
    Gson gson = new Gson();

    @Override // com.google.gson.JsonDeserializer
    public ArrayList<BaseSearchResult> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Object fromJson;
        ArrayList<BaseSearchResult> arrayList = new ArrayList<>();
        Iterator<JsonElement> it = json.getAsJsonArray().iterator();
        while (it.hasNext()) {
            JsonElement next = it.next();
            int asInt = next.getAsJsonObject().get("type").getAsInt();
            if (asInt == 1) {
                fromJson = this.gson.fromJson(next, (Class<Object>) RepositorySearchItem.class);
            } else if (asInt == 2) {
                fromJson = this.gson.fromJson(next, (Class<Object>) EmployerSearchItem.class);
            } else if (asInt == 3) {
                fromJson = this.gson.fromJson(next, (Class<Object>) StudentSearchItem.class);
            } else if (asInt == 4) {
                fromJson = this.gson.fromJson(next, (Class<Object>) DepartmentSearchItem.class);
            } else if (asInt == 5) {
                fromJson = this.gson.fromJson(next, (Class<Object>) GroupSearchItem.class);
            } else {
                fromJson = this.gson.fromJson(next, (Class<Object>) BaseSearchResult.class);
            }
            arrayList.add((BaseSearchResult) fromJson);
        }
        return arrayList;
    }
}

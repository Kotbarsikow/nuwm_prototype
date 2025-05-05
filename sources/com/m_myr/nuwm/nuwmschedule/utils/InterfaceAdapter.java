package com.m_myr.nuwm.nuwmschedule.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class InterfaceAdapter implements JsonSerializer, JsonDeserializer {
    private static final String CLASSNAME = "CLASSNAME";
    private static final String DATA = "DATA";

    @Override // com.google.gson.JsonDeserializer
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ArrayList arrayList = new ArrayList();
        Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
        while (it.hasNext()) {
            JsonObject asJsonObject = it.next().getAsJsonObject();
            arrayList.add((ItemTimetableContract) jsonDeserializationContext.deserialize(asJsonObject.get(DATA), getObjectClass(((JsonPrimitive) asJsonObject.get(CLASSNAME)).getAsString())));
        }
        return arrayList;
    }

    @Override // com.google.gson.JsonSerializer
    public JsonElement serialize(Object object, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray jsonArray = new JsonArray();
        Iterator it = ((ArrayList) object).iterator();
        while (it.hasNext()) {
            ItemTimetableContract itemTimetableContract = (ItemTimetableContract) it.next();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(CLASSNAME, itemTimetableContract.getClass().getCanonicalName());
            jsonObject.add(DATA, context.serialize(itemTimetableContract));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public Class getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }
}

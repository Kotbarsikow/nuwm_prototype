package com.m_myr.nuwm.nuwmschedule.ui.framents.pushFeed;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.EmptyPost;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.ImagePost;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.NewsPost;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.PollPost;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.PostInteractive;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.PostMessage;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.TextPost;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class JsonPushResponseDeserialize implements JsonDeserializer<ArrayList<PostMessage>> {
    Gson gson = new Gson();

    @Override // com.google.gson.JsonDeserializer
    public ArrayList<PostMessage> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Object fromJson;
        ArrayList<PostMessage> arrayList = new ArrayList<>();
        Iterator<JsonElement> it = json.getAsJsonArray().iterator();
        while (it.hasNext()) {
            JsonElement next = it.next();
            int asInt = next.getAsJsonObject().get("action").getAsInt();
            if (asInt == 1) {
                fromJson = this.gson.fromJson(next, (Class<Object>) NewsPost.class);
            } else if (asInt == 2) {
                fromJson = this.gson.fromJson(next, (Class<Object>) TextPost.class);
            } else if (asInt == 3) {
                fromJson = this.gson.fromJson(next, (Class<Object>) PollPost.class);
            } else if (asInt == 4) {
                fromJson = this.gson.fromJson(next, (Class<Object>) ImagePost.class);
            } else if (asInt == 5) {
                fromJson = this.gson.fromJson(next, (Class<Object>) PostInteractive.class);
            } else {
                fromJson = this.gson.fromJson(next, (Class<Object>) EmptyPost.class);
            }
            arrayList.add((PostMessage) fromJson);
        }
        return arrayList;
    }
}

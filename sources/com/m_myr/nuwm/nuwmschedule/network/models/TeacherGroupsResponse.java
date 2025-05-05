package com.m_myr.nuwm.nuwmschedule.network.models;

import com.google.gson.annotations.SerializedName;
import com.m_myr.nuwm.nuwmschedule.data.models.CompositeGroup;
import com.m_myr.nuwm.nuwmschedule.data.models.Group;
import com.m_myr.nuwm.nuwmschedule.data.models.Stream;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class TeacherGroupsResponse {

    @SerializedName("composite_group")
    ArrayList<CompositeGroup> compositeGroup;

    @SerializedName("groups")
    ArrayList<Group> groups;

    @SerializedName("streams")
    ArrayList<Stream> streams;

    public ArrayList<Group> getGroups() {
        return this.groups;
    }

    public ArrayList<Stream> getStreams() {
        return this.streams;
    }

    public ArrayList<CompositeGroup> getCompositeGroup() {
        return this.compositeGroup;
    }
}

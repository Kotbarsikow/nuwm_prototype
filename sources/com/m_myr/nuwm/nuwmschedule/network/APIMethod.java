package com.m_myr.nuwm.nuwmschedule.network;

import androidx.core.app.NotificationCompat;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.network.api.ApiRequestBuilder;
import com.m_myr.nuwm.nuwmschedule.utils.Constant;
import org.mortbay.util.URIUtil;

@Deprecated
/* loaded from: classes2.dex */
public class APIMethod {
    @Deprecated
    public static API5RequestBuilder auth(String code) {
        return API5RequestBuilder.get("auth.php").add("code", code);
    }

    @Deprecated
    public static API5RequestBuilder profile(String token) {
        return API5RequestBuilder.get("profile.php").add("token", token);
    }

    @Deprecated
    public static API5RequestBuilder profile() {
        return API5RequestBuilder.get("profile.php").addToken();
    }

    @Deprecated
    public static API5RequestBuilder getElevationUpdate() {
        return API5RequestBuilder.get("getElevationUpdate.php").addToken();
    }

    @Deprecated
    public static API5RequestBuilder getMarks() {
        return API5RequestBuilder.get("getMarks.php").useCompact().addToken();
    }

    @Deprecated
    public static API5RequestBuilder getEvaluationStat() {
        return API5RequestBuilder.get("getEvaluationStat.php").getExtend().addToken();
    }

    @Deprecated
    public static API5RequestBuilder getVerified() {
        return API5RequestBuilder.get("checkVerified.php").addToken();
    }

    @Deprecated
    public static API5RequestBuilder createLesson(Lesson lesson, String uid) {
        return API5RequestBuilder.post("createLesson.php").add("uid", uid).addForm("lesson", lesson).addToken();
    }

    @Deprecated
    public static API5RequestBuilder createEvent(Lesson lesson, String uid) {
        return API5RequestBuilder.post("createEvent.php").add("uid", uid).addAppVersion().addOS().addForm("lesson", lesson).addToken();
    }

    @Deprecated
    public static API5RequestBuilder getTopics() {
        return API5RequestBuilder.get("getTopics.php").addToken();
    }

    @Deprecated
    public static API5RequestBuilder getGroupMarks(Integer subject, Integer half) {
        return API5RequestBuilder.get("getGroupMarks.php").add("subject", subject.intValue()).add("half", half.intValue()).addToken();
    }

    public static class Patch {
        public static int PATCH_HESK_FILE_UPLOAD = 100042;

        public static String getPatchFileUpload() {
            return API5RequestBuilder.post("upload.php").addToken().build().toString();
        }

        public static String getPatchLibImageUpload() {
            return API5RequestBuilder.post("uploadImage.php").addToken().build().toString();
        }

        public static String getAccountManager() {
            return ApiRequestBuilder.buildPatch(NotificationCompat.CATEGORY_SERVICE).addPatch("addEmail.php").addToken().build().toString();
        }

        public static String getAccountManagerDetachEmail(String email) {
            return ApiRequestBuilder.buildPatch(NotificationCompat.CATEGORY_SERVICE).addPatch("detachEmail.php").add("email", email).addToken().build().toString();
        }

        public static String getLinkDesk() {
            return ApiRequestBuilder.buildPatch(URIUtil.SLASH).addPatch("navigateToDesk.php").add("type", Constant.getNameOfTypeUser(AppDataManager.getCurrentInstance().getAuthType())).addToken().build().toString();
        }
    }
}

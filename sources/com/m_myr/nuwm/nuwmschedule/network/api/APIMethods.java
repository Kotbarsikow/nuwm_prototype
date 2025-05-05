package com.m_myr.nuwm.nuwmschedule.network.api;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.BuildConfig;
import com.m_myr.nuwm.nuwmschedule.data.models.AccountInfo;
import com.m_myr.nuwm.nuwmschedule.data.models.AppUserInfoUpdateData;
import com.m_myr.nuwm.nuwmschedule.data.models.Attendee;
import com.m_myr.nuwm.nuwmschedule.data.models.DepartmentProfile;
import com.m_myr.nuwm.nuwmschedule.data.models.EmployerInfo;
import com.m_myr.nuwm.nuwmschedule.data.models.Group;
import com.m_myr.nuwm.nuwmschedule.data.models.GroupExtended;
import com.m_myr.nuwm.nuwmschedule.data.models.HelpdeskCategory;
import com.m_myr.nuwm.nuwmschedule.data.models.HtmlData;
import com.m_myr.nuwm.nuwmschedule.data.models.IndividualCurriculum;
import com.m_myr.nuwm.nuwmschedule.data.models.MarkItem;
import com.m_myr.nuwm.nuwmschedule.data.models.NewsViewItem;
import com.m_myr.nuwm.nuwmschedule.data.models.RepositoryDocument;
import com.m_myr.nuwm.nuwmschedule.data.models.StudentInfo;
import com.m_myr.nuwm.nuwmschedule.data.models.StudentNumw;
import com.m_myr.nuwm.nuwmschedule.data.models.SubjectEvaluation;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.data.models.Workload;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.PollPost;
import com.m_myr.nuwm.nuwmschedule.data.models.feed.PostMessage;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.HelpdeskData;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.HelpdeskTicketFull;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.HelpdeskUserProfile;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.domain.AppPreferences;
import com.m_myr.nuwm.nuwmschedule.network.models.ActionResult;
import com.m_myr.nuwm.nuwmschedule.network.models.AppVersions;
import com.m_myr.nuwm.nuwmschedule.network.models.ElevationUpdate;
import com.m_myr.nuwm.nuwmschedule.network.models.EmailInfo;
import com.m_myr.nuwm.nuwmschedule.network.models.EmptyResult;
import com.m_myr.nuwm.nuwmschedule.network.models.GroupMarksResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.ProfileResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.RepositoryPage;
import com.m_myr.nuwm.nuwmschedule.network.models.ScheduleResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.SearchResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.SemesterSubjectEvaluation;
import com.m_myr.nuwm.nuwmschedule.network.models.TeacherGroupsResponse;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class APIMethods {
    public static final String GROUP_IDS = "groups_ids";
    public static final String SCHEDULE_IDS = "schedule_ids";

    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupIdType {
    }

    public static ApiRequestBuilder<ArrayList<StudentNumw>> getStudentsByGroup(int id) {
        return ApiRequestBuilder.getArray("getStudents.php", StudentNumw.class, "students").add(FirebaseAnalytics.Param.GROUP_ID, id).addToken();
    }

    public static ApiRequestBuilder<HelpdeskUserProfile> authHesk() {
        return ApiRequestBuilder.getObject("authHelpdesk.php", HelpdeskUserProfile.class, "hesk_profile").addToken();
    }

    public static ApiRequestBuilder<ArrayList<MarkItem>> getMarksHistory() {
        return ApiRequestBuilder.getArray("getMarksHistory.php", MarkItem.class).addToken();
    }

    public static ApiRequestBuilder<ArrayList<MarkItem>> getTeacherMarksJournal() {
        return ApiRequestBuilder.getArray("getTeacherMarksJournal.php", MarkItem.class).addToken();
    }

    public static ApiRequestBuilder<ArrayList<StudentNumw>> getStudentsByGroupsIds(String ids) {
        return ApiRequestBuilder.getArray("getStudents.php", StudentNumw.class, "students").add("group_ids", ids).addToken();
    }

    public static ApiRequestBuilder<ArrayList<StudentNumw>> findStudentsByGroupsName(String names) {
        return ApiRequestBuilder.getArray("getStudents.php", StudentNumw.class, "students").add("group_names", names).addToken();
    }

    public static ApiRequestBuilder<GroupExtended> getGroup(int id, boolean getStudents) {
        return ApiRequestBuilder.getObject("getGroup.php", GroupExtended.class, "group").add("id", id).add("participants", getStudents).addToken();
    }

    public static ApiRequestBuilder<HelpdeskData> getHelpdesk(String assigned, String sortBy, String category) {
        return ApiRequestBuilder.getObject("getHelpdesk.php", HelpdeskData.class).add("assigned", assigned).add("sortBy", sortBy).add("category", category).addToken();
    }

    public static ApiRequestBuilder<ArrayList<HelpdeskCategory>> getHelpdeskCategories() {
        return ApiRequestBuilder.getArray("getHelpdeskCategories.php", HelpdeskCategory.class).addToken();
    }

    public static ApiRequestBuilder<HelpdeskTicketFull> getHelpdeskTicket(int ticket_id) {
        return ApiRequestBuilder.getObject("getHelpdeskTicket.php", HelpdeskTicketFull.class).isExtended(true).add("ticket_id", ticket_id).addToken();
    }

    public static ApiRequestBuilder<EmptyResult> sendHelpdeskReply(int ticket_id, String messages, String attach) {
        return ApiRequestBuilder.postObject("sendHelpdeskReply.php", EmptyResult.class).add("ticket_id", ticket_id).add("messages", messages).add("attach", attach).addToken();
    }

    public static ApiRequestBuilder<Integer> createHelpdeskTicket(int category, String subject, String message, String attach, int priority) {
        return ApiRequestBuilder.postObject("createHelpdeskTicket.php", Integer.class, "ticket_id").add("category", category).add("subject", subject).add("message", message).add("attach", attach).add("priority", priority).addToken();
    }

    public static ApiRequestBuilder<GroupExtended> findGroup(String name, boolean getStudents) {
        return ApiRequestBuilder.getObject("getGroup.php", GroupExtended.class, "group").add(AppMeasurementSdk.ConditionalUserProperty.NAME, name).add("participants", getStudents).addToken();
    }

    public static ApiRequestBuilder<SubjectEvaluation> getMarks(int subject_id, int half) {
        return ApiRequestBuilder.getObject("getMarks.php", SubjectEvaluation.class).add("subject_id", subject_id).add("half", half).addToken();
    }

    public static ApiRequestBuilder<AccountInfo> getMyAccount() {
        return ApiRequestBuilder.getObject("getMyAccount.php", AccountInfo.class).addToken();
    }

    public static ApiRequestBuilder<ArrayList<StudentNumw>> getMyGroupStudents() {
        return ApiRequestBuilder.getArray("getStudents.php", StudentNumw.class, "students").addToken();
    }

    public static ApiRequestBuilder<ArrayList<Group>> getGroupsOfStream(String type, String consists) {
        return ApiRequestBuilder.getArray("getGroupsOfStream.php", Group.class, "groups").add(type, consists).addToken();
    }

    public static ApiRequestBuilder<ArrayList<Attendee>> getAttendees(String eventId) {
        return ApiRequestBuilder.getArray("getAttendees.php", Attendee.class, "attendees").add("eventId", eventId).addToken();
    }

    public static ApiRequestBuilder<RepositoryDocument> getRepositoryDocument(int eprintid) {
        return ApiRequestBuilder.getObject("getRepositoryFile.php", RepositoryDocument.class, "document").add("eprintid", eprintid).addToken();
    }

    public static ApiRequestBuilder<RepositoryPage> getRepositoryItemsByUser(String userName, String userInitial) {
        return ApiRequestBuilder.getObject("getRepositoryItems.php", RepositoryPage.class).add(AppMeasurementSdk.ConditionalUserProperty.NAME, userName).add("initial", userInitial).addToken();
    }

    public static ApiRequestBuilder<ProfileResponse> getProfileJson() {
        return ApiRequestBuilder.getObject("profile.php", ProfileResponse.class).addToken();
    }

    public static ApiRequestBuilder<IndividualCurriculum> getCurriculum() {
        return ApiRequestBuilder.getObject("getCurriculum.php", IndividualCurriculum.class, "curriculum").addToken();
    }

    public static ApiRequestBuilder<HtmlData> getUserInfo() {
        return ApiRequestBuilder.getObject("getUserInfo.php", HtmlData.class).addToken();
    }

    public static ApiRequestBuilder<ArrayList<SemesterSubjectEvaluation>> getSemesterMarks() {
        return ApiRequestBuilder.getArray("getSemesterMarks.php", SemesterSubjectEvaluation.class, "subjects").addToken();
    }

    public static ApiRequestBuilder<EmailInfo> getEmailInfo(String email) {
        return ApiRequestBuilder.getObject("getEmailInfo.php", EmailInfo.class, "user").add("email", email);
    }

    public static ApiRequestBuilder<Workload> getWorkload() {
        return ApiRequestBuilder.getObject("getWorkload.php", Workload.class, "workload").addToken();
    }

    public static ApiRequestBuilder<Workload> getWorkload(int groupId) {
        return ApiRequestBuilder.getObject("getWorkload.php", Workload.class, "workload").add(FirebaseAnalytics.Param.GROUP_ID, groupId).addToken();
    }

    public static ApiRequestBuilder<TeacherGroupsResponse> getTeacherGroup() {
        return ApiRequestBuilder.getObject("getMyGroups.php", TeacherGroupsResponse.class).addToken();
    }

    public static ApiRequestBuilder<SearchResponse> search(String q) {
        return ApiRequestBuilder.getObject("search.php", SearchResponse.class).add(SearchIntents.EXTRA_QUERY, q).addToken();
    }

    public static ApiRequestBuilder<PollPost> vote(int poolId, byte answerId) {
        return ApiRequestBuilder.getObject("vote.php", PollPost.class).add("poolId", poolId).add("answerId", (int) answerId).addToken();
    }

    public static ApiRequestBuilder<PollPost> cancelVote(int poolId) {
        return ApiRequestBuilder.getObject("cancelVote.php", PollPost.class).add("poolId", poolId).addToken();
    }

    public static ApiRequestBuilder<ScheduleResponse> getTimetable(TimetableIdentifier identifier, int weekNum) {
        return ApiRequestBuilder.getObject("getTimetable.php", ScheduleResponse.class).add("week", weekNum).add(identifier.getField(), identifier.getValue()).add(NotificationCompat.CATEGORY_EVENT, AppPreferences.getInstance().isTimetableShowEvent() && identifier.isSelf()).addToken();
    }

    public static ApiRequestBuilder<ScheduleResponse> getTimetableToday(TimetableIdentifier identifier) {
        return ApiRequestBuilder.getObject("getTimetable.php", ScheduleResponse.class).add(identifier.getField(), identifier.getValue()).add(NotificationCompat.CATEGORY_EVENT, false).addToken();
    }

    public static ApiRequestBuilder<EmployerInfo> getPersonal(int uid, boolean extended) {
        return ApiRequestBuilder.getObject("getById.php", EmployerInfo.class, "person").add("id", uid).add("extended", extended).addToken();
    }

    public static ApiRequestBuilder<EmployerInfo> getPersonal(String name, boolean extended) {
        return ApiRequestBuilder.getObject("findByQuery.php", EmployerInfo.class, "person").add(SearchIntents.EXTRA_QUERY, name).add("type", "person").add("extended", extended).addToken();
    }

    public static ApiRequestBuilder<EmployerInfo> getPersonalEmail(String email, boolean extended) {
        return ApiRequestBuilder.getObject("findByQuery.php", EmployerInfo.class, "person").add(SearchIntents.EXTRA_QUERY, email).add("type", "person").add("extended", extended).addToken();
    }

    public static ApiRequestBuilder<StudentInfo> getStudent(int uid, boolean extended) {
        return ApiRequestBuilder.getObject("getById.php", StudentInfo.class, "student").add("id", uid).add("extended", extended).addToken();
    }

    public static ApiRequestBuilder<DepartmentProfile> getDepartment(int uid) {
        return ApiRequestBuilder.getObject("getById.php", DepartmentProfile.class, "department").add("id", uid).addToken();
    }

    public static ApiRequestBuilder<Void> setPostViewed(int uid) {
        return ApiRequestBuilder.getVoid("markAsRead.php").add("uid", uid).addToken();
    }

    public static ApiRequestBuilder<Void> sendMessagingToken(String registrationToken, String uid) {
        return ApiRequestBuilder.getVoid("updateMessagingToken.php").add("registration_token", registrationToken).add("platform", "android").add("uid", uid).addToken();
    }

    public static ApiRequestBuilder<AppVersions> getAppVersion() {
        return ApiRequestBuilder.getObject("app.php", AppVersions.class).add("type", BuildConfig.FLAVOR);
    }

    public static ApiRequestBuilder<ArrayList<NewsViewItem>> getNewsFeed(String category, int categoryId, int offset) {
        if (categoryId != 0) {
            return getNewsFeed(categoryId, offset);
        }
        return getNewsFeed(category, offset);
    }

    public static ApiRequestBuilder<ArrayList<NewsViewItem>> getNewsFeed(int categoryId, int offset) {
        return ApiRequestBuilder.getArray("news/getNewsFeed.php", NewsViewItem.class, "item").add("categoryId", categoryId).add(TypedValues.Cycle.S_WAVE_OFFSET, offset).add("html", 1).addToken();
    }

    public static ApiRequestBuilder<ArrayList<NewsViewItem>> getNewsFeed(String category, int offset) {
        return ApiRequestBuilder.getArray("news/getNewsFeed.php", NewsViewItem.class, "item").add("category", category).add(TypedValues.Cycle.S_WAVE_OFFSET, offset).add("html", 1).addToken();
    }

    public static ApiRequestBuilder<ArrayList<PostMessage>> getPush(ArrayList<String> topics) {
        return ApiRequestBuilder.getArray("getPush.php", PostMessage.class, "push").add("topics", topics).addToken();
    }

    public static ApiRequestBuilder<AppUserInfoUpdateData> checkUnread(ArrayList<String> topics) {
        return ApiRequestBuilder.getObject("checkUnread.php", AppUserInfoUpdateData.class).add("topics", topics).addToken();
    }

    public static ApiRequestBuilder<String> getHeskImageUploadLink() {
        return ApiRequestBuilder.getObject("getHeskImageUploadLink.php", String.class, ImagesContract.URL).addToken();
    }

    public static ApiRequestBuilder<ActionResult> sendAction(int id, String action, String data) {
        return ApiRequestBuilder.postObject("sendUserActionClick.php", ActionResult.class).add("id", id).add("action", action).add(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, data).add("guest", AppDataManager.getInstance().isGuest()).add(AppMeasurementSdk.ConditionalUserProperty.NAME, AppDataManager.getInstance().getUser().getFullName()).addToken();
    }

    public static ApiRequestBuilder<ActionResult> sendAction(int id, String action) {
        return sendAction(id, action, null);
    }

    public static ApiRequestBuilder<GroupMarksResponse> getGroupMarks(Integer subject, Integer half) {
        return ApiRequestBuilder.getObject("getGroupMarks.php", GroupMarksResponse.class).add("subject", subject.intValue()).add("half", half.intValue()).addToken();
    }

    public static ApiRequestBuilder<ElevationUpdate> getElevationUpdate(Integer subject) {
        return ApiRequestBuilder.getObject("getElevationUpdate.php", ElevationUpdate.class, "update").add("subject", subject.intValue()).addToken();
    }
}

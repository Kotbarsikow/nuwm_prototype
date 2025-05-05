package com.m_myr.nuwm.nuwmschedule.data.repositories;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.m_myr.nuwm.nuwmschedule.PersonNuwm;
import com.m_myr.nuwm.nuwmschedule.data.models.EmployeeNuwm;
import com.m_myr.nuwm.nuwmschedule.data.models.GuestUser;
import com.m_myr.nuwm.nuwmschedule.data.models.LoggedUser;
import com.m_myr.nuwm.nuwmschedule.data.models.OfficeUserNuwm;
import com.m_myr.nuwm.nuwmschedule.data.models.StudentNumw;
import com.m_myr.nuwm.nuwmschedule.data.models.TeacherNuwm;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.data.models.UserNuwm;
import com.m_myr.nuwm.nuwmschedule.data.models.helpdesk.TicketFilters;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.UserPermissions;
import com.m_myr.nuwm.nuwmschedule.network.models.AuthResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.VerifiedResponse;
import com.m_myr.nuwm.nuwmschedule.utils.Constant;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class AppDataManager {
    public static final String HINT_eventAdd = "h_eventAdd";
    public static final String HINT_idAlert = "h_idAlert";
    public static String HINT_mergeLesson = "h_mergeLess";
    private static AppDataManager instance;
    private String account;
    private int authType;
    private long lastUpdate;
    private int schstyle;
    SharedPreferences sharedPreferences;
    private boolean showNotification;
    private boolean showSaturday;
    private String token;
    private LoggedUser user;

    public static AppDataManager getInstance() {
        if (instance == null) {
            instance = new AppDataManager();
        }
        return instance;
    }

    public static AppDataManager getCurrentInstance() {
        if (instance == null) {
            instance = new AppDataManager();
        }
        AppDataManager appDataManager = instance;
        if (appDataManager.authType == 0) {
            return null;
        }
        return appDataManager;
    }

    public static SharedPreferences getIndependentInstance() {
        return App.getInstance().getSharedPreferences("generic", 0);
    }

    public int getAuthType() {
        return this.authType;
    }

    private AppDataManager(String account, int type) {
        this.authType = 0;
        this.showSaturday = false;
        this.account = account;
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(account, 0);
        this.sharedPreferences = sharedPreferences;
        this.showSaturday = sharedPreferences.getBoolean("show_saturday", false);
        this.token = this.sharedPreferences.getString("token", "");
        this.authType = this.sharedPreferences.getInt("authType", type);
        this.lastUpdate = this.sharedPreferences.getLong("lastUpdate", 0L);
        this.schstyle = this.sharedPreferences.getInt("schstyle", 0);
        this.showNotification = this.sharedPreferences.getBoolean("showNotification", true);
        if (this.authType == 1) {
            this.user = GuestUser.create(this.sharedPreferences.getString("user", null));
        }
        int i = this.authType;
        if (i == 4) {
            this.user = StudentNumw.create(this.sharedPreferences.getString("user", null));
            return;
        }
        if ((i & 8) == 8) {
            this.user = TeacherNuwm.create(this.sharedPreferences.getString("user", null));
        } else if (i == 2) {
            this.user = EmployeeNuwm.create(this.sharedPreferences.getString("user", null));
        } else if (i == 32) {
            this.user = OfficeUserNuwm.create(this.sharedPreferences.getString("user", null));
        }
    }

    public AppDataManager saveHelpdeskSetting(TicketFilters settings) {
        this.sharedPreferences.edit().putString("helpdesk_setting", new Gson().toJson(settings)).apply();
        return this;
    }

    public TicketFilters readHelpdeskSetting() {
        return (TicketFilters) new Gson().fromJson(this.sharedPreferences.getString("helpdesk_setting", "{}"), TicketFilters.class);
    }

    public long getLastUpdate() {
        return this.lastUpdate;
    }

    private AppDataManager(String account) {
        this(account, 0);
    }

    public UserPermissions getUserPermission() {
        return this.user.getPermission();
    }

    private AppDataManager() {
        this(App.getInstance().getCurrentAccount());
    }

    public static AppDataManager getInstanceOf(String s) {
        return new AppDataManager(s);
    }

    public static AppDataManager createInstance(AuthResponse authData, int type, LoggedUser userNuwm) throws IllegalAccessException {
        if (userNuwm instanceof UserNuwm) {
            UserNuwm userNuwm2 = (UserNuwm) userNuwm;
            App.getInstance().setCurrentAccount(userNuwm2.getEmail());
            if (!App.getInstance().addAccount(userNuwm2.getEmail())) {
                throw new IllegalAccessException("Цей акаунт уже додано");
            }
            instance = new AppDataManager(userNuwm2.getEmail(), type);
        } else {
            instance = new AppDataManager(Constant.getNameOfTypeUser(type), type);
        }
        instance.token = authData.getToken();
        AppDataManager appDataManager = instance;
        appDataManager.user = userNuwm;
        appDataManager.lastUpdate = System.currentTimeMillis();
        return instance;
    }

    public static void createInstance() {
        instance = new AppDataManager();
    }

    public boolean isAuth() {
        return this.authType != 0;
    }

    public String getToken() {
        return this.token;
    }

    public UserNuwm getNuwmUser() {
        LoggedUser loggedUser = this.user;
        if (loggedUser != null) {
            return (UserNuwm) loggedUser;
        }
        throw new RuntimeException("This user is not instanceof UserNuwm");
    }

    public PersonNuwm getPersonNuwm() {
        LoggedUser loggedUser = this.user;
        if (loggedUser != null) {
            return (PersonNuwm) loggedUser;
        }
        throw new RuntimeException("This user is not instanceof UserNuwm");
    }

    public void apply() {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        edit.putBoolean("show_saturday", this.showSaturday);
        edit.putString("user", this.user.toString());
        edit.putInt("authType", this.authType);
        edit.putString("token", this.token);
        edit.putInt("schstyle", this.schstyle);
        edit.putBoolean("showNotification", this.showNotification);
        edit.putLong("lastUpdate", this.lastUpdate);
        edit.apply();
    }

    public boolean isGuest() {
        return (this.authType & 1) == 1;
    }

    public boolean isStudent() {
        return (this.authType & 4) == 4;
    }

    public boolean isEmployee() {
        return (this.authType & 2) == 2;
    }

    public boolean isOffice() {
        return (this.authType & 32) == 32;
    }

    public boolean isTeacher() {
        return (this.authType & 8) == 8;
    }

    public StudentNumw getStudent() {
        return (StudentNumw) this.user;
    }

    public EmployeeNuwm getEmployee() {
        return (EmployeeNuwm) this.user;
    }

    public TeacherNuwm getTeacher() {
        return (TeacherNuwm) this.user;
    }

    public AppDataManager updateUserState(VerifiedResponse data) {
        if (isEmployee() || isStudent()) {
            getPersonNuwm().setCode(data.getBarcode());
            getPersonNuwm().setVerified(data.isVerified());
            getPersonNuwm().setIdCardImage(data.getPicture());
        }
        return this;
    }

    public boolean getHint(String idAlert) {
        return !this.sharedPreferences.getBoolean(idAlert, false);
    }

    public void setHideHint(String idAlert) {
        this.sharedPreferences.edit().putBoolean(idAlert, true).apply();
    }

    public AppDataManager setSchedulerStyle(byte schedulerItemStyleDefault) {
        this.schstyle = schedulerItemStyleDefault;
        return this;
    }

    public AppDataManager setShowNotification(boolean checked) {
        this.showNotification = checked;
        return this;
    }

    public ArrayList<String> getTopics() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("android");
        arrayList.add("global");
        if (getUser() instanceof PersonNuwm) {
            arrayList.add(Utils.DataCompat.getSex(getPersonNuwm().getSex()));
        }
        if (getUser() instanceof UserNuwm) {
            arrayList.add(getNuwmUser().getEmail().replace("@", "_"));
        }
        if (isTeacher()) {
            arrayList.add("teacher");
        }
        if (isEmployee()) {
            arrayList.add("employee");
        }
        if (isOffice()) {
            arrayList.add("office");
        }
        if (isStudent()) {
            StudentNumw student = getStudent();
            arrayList.add("student");
            arrayList.add(Utils.md5(student.getGroupName()));
            arrayList.add("course" + student.getCourse());
            if ("Денна".equals(student.getForm())) {
                arrayList.add("fulltime");
            } else if ("Заочна".equals(student.getForm())) {
                arrayList.add("externalform");
            }
            arrayList.add("id_fac" + student.getId_fac());
        }
        return arrayList;
    }

    public LoggedUser getUser() {
        return this.user;
    }

    public void remove() {
        this.sharedPreferences.edit().clear().commit();
        instance = null;
    }

    public boolean isNuwmUser() {
        return this.user instanceof UserNuwm;
    }

    public boolean isNuwmPerson() {
        return this.user instanceof PersonNuwm;
    }

    public AppDataManager updateUser(UserNuwm userNuwm) {
        this.user = userNuwm;
        this.lastUpdate = System.currentTimeMillis();
        return this;
    }

    public TimetableIdentifier getTimetableIdentifier() {
        return TimetableIdentifier.getSelf(this);
    }

    public AppDataManager forceRequestToUpdates() {
        this.lastUpdate = 0L;
        return this;
    }

    public String getAccount() {
        return this.account;
    }

    public AppDataManager updateToken(String newToken) {
        this.token = newToken;
        return this;
    }
}

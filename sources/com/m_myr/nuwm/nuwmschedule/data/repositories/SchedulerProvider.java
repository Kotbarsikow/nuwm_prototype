package com.m_myr.nuwm.nuwmschedule.data.repositories;

import android.os.Looper;
import android.util.Log;
import com.m_myr.nuwm.nuwmschedule.data.models.ItemTimetableContract;
import com.m_myr.nuwm.nuwmschedule.data.models.Lesson;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay;
import com.m_myr.nuwm.nuwmschedule.data.models.TimetableIdentifier;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.MergeEntity;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.RepositoryProvider;
import com.m_myr.nuwm.nuwmschedule.domain.interfaces.TimetableStorage;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.api.APIMethods;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectListener;
import com.m_myr.nuwm.nuwmschedule.network.api.APIObjectPreparator;
import com.m_myr.nuwm.nuwmschedule.network.api.PreparedObject;
import com.m_myr.nuwm.nuwmschedule.network.models.ScheduleResponse;
import j$.util.Comparator;
import j$.util.function.Function$CC;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.function.Function;

/* loaded from: classes2.dex */
public class SchedulerProvider implements Serializable {
    public static final int DAYS_IN_WEEK = 7;
    public static final int NULL_DAY_OFFSET = 2;
    protected final Comparator<ItemTimetableContract> dateComparator = Comparator.CC.comparing(new Function() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider$$ExternalSyntheticLambda0
        @Override // java.util.function.Function
        /* renamed from: andThen */
        public /* synthetic */ Function mo229andThen(Function function) {
            return Function$CC.$default$andThen(this, function);
        }

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return ((ItemTimetableContract) obj).getStartDate();
        }

        @Override // java.util.function.Function
        public /* synthetic */ Function compose(Function function) {
            return Function$CC.$default$compose(this, function);
        }
    });
    protected TimetableIdentifier identifier;
    protected boolean needReset;
    protected RepositoryProvider provider;
    protected TimetableStorage storage;

    public interface SchedulerCallback {

        /* renamed from: com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider$SchedulerCallback$-CC */
        public final /* synthetic */ class CC {
            public static void $default$onSuccess(SchedulerCallback _this, HashMap map, int week) {
            }
        }

        void invalidate(boolean state, String error);

        void onFaliture(int week, ErrorResponse errorResponse);

        void onSuccess(HashMap<Integer, TimetableDay> map, int week);
    }

    public static int getPositionFromDay(int dayOfYear) {
        return dayOfYear - 1;
    }

    public static int getRealDay(int position) {
        return position + 1;
    }

    public SchedulerProvider(RepositoryProvider provider, TimetableIdentifier identifier) {
        this.provider = provider;
        this.identifier = identifier;
        if (identifier.isSelf()) {
            this.storage = TimetableLocalStorage.getInstance(identifier);
        } else {
            this.storage = TimetableMemoryStorage.getInstance(identifier);
        }
    }

    public static TimetableStorage getSelfForce() {
        return TimetableLocalStorage.getInstance(AppDataManager.getInstance().getTimetableIdentifier());
    }

    public TimetableStorage getStorage() {
        return this.storage;
    }

    public static String getRealDate(int position, SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(getRealDate(position));
    }

    public static Date getRealDate(int position) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(6, getRealDay(position));
        calendar.set(11, 10);
        return calendar.getTime();
    }

    public static int calculateWeekNum(int position) {
        return (position + 2) / 7;
    }

    public static int calculateWeekNumFromDay(int day) {
        int i = (day + 1) / 7;
        Log.e("calculateWeekNumFromDay", "day: " + day + ", week: " + i);
        return i + 1;
    }

    public static int calculateFirstDayPositionFromWeek(int week) {
        return Math.max(0, (week * 7) - 2);
    }

    public void invalidateWhenSuccess() {
        this.needReset = true;
    }

    public void clearCache() {
        invalidateWhenSuccess();
        throw new RuntimeException("STUD");
    }

    public static int getTodayPosition() {
        return Math.abs(getPositionFromDay(Calendar.getInstance().get(6)));
    }

    public static int getTodayDay() {
        return new GregorianCalendar().get(6);
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider$2 */
    class AnonymousClass2 extends APIObjectPreparator<ScheduleResponse, HashMap<Integer, TimetableDay>> {
        AnonymousClass2() {
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIObjectPreparator
        public HashMap<Integer, TimetableDay> onSuccessDataInThread(ScheduleResponse data) throws Exception {
            Log.e("SchedulerRepository", "prepare in ".concat(Looper.myLooper() == Looper.getMainLooper() ? "UI" : "IO"));
            EventRepository.put(data.events);
            return SchedulerProvider.this.prepareMergeLesson(data);
        }
    }

    public void getTimetable(int weekNum, SchedulerCallback callback) {
        this.provider.getRepository().call(APIMethods.getTimetable(this.identifier, weekNum).setTimeout(10000)).prepareInThread(new APIObjectPreparator<ScheduleResponse, HashMap<Integer, TimetableDay>>() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider.2
            AnonymousClass2() {
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.api.APIObjectPreparator
            public HashMap<Integer, TimetableDay> onSuccessDataInThread(ScheduleResponse data) throws Exception {
                Log.e("SchedulerRepository", "prepare in ".concat(Looper.myLooper() == Looper.getMainLooper() ? "UI" : "IO"));
                EventRepository.put(data.events);
                return SchedulerProvider.this.prepareMergeLesson(data);
            }
        }).async(new APIObjectListener<PreparedObject<HashMap<Integer, TimetableDay>>>() { // from class: com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider.1
            final /* synthetic */ SchedulerCallback val$callback;
            final /* synthetic */ int val$weekNum;

            AnonymousClass1(SchedulerCallback callback2, int weekNum2) {
                val$callback = callback2;
                val$weekNum = weekNum2;
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onError(ErrorResponse response) {
                Log.e("SchedulerRepository", "onError in ".concat(Looper.myLooper() == Looper.getMainLooper() ? "UI" : "IO"));
                if (SchedulerProvider.this.needReset) {
                    SchedulerProvider.this.needReset = false;
                    val$callback.invalidate(false, response.getMessage());
                }
                val$callback.onFaliture(val$weekNum, response);
            }

            @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
            public void onSuccessData(PreparedObject<HashMap<Integer, TimetableDay>> data) {
                Log.e("SchedulerRepository", "onSuccessData in ".concat(Looper.myLooper() == Looper.getMainLooper() ? "UI" : "IO"));
                if (SchedulerProvider.this.needReset) {
                    SchedulerProvider.this.needReset = false;
                    SchedulerProvider.this.storage.clear();
                    val$callback.invalidate(true, null);
                }
                SchedulerProvider.this.storage.put(data.getData());
                val$callback.onSuccess(data.getData(), val$weekNum);
            }
        });
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider$1 */
    class AnonymousClass1 extends APIObjectListener<PreparedObject<HashMap<Integer, TimetableDay>>> {
        final /* synthetic */ SchedulerCallback val$callback;
        final /* synthetic */ int val$weekNum;

        AnonymousClass1(SchedulerCallback callback2, int weekNum2) {
            val$callback = callback2;
            val$weekNum = weekNum2;
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onError(ErrorResponse response) {
            Log.e("SchedulerRepository", "onError in ".concat(Looper.myLooper() == Looper.getMainLooper() ? "UI" : "IO"));
            if (SchedulerProvider.this.needReset) {
                SchedulerProvider.this.needReset = false;
                val$callback.invalidate(false, response.getMessage());
            }
            val$callback.onFaliture(val$weekNum, response);
        }

        @Override // com.m_myr.nuwm.nuwmschedule.network.listener.RequestObjectCallback
        public void onSuccessData(PreparedObject<HashMap<Integer, TimetableDay>> data) {
            Log.e("SchedulerRepository", "onSuccessData in ".concat(Looper.myLooper() == Looper.getMainLooper() ? "UI" : "IO"));
            if (SchedulerProvider.this.needReset) {
                SchedulerProvider.this.needReset = false;
                SchedulerProvider.this.storage.clear();
                val$callback.invalidate(true, null);
            }
            SchedulerProvider.this.storage.put(data.getData());
            val$callback.onSuccess(data.getData(), val$weekNum);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0220 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.util.HashMap<java.lang.Integer, com.m_myr.nuwm.nuwmschedule.data.models.TimetableDay> prepareMergeLesson(com.m_myr.nuwm.nuwmschedule.network.models.ScheduleResponse r15) {
        /*
            Method dump skipped, instructions count: 548
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.m_myr.nuwm.nuwmschedule.data.repositories.SchedulerProvider.prepareMergeLesson(com.m_myr.nuwm.nuwmschedule.network.models.ScheduleResponse):java.util.HashMap");
    }

    protected void mergeEntity(ItemTimetableContract current, ItemTimetableContract next) {
        boolean z = current instanceof Lesson;
        if (z && (next instanceof Lesson)) {
            if (current.getStartDate().equals(next.getStartDate()) && current.getEndDate().equals(next.getEndDate()) && current.getFastTitle().equals(next.getFastTitle()) && current.getType().equals(next.getType())) {
                collapseLesson((Lesson) current, (Lesson) next);
                return;
            }
            return;
        }
        if (z) {
            mergeLesson((Lesson) current, next);
        } else if (next instanceof Lesson) {
            mergeLesson((Lesson) next, current);
        }
    }

    protected void collapseLesson(Lesson current, Lesson next) {
        current.setAttribute((byte) 16);
        next.setAttribute((byte) 16);
        next.setAttribute((byte) 64);
    }

    protected void mergeLesson(Lesson current, ItemTimetableContract next) {
        current.setAttribute((byte) 32);
        next.setAttribute((byte) 64);
        if (next instanceof MergeEntity) {
            ((MergeEntity) next).setAttachLessonUid(current.generateInternalUid());
        }
    }
}

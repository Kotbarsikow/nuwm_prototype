package com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation.details;

import android.os.Bundle;
import android.view.View;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.SubjectSemester;
import com.m_myr.nuwm.nuwmschedule.databinding.ItemSemestrEvalTabBinding;
import com.m_myr.nuwm.nuwmschedule.databinding.SemestrEvaluationDetailsActivityBinding;
import com.m_myr.nuwm.nuwmschedule.domain.App;
import com.m_myr.nuwm.nuwmschedule.network.models.SemesterSubjectEvaluation;
import com.m_myr.nuwm.nuwmschedule.ui.activities.BaseToolbarActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.user.UserProfileActivity;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SemestrEvaluationDetails.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/semestrEvaluation/details/SemestrEvaluationDetails;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/BaseToolbarActivity;", "()V", "binding", "Lcom/m_myr/nuwm/nuwmschedule/databinding/SemestrEvaluationDetailsActivityBinding;", "getBinding", "()Lcom/m_myr/nuwm/nuwmschedule/databinding/SemestrEvaluationDetailsActivityBinding;", "setBinding", "(Lcom/m_myr/nuwm/nuwmschedule/databinding/SemestrEvaluationDetailsActivityBinding;)V", "semesterSubjectEvaluation", "Lcom/m_myr/nuwm/nuwmschedule/network/models/SemesterSubjectEvaluation;", "getSemesterSubjectEvaluation", "()Lcom/m_myr/nuwm/nuwmschedule/network/models/SemesterSubjectEvaluation;", "setSemesterSubjectEvaluation", "(Lcom/m_myr/nuwm/nuwmschedule/network/models/SemesterSubjectEvaluation;)V", "getOrEmpty", "", "x", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SemestrEvaluationDetails extends BaseToolbarActivity {
    public SemestrEvaluationDetailsActivityBinding binding;
    public SemesterSubjectEvaluation semesterSubjectEvaluation;

    public final SemesterSubjectEvaluation getSemesterSubjectEvaluation() {
        SemesterSubjectEvaluation semesterSubjectEvaluation = this.semesterSubjectEvaluation;
        if (semesterSubjectEvaluation != null) {
            return semesterSubjectEvaluation;
        }
        Intrinsics.throwUninitializedPropertyAccessException("semesterSubjectEvaluation");
        return null;
    }

    public final void setSemesterSubjectEvaluation(SemesterSubjectEvaluation semesterSubjectEvaluation) {
        Intrinsics.checkNotNullParameter(semesterSubjectEvaluation, "<set-?>");
        this.semesterSubjectEvaluation = semesterSubjectEvaluation;
    }

    public final SemestrEvaluationDetailsActivityBinding getBinding() {
        SemestrEvaluationDetailsActivityBinding semestrEvaluationDetailsActivityBinding = this.binding;
        if (semestrEvaluationDetailsActivityBinding != null) {
            return semestrEvaluationDetailsActivityBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(SemestrEvaluationDetailsActivityBinding semestrEvaluationDetailsActivityBinding) {
        Intrinsics.checkNotNullParameter(semestrEvaluationDetailsActivityBinding, "<set-?>");
        this.binding = semestrEvaluationDetailsActivityBinding;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(App.getInstance().getAppTheme());
        super.onCreate(savedInstanceState);
        SemestrEvaluationDetailsActivityBinding inflate = SemestrEvaluationDetailsActivityBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        setBinding(inflate);
        setContentView(getBinding().getRoot());
        attachToolbar();
        Serializable serializableExtra = getIntent().getSerializableExtra("subject");
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.m_myr.nuwm.nuwmschedule.network.models.SemesterSubjectEvaluation");
        setSemesterSubjectEvaluation((SemesterSubjectEvaluation) serializableExtra);
        setTitle(getSemesterSubjectEvaluation().getSubjectName());
        String departmentName = getSemesterSubjectEvaluation().getDepartmentName();
        if (departmentName == null || StringsKt.isBlank(departmentName)) {
            getBinding().department.setText("---");
        } else {
            getBinding().department.setText(getSemesterSubjectEvaluation().getDepartmentName());
        }
        getBinding().teacherName.setText(getSemesterSubjectEvaluation().getTeacherName());
        getBinding().subject.setText(getSemesterSubjectEvaluation().getSubjectName());
        getBinding().hour.setText(getSemesterSubjectEvaluation().getTotalHours() + " год.");
        getBinding().evaluation.setText(String.valueOf(getSemesterSubjectEvaluation().getMark()));
        getBinding().updated.setText(new SimpleDateFormat("d MMMM HH:mm").format(getSemesterSubjectEvaluation().getLastUpdate()));
        String teacherEmail = getSemesterSubjectEvaluation().getTeacherEmail();
        if (teacherEmail != null && !StringsKt.isBlank(teacherEmail)) {
            getBinding().teacherName.setClickable(true);
            getBinding().teacherName.setBackground(ResourcesHelper.getAttrDrawable(this, R.attr.selectableItemBackground));
            getBinding().teacherName.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.semestrEvaluation.details.SemestrEvaluationDetails$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SemestrEvaluationDetails.onCreate$lambda$0(SemestrEvaluationDetails.this, view);
                }
            });
        }
        List<SubjectSemester> semesters = getSemesterSubjectEvaluation().getSemesters();
        Intrinsics.checkNotNullExpressionValue(semesters, "getSemesters(...)");
        for (SubjectSemester subjectSemester : semesters) {
            ItemSemestrEvalTabBinding inflate2 = ItemSemestrEvalTabBinding.inflate(getLayoutInflater(), getBinding().data, true);
            Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
            inflate2.semestr.setText("Семестр " + subjectSemester.semester + ", " + subjectSemester.hour + " год.");
            inflate2.text1.setText(getOrEmpty(subjectSemester.XR1));
            inflate2.text2.setText(getOrEmpty(subjectSemester.XR1));
            inflate2.text3.setText(getOrEmpty(subjectSemester.XR4));
            inflate2.text4.setText(getOrEmpty(subjectSemester.XR2));
            inflate2.text5.setText(getOrEmpty(subjectSemester.XR5));
            inflate2.text6.setText(getOrEmpty(subjectSemester.XR3));
            inflate2.text7.setText(subjectSemester.ects);
            inflate2.text8.setText(getOrEmpty(subjectSemester.national));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(SemestrEvaluationDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserProfileActivity.findByEmail(this$0, this$0.getSemesterSubjectEvaluation().getTeacherEmail());
    }

    private final String getOrEmpty(int x) {
        if (x > 0) {
            return String.valueOf(x);
        }
        return MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
    }
}

package com.m_myr.nuwm.nuwmschedule.ui.activities.intro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;

/* loaded from: classes2.dex */
public class ScreenSlideIntroFragment extends Fragment {
    int p;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int i = getArguments().getInt("position");
        this.p = i;
        int i2 = R.layout.fragment_screen_slide_page_1;
        if (i != 0) {
            if (i == 1) {
                i2 = R.layout.fragment_screen_slide_page_2;
            } else if (i == 2) {
                i2 = R.layout.fragment_screen_slide_page_3;
            }
        }
        return inflater.inflate(i2, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int i = this.p;
        if (i == 0) {
            if (ContextCompat.checkSelfPermission(getContext(), "android.permission.READ_CALENDAR") == 0) {
                ((MaterialButton) view.findViewById(R.id.button)).setText("Далі");
                view.findViewById(R.id.skip).setVisibility(4);
                view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.intro.ScreenSlideIntroFragment.1
                    AnonymousClass1() {
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        ((IntroActivity) ScreenSlideIntroFragment.this.getActivity()).next(ScreenSlideIntroFragment.this.p + 1);
                    }
                });
            } else {
                view.findViewById(R.id.button).setVisibility(0);
                view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.intro.ScreenSlideIntroFragment.2
                    AnonymousClass2() {
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        if (ContextCompat.checkSelfPermission(ScreenSlideIntroFragment.this.getContext(), "android.permission.READ_CALENDAR") != 0) {
                            ScreenSlideIntroFragment.this.requestPermissions(new String[]{"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"}, 51);
                        }
                    }
                });
            }
            view.findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.intro.ScreenSlideIntroFragment.3
                AnonymousClass3() {
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    ((IntroActivity) ScreenSlideIntroFragment.this.getActivity()).next(ScreenSlideIntroFragment.this.p + 1);
                }
            });
            return;
        }
        if (i != 1) {
            if (i == 2) {
                view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.intro.ScreenSlideIntroFragment.5
                    final /* synthetic */ View val$view;

                    AnonymousClass5(View view2) {
                        val$view = view2;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        AppDataManager.getInstance().setShowNotification(((CheckBox) val$view.findViewById(R.id.notification)).isChecked());
                        ((IntroActivity) ScreenSlideIntroFragment.this.getActivity()).finishIntro();
                    }
                });
            }
        } else {
            AnonymousClass4 anonymousClass4 = new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.intro.ScreenSlideIntroFragment.4
                AnonymousClass4() {
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    int id = v.getId();
                    if (id != R.id.button) {
                        switch (id) {
                            case R.id.radioButton1 /* 2131362545 */:
                                AppDataManager.getInstance().setSchedulerStyle((byte) 0);
                                break;
                            case R.id.radioButton2 /* 2131362546 */:
                                AppDataManager.getInstance().setSchedulerStyle((byte) 1);
                                break;
                            case R.id.radioButton3 /* 2131362547 */:
                                AppDataManager.getInstance().setSchedulerStyle((byte) 2);
                                break;
                        }
                    }
                    ((IntroActivity) ScreenSlideIntroFragment.this.getActivity()).next(ScreenSlideIntroFragment.this.p + 1);
                }
            };
            view2.findViewById(R.id.radioButton1).setOnClickListener(anonymousClass4);
            view2.findViewById(R.id.radioButton2).setOnClickListener(anonymousClass4);
            view2.findViewById(R.id.radioButton3).setOnClickListener(anonymousClass4);
            view2.findViewById(R.id.button).setOnClickListener(anonymousClass4);
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.intro.ScreenSlideIntroFragment$1 */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            ((IntroActivity) ScreenSlideIntroFragment.this.getActivity()).next(ScreenSlideIntroFragment.this.p + 1);
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.intro.ScreenSlideIntroFragment$2 */
    class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            if (ContextCompat.checkSelfPermission(ScreenSlideIntroFragment.this.getContext(), "android.permission.READ_CALENDAR") != 0) {
                ScreenSlideIntroFragment.this.requestPermissions(new String[]{"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"}, 51);
            }
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.intro.ScreenSlideIntroFragment$3 */
    class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            ((IntroActivity) ScreenSlideIntroFragment.this.getActivity()).next(ScreenSlideIntroFragment.this.p + 1);
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.intro.ScreenSlideIntroFragment$4 */
    class AnonymousClass4 implements View.OnClickListener {
        AnonymousClass4() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            int id = v.getId();
            if (id != R.id.button) {
                switch (id) {
                    case R.id.radioButton1 /* 2131362545 */:
                        AppDataManager.getInstance().setSchedulerStyle((byte) 0);
                        break;
                    case R.id.radioButton2 /* 2131362546 */:
                        AppDataManager.getInstance().setSchedulerStyle((byte) 1);
                        break;
                    case R.id.radioButton3 /* 2131362547 */:
                        AppDataManager.getInstance().setSchedulerStyle((byte) 2);
                        break;
                }
            }
            ((IntroActivity) ScreenSlideIntroFragment.this.getActivity()).next(ScreenSlideIntroFragment.this.p + 1);
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.intro.ScreenSlideIntroFragment$5 */
    class AnonymousClass5 implements View.OnClickListener {
        final /* synthetic */ View val$view;

        AnonymousClass5(View view2) {
            val$view = view2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            AppDataManager.getInstance().setShowNotification(((CheckBox) val$view.findViewById(R.id.notification)).isChecked());
            ((IntroActivity) ScreenSlideIntroFragment.this.getActivity()).finishIntro();
        }
    }

    public boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int i : grantResults) {
            if (i == -1) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (hasAllPermissionsGranted(grantResults)) {
            if (this.p == 0) {
                getView().findViewById(R.id.button).setVisibility(4);
            }
            ((IntroActivity) getActivity()).next(this.p + 1);
        }
    }
}

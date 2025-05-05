package com.m_myr.nuwm.nuwmschedule.ui.activities.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.m_myr.nuwm.nuwmschedule.PersonNuwm;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener;
import com.m_myr.nuwm.nuwmschedule.data.repositories.AppDataManager;
import com.m_myr.nuwm.nuwmschedule.data.repositories.OldAPIRepository;
import com.m_myr.nuwm.nuwmschedule.network.APIMethod;
import com.m_myr.nuwm.nuwmschedule.network.ErrorResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.UploadResponse;
import com.m_myr.nuwm.nuwmschedule.network.models.VerifiedResponse;
import com.m_myr.nuwm.nuwmschedule.ui.activities.qrScaner.CodeViewActivity;
import com.m_myr.nuwm.nuwmschedule.utils.Gs1_128;
import com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import com.theartofdev.edmodo.cropper.CropImage;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class IdCardDelegate extends AlertDialog {
    Activity activity;
    OldAPIRepository apiRepository;
    FloatingActionButton floatingActionButton;
    View inflate;
    MaterialButton materialButton;
    ImageView person_photo;
    ProgressBar progressBar;
    TextView verificationText;

    protected IdCardDelegate(AppCompatActivity context) {
        super(context);
        this.activity = context;
        View inflate = getLayoutInflater().inflate(getResoureId(), (ViewGroup) null);
        this.inflate = inflate;
        setView(inflate);
        this.materialButton = (MaterialButton) this.inflate.findViewById(R.id.load_photo);
        this.progressBar = (ProgressBar) this.inflate.findViewById(R.id.progressBar);
        this.verificationText = (TextView) this.inflate.findViewById(R.id.verificationText);
        this.person_photo = (ImageView) this.inflate.findViewById(R.id.person_photo);
        FloatingActionButton floatingActionButton = (FloatingActionButton) this.inflate.findViewById(R.id.fab);
        this.floatingActionButton = floatingActionButton;
        floatingActionButton.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.IdCardDelegate$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IdCardDelegate.this.m174x6c4e07a(view);
            }
        });
        Utils.sendAnalytic(context, "id_view", new Pair("who", AppDataManager.getInstance().getNuwmUser().getWho()));
        if (AppDataManager.getInstance().isStudent()) {
            updateDesign();
        }
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.IdCardDelegate$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                IdCardDelegate.this.m175xfa5464bb(dialogInterface);
            }
        });
        initData();
        if (AppDataManager.getInstance().getPersonNuwm().isVerified()) {
            return;
        }
        this.verificationText.setText("Перевірка....");
        OldAPIRepository oldAPIRepository = new OldAPIRepository();
        this.apiRepository = oldAPIRepository;
        oldAPIRepository.call(APIMethod.getVerified()).async(new APIOldObjectListener<VerifiedResponse>(VerifiedResponse.class) { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.IdCardDelegate.1
            @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            protected void onError(ErrorResponse response) {
                if (response.getCode() == -8) {
                    Toast.makeText(IdCardDelegate.this.getContext(), "Для використання ID картики офлайн спочатку верифікуйте її!", 1).show();
                } else {
                    Toast.makeText(IdCardDelegate.this.getContext(), "Не вдалося перевірити вашу ID картку, зверніться в   ", 1).show();
                }
                IdCardDelegate.this.dismiss();
            }

            @Override // com.m_myr.nuwm.nuwmschedule.data.repositories.APIOldObjectListener
            public void onSuccessData(VerifiedResponse data) {
                IdCardDelegate.this.update(data);
            }
        });
    }

    /* renamed from: lambda$new$0$com-m_myr-nuwm-nuwmschedule-ui-activities-main-IdCardDelegate, reason: not valid java name */
    /* synthetic */ void m174x6c4e07a(View view) {
        dismiss();
    }

    /* renamed from: lambda$new$1$com-m_myr-nuwm-nuwmschedule-ui-activities-main-IdCardDelegate, reason: not valid java name */
    /* synthetic */ void m175xfa5464bb(DialogInterface dialogInterface) {
        dismiss();
    }

    private int getResoureId() {
        float f = r0.heightPixels / getContext().getResources().getDisplayMetrics().density;
        Log.e("dpHeight", String.valueOf(f));
        return f < 300.0f ? R.layout.id_card_layout_new_0_299 : f < 420.0f ? R.layout.id_card_layout_300_420 : f < 500.0f ? R.layout.id_card_layout_420_500 : R.layout.id_card_layout_new;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update(VerifiedResponse data) {
        if (data.isVerified()) {
            AppDataManager.getInstance().updateUserState(data).apply();
        } else if (!Utils.StringUtils.isBlank(data.getPicture())) {
            AppDataManager.getInstance().updateUserState(data).apply();
        }
        initData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initData() {
        final ImageView imageView = (ImageView) this.inflate.findViewById(R.id.code);
        TextView textView = (TextView) this.inflate.findViewById(R.id.post);
        TextView textView2 = (TextView) this.inflate.findViewById(R.id.name);
        TextView textView3 = (TextView) this.inflate.findViewById(R.id.department);
        TextView textView4 = (TextView) this.inflate.findViewById(R.id.codetext);
        final PersonNuwm personNuwm = AppDataManager.getInstance().getPersonNuwm();
        textView2.setText(personNuwm.getFullName());
        Glide.with(getContext()).load(personNuwm.getIdCardImage()).into(this.person_photo);
        textView4.setText(personNuwm.getCode());
        textView.setText(personNuwm.getWho());
        if (AppDataManager.getInstance().isEmployee()) {
            textView3.setText(AppDataManager.getInstance().getEmployee().getDepartmentName());
        } else {
            textView3.setText(AppDataManager.getInstance().getStudent().getFac_name().trim());
        }
        if (personNuwm.isVerified()) {
            this.progressBar.setVisibility(8);
            this.verificationText.setVisibility(4);
            imageView.setAlpha(1.0f);
            this.person_photo.setAlpha(1.0f);
        } else {
            this.progressBar.setVisibility(0);
            imageView.setAlpha(0.16f);
            this.person_photo.setAlpha(0.49f);
            this.verificationText.setVisibility(0);
            this.verificationText.setText("Очікується верифікація...");
            this.progressBar.setVisibility(4);
            this.materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.IdCardDelegate.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    CropImage.activity().setAspectRatio(1, 1).setRequestedSize(300, 300).setOutputCompressQuality(80).start(IdCardDelegate.this.activity);
                }
            });
            this.materialButton.setVisibility(0);
            if (personNuwm.getIdCardImage() == null) {
                this.materialButton.setText("Завантажити фото");
            } else {
                this.materialButton.setText("Оновити фотографію");
            }
        }
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.IdCardDelegate.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                try {
                    Gs1_128 gs1_128 = new Gs1_128(personNuwm.getCode());
                    ImageView imageView2 = imageView;
                    imageView2.setImageBitmap(gs1_128.ToBitMap(imageView2.getHeight(), IdCardDelegate.this.getContext().getResources().getDimensionPixelSize(R.dimen.code128)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.IdCardDelegate.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CodeViewActivity.showGS128(IdCardDelegate.this.activity, personNuwm.getCode(), ((TextView) IdCardDelegate.this.inflate.findViewById(R.id.textView3)).getText().toString(), personNuwm.getFullName());
            }
        });
    }

    private void updateDesign() {
        int color = getContext().getResources().getColor(R.color.studentColor);
        ViewCompat.setBackgroundTintList(this.inflate.findViewById(R.id.imageView2), ColorStateList.valueOf(color));
        ((TextView) this.inflate.findViewById(R.id.textView2)).setTextColor(color);
        ((TextView) this.inflate.findViewById(R.id.textView3)).setText("Студент");
        this.inflate.findViewById(R.id.background_photo).setBackgroundResource(R.color.studentColor);
        this.inflate.findViewById(R.id.background_bottom).setBackgroundResource(R.color.studentColor);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        OldAPIRepository oldAPIRepository = this.apiRepository;
        if (oldAPIRepository != null) {
            oldAPIRepository.unsubscribe();
        }
        this.activity.setRequestedOrientation(2);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        this.activity.setRequestedOrientation(1);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -1;
        getWindow().setAttributes(layoutParams);
        getWindow().setBackgroundDrawable(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
    }

    public void uploadImage(final Uri uri) {
        this.progressBar.setVisibility(0);
        Executors.newSingleThreadExecutor().execute(new UploadRunnable(getContext(), uri, new UploadRunnable.ProgressListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.main.IdCardDelegate.5
            @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
            public void progressUpload(long num, float p) {
                IdCardDelegate.this.progressBar.setProgress((int) p);
                Log.e("UploadRunnable", String.format("long %s, float %s", Long.valueOf(num), Float.valueOf(p)));
            }

            @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
            public void failUpload(String error) {
                Log.e("UploadRunnable", "error " + error);
                Toast.makeText(IdCardDelegate.this.getContext(), error, 0).show();
                IdCardDelegate.this.dismiss();
            }

            @Override // com.m_myr.nuwm.nuwmschedule.utils.UploadRunnable.ProgressListener
            public void finishUpload(UploadResponse result) {
                Log.e("UploadRunnable", "finishUpload " + result.getUrl());
                AppDataManager.getInstance().getPersonNuwm().setIdCardImage(result.getUrl() + "?time=" + System.currentTimeMillis());
                AppDataManager.getInstance().apply();
                IdCardDelegate.this.initData();
            }
        }, APIMethod.Patch.getPatchLibImageUpload()));
    }
}

package com.m_myr.nuwm.nuwmschedule.ui.activities.qrScaner;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.ui.activities.BaseToolbarActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QrCodeScannerActivity.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J-\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00042\u000e\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\fH\u0002J\u0018\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/m_myr/nuwm/nuwmschedule/ui/activities/qrScaner/QrCodeScannerActivity;", "Lcom/m_myr/nuwm/nuwmschedule/ui/activities/BaseToolbarActivity;", "()V", "PERMISSION_REQUEST_CODE", "", "barcodeLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Lcom/journeyapps/barcodescanner/ScanOptions;", "kotlin.jvm.PlatformType", "checkPermission", "", "initCamera", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "requestCode", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "requestPermission", "showMessageOKCancel", "message", "okListener", "Landroid/content/DialogInterface$OnClickListener;", "app_publicReleaseRelease"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class QrCodeScannerActivity extends BaseToolbarActivity {
    private final int PERMISSION_REQUEST_CODE = 200;
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher;

    public QrCodeScannerActivity() {
        ActivityResultLauncher<ScanOptions> registerForActivityResult = registerForActivityResult(new ScanContract(), new ActivityResultCallback() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.qrScaner.QrCodeScannerActivity$$ExternalSyntheticLambda1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                QrCodeScannerActivity.barcodeLauncher$lambda$0(QrCodeScannerActivity.this, (ScanIntentResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.barcodeLauncher = registerForActivityResult;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode_scanner_activity);
        if (checkPermission()) {
            initCamera();
        } else {
            requestPermission();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void barcodeLauncher$lambda$0(QrCodeScannerActivity this$0, ScanIntentResult result) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(result, "result");
        this$0.finish();
    }

    private final void initCamera() {
        ScanOptions scanOptions = new ScanOptions();
        scanOptions.setDesiredBarcodeFormats("CODE_128", "QR_CODE");
        scanOptions.setPrompt("Відскануйте код");
        scanOptions.setBeepEnabled(true);
        scanOptions.setOrientationLocked(false);
        this.barcodeLauncher.launch(scanOptions);
    }

    private final boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0;
    }

    private final void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA"}, this.PERMISSION_REQUEST_CODE);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == this.PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == 0) {
                initCamera();
            } else {
                if (Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
                    return;
                }
                showMessageOKCancel("Необхідно надати доступ до камери!", new DialogInterface.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.qrScaner.QrCodeScannerActivity$$ExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        QrCodeScannerActivity.onRequestPermissionsResult$lambda$1(QrCodeScannerActivity.this, dialogInterface, i);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onRequestPermissionsResult$lambda$1(QrCodeScannerActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Build.VERSION.SDK_INT >= 23) {
            this$0.requestPermission();
        }
    }

    private final void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new MaterialAlertDialogBuilder(this).setMessage((CharSequence) message).setPositiveButton(android.R.string.ok, okListener).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).create().show();
    }
}

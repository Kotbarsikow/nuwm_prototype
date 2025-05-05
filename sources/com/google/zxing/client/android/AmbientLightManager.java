package com.google.zxing.client.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.journeyapps.barcodescanner.camera.CameraManager;
import com.journeyapps.barcodescanner.camera.CameraSettings;

/* loaded from: classes2.dex */
public final class AmbientLightManager implements SensorEventListener {
    private static final float BRIGHT_ENOUGH_LUX = 450.0f;
    private static final float TOO_DARK_LUX = 45.0f;
    private CameraManager cameraManager;
    private CameraSettings cameraSettings;
    private Context context;
    private Handler handler = new Handler();
    private Sensor lightSensor;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public AmbientLightManager(Context context, CameraManager cameraManager, CameraSettings cameraSettings) {
        this.context = context;
        this.cameraManager = cameraManager;
        this.cameraSettings = cameraSettings;
    }

    public void start() {
        if (this.cameraSettings.isAutoTorchEnabled()) {
            SensorManager sensorManager = (SensorManager) this.context.getSystemService("sensor");
            Sensor defaultSensor = sensorManager.getDefaultSensor(5);
            this.lightSensor = defaultSensor;
            if (defaultSensor != null) {
                sensorManager.registerListener(this, defaultSensor, 3);
            }
        }
    }

    public void stop() {
        if (this.lightSensor != null) {
            ((SensorManager) this.context.getSystemService("sensor")).unregisterListener(this);
            this.lightSensor = null;
        }
    }

    private void setTorch(boolean z) {
        this.handler.post(new Runnable() { // from class: com.google.zxing.client.android.AmbientLightManager$$ExternalSyntheticLambda0
            public final /* synthetic */ boolean f$1;

            public /* synthetic */ AmbientLightManager$$ExternalSyntheticLambda0(boolean z2) {
                r2 = z2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AmbientLightManager.this.m132x2388a1df(r2);
            }
        });
    }

    /* renamed from: lambda$setTorch$0$com-google-zxing-client-android-AmbientLightManager */
    /* synthetic */ void m132x2388a1df(boolean z) {
        this.cameraManager.setTorch(z);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        if (this.cameraManager != null) {
            if (f <= TOO_DARK_LUX) {
                setTorch(true);
            } else if (f >= BRIGHT_ENOUGH_LUX) {
                setTorch(false);
            }
        }
    }
}

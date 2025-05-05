package com.m_myr.nuwm.nuwmschedule.domain;

import android.content.Intent;
import android.os.Process;
import com.google.firebase.messaging.Constants;
import com.m_myr.nuwm.nuwmschedule.utils.CrashScreen;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;

/* loaded from: classes2.dex */
public class TopExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread t, Throwable exception) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        Intent intent = new Intent(App.getInstance(), (Class<?>) CrashScreen.class);
        intent.putExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR, "************ CAUSE OF ERROR ************\n\n" + stringWriter.toString());
        intent.addFlags(268435456);
        intent.addFlags(268468224);
        App.getInstance().startActivity(intent);
        Process.killProcess(Process.myPid());
    }
}

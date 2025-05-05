package j$.util;

import java.security.AccessController;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class Tripwire {
    static final boolean ENABLED = ((Boolean) AccessController.doPrivileged(new Tripwire$$ExternalSyntheticLambda0())).booleanValue();

    static void trip(Class cls, String str) {
        throw new UnsupportedOperationException(cls + " tripwire tripped but logging not supported: " + str);
    }
}

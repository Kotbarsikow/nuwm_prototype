package javax.servlet.http;

import java.util.Enumeration;

/* loaded from: classes2.dex */
public interface HttpSessionContext {
    Enumeration getIds();

    HttpSession getSession(String str);
}

package javax.servlet;

import java.util.EventListener;

/* loaded from: classes2.dex */
public interface ServletRequestAttributeListener extends EventListener {
    void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent);

    void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent);

    void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent);
}

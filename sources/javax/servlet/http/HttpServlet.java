package javax.servlet.http;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.mortbay.jetty.MimeTypes;

/* loaded from: classes2.dex */
public abstract class HttpServlet extends GenericServlet implements Serializable {
    private static final String HEADER_IFMODSINCE = "If-Modified-Since";
    private static final String HEADER_LASTMOD = "Last-Modified";
    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_HEAD = "HEAD";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_TRACE = "TRACE";
    static /* synthetic */ Class class$javax$servlet$http$HttpServlet;
    private static final String LSTRING_FILE = "javax.servlet.http.LocalStrings";
    private static ResourceBundle lStrings = ResourceBundle.getBundle(LSTRING_FILE);

    protected long getLastModified(HttpServletRequest httpServletRequest) {
        return -1L;
    }

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String protocol = httpServletRequest.getProtocol();
        String string = lStrings.getString("http.method_get_not_supported");
        if (protocol.endsWith("1.1")) {
            httpServletResponse.sendError(405, string);
        } else {
            httpServletResponse.sendError(400, string);
        }
    }

    protected void doHead(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        NoBodyResponse noBodyResponse = new NoBodyResponse(httpServletResponse);
        doGet(httpServletRequest, noBodyResponse);
        noBodyResponse.setContentLength();
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String protocol = httpServletRequest.getProtocol();
        String string = lStrings.getString("http.method_post_not_supported");
        if (protocol.endsWith("1.1")) {
            httpServletResponse.sendError(405, string);
        } else {
            httpServletResponse.sendError(400, string);
        }
    }

    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String protocol = httpServletRequest.getProtocol();
        String string = lStrings.getString("http.method_put_not_supported");
        if (protocol.endsWith("1.1")) {
            httpServletResponse.sendError(405, string);
        } else {
            httpServletResponse.sendError(400, string);
        }
    }

    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String protocol = httpServletRequest.getProtocol();
        String string = lStrings.getString("http.method_delete_not_supported");
        if (protocol.endsWith("1.1")) {
            httpServletResponse.sendError(405, string);
        } else {
            httpServletResponse.sendError(400, string);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    private Method[] getAllDeclaredMethods(Class cls) {
        Class cls2 = class$javax$servlet$http$HttpServlet;
        if (cls2 == null) {
            cls2 = class$("javax.servlet.http.HttpServlet");
            class$javax$servlet$http$HttpServlet = cls2;
        }
        if (cls.equals(cls2)) {
            return null;
        }
        Method[] allDeclaredMethods = getAllDeclaredMethods(cls.getSuperclass());
        Method[] declaredMethods = cls.getDeclaredMethods();
        if (allDeclaredMethods == null || allDeclaredMethods.length <= 0) {
            return declaredMethods;
        }
        Method[] methodArr = new Method[allDeclaredMethods.length + declaredMethods.length];
        System.arraycopy(allDeclaredMethods, 0, methodArr, 0, allDeclaredMethods.length);
        System.arraycopy(declaredMethods, 0, methodArr, allDeclaredMethods.length, declaredMethods.length);
        return methodArr;
    }

    protected void doOptions(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String stringBuffer;
        String stringBuffer2;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        for (Method method : getAllDeclaredMethods(getClass())) {
            if (method.getName().equals("doGet")) {
                z = true;
                z2 = true;
            }
            if (method.getName().equals("doPost")) {
                z3 = true;
            }
            if (method.getName().equals("doPut")) {
                z4 = true;
            }
            if (method.getName().equals("doDelete")) {
                z5 = true;
            }
        }
        String str = z ? "GET" : null;
        if (z2) {
            if (str == null) {
                str = "HEAD";
            } else {
                str = new StringBuffer().append(str).append(", HEAD").toString();
            }
        }
        if (z3) {
            if (str == null) {
                str = "POST";
            } else {
                str = new StringBuffer().append(str).append(", POST").toString();
            }
        }
        if (z4) {
            if (str == null) {
                str = "PUT";
            } else {
                str = new StringBuffer().append(str).append(", PUT").toString();
            }
        }
        if (z5) {
            if (str == null) {
                str = "DELETE";
            } else {
                str = new StringBuffer().append(str).append(", DELETE").toString();
            }
        }
        if (str == null) {
            stringBuffer = "TRACE";
        } else {
            stringBuffer = new StringBuffer().append(str).append(", TRACE").toString();
        }
        if (stringBuffer == null) {
            stringBuffer2 = "OPTIONS";
        } else {
            stringBuffer2 = new StringBuffer().append(stringBuffer).append(", OPTIONS").toString();
        }
        httpServletResponse.setHeader("Allow", stringBuffer2);
    }

    protected void doTrace(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String stringBuffer = new StringBuffer("TRACE ").append(httpServletRequest.getRequestURI()).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(httpServletRequest.getProtocol()).toString();
        Enumeration headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String str = (String) headerNames.nextElement();
            stringBuffer = new StringBuffer().append(stringBuffer).append("\r\n").append(str).append(": ").append(httpServletRequest.getHeader(str)).toString();
        }
        String stringBuffer2 = new StringBuffer().append(stringBuffer).append("\r\n").toString();
        int length = stringBuffer2.length();
        httpServletResponse.setContentType(MimeTypes.MESSAGE_HTTP);
        httpServletResponse.setContentLength(length);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.print(stringBuffer2);
        outputStream.close();
    }

    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String method = httpServletRequest.getMethod();
        if (method.equals("GET")) {
            long lastModified = getLastModified(httpServletRequest);
            if (lastModified == -1) {
                doGet(httpServletRequest, httpServletResponse);
                return;
            } else if (httpServletRequest.getDateHeader("If-Modified-Since") < (lastModified / 1000) * 1000) {
                maybeSetLastModified(httpServletResponse, lastModified);
                doGet(httpServletRequest, httpServletResponse);
                return;
            } else {
                httpServletResponse.setStatus(304);
                return;
            }
        }
        if (method.equals("HEAD")) {
            maybeSetLastModified(httpServletResponse, getLastModified(httpServletRequest));
            doHead(httpServletRequest, httpServletResponse);
            return;
        }
        if (method.equals("POST")) {
            doPost(httpServletRequest, httpServletResponse);
            return;
        }
        if (method.equals("PUT")) {
            doPut(httpServletRequest, httpServletResponse);
            return;
        }
        if (method.equals("DELETE")) {
            doDelete(httpServletRequest, httpServletResponse);
            return;
        }
        if (method.equals("OPTIONS")) {
            doOptions(httpServletRequest, httpServletResponse);
        } else if (method.equals("TRACE")) {
            doTrace(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendError(501, MessageFormat.format(lStrings.getString("http.method_not_implemented"), method));
        }
    }

    private void maybeSetLastModified(HttpServletResponse httpServletResponse, long j) {
        if (!httpServletResponse.containsHeader("Last-Modified") && j >= 0) {
            httpServletResponse.setDateHeader("Last-Modified", j);
        }
    }

    @Override // javax.servlet.GenericServlet, javax.servlet.Servlet
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try {
            service((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
        } catch (ClassCastException unused) {
            throw new ServletException("non-HTTP request or response");
        }
    }
}

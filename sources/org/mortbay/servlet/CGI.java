package org.mortbay.servlet;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.log.Log;
import org.mortbay.util.IO;
import org.mortbay.util.StringUtil;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class CGI extends HttpServlet {
    private String _cmdPrefix;
    private File _docRoot;
    private EnvList _env;
    private boolean _ignoreExitState;
    private boolean _ok;
    private String _path;

    @Override // javax.servlet.GenericServlet
    public void init() throws ServletException {
        String property;
        this._env = new EnvList();
        this._cmdPrefix = getInitParameter("commandPrefix");
        String initParameter = getInitParameter("cgibinResourceBase");
        if (initParameter == null && (initParameter = getInitParameter("resourceBase")) == null) {
            initParameter = getServletContext().getRealPath(URIUtil.SLASH);
        }
        if (initParameter == null) {
            Log.warn("CGI: no CGI bin !");
            return;
        }
        File file = new File(initParameter);
        if (!file.exists()) {
            Log.warn(new StringBuffer("CGI: CGI bin does not exist - ").append(file).toString());
            return;
        }
        if (!file.canRead()) {
            Log.warn(new StringBuffer("CGI: CGI bin is not readable - ").append(file).toString());
            return;
        }
        if (!file.isDirectory()) {
            Log.warn(new StringBuffer("CGI: CGI bin is not a directory - ").append(file).toString());
            return;
        }
        try {
            this._docRoot = file.getCanonicalFile();
            String initParameter2 = getInitParameter("Path");
            this._path = initParameter2;
            if (initParameter2 != null) {
                this._env.set("PATH", initParameter2);
            }
            this._ignoreExitState = "true".equalsIgnoreCase(getInitParameter("ignoreExitState"));
            Enumeration initParameterNames = getInitParameterNames();
            while (initParameterNames.hasMoreElements()) {
                String str = (String) initParameterNames.nextElement();
                if (str != null && str.startsWith("ENV_")) {
                    this._env.set(str.substring(4), getInitParameter(str));
                }
            }
            if (!this._env.envMap.containsKey("SystemRoot") && (property = System.getProperty("os.name")) != null && property.toLowerCase().indexOf("windows") != -1) {
                String property2 = System.getProperty("windir");
                EnvList envList = this._env;
                if (property2 == null) {
                    property2 = "C:\\WINDOWS";
                }
                envList.set("SystemRoot", property2);
            }
            this._ok = true;
        } catch (IOException e) {
            Log.warn(new StringBuffer("CGI: CGI bin failed - ").append(file).toString(), (Throwable) e);
        }
    }

    @Override // javax.servlet.http.HttpServlet
    public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (!this._ok) {
            httpServletResponse.sendError(503);
            return;
        }
        String stringBuffer = new StringBuffer().append(StringUtil.nonNull(httpServletRequest.getServletPath())).append(StringUtil.nonNull(httpServletRequest.getPathInfo())).toString();
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("CGI: ContextPath : ").append(httpServletRequest.getContextPath()).toString());
            Log.debug(new StringBuffer("CGI: ServletPath : ").append(httpServletRequest.getServletPath()).toString());
            Log.debug(new StringBuffer("CGI: PathInfo    : ").append(httpServletRequest.getPathInfo()).toString());
            Log.debug(new StringBuffer("CGI: _docRoot    : ").append(this._docRoot).toString());
            Log.debug(new StringBuffer("CGI: _path       : ").append(this._path).toString());
            Log.debug(new StringBuffer("CGI: _ignoreExitState: ").append(this._ignoreExitState).toString());
        }
        File file = new File(this._docRoot, stringBuffer);
        String str = "";
        String str2 = stringBuffer;
        while (true) {
            if ((str2.endsWith(URIUtil.SLASH) || !file.exists()) && str2.length() >= 0) {
                int lastIndexOf = str2.lastIndexOf(47);
                str2 = str2.substring(0, lastIndexOf);
                str = stringBuffer.substring(lastIndexOf, stringBuffer.length());
                file = new File(this._docRoot, str2);
            }
        }
        if (str2.length() == 0 || !file.exists() || file.isDirectory() || !file.getCanonicalPath().equals(file.getAbsolutePath())) {
            httpServletResponse.sendError(404);
            return;
        }
        if (Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("CGI: script is ").append(file).toString());
            Log.debug(new StringBuffer("CGI: pathInfo is ").append(str).toString());
        }
        exec(file, str, httpServletRequest, httpServletResponse);
    }

    private void exec(File file, String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        int exitValue;
        int indexOf;
        String absolutePath = file.getAbsolutePath();
        File parentFile = file.getParentFile();
        String substring = httpServletRequest.getRequestURI().substring(0, httpServletRequest.getRequestURI().length() - str.length());
        String realPath = getServletContext().getRealPath(substring);
        String pathTranslated = httpServletRequest.getPathTranslated();
        final int contentLength = httpServletRequest.getContentLength();
        if (contentLength < 0) {
            contentLength = 0;
        }
        if (pathTranslated == null || pathTranslated.length() == 0) {
            pathTranslated = absolutePath;
        }
        EnvList envList = new EnvList(this._env);
        envList.set("AUTH_TYPE", httpServletRequest.getAuthType());
        envList.set("CONTENT_LENGTH", Integer.toString(contentLength));
        envList.set("CONTENT_TYPE", httpServletRequest.getContentType());
        envList.set("GATEWAY_INTERFACE", "CGI/1.1");
        if (str != null && str.length() > 0) {
            envList.set("PATH_INFO", str);
        }
        envList.set("PATH_TRANSLATED", pathTranslated);
        envList.set("QUERY_STRING", httpServletRequest.getQueryString());
        envList.set("REMOTE_ADDR", httpServletRequest.getRemoteAddr());
        envList.set("REMOTE_HOST", httpServletRequest.getRemoteHost());
        envList.set("REMOTE_USER", httpServletRequest.getRemoteUser());
        envList.set("REQUEST_METHOD", httpServletRequest.getMethod());
        envList.set("SCRIPT_NAME", substring);
        envList.set("SCRIPT_FILENAME", realPath);
        envList.set("SERVER_NAME", httpServletRequest.getServerName());
        envList.set("SERVER_PORT", Integer.toString(httpServletRequest.getServerPort()));
        envList.set("SERVER_PROTOCOL", httpServletRequest.getProtocol());
        envList.set("SERVER_SOFTWARE", getServletContext().getServerInfo());
        Enumeration headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String str2 = (String) headerNames.nextElement();
            envList.set(new StringBuffer("HTTP_").append(str2.toUpperCase().replace('-', '_')).toString(), httpServletRequest.getHeader(str2));
        }
        envList.set("HTTPS", httpServletRequest.isSecure() ? "ON" : "OFF");
        String stringBuffer = (absolutePath.charAt(0) == '\"' || absolutePath.indexOf(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) < 0) ? absolutePath : new StringBuffer("\"").append(absolutePath).append("\"").toString();
        if (this._cmdPrefix != null) {
            stringBuffer = new StringBuffer().append(this._cmdPrefix).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(stringBuffer).toString();
        }
        Process exec = parentFile == null ? Runtime.getRuntime().exec(stringBuffer, envList.getEnvArray()) : Runtime.getRuntime().exec(stringBuffer, envList.getEnvArray(), parentFile);
        final ServletInputStream inputStream = httpServletRequest.getInputStream();
        final OutputStream outputStream = exec.getOutputStream();
        IO.copyThread(exec.getErrorStream(), System.err);
        new Thread(new Runnable() { // from class: org.mortbay.servlet.CGI.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    int i = contentLength;
                    if (i > 0) {
                        IO.copy(inputStream, outputStream, i);
                    }
                    outputStream.close();
                } catch (IOException e) {
                    Log.ignore(e);
                }
            }
        }).start();
        ServletOutputStream servletOutputStream = null;
        try {
            try {
                InputStream inputStream2 = exec.getInputStream();
                while (true) {
                    String textLineFromStream = getTextLineFromStream(inputStream2);
                    if (textLineFromStream.length() <= 0) {
                        break;
                    }
                    if (!textLineFromStream.startsWith("HTTP") && (indexOf = textLineFromStream.indexOf(58)) > 0) {
                        String trim = textLineFromStream.substring(0, indexOf).trim();
                        String trim2 = textLineFromStream.substring(indexOf + 1).trim();
                        if ("Location".equals(trim)) {
                            httpServletResponse.sendRedirect(trim2);
                        } else if ("Status".equals(trim)) {
                            httpServletResponse.setStatus(Integer.parseInt(trim2.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)[0]));
                        } else {
                            httpServletResponse.addHeader(trim, trim2);
                        }
                    }
                }
                servletOutputStream = httpServletResponse.getOutputStream();
                IO.copy(inputStream2, servletOutputStream);
                exec.waitFor();
                if (!this._ignoreExitState && (exitValue = exec.exitValue()) != 0) {
                    Log.warn(new StringBuffer().append("Non-zero exit status (").append(exitValue).append(") from CGI program: ").append(absolutePath).toString());
                    if (!httpServletResponse.isCommitted()) {
                        httpServletResponse.sendError(500, "Failed to exec CGI");
                    }
                }
                if (servletOutputStream != null) {
                    try {
                        servletOutputStream.close();
                    } catch (Exception e) {
                        e = e;
                        Log.ignore(e);
                        exec.destroy();
                    }
                }
            } catch (IOException unused) {
                Log.debug("CGI: Client closed connection!");
                if (servletOutputStream != null) {
                    try {
                        servletOutputStream.close();
                    } catch (Exception e2) {
                        e = e2;
                        Log.ignore(e);
                        exec.destroy();
                    }
                }
            } catch (InterruptedException unused2) {
                Log.debug("CGI: interrupted!");
                if (servletOutputStream != null) {
                    try {
                        servletOutputStream.close();
                    } catch (Exception e3) {
                        e = e3;
                        Log.ignore(e);
                        exec.destroy();
                    }
                }
            }
            exec.destroy();
        } catch (Throwable th) {
            if (servletOutputStream != null) {
                try {
                    servletOutputStream.close();
                } catch (Exception e4) {
                    Log.ignore(e4);
                }
            }
            exec.destroy();
            throw th;
        }
    }

    private String getTextLineFromStream(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            int read = inputStream.read();
            if (read == -1 || read == 10) {
                break;
            }
            stringBuffer.append((char) read);
        }
        return stringBuffer.toString().trim();
    }

    private static class EnvList {
        private Map envMap;

        EnvList() {
            this.envMap = new HashMap();
        }

        EnvList(EnvList envList) {
            this.envMap = new HashMap(envList.envMap);
        }

        public void set(String str, String str2) {
            this.envMap.put(str, new StringBuffer().append(str).append("=").append(StringUtil.nonNull(str2)).toString());
        }

        public String[] getEnvArray() {
            return (String[]) this.envMap.values().toArray(new String[this.envMap.size()]);
        }

        public String toString() {
            return this.envMap.toString();
        }
    }
}

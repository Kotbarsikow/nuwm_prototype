package org.mortbay.jetty;

import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.log.Log;
import org.mortbay.util.URIUtil;

/* loaded from: classes3.dex */
public class Main {
    public static void main(String[] strArr) {
        if (strArr.length < 1 || strArr.length > 3) {
            System.err.println("Usage - java org.mortbay.jetty.Main [<addr>:]<port>");
            System.err.println("Usage - java org.mortbay.jetty.Main [<addr>:]<port> docroot");
            System.err.println("Usage - java org.mortbay.jetty.Main [<addr>:]<port> -webapp myapp.war");
            System.err.println("Usage - java org.mortbay.jetty.Main [<addr>:]<port> -webapps webapps");
            System.err.println("Usage - java -jar jetty-x.x.x-standalone.jar [<addr>:]<port>");
            System.err.println("Usage - java -jar jetty-x.x.x-standalone.jar [<addr>:]<port> docroot");
            System.err.println("Usage - java -jar jetty-x.x.x-standalone.jar [<addr>:]<port> -webapp myapp.war");
            System.err.println("Usage - java -jar jetty-x.x.x-standalone.jar [<addr>:]<port> -webapps webapps");
            System.exit(1);
        }
        try {
            Server server = new Server();
            ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
            server.setHandler(contextHandlerCollection);
            Connector socketConnector = new SocketConnector();
            String str = strArr[0];
            int lastIndexOf = str.lastIndexOf(58);
            if (lastIndexOf < 0) {
                socketConnector.setPort(Integer.parseInt(str));
            } else {
                socketConnector.setHost(str.substring(0, lastIndexOf));
                socketConnector.setPort(Integer.parseInt(str.substring(lastIndexOf + 1)));
            }
            server.setConnectors(new Connector[]{socketConnector});
            if (strArr.length < 3) {
                ContextHandler contextHandler = new ContextHandler();
                contextHandler.setContextPath(URIUtil.SLASH);
                contextHandler.setResourceBase(strArr.length == 1 ? "." : strArr[1]);
                ServletHandler servletHandler = new ServletHandler();
                servletHandler.addServletWithMapping("org.mortbay.jetty.servlet.DefaultServlet", URIUtil.SLASH);
                contextHandler.setHandler(servletHandler);
                contextHandlerCollection.addHandler(contextHandler);
            } else if ("-webapps".equals(strArr[1])) {
                WebAppContext.addWebApplications(server, strArr[2], WebAppContext.WEB_DEFAULTS_XML, true, true);
            } else if ("-webapp".equals(strArr[1])) {
                WebAppContext webAppContext = new WebAppContext();
                webAppContext.setWar(strArr[2]);
                webAppContext.setContextPath(URIUtil.SLASH);
                contextHandlerCollection.addHandler(webAppContext);
            }
            server.start();
        } catch (Exception e) {
            Log.warn(Log.EXCEPTION, (Throwable) e);
        }
    }
}

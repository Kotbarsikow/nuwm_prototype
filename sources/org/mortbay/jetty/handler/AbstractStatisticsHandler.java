package org.mortbay.jetty.handler;

/* loaded from: classes3.dex */
public abstract class AbstractStatisticsHandler extends HandlerWrapper {
    public abstract long getRequestTimeAverage();

    public abstract long getRequestTimeMax();

    public abstract long getRequestTimeMin();

    public abstract long getRequestTimeTotal();

    public abstract int getRequests();

    public abstract int getRequestsActive();

    public abstract int getRequestsActiveMax();

    public abstract int getResponses1xx();

    public abstract int getResponses2xx();

    public abstract int getResponses3xx();

    public abstract int getResponses4xx();

    public abstract int getResponses5xx();

    public abstract long getStatsOnMs();

    public abstract void statsReset();

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.handler.AbstractHandler, org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        super.doStart();
        statsReset();
    }
}

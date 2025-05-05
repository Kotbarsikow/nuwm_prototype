package org.mortbay.jetty.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Response;

/* loaded from: classes3.dex */
public class StatisticsHandler extends AbstractStatisticsHandler {
    private transient long _maxRequestTime;
    private transient long _minRequestTime;
    private transient int _requests;
    private transient int _requestsActive;
    private transient int _requestsActiveMax;
    private transient int _responses1xx;
    private transient int _responses2xx;
    private transient int _responses3xx;
    private transient int _responses4xx;
    private transient int _responses5xx;
    private transient long _statsStartedAt;
    private transient long _totalRequestTime;

    @Override // org.mortbay.jetty.handler.HandlerWrapper, org.mortbay.jetty.Handler
    public void handle(String str, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
        synchronized (this) {
            this._requests++;
            int i2 = this._requestsActive + 1;
            this._requestsActive = i2;
            if (i2 > this._requestsActiveMax) {
                this._requestsActiveMax = i2;
            }
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            super.handle(str, httpServletRequest, httpServletResponse, i);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            synchronized (this) {
                int i3 = this._requestsActive - 1;
                this._requestsActive = i3;
                if (i3 < 0) {
                    this._requestsActive = 0;
                }
                this._totalRequestTime += currentTimeMillis2;
                long j = this._minRequestTime;
                if (currentTimeMillis2 < j || j == 0) {
                    this._minRequestTime = currentTimeMillis2;
                }
                if (currentTimeMillis2 > this._maxRequestTime) {
                    this._maxRequestTime = currentTimeMillis2;
                }
                int status = (httpServletResponse instanceof Response ? (Response) httpServletResponse : HttpConnection.getCurrentConnection().getResponse()).getStatus() / 100;
                if (status == 1) {
                    this._responses1xx++;
                } else if (status == 2) {
                    this._responses2xx++;
                } else if (status == 3) {
                    this._responses3xx++;
                } else if (status == 4) {
                    this._responses4xx++;
                } else if (status == 5) {
                    this._responses5xx++;
                }
            }
        } catch (Throwable th) {
            long currentTimeMillis3 = System.currentTimeMillis() - currentTimeMillis;
            synchronized (this) {
                this._requestsActive--;
                if (this._requestsActive < 0) {
                    this._requestsActive = 0;
                }
                this._totalRequestTime += currentTimeMillis3;
                long j2 = this._minRequestTime;
                if (currentTimeMillis3 < j2 || j2 == 0) {
                    this._minRequestTime = currentTimeMillis3;
                }
                if (currentTimeMillis3 > this._maxRequestTime) {
                    this._maxRequestTime = currentTimeMillis3;
                }
                int status2 = (httpServletResponse instanceof Response ? (Response) httpServletResponse : HttpConnection.getCurrentConnection().getResponse()).getStatus() / 100;
                if (status2 == 1) {
                    this._responses1xx++;
                } else if (status2 == 2) {
                    this._responses2xx++;
                } else if (status2 == 3) {
                    this._responses3xx++;
                } else if (status2 == 4) {
                    this._responses4xx++;
                } else if (status2 == 5) {
                    this._responses5xx++;
                }
                throw th;
            }
        }
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public void statsReset() {
        synchronized (this) {
            this._statsStartedAt = System.currentTimeMillis();
            this._requests = 0;
            this._minRequestTime = 0L;
            this._maxRequestTime = 0L;
            this._totalRequestTime = 0L;
            this._requestsActiveMax = this._requestsActive;
            this._requestsActive = 0;
            this._responses1xx = 0;
            this._responses2xx = 0;
            this._responses3xx = 0;
            this._responses4xx = 0;
            this._responses5xx = 0;
        }
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public int getRequests() {
        int i;
        synchronized (this) {
            i = this._requests;
        }
        return i;
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public int getRequestsActive() {
        int i;
        synchronized (this) {
            i = this._requestsActive;
        }
        return i;
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public int getRequestsActiveMax() {
        int i;
        synchronized (this) {
            i = this._requestsActiveMax;
        }
        return i;
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public int getResponses1xx() {
        int i;
        synchronized (this) {
            i = this._responses1xx;
        }
        return i;
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public int getResponses2xx() {
        int i;
        synchronized (this) {
            i = this._responses2xx;
        }
        return i;
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public int getResponses3xx() {
        int i;
        synchronized (this) {
            i = this._responses3xx;
        }
        return i;
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public int getResponses4xx() {
        int i;
        synchronized (this) {
            i = this._responses4xx;
        }
        return i;
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public int getResponses5xx() {
        int i;
        synchronized (this) {
            i = this._responses5xx;
        }
        return i;
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public long getStatsOnMs() {
        long currentTimeMillis;
        synchronized (this) {
            currentTimeMillis = System.currentTimeMillis() - this._statsStartedAt;
        }
        return currentTimeMillis;
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public long getRequestTimeMin() {
        long j;
        synchronized (this) {
            j = this._minRequestTime;
        }
        return j;
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public long getRequestTimeMax() {
        long j;
        synchronized (this) {
            j = this._maxRequestTime;
        }
        return j;
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public long getRequestTimeTotal() {
        long j;
        synchronized (this) {
            j = this._totalRequestTime;
        }
        return j;
    }

    @Override // org.mortbay.jetty.handler.AbstractStatisticsHandler
    public long getRequestTimeAverage() {
        long j;
        synchronized (this) {
            int i = this._requests;
            j = i == 0 ? 0L : this._totalRequestTime / i;
        }
        return j;
    }
}

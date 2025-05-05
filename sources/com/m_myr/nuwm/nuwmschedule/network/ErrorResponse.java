package com.m_myr.nuwm.nuwmschedule.network;

import android.util.Log;
import com.google.gson.JsonObject;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.UnknownHostException;

/* loaded from: classes2.dex */
public class ErrorResponse {
    public static final int ACCESS_DENIED = 60;
    public static final int DEPRECATED_METHOD = 66;
    public static final int GATEWAY_TIMEOUT = 89;
    public static final int INTERNET_DISCONNECTED = -8;
    public static final int INVALID_REQUEST = 32;
    public static final int INVALID_TOKEN = 63;
    public static final int INVALID_VERSION_SPECIFIED = 34;
    public static final int NOT_FOUND = 33;
    public static final int OK = 100;
    public static final int SERVER_NOT_RESPONSING = 90;
    public static final int TOKEN_EXPIRED = 61;
    public static final int UNKNOWN = -5;
    private int code;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(int cause, String errorMessage) {
        this.code = cause;
        this.message = errorMessage;
    }

    public ErrorResponse(int cause) {
        this(cause, getErrorMessage(cause));
    }

    public ErrorResponse(JsonObject error) {
        this(error.getAsJsonPrimitive("code").getAsInt(), getErrorMessage(error.getAsJsonPrimitive("code").getAsInt(), error.getAsJsonPrimitive("message").getAsString()));
    }

    public ErrorResponse(Throwable throwable) {
        if (throwable instanceof UnknownHostException) {
            this.code = -8;
            this.message = getErrorMessage(-8);
        } else {
            this.code = 0;
            this.message = getStackTrace(throwable);
        }
    }

    private String getStackTrace(final Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter((Writer) stringWriter, true));
        return stringWriter.getBuffer().toString();
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public static String getErrorMessage(int c, String defaultError) {
        if (c == -8) {
            return "Відсутнє з'єднання";
        }
        if (c == 34) {
            return "Додаток застарілий";
        }
        if (c == 63) {
            return "Авторизація недійсна";
        }
        if (c == 66) {
            return "Додаток застарілий";
        }
        if (c == 60) {
            return "Помилка доступу";
        }
        if (c == 61) {
            return "Термін дії авторизації минув";
        }
        if (c == 89) {
            return "Час з'єднання минув";
        }
        if (c == 90) {
            return "Сервер тимчасово недоступний";
        }
        if (defaultError != null && !Utils.StringUtils.isBlank(defaultError)) {
            return defaultError;
        }
        if (c == 32) {
            return "Хибний запит";
        }
        if (c == 33) {
            return "Не знайдено";
        }
        return "Помилка  " + c;
    }

    public static String getErrorMessage(int c) {
        return getErrorMessage(c, "Помилка доступу");
    }

    public String toString() {
        return "{ErrorResponse code: " + this.code + ", message: " + getMessage() + "}";
    }

    public void print() {
        Log.e("ErrorResponse", getMessage());
    }
}

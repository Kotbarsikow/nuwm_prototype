package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessControlException;
import java.util.Locale;

/* loaded from: classes2.dex */
class DefaultCredentialProvider extends SystemEnvironmentProvider {
    static final String APP_ENGINE_CREDENTIAL_CLASS = "com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential$AppEngineCredentialWrapper";
    static final String CLOUDSDK_CONFIG_DIRECTORY = "gcloud";
    static final String CLOUD_SHELL_ENV_VAR = "DEVSHELL_CLIENT_PORT";
    static final String CREDENTIAL_ENV_VAR = "GOOGLE_APPLICATION_CREDENTIALS";
    static final String HELP_PERMALINK = "https://developers.google.com/accounts/docs/application-default-credentials";
    static final String WELL_KNOWN_CREDENTIALS_FILE = "application_default_credentials.json";
    private GoogleCredential cachedCredential = null;
    private Environment detectedEnvironment = null;

    private enum Environment {
        UNKNOWN,
        ENVIRONMENT_VARIABLE,
        WELL_KNOWN_FILE,
        CLOUD_SHELL,
        APP_ENGINE,
        COMPUTE_ENGINE
    }

    DefaultCredentialProvider() {
    }

    final GoogleCredential getDefaultCredential(HttpTransport httpTransport, JsonFactory jsonFactory) throws IOException {
        synchronized (this) {
            if (this.cachedCredential == null) {
                this.cachedCredential = getDefaultCredentialUnsynchronized(httpTransport, jsonFactory);
            }
            GoogleCredential googleCredential = this.cachedCredential;
            if (googleCredential != null) {
                return googleCredential;
            }
            throw new IOException(String.format("The Application Default Credentials are not available. They are available if running on Google App Engine, Google Compute Engine, or Google Cloud Shell. Otherwise, the environment variable %s must be defined pointing to a file defining the credentials. See %s for more information.", CREDENTIAL_ENV_VAR, HELP_PERMALINK));
        }
    }

    private final GoogleCredential getDefaultCredentialUnsynchronized(HttpTransport httpTransport, JsonFactory jsonFactory) throws IOException {
        if (this.detectedEnvironment == null) {
            this.detectedEnvironment = detectEnvironment(httpTransport);
        }
        int i = AnonymousClass1.$SwitchMap$com$google$api$client$googleapis$auth$oauth2$DefaultCredentialProvider$Environment[this.detectedEnvironment.ordinal()];
        if (i == 1) {
            return getCredentialUsingEnvironmentVariable(httpTransport, jsonFactory);
        }
        if (i == 2) {
            return getCredentialUsingWellKnownFile(httpTransport, jsonFactory);
        }
        if (i == 3) {
            return getAppEngineCredential(httpTransport, jsonFactory);
        }
        if (i == 4) {
            return getCloudShellCredential(jsonFactory);
        }
        if (i != 5) {
            return null;
        }
        return getComputeCredential(httpTransport, jsonFactory);
    }

    /* renamed from: com.google.api.client.googleapis.auth.oauth2.DefaultCredentialProvider$1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$client$googleapis$auth$oauth2$DefaultCredentialProvider$Environment;

        static {
            int[] iArr = new int[Environment.values().length];
            $SwitchMap$com$google$api$client$googleapis$auth$oauth2$DefaultCredentialProvider$Environment = iArr;
            try {
                iArr[Environment.ENVIRONMENT_VARIABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$api$client$googleapis$auth$oauth2$DefaultCredentialProvider$Environment[Environment.WELL_KNOWN_FILE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$api$client$googleapis$auth$oauth2$DefaultCredentialProvider$Environment[Environment.APP_ENGINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$api$client$googleapis$auth$oauth2$DefaultCredentialProvider$Environment[Environment.CLOUD_SHELL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$api$client$googleapis$auth$oauth2$DefaultCredentialProvider$Environment[Environment.COMPUTE_ENGINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private final File getWellKnownCredentialsFile() {
        File file;
        if (getProperty("os.name", "").toLowerCase(Locale.US).indexOf("windows") >= 0) {
            file = new File(new File(getEnv("APPDATA")), CLOUDSDK_CONFIG_DIRECTORY);
        } else {
            file = new File(new File(getProperty("user.home", ""), ".config"), CLOUDSDK_CONFIG_DIRECTORY);
        }
        return new File(file, WELL_KNOWN_CREDENTIALS_FILE);
    }

    boolean fileExists(File file) {
        return file.exists() && !file.isDirectory();
    }

    String getProperty(String str, String str2) {
        return System.getProperty(str, str2);
    }

    Class<?> forName(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }

    private final Environment detectEnvironment(HttpTransport httpTransport) throws IOException {
        if (runningUsingEnvironmentVariable()) {
            return Environment.ENVIRONMENT_VARIABLE;
        }
        if (runningUsingWellKnownFile()) {
            return Environment.WELL_KNOWN_FILE;
        }
        if (runningOnAppEngine()) {
            return Environment.APP_ENGINE;
        }
        if (runningOnCloudShell()) {
            return Environment.CLOUD_SHELL;
        }
        if (OAuth2Utils.runningOnComputeEngine(httpTransport, this)) {
            return Environment.COMPUTE_ENGINE;
        }
        return Environment.UNKNOWN;
    }

    private boolean runningUsingEnvironmentVariable() throws IOException {
        String env = getEnv(CREDENTIAL_ENV_VAR);
        if (env != null && env.length() != 0) {
            try {
                File file = new File(env);
                if (!file.exists() || file.isDirectory()) {
                    throw new IOException(String.format("Error reading credential file from environment variable %s, value '%s': File does not exist.", CREDENTIAL_ENV_VAR, env));
                }
                return true;
            } catch (AccessControlException unused) {
            }
        }
        return false;
    }

    private GoogleCredential getCredentialUsingEnvironmentVariable(HttpTransport httpTransport, JsonFactory jsonFactory) throws IOException {
        String env = getEnv(CREDENTIAL_ENV_VAR);
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(env);
                try {
                    GoogleCredential fromStream = GoogleCredential.fromStream(fileInputStream2, httpTransport, jsonFactory);
                    fileInputStream2.close();
                    return fromStream;
                } catch (IOException e) {
                    e = e;
                    throw ((IOException) OAuth2Utils.exceptionWithCause(new IOException(String.format("Error reading credential file from environment variable %s, value '%s': %s", CREDENTIAL_ENV_VAR, env, e.getMessage())), e));
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private boolean runningUsingWellKnownFile() {
        try {
            return fileExists(getWellKnownCredentialsFile());
        } catch (AccessControlException unused) {
            return false;
        }
    }

    private GoogleCredential getCredentialUsingWellKnownFile(HttpTransport httpTransport, JsonFactory jsonFactory) throws IOException {
        File wellKnownCredentialsFile = getWellKnownCredentialsFile();
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(wellKnownCredentialsFile);
                try {
                    GoogleCredential fromStream = GoogleCredential.fromStream(fileInputStream2, httpTransport, jsonFactory);
                    fileInputStream2.close();
                    return fromStream;
                } catch (IOException e) {
                    e = e;
                    fileInputStream = fileInputStream2;
                    throw new IOException(String.format("Error reading credential file from location %s: %s", wellKnownCredentialsFile, e.getMessage()));
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    private boolean runningOnAppEngine() {
        try {
            try {
                Field field = forName("com.google.appengine.api.utils.SystemProperty").getField("environment");
                return field.getType().getMethod("value", null).invoke(field.get(null), null) != null;
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                throw ((RuntimeException) OAuth2Utils.exceptionWithCause(new RuntimeException(String.format("Unexpcted error trying to determine if runnning on Google App Engine: %s", e.getMessage())), e));
            }
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private final GoogleCredential getAppEngineCredential(HttpTransport httpTransport, JsonFactory jsonFactory) throws IOException {
        try {
            return (GoogleCredential) forName(APP_ENGINE_CREDENTIAL_CLASS).getConstructor(HttpTransport.class, JsonFactory.class).newInstance(httpTransport, jsonFactory);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw ((IOException) OAuth2Utils.exceptionWithCause(new IOException(String.format("Application Default Credentials failed to create the Google App Engine service account credentials class %s. Check that the component 'google-api-client-appengine' is deployed.", APP_ENGINE_CREDENTIAL_CLASS)), e));
        }
    }

    private boolean runningOnCloudShell() {
        return getEnv(CLOUD_SHELL_ENV_VAR) != null;
    }

    private GoogleCredential getCloudShellCredential(JsonFactory jsonFactory) {
        return new CloudShellCredential(Integer.parseInt(getEnv(CLOUD_SHELL_ENV_VAR)), jsonFactory);
    }

    private final GoogleCredential getComputeCredential(HttpTransport httpTransport, JsonFactory jsonFactory) {
        return new ComputeGoogleCredential(httpTransport, jsonFactory);
    }

    private static class ComputeGoogleCredential extends GoogleCredential {
        private static final String TOKEN_SERVER_ENCODED_URL = String.valueOf(OAuth2Utils.getMetadataServerUrl()).concat("/computeMetadata/v1/instance/service-accounts/default/token");

        ComputeGoogleCredential(HttpTransport httpTransport, JsonFactory jsonFactory) {
            super(new GoogleCredential.Builder().setTransport(httpTransport).setJsonFactory(jsonFactory).setTokenServerEncodedUrl(TOKEN_SERVER_ENCODED_URL));
        }

        @Override // com.google.api.client.googleapis.auth.oauth2.GoogleCredential, com.google.api.client.auth.oauth2.Credential
        protected TokenResponse executeRefreshToken() throws IOException {
            HttpRequest buildGetRequest = getTransport().createRequestFactory().buildGetRequest(new GenericUrl(getTokenServerEncodedUrl()));
            JsonObjectParser jsonObjectParser = new JsonObjectParser(getJsonFactory());
            buildGetRequest.setParser(jsonObjectParser);
            buildGetRequest.getHeaders().set("Metadata-Flavor", "Google");
            buildGetRequest.setThrowExceptionOnExecuteError(false);
            HttpResponse execute = buildGetRequest.execute();
            int statusCode = execute.getStatusCode();
            if (statusCode != 200) {
                if (statusCode == 404) {
                    throw new IOException(String.format("Error code %s trying to get security access token from Compute Engine metadata for the default service account. This may be because the virtual machine instance does not have permission scopes specified.", Integer.valueOf(statusCode)));
                }
                throw new IOException(String.format("Unexpected Error code %s trying to get security access token from Compute Engine metadata for the default service account: %s", Integer.valueOf(statusCode), execute.parseAsString()));
            }
            InputStream content = execute.getContent();
            if (content == null) {
                throw new IOException("Empty content from metadata token server request.");
            }
            return (TokenResponse) jsonObjectParser.parseAndClose(content, execute.getContentCharset(), TokenResponse.class);
        }
    }
}

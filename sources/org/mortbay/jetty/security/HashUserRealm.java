package org.mortbay.jetty.security;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import org.mortbay.component.AbstractLifeCycle;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Response;
import org.mortbay.log.Log;
import org.mortbay.resource.Resource;
import org.mortbay.util.Scanner;

/* loaded from: classes3.dex */
public class HashUserRealm extends AbstractLifeCycle implements UserRealm, SSORealm {
    public static final String __SSO = "org.mortbay.http.SSO";
    private String _config;
    private Resource _configResource;
    private String _realmName;
    private Scanner _scanner;
    private SSORealm _ssoRealm;
    protected HashMap _users = new HashMap();
    protected HashMap _roles = new HashMap(7);
    private int _refreshInterval = 0;

    @Override // org.mortbay.jetty.security.UserRealm
    public void disassociate(Principal principal) {
    }

    @Override // org.mortbay.jetty.security.UserRealm
    public void logout(Principal principal) {
    }

    public HashUserRealm() {
    }

    public HashUserRealm(String str) {
        this._realmName = str;
    }

    public HashUserRealm(String str, String str2) throws IOException {
        this._realmName = str;
        setConfig(str2);
    }

    public String getConfig() {
        return this._config;
    }

    public Resource getConfigResource() {
        return this._configResource;
    }

    public void setConfig(String str) throws IOException {
        this._config = str;
        this._configResource = Resource.newResource(str);
        loadConfig();
    }

    public void setRefreshInterval(int i) {
        this._refreshInterval = i;
    }

    public int getRefreshInterval() {
        return this._refreshInterval;
    }

    protected void loadConfig() throws IOException {
        Set<Map.Entry> entrySet;
        String str;
        synchronized (this) {
            this._users.clear();
            this._roles.clear();
            if (Log.isDebugEnabled()) {
                Log.debug(new StringBuffer("Load ").append(this).append(" from ").append(this._config).toString());
            }
            Properties properties = new Properties();
            properties.load(this._configResource.getInputStream());
            entrySet = properties.entrySet();
            for (Map.Entry entry : entrySet) {
                String trim = entry.getKey().toString().trim();
                String trim2 = entry.getValue().toString().trim();
                int indexOf = trim2.indexOf(44);
                if (indexOf > 0) {
                    str = trim2.substring(indexOf + 1).trim();
                    trim2 = trim2.substring(0, indexOf).trim();
                } else {
                    str = null;
                }
                if (trim != null && trim.length() > 0 && trim2 != null && trim2.length() > 0) {
                    put(trim, trim2);
                    if (str != null && str.length() > 0) {
                        StringTokenizer stringTokenizer = new StringTokenizer(str, ", ");
                        while (stringTokenizer.hasMoreTokens()) {
                            addUserToRole(trim, stringTokenizer.nextToken());
                        }
                    }
                }
            }
        }
    }

    public void setName(String str) {
        this._realmName = str;
    }

    @Override // org.mortbay.jetty.security.UserRealm
    public String getName() {
        return this._realmName;
    }

    @Override // org.mortbay.jetty.security.UserRealm
    public Principal getPrincipal(String str) {
        return (Principal) this._users.get(str);
    }

    @Override // org.mortbay.jetty.security.UserRealm
    public Principal authenticate(String str, Object obj, Request request) {
        KnownUser knownUser;
        synchronized (this) {
            knownUser = (KnownUser) this._users.get(str);
        }
        if (knownUser != null && knownUser.authenticate(obj)) {
            return knownUser;
        }
        return null;
    }

    @Override // org.mortbay.jetty.security.UserRealm
    public Principal pushRole(Principal principal, String str) {
        if (principal == null) {
            principal = new User();
        }
        return new WrappedUser(principal, str);
    }

    @Override // org.mortbay.jetty.security.UserRealm
    public Principal popRole(Principal principal) {
        return ((WrappedUser) principal).getUserPrincipal();
    }

    public synchronized Object put(Object obj, Object obj2) {
        if (obj2 instanceof Principal) {
            return this._users.put(obj.toString(), obj2);
        }
        if (obj2 instanceof Password) {
            return this._users.put(obj, new KnownUser(obj.toString(), (Password) obj2));
        }
        if (obj2 == null) {
            return null;
        }
        return this._users.put(obj, new KnownUser(obj.toString(), Credential.getCredential(obj2.toString())));
    }

    public synchronized void addUserToRole(String str, String str2) {
        HashSet hashSet = (HashSet) this._roles.get(str2);
        if (hashSet == null) {
            hashSet = new HashSet(11);
            this._roles.put(str2, hashSet);
        }
        hashSet.add(str);
    }

    @Override // org.mortbay.jetty.security.UserRealm
    public boolean reauthenticate(Principal principal) {
        return ((User) principal).isAuthenticated();
    }

    @Override // org.mortbay.jetty.security.UserRealm
    public synchronized boolean isUserInRole(Principal principal, String str) {
        if (principal instanceof WrappedUser) {
            return ((WrappedUser) principal).isUserInRole(str);
        }
        boolean z = false;
        if (principal != null && (principal instanceof User) && ((User) principal).getUserRealm() == this) {
            HashSet hashSet = (HashSet) this._roles.get(str);
            if (hashSet != null && hashSet.contains(principal.getName())) {
                z = true;
            }
            return z;
        }
        return false;
    }

    public String toString() {
        return new StringBuffer("Realm[").append(this._realmName).append("]==").append(this._users.keySet()).toString();
    }

    public void dump(PrintStream printStream) {
        printStream.println(new StringBuffer().append(this).append(":").toString());
        printStream.println(super.toString());
        printStream.println(this._roles);
    }

    public SSORealm getSSORealm() {
        return this._ssoRealm;
    }

    public void setSSORealm(SSORealm sSORealm) {
        this._ssoRealm = sSORealm;
    }

    @Override // org.mortbay.jetty.security.SSORealm
    public Credential getSingleSignOn(Request request, Response response) {
        SSORealm sSORealm = this._ssoRealm;
        if (sSORealm != null) {
            return sSORealm.getSingleSignOn(request, response);
        }
        return null;
    }

    @Override // org.mortbay.jetty.security.SSORealm
    public void setSingleSignOn(Request request, Response response, Principal principal, Credential credential) {
        SSORealm sSORealm = this._ssoRealm;
        if (sSORealm != null) {
            sSORealm.setSingleSignOn(request, response, principal, credential);
        }
    }

    @Override // org.mortbay.jetty.security.SSORealm
    public void clearSingleSignOn(String str) {
        SSORealm sSORealm = this._ssoRealm;
        if (sSORealm != null) {
            sSORealm.clearSingleSignOn(str);
        }
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStart() throws Exception {
        super.doStart();
        Scanner scanner = this._scanner;
        if (scanner != null) {
            scanner.stop();
        }
        if (getRefreshInterval() > 0) {
            Scanner scanner2 = new Scanner();
            this._scanner = scanner2;
            scanner2.setScanInterval(getRefreshInterval());
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(this._configResource.getFile());
            this._scanner.setScanDirs(arrayList);
            this._scanner.setFilenameFilter(new FilenameFilter() { // from class: org.mortbay.jetty.security.HashUserRealm.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str) {
                    try {
                        return new File(file, str).compareTo(HashUserRealm.this._configResource.getFile()) == 0;
                    } catch (IOException unused) {
                        return false;
                    }
                }
            });
            this._scanner.addListener(new Scanner.BulkListener() { // from class: org.mortbay.jetty.security.HashUserRealm.2
                @Override // org.mortbay.util.Scanner.BulkListener
                public void filesChanged(List list) throws Exception {
                    if (list != null && !list.isEmpty() && list.size() == 1 && list.get(0).equals(HashUserRealm.this._config)) {
                        HashUserRealm.this.loadConfig();
                    }
                }

                public String toString() {
                    return "HashUserRealm$Scanner";
                }
            });
            this._scanner.setReportExistingFilesOnStartup(false);
            this._scanner.setRecursive(false);
            this._scanner.start();
        }
    }

    @Override // org.mortbay.component.AbstractLifeCycle
    protected void doStop() throws Exception {
        super.doStop();
        Scanner scanner = this._scanner;
        if (scanner != null) {
            scanner.stop();
        }
        this._scanner = null;
    }

    private class User implements Principal {
        List roles;

        public boolean isAuthenticated() {
            return false;
        }

        private User() {
            this.roles = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public UserRealm getUserRealm() {
            return HashUserRealm.this;
        }

        @Override // java.security.Principal
        public String getName() {
            return "Anonymous";
        }

        @Override // java.security.Principal
        public String toString() {
            return getName();
        }
    }

    private class KnownUser extends User {
        private Credential _cred;
        private String _userName;

        @Override // org.mortbay.jetty.security.HashUserRealm.User
        public boolean isAuthenticated() {
            return true;
        }

        KnownUser(String str, Credential credential) {
            super();
            this._userName = str;
            this._cred = credential;
        }

        boolean authenticate(Object obj) {
            Credential credential = this._cred;
            return credential != null && credential.check(obj);
        }

        @Override // org.mortbay.jetty.security.HashUserRealm.User, java.security.Principal
        public String getName() {
            return this._userName;
        }
    }

    private class WrappedUser extends User {
        private String role;
        private Principal user;

        @Override // org.mortbay.jetty.security.HashUserRealm.User
        public boolean isAuthenticated() {
            return true;
        }

        WrappedUser(Principal principal, String str) {
            super();
            this.user = principal;
            this.role = str;
        }

        Principal getUserPrincipal() {
            return this.user;
        }

        @Override // org.mortbay.jetty.security.HashUserRealm.User, java.security.Principal
        public String getName() {
            return new StringBuffer("role:").append(this.role).toString();
        }

        public boolean isUserInRole(String str) {
            return this.role.equals(str);
        }
    }
}

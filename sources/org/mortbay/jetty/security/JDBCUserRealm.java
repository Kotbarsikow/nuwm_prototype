package org.mortbay.jetty.security;

import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.mortbay.jetty.Request;
import org.mortbay.log.Log;
import org.mortbay.util.Loader;

/* loaded from: classes3.dex */
public class JDBCUserRealm extends HashUserRealm implements UserRealm {
    private int _cacheTime;
    private Connection _con;
    private String _jdbcDriver;
    private long _lastHashPurge;
    private String _password;
    private String _roleSql;
    private String _roleTable;
    private String _roleTableKey;
    private String _roleTableRoleField;
    private String _url;
    private String _userName;
    private String _userRoleTable;
    private String _userRoleTableRoleKey;
    private String _userRoleTableUserKey;
    private String _userSql;
    private String _userTable;
    private String _userTableKey;
    private String _userTablePasswordField;
    private String _userTableUserField;

    @Override // org.mortbay.jetty.security.HashUserRealm, org.mortbay.jetty.security.UserRealm
    public void logout(Principal principal) {
    }

    public JDBCUserRealm() {
    }

    public JDBCUserRealm(String str) {
        super(str);
    }

    public JDBCUserRealm(String str, String str2) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super(str);
        setConfig(str2);
        Loader.loadClass(getClass(), this._jdbcDriver).newInstance();
    }

    @Override // org.mortbay.jetty.security.HashUserRealm
    protected void loadConfig() throws IOException {
        String str;
        String str2;
        Properties properties = new Properties();
        properties.load(getConfigResource().getInputStream());
        this._jdbcDriver = properties.getProperty("jdbcdriver");
        this._url = properties.getProperty(ImagesContract.URL);
        this._userName = properties.getProperty("username");
        this._password = properties.getProperty("password");
        this._userTable = properties.getProperty("usertable");
        this._userTableKey = properties.getProperty("usertablekey");
        this._userTableUserField = properties.getProperty("usertableuserfield");
        this._userTablePasswordField = properties.getProperty("usertablepasswordfield");
        this._roleTable = properties.getProperty("roletable");
        this._roleTableKey = properties.getProperty("roletablekey");
        this._roleTableRoleField = properties.getProperty("roletablerolefield");
        this._userRoleTable = properties.getProperty("userroletable");
        this._userRoleTableUserKey = properties.getProperty("userroletableuserkey");
        this._userRoleTableRoleKey = properties.getProperty("userroletablerolekey");
        String property = properties.getProperty("cachetime");
        this._cacheTime = property != null ? new Integer(property).intValue() : 30;
        String str3 = this._jdbcDriver;
        if ((str3 == null || str3.equals("") || (str = this._url) == null || str.equals("") || (str2 = this._userName) == null || str2.equals("") || this._password == null || this._cacheTime < 0) && Log.isDebugEnabled()) {
            Log.debug(new StringBuffer("UserRealm ").append(getName()).append(" has not been properly configured").toString());
        }
        this._cacheTime *= 1000;
        this._lastHashPurge = 0L;
        this._userSql = new StringBuffer("select ").append(this._userTableKey).append(",").append(this._userTablePasswordField).append(" from ").append(this._userTable).append(" where ").append(this._userTableUserField).append(" = ?").toString();
        this._roleSql = new StringBuffer("select r.").append(this._roleTableRoleField).append(" from ").append(this._roleTable).append(" r, ").append(this._userRoleTable).append(" u where u.").append(this._userRoleTableUserKey).append(" = ? and r.").append(this._roleTableKey).append(" = u.").append(this._userRoleTableRoleKey).toString();
    }

    public void connectDatabase() {
        try {
            Class.forName(this._jdbcDriver);
            this._con = DriverManager.getConnection(this._url, this._userName, this._password);
        } catch (ClassNotFoundException e) {
            Log.warn(new StringBuffer("UserRealm ").append(getName()).append(" could not connect to database; will try later").toString(), (Throwable) e);
        } catch (SQLException e2) {
            Log.warn(new StringBuffer("UserRealm ").append(getName()).append(" could not connect to database; will try later").toString(), (Throwable) e2);
        }
    }

    @Override // org.mortbay.jetty.security.HashUserRealm, org.mortbay.jetty.security.UserRealm
    public Principal authenticate(String str, Object obj, Request request) {
        synchronized (this) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this._lastHashPurge;
            int i = this._cacheTime;
            if (j > i || i == 0) {
                this._users.clear();
                this._roles.clear();
                this._lastHashPurge = currentTimeMillis;
                closeConnection();
            }
            if (super.getPrincipal(str) == null) {
                loadUser(str);
                super.getPrincipal(str);
            }
        }
        return super.authenticate(str, obj, request);
    }

    @Override // org.mortbay.jetty.security.HashUserRealm, org.mortbay.jetty.security.UserRealm
    public synchronized boolean isUserInRole(Principal principal, String str) {
        if (super.getPrincipal(principal.getName()) == null) {
            loadUser(principal.getName());
        }
        return super.isUserInRole(principal, str);
    }

    private void loadUser(String str) {
        try {
            if (this._con == null) {
                connectDatabase();
            }
            Connection connection = this._con;
            if (connection == null) {
                throw new SQLException("Can't connect to database");
            }
            PreparedStatement prepareStatement = connection.prepareStatement(this._userSql);
            prepareStatement.setObject(1, str);
            ResultSet executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int i = executeQuery.getInt(this._userTableKey);
                put(str, executeQuery.getString(this._userTablePasswordField));
                prepareStatement.close();
                PreparedStatement prepareStatement2 = this._con.prepareStatement(this._roleSql);
                prepareStatement2.setInt(1, i);
                ResultSet executeQuery2 = prepareStatement2.executeQuery();
                while (executeQuery2.next()) {
                    addUserToRole(str, executeQuery2.getString(this._roleTableRoleField));
                }
                prepareStatement2.close();
            }
        } catch (SQLException e) {
            Log.warn(new StringBuffer("UserRealm ").append(getName()).append(" could not load user information from database").toString(), (Throwable) e);
            closeConnection();
        }
    }

    private void closeConnection() {
        if (this._con != null) {
            if (Log.isDebugEnabled()) {
                Log.debug("Closing db connection for JDBCUserRealm");
            }
            try {
                this._con.close();
            } catch (Exception e) {
                Log.ignore(e);
            }
        }
        this._con = null;
    }
}

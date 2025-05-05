package org.mortbay.jetty.security;

import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.mortbay.log.Log;
import org.mortbay.util.StringUtil;
import org.mortbay.util.TypeUtil;

/* loaded from: classes3.dex */
public abstract class Credential {
    public abstract boolean check(Object obj);

    public static Credential getCredential(String str) {
        if (str.startsWith(Crypt.__TYPE)) {
            return new Crypt(str);
        }
        if (str.startsWith(MD5.__TYPE)) {
            return new MD5(str);
        }
        return new Password(str);
    }

    public static class Crypt extends Credential {
        public static final String __TYPE = "CRYPT:";
        private String _cooked;

        Crypt(String str) {
            this._cooked = str.startsWith(__TYPE) ? str.substring(6) : str;
        }

        @Override // org.mortbay.jetty.security.Credential
        public boolean check(Object obj) {
            if (!(obj instanceof String) && !(obj instanceof Password)) {
                Log.warn(new StringBuffer("Can't check ").append(obj.getClass()).append(" against CRYPT").toString());
            }
            String obj2 = obj.toString();
            String str = this._cooked;
            return str.equals(UnixCrypt.crypt(obj2, str));
        }

        public static String crypt(String str, String str2) {
            return new StringBuffer(__TYPE).append(UnixCrypt.crypt(str2, str)).toString();
        }
    }

    public static class MD5 extends Credential {
        public static final String __TYPE = "MD5:";
        private static MessageDigest __md;
        public static final Object __md5Lock = new Object();
        private byte[] _digest;

        MD5(String str) {
            this._digest = TypeUtil.parseBytes(str.startsWith(__TYPE) ? str.substring(4) : str, 16);
        }

        public byte[] getDigest() {
            return this._digest;
        }

        @Override // org.mortbay.jetty.security.Credential
        public boolean check(Object obj) {
            byte[] digest;
            try {
                if (!(obj instanceof Password) && !(obj instanceof String)) {
                    if (obj instanceof MD5) {
                        MD5 md5 = (MD5) obj;
                        if (this._digest.length != md5._digest.length) {
                            return false;
                        }
                        int i = 0;
                        while (true) {
                            byte[] bArr = this._digest;
                            if (i >= bArr.length) {
                                return true;
                            }
                            if (bArr[i] != md5._digest[i]) {
                                return false;
                            }
                            i++;
                        }
                    } else {
                        if (obj instanceof Credential) {
                            return ((Credential) obj).check(this);
                        }
                        Log.warn(new StringBuffer("Can't check ").append(obj.getClass()).append(" against MD5").toString());
                        return false;
                    }
                }
                synchronized (__md5Lock) {
                    if (__md == null) {
                        __md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                    }
                    __md.reset();
                    __md.update(obj.toString().getBytes(StringUtil.__ISO_8859_1));
                    digest = __md.digest();
                }
                if (digest != null && digest.length == this._digest.length) {
                    for (int i2 = 0; i2 < digest.length; i2++) {
                        if (digest[i2] != this._digest[i2]) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            } catch (Exception e) {
                Log.warn(e);
                return false;
            }
        }

        public static String digest(String str) {
            byte[] digest;
            try {
                synchronized (__md5Lock) {
                    if (__md == null) {
                        try {
                            __md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                        } catch (Exception e) {
                            Log.warn(e);
                            return null;
                        }
                    }
                    __md.reset();
                    __md.update(str.getBytes(StringUtil.__ISO_8859_1));
                    digest = __md.digest();
                }
                return new StringBuffer(__TYPE).append(TypeUtil.toString(digest, 16)).toString();
            } catch (Exception e2) {
                Log.warn(e2);
                return null;
            }
        }
    }
}

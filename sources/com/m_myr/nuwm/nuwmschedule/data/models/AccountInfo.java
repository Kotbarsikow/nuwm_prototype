package com.m_myr.nuwm.nuwmschedule.data.models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class AccountInfo {

    @SerializedName("accounts")
    ArrayList<SimpleAccount> accounts;

    @SerializedName("token_info")
    TokenInfo tokenInfo;

    public static class SimpleAccount {

        @SerializedName("email")
        public String email;

        @SerializedName("main")
        public boolean main;
    }

    public static class TokenInfo {

        @SerializedName("valid")
        public boolean valid;
    }

    public ArrayList<SimpleAccount> getAccounts() {
        return this.accounts;
    }

    public TokenInfo getTokenInfo() {
        return this.tokenInfo;
    }
}

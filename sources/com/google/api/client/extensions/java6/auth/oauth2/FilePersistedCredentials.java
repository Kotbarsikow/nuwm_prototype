package com.google.api.client.extensions.java6.auth.oauth2;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStore;
import java.io.IOException;
import java.util.Map;

@Deprecated
/* loaded from: classes2.dex */
public class FilePersistedCredentials extends GenericJson {

    @Key
    private Map<String, FilePersistedCredential> credentials = Maps.newHashMap();

    void store(String str, Credential credential) {
        Preconditions.checkNotNull(str);
        FilePersistedCredential filePersistedCredential = this.credentials.get(str);
        if (filePersistedCredential == null) {
            filePersistedCredential = new FilePersistedCredential();
            this.credentials.put(str, filePersistedCredential);
        }
        filePersistedCredential.store(credential);
    }

    boolean load(String str, Credential credential) {
        Preconditions.checkNotNull(str);
        FilePersistedCredential filePersistedCredential = this.credentials.get(str);
        if (filePersistedCredential == null) {
            return false;
        }
        filePersistedCredential.load(credential);
        return true;
    }

    void delete(String str) {
        Preconditions.checkNotNull(str);
        this.credentials.remove(str);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData
    public FilePersistedCredentials set(String str, Object obj) {
        return (FilePersistedCredentials) super.set(str, obj);
    }

    @Override // com.google.api.client.json.GenericJson, com.google.api.client.util.GenericData, java.util.AbstractMap
    public FilePersistedCredentials clone() {
        return (FilePersistedCredentials) super.clone();
    }

    void migrateTo(DataStore<StoredCredential> dataStore) throws IOException {
        for (Map.Entry<String, FilePersistedCredential> entry : this.credentials.entrySet()) {
            dataStore.set(entry.getKey(), entry.getValue().toStoredCredential());
        }
    }
}

package com.m_myr.nuwm.nuwmschedule.ui.view;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.Document;
import com.m_myr.nuwm.nuwmschedule.network.models.UploadResponse;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;

/* loaded from: classes2.dex */
public class DocumentHolderItem {
    public TextView txt1;
    public TextView txt2;
    View v;

    protected DocumentHolderItem(View viewGroup) {
        this.v = viewGroup;
        this.txt1 = (TextView) viewGroup.findViewById(R.id.file_name);
        this.txt2 = (TextView) viewGroup.findViewById(R.id.file_info);
    }

    @Deprecated
    public DocumentHolderItem(LayoutInflater inflater) {
        this(inflater.inflate(R.layout.file_attach, (ViewGroup) null));
    }

    @Deprecated
    public DocumentHolderItem init(final Document doc, final Activity activity) {
        this.txt1.setText(doc.doc_name);
        this.txt2.setText(Utils.resolveContentName(doc.doc_mime));
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.DocumentHolderItem.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (activity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                        DocumentHolderItem.this.load(activity, doc);
                        return;
                    } else {
                        ActivityCompat.requestPermissions(activity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                        return;
                    }
                }
                DocumentHolderItem.this.load(activity, doc);
            }
        });
        return this;
    }

    public View getView() {
        return this.v;
    }

    protected void load(Context context, Document doc) {
        String str;
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(doc.doc_url));
        if (doc.doc_name.length() >= 40) {
            str = doc.doc_name.substring(0, 35) + "...";
        } else {
            str = doc.doc_name;
        }
        String str2 = str + "." + doc.doc_mime;
        String str3 = str + "." + doc.doc_mime;
        Log.e("load", str2);
        if (doc.doc_mime != null) {
            request.setMimeType(Utils.resolveContentType(doc.doc_mime));
        }
        request.setTitle(str3);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(1);
        request.allowScanningByMediaScanner();
        if (doc.doc_mime != null) {
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, str2);
        }
        ((DownloadManager) context.getSystemService("download")).enqueue(request);
    }

    public DocumentHolderItem init(final UploadResponse doc, final Uri uri, final Context context) {
        this.txt1.setText(doc.getName());
        this.txt2.setText(Utils.resolveContentName(doc.getExtension()));
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.view.DocumentHolderItem.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(uri);
                context.startActivity(intent);
            }
        });
        return this;
    }
}

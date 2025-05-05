package com.m_myr.nuwm.nuwmschedule.ui.activities.repository;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.material.button.MaterialButton;
import com.hootsuite.nachos.NachoTextView;
import com.m_myr.nuwm.nuwmschedule.R;
import com.m_myr.nuwm.nuwmschedule.data.models.RepositoryDocument;
import com.m_myr.nuwm.nuwmschedule.ui.AdapterContract;
import com.m_myr.nuwm.nuwmschedule.ui.GeneralListAdapter;
import com.m_myr.nuwm.nuwmschedule.ui.activities.base.BaseStateActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.imageview.ImageViewActivity;
import com.m_myr.nuwm.nuwmschedule.ui.activities.repositoryPeople.UserRepositoryActivity;
import com.m_myr.nuwm.nuwmschedule.utils.ResourcesHelper;
import com.m_myr.nuwm.nuwmschedule.utils.Utils;
import java.io.File;
import java.io.Serializable;
import java.text.StringCharacterIterator;
import org.mortbay.util.URIUtil;

/* loaded from: classes2.dex */
public class RepositoryItemActivity extends BaseStateActivity implements RepositoryItemView, Runnable {
    private RepositoryDocument data;
    long downloadId;
    private RecyclerView images;
    private TextView mAbstracts;
    private ConstraintLayout mConstraintLayout;
    private ViewGroup mCreators;
    private TextView mDateYear;
    private MaterialButton mDownloadButton;
    private LinearLayout mDownloading;
    private TextView mFileSize;
    private Flow mFlowVud;
    private ImageView mImageCover;
    private TextView mKeywords;
    private TextView mLoadCounter;
    private NachoTextView mNachoTextView;
    private MaterialButton mOpenFile;
    private ProgressBar mProgressHorizontal;
    private TextView mShufr;
    private TextView mTitle;
    private ImageView mTypeFileImage;
    private TextView mTypeFileName;
    RepositoryItemPresenter presenter = new RepositoryItemPresenter(this);
    Handler handlerDownloader = new Handler();
    BroadcastReceiver onCompleteReceiver = new BroadcastReceiver() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemActivity.3
        AnonymousClass3() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context ctxt, Intent intent) {
            RepositoryItemActivity.this.handlerDownloader.removeCallbacks(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemActivity$3$$ExternalSyntheticLambda0
                public /* synthetic */ RepositoryItemActivity$3$$ExternalSyntheticLambda0() {
                }

                @Override // java.lang.Runnable
                public final void run() {
                    RepositoryItemActivity.this.run();
                }
            });
            try {
                RepositoryItemActivity.this.mDownloadButton.setVisibility(4);
                RepositoryItemActivity.this.mOpenFile.setVisibility(0);
                RepositoryItemActivity.this.mDownloading.setVisibility(4);
            } catch (Exception unused) {
            }
        }
    };

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScrollContentView(R.layout.layout_repository_document);
        attachToolbar();
        showContent();
        this.mImageCover = (ImageView) findViewById(R.id.imageView4);
        this.mTitle = (TextView) findViewById(R.id.title);
        this.mConstraintLayout = (ConstraintLayout) findBaseViewById(R.id.content_layout);
        this.mCreators = (ViewGroup) findViewById(R.id.creators);
        this.mLoadCounter = (TextView) findViewById(R.id.loadCounter);
        this.mFileSize = (TextView) findViewById(R.id.fileSize);
        this.mAbstracts = (TextView) findViewById(R.id.abstracts);
        this.mDownloadButton = (MaterialButton) findViewById(R.id.downloadButton);
        this.mOpenFile = (MaterialButton) findViewById(R.id.openFile);
        registerReceiver(this.onCompleteReceiver, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
        this.mTypeFileImage = (ImageView) findViewById(R.id.typeFileImage);
        this.mTypeFileName = (TextView) findViewById(R.id.typeFileName);
        this.mProgressHorizontal = (ProgressBar) findViewById(R.id.progress_horizontal);
        this.mDownloading = (LinearLayout) findViewById(R.id.downloading);
        this.mShufr = (TextView) findViewById(R.id.shufr);
        this.mDateYear = (TextView) findViewById(R.id.date_year);
        this.mKeywords = (TextView) findViewById(R.id.keywords);
        this.images = (RecyclerView) findViewById(R.id.images);
        this.mDownloadButton.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemActivity$$ExternalSyntheticLambda0
            public /* synthetic */ RepositoryItemActivity$$ExternalSyntheticLambda0() {
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RepositoryItemActivity.this.onClickDownload(view);
            }
        });
        this.mOpenFile.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemActivity$$ExternalSyntheticLambda1
            public /* synthetic */ RepositoryItemActivity$$ExternalSyntheticLambda1() {
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RepositoryItemActivity.this.onClickOpen(view);
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.onCompleteReceiver);
    }

    @Override // com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemView
    public void showContent(RepositoryDocument data) {
        this.data = data;
        super.showContent();
        checkIfFileDownloaded();
        checkFileType();
        Utils.sendAnalytic(this, "eprint_view", new Pair("eprint_id", Integer.valueOf(data.getEprintid())));
        setTitle(data.getDocumentTypeName());
        this.mShufr.setText(data.getShufr());
        this.mDateYear.setText(String.valueOf(data.getDateYear()));
        this.mKeywords.setText(data.getKeywords());
        this.mAbstracts.setText(data.getAbstract());
        this.mFileSize.setText(humanReadableByteCountSI(data.getFile().getFilesize()));
        if (data.getTitle().equals(data.getTitle().toUpperCase())) {
            this.mTitle.setText(toTitleCase(data.getTitle().toLowerCase()));
        } else {
            this.mTitle.setText(data.getTitle());
        }
        this.mLoadCounter.setText(String.valueOf(data.getDownloads()));
        Glide.with((FragmentActivity) this).load(data.getImageUrl()).into(this.mImageCover);
        if (data.getFile().getPreview() != null) {
            this.mImageCover.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemActivity.1
                final /* synthetic */ RepositoryDocument val$data;

                AnonymousClass1(RepositoryDocument data2) {
                    val$data = data2;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Intent intent = new Intent(RepositoryItemActivity.this.getContext(), (Class<?>) ImageViewActivity.class);
                    intent.putExtra(ImagesContract.URL, val$data.getImageUrl());
                    ActivityCompat.startActivity(RepositoryItemActivity.this.getContext(), intent, ActivityOptionsCompat.makeSceneTransitionAnimation(RepositoryItemActivity.this, v, "image").toBundle());
                }
            });
        }
        for (RepositoryDocument.Author author : data2.getCreators()) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this);
            appCompatTextView.setId(data2.getCreators().indexOf(author));
            appCompatTextView.setTextColor(ResourcesHelper.getAttrColor(this, R.attr.colorAccent));
            appCompatTextView.setClickable(false);
            appCompatTextView.setBackground(ResourcesHelper.getAttrDrawable(this, R.attr.selectableItemBackground));
            appCompatTextView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            appCompatTextView.setTag(author);
            appCompatTextView.setOnClickListener(new View.OnClickListener() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemActivity.2
                final /* synthetic */ RepositoryDocument.Author val$a;

                AnonymousClass2(RepositoryDocument.Author author2) {
                    val$a = author2;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), (Class<?>) UserRepositoryActivity.class);
                    intent.putExtra("author_name_str", val$a.family);
                    intent.putExtra("author_initials", val$a.given);
                    RepositoryItemActivity.this.startActivity(intent);
                }
            });
            appCompatTextView.setTypeface(null, 1);
            appCompatTextView.setText(author2.full_name);
            this.mCreators.addView(appCompatTextView);
        }
        int i = 27;
        do {
            i -= 2;
        } while (getHeight(data2.getTitle(), i, this.mTitle.getWidth()) > this.mImageCover.getHeight() * 1.5d);
        if (i < 7) {
            i = 7;
        }
        this.mTitle.setTextSize(1, i);
        this.images.setAdapter(new GeneralListAdapter(new ImagesAdapters(), (Serializable) data2.getPagesPreview()));
        if (data2.getPagesPreview() == null || data2.getPagesPreview().isEmpty()) {
            findViewById(R.id.preview_groups).setVisibility(8);
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemActivity$1 */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ RepositoryDocument val$data;

        AnonymousClass1(RepositoryDocument data2) {
            val$data = data2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            Intent intent = new Intent(RepositoryItemActivity.this.getContext(), (Class<?>) ImageViewActivity.class);
            intent.putExtra(ImagesContract.URL, val$data.getImageUrl());
            ActivityCompat.startActivity(RepositoryItemActivity.this.getContext(), intent, ActivityOptionsCompat.makeSceneTransitionAnimation(RepositoryItemActivity.this, v, "image").toBundle());
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemActivity$2 */
    class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ RepositoryDocument.Author val$a;

        AnonymousClass2(RepositoryDocument.Author author2) {
            val$a = author2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), (Class<?>) UserRepositoryActivity.class);
            intent.putExtra("author_name_str", val$a.family);
            intent.putExtra("author_initials", val$a.given);
            RepositoryItemActivity.this.startActivity(intent);
        }
    }

    private static class ImagesAdapters extends AdapterContract<String> {
        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        public void onClick(Context context, String o) {
        }

        private ImagesAdapters() {
        }

        /* synthetic */ ImagesAdapters(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        protected AdapterContract.ViewHolderGeneral inflate(ViewGroup parent) {
            return new AdapterContract.ViewHolderGeneral(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_itam, parent, false));
        }

        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        public void bind(AdapterContract.ViewHolderGeneral holder, String url) {
            Glide.with(holder.itemView.getContext()).load(url).into((ImageView) holder.itemView);
        }

        @Override // com.m_myr.nuwm.nuwmschedule.ui.AdapterContract
        public void onClick(Context context, View v, String o) {
            super.onClick(context, v, (View) o);
            Intent intent = new Intent(context, (Class<?>) ImageViewActivity.class);
            intent.putExtra(ImagesContract.URL, o);
            ActivityCompat.startActivity(context, intent, ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, v, "image").toBundle());
        }
    }

    public String toTitleCase(String input) {
        StringBuilder sb = new StringBuilder(input.length());
        boolean z = true;
        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                z = true;
            } else if (z) {
                c = Character.toTitleCase(c);
                z = false;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public int getHeight(String text, int textSize, int deviceWidth) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(1, textSize);
        textView.measure(View.MeasureSpec.makeMeasureSpec(deviceWidth, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
        return textView.getMeasuredHeight();
    }

    public static String humanReadableByteCountSI(long bytes) {
        if (-1000 < bytes && bytes < 1000) {
            return bytes + " B";
        }
        StringCharacterIterator stringCharacterIterator = new StringCharacterIterator("kMGTPE");
        while (true) {
            if (bytes > -999950 && bytes < 999950) {
                return String.format("%.1f %cB", Double.valueOf(bytes / 1000.0d), Character.valueOf(stringCharacterIterator.current()));
            }
            bytes /= 1000;
            stringCharacterIterator.next();
        }
    }

    public void onClickDownload(View view) {
        if (Build.VERSION.SDK_INT >= 33) {
            loadFile();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        } else {
            loadFile();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == 0) {
            loadFile();
        } else {
            Utils.showStub(this, "Спочатку надайде доступ для запису файлів");
        }
    }

    private void loadFile() {
        this.mDownloading.setVisibility(0);
        this.mDownloadButton.setVisibility(4);
        this.mOpenFile.setVisibility(4);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(this.data.getFile().getUri()));
        request.setTitle(this.data.getFile().getFilename());
        request.allowScanningByMediaScanner();
        request.setDescription("Завантаження...");
        request.setAllowedNetworkTypes(3);
        request.setNotificationVisibility(1);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, this.data.getFile().getFilename());
        request.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        this.downloadId = ((DownloadManager) getSystemService("download")).enqueue(request);
        this.handlerDownloader.postDelayed(this, 300L);
        Utils.sendAnalytic(this, "eprint_download", new Pair("eprint_id", Integer.valueOf(this.data.getEprintid())));
    }

    public void onClickOpen(View view) {
        try {
            Uri uriForFile = FileProvider.getUriForFile(this, "nuwmschedule.provider", new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + URIUtil.SLASH + this.data.getFile().getFilename()));
            grantUriPermission(getPackageName(), uriForFile, 1);
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(this.data.getFile().getMimeType());
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(uriForFile, mimeTypeFromExtension);
            intent.addFlags(1);
            intent.addFlags(1073741824);
            startActivity(intent);
            Utils.sendAnalytic(this, "eprint_open", new Pair("eprint_id", Integer.valueOf(this.data.getEprintid())));
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this, "Неможливо відкрити файл", 0).show();
        } catch (Exception e) {
            e.printStackTrace();
            this.mDownloadButton.setVisibility(0);
            this.mOpenFile.setVisibility(4);
        }
    }

    /* renamed from: com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemActivity$3 */
    class AnonymousClass3 extends BroadcastReceiver {
        AnonymousClass3() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context ctxt, Intent intent) {
            RepositoryItemActivity.this.handlerDownloader.removeCallbacks(new Runnable() { // from class: com.m_myr.nuwm.nuwmschedule.ui.activities.repository.RepositoryItemActivity$3$$ExternalSyntheticLambda0
                public /* synthetic */ RepositoryItemActivity$3$$ExternalSyntheticLambda0() {
                }

                @Override // java.lang.Runnable
                public final void run() {
                    RepositoryItemActivity.this.run();
                }
            });
            try {
                RepositoryItemActivity.this.mDownloadButton.setVisibility(4);
                RepositoryItemActivity.this.mOpenFile.setVisibility(0);
                RepositoryItemActivity.this.mDownloading.setVisibility(4);
            } catch (Exception unused) {
            }
        }
    }

    private void checkFileType() {
        String mimeType = this.data.getFile().getMimeType();
        mimeType.hashCode();
        switch (mimeType) {
            case "text/x-pascal":
                this.mTypeFileImage.setImageResource(R.drawable.ic_code);
                this.mTypeFileName.setText("Код PASCAL");
                break;
            case "text/x-python":
                this.mTypeFileImage.setImageResource(R.drawable.ic_code);
                this.mTypeFileName.setText("Код Python");
                break;
            case "image/jpeg":
                this.mTypeFileImage.setImageResource(R.drawable.ic_files_and_folders);
                this.mTypeFileName.setText("Зображення JPEG");
                break;
            case "application/pdf":
                this.mTypeFileImage.setImageResource(R.drawable.ic_pdf);
                this.mTypeFileName.setText("Документ PDF");
                break;
            case "application/xml":
                this.mTypeFileImage.setImageResource(R.drawable.ic_code);
                this.mTypeFileName.setText("Документ XML");
                break;
            case "text/html":
                this.mTypeFileImage.setImageResource(R.drawable.ic_code);
                this.mTypeFileName.setText("HTML сторінка");
                break;
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                this.mTypeFileImage.setImageResource(R.drawable.ic_file);
                this.mTypeFileName.setText("Файл Microsoft Word");
                break;
            case "text/x-c":
                this.mTypeFileImage.setImageResource(R.drawable.ic_code);
                this.mTypeFileName.setText("Код С");
                break;
            case "image/png":
                this.mTypeFileImage.setImageResource(R.drawable.ic_files_and_folders);
                this.mTypeFileName.setText("Зображення PNG");
                break;
            case "application/vnd.ms-excel":
                this.mTypeFileImage.setImageResource(R.drawable.ic_file);
                this.mTypeFileName.setText("Таблиці XLS");
                break;
            case "text/x-java":
                this.mTypeFileImage.setImageResource(R.drawable.ic_code);
                this.mTypeFileName.setText("Код JAVA");
                break;
            case "text/plain":
                this.mTypeFileImage.setImageResource(R.drawable.ic_file);
                this.mTypeFileName.setText("Текстовий файл");
                break;
            case "text/x-c++":
                this.mTypeFileImage.setImageResource(R.drawable.ic_code);
                this.mTypeFileName.setText("Код C++");
                break;
            case "application/msword":
                this.mTypeFileImage.setImageResource(R.drawable.ic_doc);
                this.mTypeFileName.setText("Документ MS Word");
                break;
            default:
                this.mTypeFileImage.setImageResource(R.drawable.ic_file);
                this.mTypeFileName.setText("Файл " + this.data.getFile().getMimeType());
                break;
        }
    }

    private void checkIfFileDownloaded() {
        try {
            if (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + URIUtil.SLASH + this.data.getFile().getFilename()).isFile()) {
                this.mDownloadButton.setVisibility(4);
                this.mOpenFile.setVisibility(0);
                this.mDownloading.setVisibility(4);
            }
        } catch (Exception unused) {
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(this.downloadId);
        Cursor query2 = ((DownloadManager) getSystemService("download")).query(query);
        if (query2.moveToFirst()) {
            int columnIndex = query2.getColumnIndex("total_size");
            int columnIndex2 = query2.getColumnIndex("bytes_so_far");
            long j = query2.getInt(columnIndex);
            this.mProgressHorizontal.setProgress((int) (j != -1 ? (query2.getInt(columnIndex2) * 100.0d) / j : com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON));
        }
        this.handlerDownloader.postDelayed(this, 400L);
    }
}

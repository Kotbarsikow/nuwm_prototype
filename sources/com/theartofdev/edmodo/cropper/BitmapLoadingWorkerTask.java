package com.theartofdev.edmodo.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import com.theartofdev.edmodo.cropper.BitmapUtils;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
final class BitmapLoadingWorkerTask extends AsyncTask<Void, Void, Result> {
    private final Context mContext;
    private final WeakReference<CropImageView> mCropImageViewReference;
    private final int mHeight;
    private final Uri mUri;
    private final int mWidth;

    public BitmapLoadingWorkerTask(CropImageView cropImageView, Uri uri) {
        this.mUri = uri;
        this.mCropImageViewReference = new WeakReference<>(cropImageView);
        this.mContext = cropImageView.getContext();
        double d = cropImageView.getResources().getDisplayMetrics().density > 1.0f ? 1.0f / r5.density : 1.0d;
        this.mWidth = (int) (r5.widthPixels * d);
        this.mHeight = (int) (r5.heightPixels * d);
    }

    public Uri getUri() {
        return this.mUri;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Result doInBackground(Void... voidArr) {
        try {
            if (isCancelled()) {
                return null;
            }
            BitmapUtils.BitmapSampled decodeSampledBitmap = BitmapUtils.decodeSampledBitmap(this.mContext, this.mUri, this.mWidth, this.mHeight);
            if (isCancelled()) {
                return null;
            }
            BitmapUtils.RotateBitmapResult rotateBitmapByExif = BitmapUtils.rotateBitmapByExif(decodeSampledBitmap.bitmap, this.mContext, this.mUri);
            return new Result(this.mUri, rotateBitmapByExif.bitmap, decodeSampledBitmap.sampleSize, rotateBitmapByExif.degrees);
        } catch (Exception e) {
            return new Result(this.mUri, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Result result) {
        CropImageView cropImageView;
        if (result != null) {
            if (!isCancelled() && (cropImageView = this.mCropImageViewReference.get()) != null) {
                cropImageView.onSetImageUriAsyncComplete(result);
            } else if (result.bitmap != null) {
                result.bitmap.recycle();
            }
        }
    }

    public static final class Result {
        public final Bitmap bitmap;
        public final int degreesRotated;
        public final Exception error;
        public final int loadSampleSize;
        public final Uri uri;

        Result(Uri uri, Bitmap bitmap, int i, int i2) {
            this.uri = uri;
            this.bitmap = bitmap;
            this.loadSampleSize = i;
            this.degreesRotated = i2;
            this.error = null;
        }

        Result(Uri uri, Exception exc) {
            this.uri = uri;
            this.bitmap = null;
            this.loadSampleSize = 0;
            this.degreesRotated = 0;
            this.error = exc;
        }
    }
}

package com.theartofdev.edmodo.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import com.theartofdev.edmodo.cropper.BitmapUtils;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
final class BitmapCroppingWorkerTask extends AsyncTask<Void, Void, Result> {
    private final int mAspectRatioX;
    private final int mAspectRatioY;
    private final Bitmap mBitmap;
    private final Context mContext;
    private final WeakReference<CropImageView> mCropImageViewReference;
    private final float[] mCropPoints;
    private final int mDegreesRotated;
    private final boolean mFixAspectRatio;
    private final boolean mFlipHorizontally;
    private final boolean mFlipVertically;
    private final int mOrgHeight;
    private final int mOrgWidth;
    private final int mReqHeight;
    private final CropImageView.RequestSizeOptions mReqSizeOptions;
    private final int mReqWidth;
    private final Bitmap.CompressFormat mSaveCompressFormat;
    private final int mSaveCompressQuality;
    private final Uri mSaveUri;
    private final Uri mUri;

    BitmapCroppingWorkerTask(CropImageView cropImageView, Bitmap bitmap, float[] fArr, int i, boolean z, int i2, int i3, int i4, int i5, boolean z2, boolean z3, CropImageView.RequestSizeOptions requestSizeOptions, Uri uri, Bitmap.CompressFormat compressFormat, int i6) {
        this.mCropImageViewReference = new WeakReference<>(cropImageView);
        this.mContext = cropImageView.getContext();
        this.mBitmap = bitmap;
        this.mCropPoints = fArr;
        this.mUri = null;
        this.mDegreesRotated = i;
        this.mFixAspectRatio = z;
        this.mAspectRatioX = i2;
        this.mAspectRatioY = i3;
        this.mReqWidth = i4;
        this.mReqHeight = i5;
        this.mFlipHorizontally = z2;
        this.mFlipVertically = z3;
        this.mReqSizeOptions = requestSizeOptions;
        this.mSaveUri = uri;
        this.mSaveCompressFormat = compressFormat;
        this.mSaveCompressQuality = i6;
        this.mOrgWidth = 0;
        this.mOrgHeight = 0;
    }

    BitmapCroppingWorkerTask(CropImageView cropImageView, Uri uri, float[] fArr, int i, int i2, int i3, boolean z, int i4, int i5, int i6, int i7, boolean z2, boolean z3, CropImageView.RequestSizeOptions requestSizeOptions, Uri uri2, Bitmap.CompressFormat compressFormat, int i8) {
        this.mCropImageViewReference = new WeakReference<>(cropImageView);
        this.mContext = cropImageView.getContext();
        this.mUri = uri;
        this.mCropPoints = fArr;
        this.mDegreesRotated = i;
        this.mFixAspectRatio = z;
        this.mAspectRatioX = i4;
        this.mAspectRatioY = i5;
        this.mOrgWidth = i2;
        this.mOrgHeight = i3;
        this.mReqWidth = i6;
        this.mReqHeight = i7;
        this.mFlipHorizontally = z2;
        this.mFlipVertically = z3;
        this.mReqSizeOptions = requestSizeOptions;
        this.mSaveUri = uri2;
        this.mSaveCompressFormat = compressFormat;
        this.mSaveCompressQuality = i8;
        this.mBitmap = null;
    }

    public Uri getUri() {
        return this.mUri;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Result doInBackground(Void... voidArr) {
        BitmapUtils.BitmapSampled cropBitmapObjectHandleOOM;
        try {
            if (isCancelled()) {
                return null;
            }
            Uri uri = this.mUri;
            if (uri != null) {
                cropBitmapObjectHandleOOM = BitmapUtils.cropBitmap(this.mContext, uri, this.mCropPoints, this.mDegreesRotated, this.mOrgWidth, this.mOrgHeight, this.mFixAspectRatio, this.mAspectRatioX, this.mAspectRatioY, this.mReqWidth, this.mReqHeight, this.mFlipHorizontally, this.mFlipVertically);
            } else {
                Bitmap bitmap = this.mBitmap;
                if (bitmap != null) {
                    cropBitmapObjectHandleOOM = BitmapUtils.cropBitmapObjectHandleOOM(bitmap, this.mCropPoints, this.mDegreesRotated, this.mFixAspectRatio, this.mAspectRatioX, this.mAspectRatioY, this.mFlipHorizontally, this.mFlipVertically);
                } else {
                    return new Result((Bitmap) null, 1);
                }
            }
            Bitmap resizeBitmap = BitmapUtils.resizeBitmap(cropBitmapObjectHandleOOM.bitmap, this.mReqWidth, this.mReqHeight, this.mReqSizeOptions);
            Uri uri2 = this.mSaveUri;
            if (uri2 == null) {
                return new Result(resizeBitmap, cropBitmapObjectHandleOOM.sampleSize);
            }
            BitmapUtils.writeBitmapToUri(this.mContext, resizeBitmap, uri2, this.mSaveCompressFormat, this.mSaveCompressQuality);
            if (resizeBitmap != null) {
                resizeBitmap.recycle();
            }
            return new Result(this.mSaveUri, cropBitmapObjectHandleOOM.sampleSize);
        } catch (Exception e) {
            return new Result(e, this.mSaveUri != null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Result result) {
        CropImageView cropImageView;
        if (result != null) {
            if (!isCancelled() && (cropImageView = this.mCropImageViewReference.get()) != null) {
                cropImageView.onImageCroppingAsyncComplete(result);
            } else if (result.bitmap != null) {
                result.bitmap.recycle();
            }
        }
    }

    static final class Result {
        public final Bitmap bitmap;
        final Exception error;
        final boolean isSave;
        final int sampleSize;
        public final Uri uri;

        Result(Bitmap bitmap, int i) {
            this.bitmap = bitmap;
            this.uri = null;
            this.error = null;
            this.isSave = false;
            this.sampleSize = i;
        }

        Result(Uri uri, int i) {
            this.bitmap = null;
            this.uri = uri;
            this.error = null;
            this.isSave = true;
            this.sampleSize = i;
        }

        Result(Exception exc, boolean z) {
            this.bitmap = null;
            this.uri = null;
            this.error = exc;
            this.isSave = z;
            this.sampleSize = 1;
        }
    }
}

package com.theartofdev.edmodo.cropper;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import androidx.exifinterface.media.ExifInterface;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* loaded from: classes2.dex */
final class BitmapUtils {
    private static int mMaxTextureSize;
    static Pair<String, WeakReference<Bitmap>> mStateBitmap;
    static final Rect EMPTY_RECT = new Rect();
    static final RectF EMPTY_RECT_F = new RectF();
    static final RectF RECT = new RectF();
    static final float[] POINTS = new float[6];
    static final float[] POINTS2 = new float[6];

    BitmapUtils() {
    }

    static RotateBitmapResult rotateBitmapByExif(Bitmap bitmap, Context context, Uri uri) {
        ExifInterface exifInterface = null;
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            if (openInputStream != null) {
                ExifInterface exifInterface2 = new ExifInterface(openInputStream);
                try {
                    openInputStream.close();
                } catch (Exception unused) {
                }
                exifInterface = exifInterface2;
            }
        } catch (Exception unused2) {
        }
        return exifInterface != null ? rotateBitmapByExif(bitmap, exifInterface) : new RotateBitmapResult(bitmap, 0);
    }

    static RotateBitmapResult rotateBitmapByExif(Bitmap bitmap, ExifInterface exifInterface) {
        int attributeInt = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        return new RotateBitmapResult(bitmap, attributeInt != 3 ? attributeInt != 6 ? attributeInt != 8 ? 0 : 270 : 90 : 180);
    }

    static BitmapSampled decodeSampledBitmap(Context context, Uri uri, int i, int i2) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            BitmapFactory.Options decodeImageForOption = decodeImageForOption(contentResolver, uri);
            if (decodeImageForOption.outWidth == -1 && decodeImageForOption.outHeight == -1) {
                throw new RuntimeException("File is not a picture");
            }
            decodeImageForOption.inSampleSize = Math.max(calculateInSampleSizeByReqestedSize(decodeImageForOption.outWidth, decodeImageForOption.outHeight, i, i2), calculateInSampleSizeByMaxTextureSize(decodeImageForOption.outWidth, decodeImageForOption.outHeight));
            return new BitmapSampled(decodeImage(contentResolver, uri, decodeImageForOption), decodeImageForOption.inSampleSize);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load sampled bitmap: " + uri + "\r\n" + e.getMessage(), e);
        }
    }

    static BitmapSampled cropBitmapObjectHandleOOM(Bitmap bitmap, float[] fArr, int i, boolean z, int i2, int i3, boolean z2, boolean z3) {
        int i4 = 1;
        do {
            try {
                return new BitmapSampled(cropBitmapObjectWithScale(bitmap, fArr, i, z, i2, i3, 1.0f / i4, z2, z3), i4);
            } catch (OutOfMemoryError e) {
                i4 *= 2;
            }
        } while (i4 <= 8);
        throw e;
    }

    private static Bitmap cropBitmapObjectWithScale(Bitmap bitmap, float[] fArr, int i, boolean z, int i2, int i3, float f, boolean z2, boolean z3) {
        float f2 = f;
        Rect rectFromPoints = getRectFromPoints(fArr, bitmap.getWidth(), bitmap.getHeight(), z, i2, i3);
        Matrix matrix = new Matrix();
        matrix.setRotate(i, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        float f3 = z2 ? -f2 : f2;
        if (z3) {
            f2 = -f2;
        }
        matrix.postScale(f3, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, rectFromPoints.left, rectFromPoints.top, rectFromPoints.width(), rectFromPoints.height(), matrix, true);
        if (createBitmap == bitmap) {
            createBitmap = bitmap.copy(bitmap.getConfig(), false);
        }
        return i % 90 != 0 ? cropForRotatedImage(createBitmap, fArr, rectFromPoints, i, z, i2, i3) : createBitmap;
    }

    static BitmapSampled cropBitmap(Context context, Uri uri, float[] fArr, int i, int i2, int i3, boolean z, int i4, int i5, int i6, int i7, boolean z2, boolean z3) {
        int i8 = 1;
        do {
            try {
                return cropBitmap(context, uri, fArr, i, i2, i3, z, i4, i5, i6, i7, z2, z3, i8);
            } catch (OutOfMemoryError e) {
                i8 *= 2;
            }
        } while (i8 <= 16);
        throw new RuntimeException("Failed to handle OOM by sampling (" + i8 + "): " + uri + "\r\n" + e.getMessage(), e);
    }

    static float getRectLeft(float[] fArr) {
        return Math.min(Math.min(Math.min(fArr[0], fArr[2]), fArr[4]), fArr[6]);
    }

    static float getRectTop(float[] fArr) {
        return Math.min(Math.min(Math.min(fArr[1], fArr[3]), fArr[5]), fArr[7]);
    }

    static float getRectRight(float[] fArr) {
        return Math.max(Math.max(Math.max(fArr[0], fArr[2]), fArr[4]), fArr[6]);
    }

    static float getRectBottom(float[] fArr) {
        return Math.max(Math.max(Math.max(fArr[1], fArr[3]), fArr[5]), fArr[7]);
    }

    static float getRectWidth(float[] fArr) {
        return getRectRight(fArr) - getRectLeft(fArr);
    }

    static float getRectHeight(float[] fArr) {
        return getRectBottom(fArr) - getRectTop(fArr);
    }

    static float getRectCenterX(float[] fArr) {
        return (getRectRight(fArr) + getRectLeft(fArr)) / 2.0f;
    }

    static float getRectCenterY(float[] fArr) {
        return (getRectBottom(fArr) + getRectTop(fArr)) / 2.0f;
    }

    static Rect getRectFromPoints(float[] fArr, int i, int i2, boolean z, int i3, int i4) {
        Rect rect = new Rect(Math.round(Math.max(0.0f, getRectLeft(fArr))), Math.round(Math.max(0.0f, getRectTop(fArr))), Math.round(Math.min(i, getRectRight(fArr))), Math.round(Math.min(i2, getRectBottom(fArr))));
        if (z) {
            fixRectForAspectRatio(rect, i3, i4);
        }
        return rect;
    }

    private static void fixRectForAspectRatio(Rect rect, int i, int i2) {
        if (i != i2 || rect.width() == rect.height()) {
            return;
        }
        if (rect.height() > rect.width()) {
            rect.bottom -= rect.height() - rect.width();
        } else {
            rect.right -= rect.width() - rect.height();
        }
    }

    static Uri writeTempStateStoreBitmap(Context context, Bitmap bitmap, Uri uri) {
        boolean z = true;
        try {
            if (uri == null) {
                uri = Uri.fromFile(File.createTempFile("aic_state_store_temp", ".jpg", context.getCacheDir()));
            } else if (new File(uri.getPath()).exists()) {
                z = false;
            }
            if (z) {
                writeBitmapToUri(context, bitmap, uri, Bitmap.CompressFormat.JPEG, 95);
            }
            return uri;
        } catch (Exception e) {
            Log.w("AIC", "Failed to write bitmap to temp file for image-cropper save instance state", e);
            return null;
        }
    }

    static void writeBitmapToUri(Context context, Bitmap bitmap, Uri uri, Bitmap.CompressFormat compressFormat, int i) throws FileNotFoundException {
        OutputStream outputStream = null;
        try {
            outputStream = context.getContentResolver().openOutputStream(uri);
            bitmap.compress(compressFormat, i, outputStream);
        } finally {
            closeSafe(outputStream);
        }
    }

    static Bitmap resizeBitmap(Bitmap bitmap, int i, int i2, CropImageView.RequestSizeOptions requestSizeOptions) {
        Bitmap createScaledBitmap;
        if (i > 0 && i2 > 0) {
            try {
                if (requestSizeOptions == CropImageView.RequestSizeOptions.RESIZE_FIT || requestSizeOptions == CropImageView.RequestSizeOptions.RESIZE_INSIDE || requestSizeOptions == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                    if (requestSizeOptions == CropImageView.RequestSizeOptions.RESIZE_EXACT) {
                        createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, false);
                    } else {
                        float width = bitmap.getWidth();
                        float height = bitmap.getHeight();
                        float max = Math.max(width / i, height / i2);
                        if (max <= 1.0f && requestSizeOptions != CropImageView.RequestSizeOptions.RESIZE_FIT) {
                            createScaledBitmap = null;
                        }
                        createScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (width / max), (int) (height / max), false);
                    }
                    if (createScaledBitmap != null) {
                        if (createScaledBitmap != bitmap) {
                            bitmap.recycle();
                        }
                        return createScaledBitmap;
                    }
                }
            } catch (Exception e) {
                Log.w("AIC", "Failed to resize cropped image, return bitmap before resize", e);
            }
        }
        return bitmap;
    }

    private static BitmapSampled cropBitmap(Context context, Uri uri, float[] fArr, int i, int i2, int i3, boolean z, int i4, int i5, int i6, int i7, boolean z2, boolean z3, int i8) {
        int i9;
        Bitmap rotateAndFlipBitmapInt;
        Rect rectFromPoints = getRectFromPoints(fArr, i2, i3, z, i4, i5);
        int width = i6 > 0 ? i6 : rectFromPoints.width();
        int height = i7 > 0 ? i7 : rectFromPoints.height();
        Bitmap bitmap = null;
        try {
            BitmapSampled decodeSampledBitmapRegion = decodeSampledBitmapRegion(context, uri, rectFromPoints, width, height, i8);
            bitmap = decodeSampledBitmapRegion.bitmap;
            i9 = decodeSampledBitmapRegion.sampleSize;
        } catch (Exception unused) {
            i9 = 1;
        }
        if (bitmap != null) {
            try {
                rotateAndFlipBitmapInt = rotateAndFlipBitmapInt(bitmap, i, z2, z3);
            } catch (OutOfMemoryError e) {
                e = e;
            }
            try {
                if (i % 90 != 0) {
                    rotateAndFlipBitmapInt = cropForRotatedImage(rotateAndFlipBitmapInt, fArr, rectFromPoints, i, z, i4, i5);
                }
                return new BitmapSampled(rotateAndFlipBitmapInt, i9);
            } catch (OutOfMemoryError e2) {
                e = e2;
                bitmap = rotateAndFlipBitmapInt;
                if (bitmap != null) {
                    bitmap.recycle();
                }
                throw e;
            }
        }
        return cropBitmap(context, uri, fArr, i, z, i4, i5, i8, rectFromPoints, width, height, z2, z3);
    }

    private static BitmapSampled cropBitmap(Context context, Uri uri, float[] fArr, int i, boolean z, int i2, int i3, int i4, Rect rect, int i5, int i6, boolean z2, boolean z3) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int calculateInSampleSizeByReqestedSize = calculateInSampleSizeByReqestedSize(rect.width(), rect.height(), i5, i6) * i4;
            options.inSampleSize = calculateInSampleSizeByReqestedSize;
            Bitmap decodeImage = decodeImage(context.getContentResolver(), uri, options);
            if (decodeImage != null) {
                try {
                    int length = fArr.length;
                    float[] fArr2 = new float[length];
                    System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                    for (int i7 = 0; i7 < length; i7++) {
                        fArr2[i7] = fArr2[i7] / options.inSampleSize;
                    }
                    bitmap = cropBitmapObjectWithScale(decodeImage, fArr2, i, z, i2, i3, 1.0f, z2, z3);
                    if (bitmap != decodeImage) {
                        decodeImage.recycle();
                    }
                } catch (Throwable th) {
                    if (decodeImage != null) {
                        decodeImage.recycle();
                    }
                    throw th;
                }
            }
            return new BitmapSampled(bitmap, calculateInSampleSizeByReqestedSize);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load sampled bitmap: " + uri + "\r\n" + e.getMessage(), e);
        } catch (OutOfMemoryError e2) {
            if (0 != 0) {
                bitmap.recycle();
            }
            throw e2;
        }
    }

    private static BitmapFactory.Options decodeImageForOption(ContentResolver contentResolver, Uri uri) throws FileNotFoundException {
        InputStream inputStream;
        try {
            inputStream = contentResolver.openInputStream(uri);
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, EMPTY_RECT, options);
            options.inJustDecodeBounds = false;
            closeSafe(inputStream);
            return options;
        } catch (Throwable th2) {
            th = th2;
            closeSafe(inputStream);
            throw th;
        }
    }

    private static Bitmap decodeImage(ContentResolver contentResolver, Uri uri, BitmapFactory.Options options) throws FileNotFoundException {
        do {
            InputStream inputStream = null;
            try {
                try {
                    inputStream = contentResolver.openInputStream(uri);
                    return BitmapFactory.decodeStream(inputStream, EMPTY_RECT, options);
                } catch (OutOfMemoryError unused) {
                    options.inSampleSize *= 2;
                    closeSafe(inputStream);
                }
            } finally {
                closeSafe(inputStream);
            }
        } while (options.inSampleSize <= 512);
        throw new RuntimeException("Failed to decode image: " + uri);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.theartofdev.edmodo.cropper.BitmapUtils.BitmapSampled decodeSampledBitmapRegion(android.content.Context r4, android.net.Uri r5, android.graphics.Rect r6, int r7, int r8, int r9) {
        /*
            r0 = 0
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            r1.<init>()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            int r2 = r6.width()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            int r3 = r6.height()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            int r7 = calculateInSampleSizeByReqestedSize(r2, r3, r7, r8)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            int r9 = r9 * r7
            r1.inSampleSize = r9     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            java.io.InputStream r4 = r4.openInputStream(r5)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            r7 = 0
            android.graphics.BitmapRegionDecoder r7 = android.graphics.BitmapRegionDecoder.newInstance(r4, r7)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5a
        L23:
            com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled r8 = new com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39 java.lang.OutOfMemoryError -> L3b
            android.graphics.Bitmap r9 = r7.decodeRegion(r6, r1)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39 java.lang.OutOfMemoryError -> L3b
            int r2 = r1.inSampleSize     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39 java.lang.OutOfMemoryError -> L3b
            r8.<init>(r9, r2)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39 java.lang.OutOfMemoryError -> L3b
            closeSafe(r4)
            if (r7 == 0) goto L36
            r7.recycle()
        L36:
            return r8
        L37:
            r5 = move-exception
            goto L58
        L39:
            r6 = move-exception
            goto L5c
        L3b:
            int r8 = r1.inSampleSize     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            int r8 = r8 * 2
            r1.inSampleSize = r8     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            int r8 = r1.inSampleSize     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L39
            r9 = 512(0x200, float:7.17E-43)
            if (r8 <= r9) goto L23
            closeSafe(r4)
            if (r7 == 0) goto L4f
            r7.recycle()
        L4f:
            com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled r4 = new com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled
            r5 = 1
            r4.<init>(r0, r5)
            return r4
        L56:
            r5 = move-exception
            r7 = r0
        L58:
            r0 = r4
            goto L87
        L5a:
            r6 = move-exception
            r7 = r0
        L5c:
            r0 = r4
            goto L63
        L5e:
            r5 = move-exception
            r7 = r0
            goto L87
        L61:
            r6 = move-exception
            r7 = r0
        L63:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L86
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L86
            r8.<init>()     // Catch: java.lang.Throwable -> L86
            java.lang.String r9 = "Failed to load sampled bitmap: "
            r8.append(r9)     // Catch: java.lang.Throwable -> L86
            r8.append(r5)     // Catch: java.lang.Throwable -> L86
            java.lang.String r5 = "\r\n"
            r8.append(r5)     // Catch: java.lang.Throwable -> L86
            java.lang.String r5 = r6.getMessage()     // Catch: java.lang.Throwable -> L86
            r8.append(r5)     // Catch: java.lang.Throwable -> L86
            java.lang.String r5 = r8.toString()     // Catch: java.lang.Throwable -> L86
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L86
            throw r4     // Catch: java.lang.Throwable -> L86
        L86:
            r5 = move-exception
        L87:
            closeSafe(r0)
            if (r7 == 0) goto L8f
            r7.recycle()
        L8f:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.theartofdev.edmodo.cropper.BitmapUtils.decodeSampledBitmapRegion(android.content.Context, android.net.Uri, android.graphics.Rect, int, int, int):com.theartofdev.edmodo.cropper.BitmapUtils$BitmapSampled");
    }

    private static Bitmap cropForRotatedImage(Bitmap bitmap, float[] fArr, Rect rect, int i, boolean z, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        if (i % 90 == 0) {
            return bitmap;
        }
        double radians = Math.toRadians(i);
        int i7 = (i < 90 || (i > 180 && i < 270)) ? rect.left : rect.right;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            if (i9 >= fArr.length) {
                i4 = 0;
                i5 = 0;
                i6 = 0;
                break;
            }
            float f = fArr[i9];
            if (f >= i7 - 1 && f <= i7 + 1) {
                int i10 = i9 + 1;
                i8 = (int) Math.abs(Math.sin(radians) * (rect.bottom - fArr[i10]));
                i5 = (int) Math.abs(Math.cos(radians) * (fArr[i10] - rect.top));
                i6 = (int) Math.abs((fArr[i10] - rect.top) / Math.sin(radians));
                i4 = (int) Math.abs((rect.bottom - fArr[i10]) / Math.cos(radians));
                break;
            }
            i9 += 2;
        }
        rect.set(i8, i5, i6 + i8, i4 + i5);
        if (z) {
            fixRectForAspectRatio(rect, i2, i3);
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height());
        if (bitmap != createBitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    private static int calculateInSampleSizeByReqestedSize(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i2 > i4 || i > i3) {
            while ((i2 / 2) / i5 > i4 && (i / 2) / i5 > i3) {
                i5 *= 2;
            }
        }
        return i5;
    }

    private static int calculateInSampleSizeByMaxTextureSize(int i, int i2) {
        if (mMaxTextureSize == 0) {
            mMaxTextureSize = getMaxTextureSize();
        }
        int i3 = 1;
        if (mMaxTextureSize > 0) {
            while (true) {
                int i4 = i2 / i3;
                int i5 = mMaxTextureSize;
                if (i4 <= i5 && i / i3 <= i5) {
                    break;
                }
                i3 *= 2;
            }
        }
        return i3;
    }

    private static Bitmap rotateAndFlipBitmapInt(Bitmap bitmap, int i, boolean z, boolean z2) {
        if (i <= 0 && !z && !z2) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(i);
        matrix.postScale(z ? -1.0f : 1.0f, z2 ? -1.0f : 1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        if (createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    private static int getMaxTextureSize() {
        try {
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            egl10.eglInitialize(eglGetDisplay, new int[2]);
            int[] iArr = new int[1];
            egl10.eglGetConfigs(eglGetDisplay, null, 0, iArr);
            int i = iArr[0];
            EGLConfig[] eGLConfigArr = new EGLConfig[i];
            egl10.eglGetConfigs(eglGetDisplay, eGLConfigArr, i, iArr);
            int[] iArr2 = new int[1];
            int i2 = 0;
            for (int i3 = 0; i3 < iArr[0]; i3++) {
                egl10.eglGetConfigAttrib(eglGetDisplay, eGLConfigArr[i3], 12332, iArr2);
                int i4 = iArr2[0];
                if (i2 < i4) {
                    i2 = i4;
                }
            }
            egl10.eglTerminate(eglGetDisplay);
            return Math.max(i2, 2048);
        } catch (Exception unused) {
            return 2048;
        }
    }

    private static void closeSafe(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    static final class BitmapSampled {
        public final Bitmap bitmap;
        final int sampleSize;

        BitmapSampled(Bitmap bitmap, int i) {
            this.bitmap = bitmap;
            this.sampleSize = i;
        }
    }

    static final class RotateBitmapResult {
        public final Bitmap bitmap;
        final int degrees;

        RotateBitmapResult(Bitmap bitmap, int i) {
            this.bitmap = bitmap;
            this.degrees = i;
        }
    }
}

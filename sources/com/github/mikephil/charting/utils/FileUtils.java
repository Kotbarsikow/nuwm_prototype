package com.github.mikephil.charting.utils;

import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class FileUtils {
    private static final String LOG = "MPChart-FileUtils";

    public static List<Entry> loadEntriesFromFile(String str) {
        File file = new File(Environment.getExternalStorageDirectory(), str);
        ArrayList arrayList = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String[] split = readLine.split("#");
                if (split.length <= 2) {
                    arrayList.add(new Entry(Float.parseFloat(split[0]), Integer.parseInt(split[1])));
                } else {
                    int length = split.length - 1;
                    float[] fArr = new float[length];
                    for (int i = 0; i < length; i++) {
                        fArr[i] = Float.parseFloat(split[i]);
                    }
                    arrayList.add(new BarEntry(Integer.parseInt(split[split.length - 1]), fArr));
                }
            }
        } catch (IOException e) {
            Log.e(LOG, e.toString());
        }
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r2v10, types: [float[]] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v13, types: [float] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x0081 -> B:19:0x0088). Please report as a decompilation issue!!! */
    public static List<Entry> loadEntriesFromAssets(AssetManager assetManager, String str) {
        ?? r2;
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2 = null;
        BufferedReader bufferedReader3 = null;
        bufferedReader = null;
        try {
        } catch (IOException e) {
            Log.e(LOG, e.toString());
            bufferedReader = bufferedReader;
        }
        try {
            try {
                BufferedReader bufferedReader4 = new BufferedReader(new InputStreamReader(assetManager.open(str), "UTF-8"));
                try {
                    String readLine = bufferedReader4.readLine();
                    while (readLine != null) {
                        String[] split = readLine.split("#");
                        if (split.length <= 2) {
                            r2 = Float.parseFloat(split[1]);
                            arrayList.add(new Entry(r2, Float.parseFloat(split[0])));
                        } else {
                            int length = split.length - 1;
                            r2 = new float[length];
                            for (int i = 0; i < length; i++) {
                                r2[i] = Float.parseFloat(split[i]);
                            }
                            arrayList.add(new BarEntry(Integer.parseInt(split[split.length - 1]), (float[]) r2));
                        }
                        readLine = bufferedReader4.readLine();
                        bufferedReader2 = r2;
                    }
                    bufferedReader4.close();
                    bufferedReader = bufferedReader2;
                } catch (IOException e2) {
                    e = e2;
                    bufferedReader3 = bufferedReader4;
                    Log.e(LOG, e.toString());
                    bufferedReader = bufferedReader3;
                    if (bufferedReader3 != null) {
                        bufferedReader3.close();
                        bufferedReader = bufferedReader3;
                    }
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader4;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            Log.e(LOG, e3.toString());
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void saveToSdCard(List<Entry> list, String str) {
        File file = new File(Environment.getExternalStorageDirectory(), str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e(LOG, e.toString());
            }
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            for (Entry entry : list) {
                bufferedWriter.append((CharSequence) (entry.getY() + "#" + entry.getX()));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e2) {
            Log.e(LOG, e2.toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v9 */
    public static List<BarEntry> loadBarEntriesFromAssets(AssetManager assetManager, String str) {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = null;
        float f = 0;
        bufferedReader = null;
        bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(assetManager.open(str), "UTF-8"));
                    try {
                        for (String readLine = bufferedReader2.readLine(); readLine != null; readLine = bufferedReader2.readLine()) {
                            String[] split = readLine.split("#");
                            f = Float.parseFloat(split[1]);
                            arrayList.add(new BarEntry(f, Float.parseFloat(split[0])));
                        }
                        bufferedReader2.close();
                        bufferedReader = f;
                    } catch (IOException e) {
                        e = e;
                        bufferedReader = bufferedReader2;
                        Log.e(LOG, e.toString());
                        if (bufferedReader != null) {
                            bufferedReader.close();
                            bufferedReader = bufferedReader;
                        }
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2) {
                                Log.e(LOG, e2.toString());
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                Log.e(LOG, e4.toString());
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}

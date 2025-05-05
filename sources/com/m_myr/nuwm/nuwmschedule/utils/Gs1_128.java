package com.m_myr.nuwm.nuwmschedule.utils;

import android.graphics.Bitmap;
import android.graphics.Color;

/* loaded from: classes2.dex */
public class Gs1_128 {
    static int[] code128_pattern = {1740, 1644, 1638, 1176, 1164, 1100, 1224, 1220, 1124, 1608, 1604, 1572, 1436, 1244, 1230, 1484, 1260, 1254, 1650, 1628, 1614, 1764, 1652, 1902, 1868, 1836, 1830, 1892, 1844, 1842, 1752, 1734, 1590, 1304, 1112, 1094, 1416, 1128, 1122, 1672, 1576, 1570, 1464, 1422, 1134, 1496, 1478, 1142, 1910, 1678, 1582, 1768, 1762, 1774, 1880, 1862, 1814, 1896, 1890, 1818, 1914, 1602, 1930, 1328, 1292, 1200, 1158, 1068, 1062, 1424, 1412, 1232, 1218, 1076, 1074, 1554, 1616, 1978, 1556, 1146, 1340, 1212, 1182, 1508, 1268, 1266, 1956, 1940, 1938, 1758, 1782, 1974, 1400, 1310, 1118, 1512, 1506, 1960, 1954, 1502, 1518, 1886, 1966, 1668, 1680, 1692};
    private boolean[] acquaintance;
    private int pivot = 0;
    private boolean[] tags;

    public Gs1_128(String barcodeString) throws Exception {
        int length = (barcodeString.length() / 2) * 11;
        this.tags = new boolean[(barcodeString.length() % 2 == 1 ? length + 68 : length + 46) * 5];
        StringBuilder sb = new StringBuilder("");
        int i = 104;
        sb.append(Integer.toBinaryString(code128_pattern[104]));
        String sb2 = sb.toString();
        int i2 = 0;
        while (i2 < barcodeString.length()) {
            sb2 = sb2 + Integer.toBinaryString(code128_pattern[barcodeString.charAt(i2) - ' ']);
            int charAt = barcodeString.charAt(i2) - ' ';
            i2++;
            i += charAt * i2;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        int i3 = i % 103;
        sb3.append(Integer.toBinaryString(code128_pattern[i3]));
        String str = sb3.toString() + "1100011101011";
        this.acquaintance = new boolean[str.length()];
        for (int i4 = 0; i4 < str.length(); i4++) {
            if (str.charAt(i4) == '1') {
                this.acquaintance[i4] = true;
            } else {
                this.acquaintance[i4] = false;
            }
        }
        PushChar(i3);
        PushChar(106);
    }

    public Bitmap ToBitMap(int dotHeight, int pixelPerDot) throws Exception {
        if (pixelPerDot < 1) {
            throw new Exception("Pixel per dot < 1");
        }
        int i = dotHeight * pixelPerDot;
        Bitmap createBitmap = Bitmap.createBitmap(this.acquaintance.length * pixelPerDot, i, Bitmap.Config.ARGB_8888);
        int i2 = 0;
        while (true) {
            boolean[] zArr = this.acquaintance;
            if (i2 >= zArr.length) {
                return createBitmap;
            }
            int i3 = zArr[i2] ? 0 : 255;
            for (int i4 = 0; i4 < i; i4++) {
                for (int i5 = 0; i5 < pixelPerDot; i5++) {
                    createBitmap.setPixel((i2 * pixelPerDot) + i5, i4, Color.rgb(i3, i3, i3));
                }
            }
            i2++;
        }
    }

    private void PushChar(int charCode) {
        switch (charCode) {
            case 0:
                PushPattern(2, 1, 2, 2, 2, 2);
                break;
            case 1:
                PushPattern(2, 2, 2, 1, 2, 2);
                break;
            case 2:
                PushPattern(2, 2, 2, 2, 2, 1);
                break;
            case 3:
                PushPattern(1, 2, 1, 2, 2, 3);
                break;
            case 4:
                PushPattern(1, 2, 1, 3, 2, 2);
                break;
            case 5:
                PushPattern(1, 3, 1, 2, 2, 2);
                break;
            case 6:
                PushPattern(1, 2, 2, 2, 1, 3);
                break;
            case 7:
                PushPattern(1, 2, 2, 3, 1, 2);
                break;
            case 8:
                PushPattern(1, 3, 2, 2, 1, 2);
                break;
            case 9:
                PushPattern(2, 2, 1, 2, 1, 3);
                break;
            case 10:
                PushPattern(2, 2, 1, 3, 1, 2);
                break;
            case 11:
                PushPattern(2, 3, 1, 2, 1, 2);
                break;
            case 12:
                PushPattern(1, 1, 2, 2, 3, 2);
                break;
            case 13:
                PushPattern(1, 2, 2, 1, 3, 2);
                break;
            case 14:
                PushPattern(1, 2, 2, 2, 3, 1);
                break;
            case 15:
                PushPattern(1, 1, 3, 2, 2, 2);
                break;
            case 16:
                PushPattern(1, 2, 3, 1, 2, 2);
                break;
            case 17:
                PushPattern(1, 2, 3, 2, 2, 1);
                break;
            case 18:
                PushPattern(2, 2, 3, 2, 1, 1);
                break;
            case 19:
                PushPattern(2, 2, 1, 1, 3, 2);
                break;
            case 20:
                PushPattern(2, 2, 1, 2, 3, 1);
                break;
            case 21:
                PushPattern(2, 1, 3, 2, 1, 2);
                break;
            case 22:
                PushPattern(2, 2, 3, 1, 1, 2);
                break;
            case 23:
                PushPattern(3, 1, 2, 1, 3, 1);
                break;
            case 24:
                PushPattern(3, 1, 1, 2, 2, 2);
                break;
            case 25:
                PushPattern(3, 2, 1, 1, 2, 2);
                break;
            case 26:
                PushPattern(3, 2, 1, 2, 2, 1);
                break;
            case 27:
                PushPattern(3, 1, 2, 2, 1, 2);
                break;
            case 28:
                PushPattern(3, 2, 2, 1, 1, 2);
                break;
            case 29:
                PushPattern(3, 2, 2, 2, 1, 1);
                break;
            case 30:
                PushPattern(2, 1, 2, 1, 2, 3);
                break;
            case 31:
                PushPattern(2, 1, 2, 3, 2, 1);
                break;
            case 32:
                PushPattern(2, 3, 2, 1, 2, 1);
                break;
            case 33:
                PushPattern(1, 1, 1, 3, 2, 3);
                break;
            case 34:
                PushPattern(1, 3, 1, 1, 2, 3);
                break;
            case 35:
                PushPattern(1, 3, 1, 3, 2, 1);
                break;
            case 36:
                PushPattern(1, 1, 2, 3, 1, 3);
                break;
            case 37:
                PushPattern(1, 3, 2, 1, 1, 3);
                break;
            case 38:
                PushPattern(1, 3, 2, 3, 1, 1);
                break;
            case 39:
                PushPattern(2, 1, 1, 3, 1, 3);
                break;
            case 40:
                PushPattern(2, 3, 1, 1, 1, 3);
                break;
            case 41:
                PushPattern(2, 3, 1, 3, 1, 1);
                break;
            case 42:
                PushPattern(1, 1, 2, 1, 3, 3);
                break;
            case 43:
                PushPattern(1, 1, 2, 3, 3, 1);
                break;
            case 44:
                PushPattern(1, 3, 2, 1, 3, 1);
                break;
            case 45:
                PushPattern(1, 1, 3, 1, 2, 3);
                break;
            case 46:
                PushPattern(1, 1, 3, 3, 2, 1);
                break;
            case 47:
                PushPattern(1, 3, 3, 1, 2, 1);
                break;
            case 48:
                PushPattern(3, 1, 3, 1, 2, 1);
                break;
            case 49:
                PushPattern(2, 1, 1, 3, 3, 1);
                break;
            case 50:
                PushPattern(2, 3, 1, 1, 3, 1);
                break;
            case 51:
                PushPattern(2, 1, 3, 1, 1, 3);
                break;
            case 52:
                PushPattern(2, 1, 3, 3, 1, 1);
                break;
            case 53:
                PushPattern(3, 1, 1, 1, 2, 3);
                break;
            case 54:
                PushPattern(3, 1, 1, 1, 2, 3);
                break;
            case 55:
                PushPattern(3, 1, 1, 3, 2, 1);
                break;
            case 56:
                PushPattern(3, 3, 1, 1, 2, 1);
                break;
            case 57:
                PushPattern(3, 1, 2, 1, 1, 3);
                break;
            case 58:
                PushPattern(3, 1, 2, 3, 1, 1);
                break;
            case 59:
                PushPattern(3, 3, 2, 1, 1, 1);
                break;
            case 60:
                PushPattern(3, 1, 4, 1, 1, 1);
                break;
            case 61:
                PushPattern(2, 2, 1, 4, 1, 1);
                break;
            case 62:
                PushPattern(4, 3, 1, 1, 1, 1);
                break;
            case 63:
                PushPattern(1, 1, 1, 2, 2, 4);
                break;
            case 64:
                PushPattern(1, 1, 1, 4, 2, 2);
                break;
            case 65:
                PushPattern(1, 2, 1, 1, 2, 4);
                break;
            case 66:
                PushPattern(1, 2, 1, 4, 2, 1);
                break;
            case 67:
                PushPattern(1, 4, 1, 1, 2, 2);
                break;
            case 68:
                PushPattern(1, 4, 1, 2, 2, 1);
                break;
            case 69:
                PushPattern(1, 1, 2, 2, 1, 4);
                break;
            case 70:
                PushPattern(1, 1, 2, 4, 1, 2);
                break;
            case 71:
                PushPattern(1, 2, 2, 1, 1, 4);
                break;
            case 72:
                PushPattern(1, 2, 2, 4, 1, 1);
                break;
            case 73:
                PushPattern(1, 4, 2, 1, 1, 2);
                break;
            case 74:
                PushPattern(1, 4, 2, 2, 1, 1);
                break;
            case 75:
                PushPattern(2, 4, 1, 2, 1, 1);
                break;
            case 76:
                PushPattern(2, 2, 1, 1, 1, 4);
                break;
            case 77:
                PushPattern(4, 1, 3, 1, 1, 1);
                break;
            case 78:
                PushPattern(2, 4, 1, 1, 1, 2);
                break;
            case 79:
                PushPattern(1, 3, 4, 1, 1, 1);
                break;
            case 80:
                PushPattern(1, 1, 1, 2, 4, 2);
                break;
            case 81:
                PushPattern(1, 2, 1, 1, 4, 2);
                break;
            case 82:
                PushPattern(1, 2, 1, 2, 4, 1);
                break;
            case 83:
                PushPattern(1, 1, 4, 2, 1, 2);
                break;
            case 84:
                PushPattern(1, 2, 4, 1, 1, 2);
                break;
            case 85:
                PushPattern(1, 2, 4, 2, 1, 1);
                break;
            case 86:
                PushPattern(4, 1, 1, 2, 1, 2);
                break;
            case 87:
                PushPattern(4, 2, 1, 1, 1, 2);
                break;
            case 88:
                PushPattern(4, 2, 1, 2, 1, 1);
                break;
            case 89:
                PushPattern(2, 1, 2, 1, 4, 1);
                break;
            case 90:
                PushPattern(2, 1, 4, 1, 2, 1);
                break;
            case 91:
                PushPattern(4, 1, 2, 1, 2, 1);
                break;
            case 92:
                PushPattern(1, 1, 1, 1, 4, 3);
                break;
            case 93:
                PushPattern(1, 1, 1, 3, 4, 1);
                break;
            case 94:
                PushPattern(1, 3, 1, 1, 4, 1);
                break;
            case 95:
                PushPattern(1, 1, 4, 1, 1, 3);
                break;
            case 96:
                PushPattern(1, 1, 4, 3, 1, 1);
                break;
            case 97:
                PushPattern(4, 1, 1, 1, 1, 3);
                break;
            case 98:
                PushPattern(4, 1, 1, 3, 1, 1);
                break;
            case 99:
                PushPattern(1, 1, 3, 1, 4, 1);
                break;
            case 100:
                PushPattern(1, 1, 4, 1, 3, 1);
                break;
            case 101:
                PushPattern(3, 1, 1, 1, 4, 1);
                break;
            case 102:
                PushPattern(4, 1, 1, 1, 3, 1);
                break;
            case 103:
                PushPattern(2, 1, 1, 4, 1, 2);
                break;
            case 104:
                PushPattern(2, 1, 1, 2, 1, 4);
                break;
            case 105:
                PushPattern(2, 1, 1, 2, 3, 2);
                break;
            case 106:
                PushPattern(2, 3, 3, 1, 1, 1, 2);
                break;
        }
    }

    private void PushPattern(int b1, int s1, int b2, int s2, int b3, int s3) {
        PushPattern(b1, s1, b2, s2, b3, s3, 0);
    }

    private void PushPattern(int b1, int s1, int b2, int s2, int b3, int s3, int b5) {
        PushDot(false, b1);
        PushDot(true, s1);
        PushDot(false, b2);
        PushDot(true, s2);
        PushDot(false, b3);
        PushDot(true, s3);
        PushDot(false, b5);
    }

    private void PushDot(boolean isWhite, int dot) {
        for (int i = 0; i < dot; i++) {
            boolean[] zArr = this.tags;
            int i2 = this.pivot;
            this.pivot = i2 + 1;
            zArr[i2] = isWhite;
        }
    }
}

package com.ebaonet.pharmacy.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Time;

import com.ebaonet.pharmacy.logger.Logger;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class containing some static utility methods.
 */
public class Utils {

    private static final String TAG = "Utils";
    /**
     * 缓存路径
     */
    public static String CACHE_DIR = "/cache";

    public static String FILES_DIR = "/files";

    public static String PHARMACY = "/pharmacy";

    private Utils() {
    }

    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static File getExternalCacheDir(Context context) {
        if (hasFroyo()) {
            File file = context.getExternalCacheDir();
            if (file != null)
                return file;
        }
        // Before Froyo we need to construct the external cache dir ourselves
        File file = new File(Environment.getExternalStorageDirectory()
                .getPath() + CACHE_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getExternalFilesDir(Context context, String fileType) {
        if (hasFroyo()) {
            File mFiles = context.getExternalFilesDir(null);
            if (mFiles == null) {
                Logger.d("the machine is not have external file ");
            } else {
                Logger.d("the machine have external file ");
                File file = new File(mFiles.getPath() + fileType);
                if (file != null)
                    return file;
            }
        }
        // Before Froyo we need to construct the external cache dir ourselves
        File file = new File(Environment.getExternalStorageDirectory()
                .getPath() + FILES_DIR + fileType);
        if (!file.exists()) {
            file.mkdirs();
        }
        Logger.d("the external file path is " + file.getPath());
        return file;
    }

    @TargetApi(9)
    public static boolean isExternalStorageRemovable() {
        if (Utils.hasGingerbread()) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    public static File getExternalCacheDir(Context context, String dir) {
        File file = new File(getExternalCacheDir(context).getPath()
                + File.separator + dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static String getDiskCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use
        // external cache dir
        // otherwise use internal cache dir
        boolean isMounted = Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState()) || !isExternalStorageRemovable();
        File cacheFile;
        if (isMounted) {
            cacheFile = getExternalCacheDir(context);
            if (!cacheFile.exists()) {
                cacheFile.mkdirs();
            }
            if (!cacheFile.canWrite()) {
                cacheFile = context.getCacheDir();
            }
        } else {
            cacheFile = context.getCacheDir();
        }
        File file = new File(cacheFile.getPath() + File.separator + uniqueName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    public static String getDiskFilesDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use
        // external cache dir
        // otherwise use internal cache dir
        boolean isMounted = Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState()) || !isExternalStorageRemovable();
        File cacheFile;
        if (isMounted) {
            cacheFile = getExternalFilesDir(context, PHARMACY);
            if (!cacheFile.exists()) {
                cacheFile.mkdirs();
            }
            if (!cacheFile.canWrite()) {
                cacheFile = context.getCacheDir();
            }
        } else {
            cacheFile = context.getCacheDir();
        }
        File file = new File(cacheFile.getPath() + File.separator + uniqueName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }


    public static boolean isAvailableExternalStorage() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState()) || !isExternalStorageRemovable();
    }

    public static String formatTimeStampString(Context context, long when,
                                               boolean fullFormat) {
        // php时间戳转换
        Time then = new Time();
        then.set(when);
        Time now = new Time();
        now.setToNow();

        // Basic settings for formatDateTime() we want for all cases.
        int format_flags = DateUtils.FORMAT_NO_NOON_MIDNIGHT
                | DateUtils.FORMAT_ABBREV_ALL | DateUtils.FORMAT_CAP_AMPM;

        // If the message is from a different year, show the date and year.
        if (then.year != now.year) {
            format_flags |= DateUtils.FORMAT_SHOW_YEAR
                    | DateUtils.FORMAT_SHOW_DATE;
        } else if (then.yearDay != now.yearDay) {
            // If it is from a different day than today, show only the date.
            format_flags |= DateUtils.FORMAT_SHOW_DATE;
        } else {
            // Otherwise, if the message is from today, show the time.
            format_flags |= DateUtils.FORMAT_SHOW_TIME;
        }

        // If the caller has asked for full details, make sure to show the date
        // and time no matter what we've determined above (but still make
        // showing
        // the year only happen if it is a different year from today).
        if (fullFormat) {
            format_flags |= (DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME);
        }

        return DateUtils.formatDateTime(context, when, format_flags);
    }

    public static String md5(String desc) {
        StringBuilder sb = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(desc.getBytes());
            sb = new StringBuilder();
            for (byte b : result) {
                String hexString = Integer.toHexString(b & 0xFF);
                if (hexString.length() == 1) {
                    sb.append("0" + hexString);// 0~F
                } else {
                    sb.append(hexString);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isURL(String s) {
        return s != null ? s.toLowerCase().startsWith("http://") : false;

    }

    public static String getCacheDir(Context mContext) {
        return getDiskCacheDir(mContext, "global");
    }

    public static String formatDate(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int duration = 0;
        try {
            Date d = df.parse(date);
            long curTime = System.currentTimeMillis();
            long saveTime = d.getTime();
            duration = (int) ((curTime - saveTime) / 1000);
            if (duration < 3600 && duration > 0) {
                date = getTime(duration);
            } else {
                date = df.format(d);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getAppVersion(Context context) {
        String version;// 版本号
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            version = pi.versionName;// 获取在AndroidManifest.xml中配置的版本号
        } catch (PackageManager.NameNotFoundException e) {
            version = "";
        }
        return version;
    }

    public static String getTime(int duration) {
        int time = 0;
        if (duration % 60 > 0) {
            time = duration / 60 + 1;
        } else {
            time = duration / 60;
        }
        return time + "分钟前";
    }

    public static String getExt(String s) {
        int pos = 0;
        if (Utils.isURL(s)) {
            pos = s.lastIndexOf("?");
            s = pos > 0 ? s.substring(0, pos) : s;
            pos = s.lastIndexOf("/");
            s = pos > 0 ? s.substring(pos + 1, s.length()) : s;
        }
        pos = s.lastIndexOf(".");
        String ext = pos > 0 ? s.substring(pos + 1, s.length()) : "";
        return ext;
    }

    public static String getCacheFileName(String s, Context mContext) {
        return Utils.getCacheDir(mContext) + "/" + Utils.md5(s) + "." + Utils.getExt(s);
    }

    public static void saveFiles(Bitmap bimMap, String url) {
        try {
            final File sdcard = Environment.getExternalStorageDirectory();
            final File file = new File(sdcard.getPath() + "/ilook/"
                    + url.substring(url.lastIndexOf("/") + 1));
            bimMap.compress(CompressFormat.JPEG, 100,
                    new FileOutputStream(file));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存到应用内
     *
     * @param data
     * @param fn
     * @return
     */
    public static boolean saveToRam(String data, String fn, Context mContext) {
        BufferedOutputStream nout = null;
        try {
            FileOutputStream fos = mContext.openFileOutput(fn, Context.MODE_PRIVATE);
            nout = new BufferedOutputStream(fos, 8192);
            nout.write(data.getBytes());
            nout.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (nout != null) {
                try {
                    nout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean save(String data, String fn, Context mContext) {
        BufferedOutputStream nout = null;
        fn = Utils.getCacheFileName(fn, mContext);
        try {
            nout = new BufferedOutputStream(new FileOutputStream(fn), 8192);
            nout.write(data.getBytes());
            nout.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (nout != null) {
                try {
                    nout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean save(byte[] data, String fn, Context mContext) {
        fn = Utils.getCacheFileName(fn, mContext);
        try {
            FileOutputStream fos = new FileOutputStream(fn);
            fos.write(data);
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**********
     * Class Utils
     **************/
    public static Class<?> getClass(String s) {
        Class<?> c = null;
        try {
            if (s != null && !s.trim().equals(""))
                c = Class.forName(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public static Object getClassObject(String s) {
        Class<?> c = getClass(s);
        Object o = null;
        try {
            if (c != null)
                o = c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    public static Method getMethod(Class<?> c, String sMethod, Class<?>[] p) {
        Method method = null;
        try {
            method = c.getMethod(sMethod, p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }

    /******************
     * REQUEST UTILS
     ******************/
    public static String getUrl(String url, Map<String, String> param) {
        try {
            if (param != null) {
                String re = "\\{([^\\{\\}]+)\\}";
                Pattern p = Pattern.compile(re);
                Matcher m = p.matcher(url);
                while (m.find()) {
                    String k2 = m.group(1);
                    if (param.containsKey(k2)) {
                        String ph = "{" + k2 + "}";
                        url = url.replace(ph, param.get(k2));
                        param.remove(k2);
                    }
                }
            }
            Map<String, String> map = new HashMap<String, String>();
            URL aURL = new URL(url);
            String q = aURL.getQuery();
            if (q != null) {
                String[] arr = q.split("&");
                for (int i = 0; i < arr.length; i++) {
                    String[] a = arr[i].split("=");
                    String k = a[0];
                    String v = a.length > 1 ? a[1] : "";
                    String re = "\\{([^\\{\\}]+)\\}";
                    Pattern p = Pattern.compile(re);
                    Matcher m = p.matcher(v);
                    if (m.find()) {
                        String k2 = m.group(1);
                        v = "";
                        if (param != null && param.containsKey(k2)) {
                            v = param.get(k2);
                            param.remove(k2);
                        }
                    }
                    map.put(k, v);
                }
            }
            if (param != null) {
                Iterator<String> ite = param.keySet().iterator();
                while (ite.hasNext()) {
                    String k = ite.next();
                    String v = param.get(k);
                    map.put(k, v);
                }
            }
            int pos = url.indexOf('?');
            String baseUrl = pos > 0 ? url.substring(0, pos) : url;
            StringBuffer newUrl = new StringBuffer(baseUrl);
            String sep = null;
            for (String k : map.keySet()) {
                sep = sep == null ? "?" : "&";
                newUrl.append(sep);
                newUrl.append(k + "=" + map.get(k));
            }
            return newUrl.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int compareVersion(String version1, String version2) {
        String[] a1 = version1.split("\\.");
        String[] a2 = version2.split("\\.");
        int n = a1.length > a2.length ? a2.length : a1.length;
        for (int i = 0; i < n; i++) {
            int v1 = Integer.parseInt(a1[i]);
            int v2 = Integer.parseInt(a2[i]);
            if (v1 == v2)
                continue;
            return v1 > v2 ? 1 : -1;
        }
        if (a1.length == a2.length)
            return 0;
        return a1.length > a2.length ? 1 : -1;
    }

    public static String encodeUrl(String url) {
        StringBuffer newUrl = null;
        try {
            URL aURL = new URL(url);
            newUrl = new StringBuffer(aURL.getProtocol());
            newUrl.append("://");
            newUrl.append(aURL.getHost());
            if (aURL.getPort() > 0) {
                newUrl.append(":").append(aURL.getPort());
            }
            String path = aURL.getPath();
            String endSep = path.endsWith("/") ? "/" : "";
            String arr[] = path.split("/");
            int n = arr.length;
            for (int i = 0; i < n; i++) {
                String s = arr[i];
                if (!s.equals(""))
                    newUrl.append("/").append(URLEncoder.encode(s, "UTF-8"));
            }
            newUrl.append(endSep);
            if (aURL.getQuery() != null) {
                newUrl.append("?");
                String a[] = aURL.getQuery().split("&");
                int n1 = a.length;
                String sep = null;
                for (int i = 0; i < n1; i++) {
                    String s = a[i];
                    String a2[] = s.split("=");
                    int n2 = a2.length;
                    if (n2 < 1)
                        continue;
                    String k = a2[0];
                    String v = n2 > 1 ? a2[1] : null;
                    sep = sep == null ? "" : "&";
                    newUrl.append(sep).append(k);
                    if (v != null)
                        newUrl.append("=")
                                .append(URLEncoder.encode(v, "UTF-8"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newUrl.toString();
    }

    public static String getStringFromFile(File f) {
        FileInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            reader = new BufferedReader(isr);
            StringBuffer s = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (Exception e) {
            Logger.e(TAG, e);
        } finally {
            StreamUtil.Release(fis);
            StreamUtil.Release(reader);
        }
        return null;
    }

    public static String replaceDefaultString(String str) {
        return TextUtils.isEmpty(str) ? "--" : str;
    }

    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        // p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        p = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}

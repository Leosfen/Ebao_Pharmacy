package com.ebaonet.pharmacy.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class UploadUtil {

    private static final String UPLOAD = "upload";

    /**
     * 图片压缩并保存到文件
     */
    public static void compressImage(Bitmap image, File file, Context mContext) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream out = null;
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 1024) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;// 每次都减少10
        }
        byte[] byteArray = baos.toByteArray();
        try {
            out = new FileOutputStream(file);
            out.write(byteArray, 0, byteArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap revitionImageSize(String path) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            if ((options.outWidth >> i <= 1000)
                    && (options.outHeight >> i <= 1000)) {
                in = new BufferedInputStream(
                        new FileInputStream(new File(path)));
                options.inSampleSize = (int) Math.pow(2.0D, i);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }

    public static void saveBitmap(Bitmap bm, String picName, Context mContext) {
        Log.e("", "保存图片");
        String sdPath = Utils.getDiskCacheDir(mContext, UPLOAD);
        try {
            File f = new File(sdPath, picName + ".jpg");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Log.e("", "已经保存");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFileExist(String fileName, Context mContext) {
        String sdPath = Utils.getDiskCacheDir(mContext, UPLOAD);
        File file = new File(sdPath + fileName);
        file.isFile();
        return file.exists();
    }

    public static void delFile(String fileName, Context mContext) {
        String sdPath = Utils.getDiskCacheDir(mContext, UPLOAD);
        File file = new File(sdPath + fileName);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

    public static void deleteDir(Context mContext) {
        String sdPath = Utils.getDiskCacheDir(mContext, UPLOAD);
        File dir = new File(sdPath);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(mContext); // 递规的方式删除文件夹
        }
//		dir.delete();// 删除目录本身
    }

    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {

            return false;
        }
        return true;
    }
}

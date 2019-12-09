package com.shouhulife.utilslibrary.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 给TextView 设置上下左右图片
 */
public class PictureUtil {
    public static void setDrawableTop(TextView textView, int resId) {
        Resources resources = textView.getContext().getResources();
        BitmapDrawable drawable = new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, resId));
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    public static void setDrawableLeft(TextView textView, int resId) {
        Resources resources = textView.getContext().getResources();
        BitmapDrawable drawable = new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, resId));
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public static void setDrawableRight(TextView textView, int resId) {
        Resources resources = textView.getContext().getResources();
        BitmapDrawable drawable = new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, resId));
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    public static void setDrawableBottom(TextView textView, int resId) {
        Resources resources = textView.getContext().getResources();
        BitmapDrawable drawable = new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, resId));
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, null, drawable);
    }

    public static void setDrawable(TextView textView,int left,int right){
        Resources resources = textView.getContext().getResources();
        BitmapDrawable leftDrawable = new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, left));
        leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());

        BitmapDrawable rightDrawable = new BitmapDrawable(resources, BitmapFactory.decodeResource(resources, right));
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());

        textView.setCompoundDrawables(leftDrawable, null, rightDrawable, null);
    }

    public static void setNullDrawable(TextView textView){
        textView.setCompoundDrawables(null, null, null, null);
    }


    /**
     * n
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 根据路径获得突破并压缩返回bitmap用于显示
     *
     * @param
     * @return
     */
    public static byte[] getSmallBitmap(String filePath, int option) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 320, 640);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        if (bitmap != null) {
            return compressImage(bitmap, option);
        } else {
            return null;
        }

    }

    public static void compressImage(Bitmap image, String filePath, int size) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > size && options > 0) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            baos.writeTo(fos);
            baos.flush();
            fos.flush();
            baos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] getImageData(String imagePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    public static byte[] compressImage(String imagePath, int size) {
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (bitmap.getHeight() / bitmap.getWidth() > 3) {
            size = 1024;
        }
        int options = 100;
        while (baos.toByteArray().length / 1024 > size && options > 0) {
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        return baos.toByteArray();
    }

    public static byte[] getimage(String srcPath, int kb) {
        if (TextUtils.isEmpty(srcPath)) {
            return null;
        }
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        if (w != 0 && (h / w) <= 3) {
            //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
            float hh = 1920f;//这里设置高度为800f
            float ww = 1080f;//这里设置宽度为480f
            //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;//be=1表示不缩放
            if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
                be = (int) (newOpts.outWidth / ww);
            } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
                be = (int) (newOpts.outHeight / hh);
            }
            if (be <= 0) {
                be = 1;
            }
            newOpts.inSampleSize = be;//设置缩放比例
            //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        }

        newOpts.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap, kb);//压缩好比例大小后再进行质量压缩
    }

    /**
     * 获取图片角度
     *
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


    /**
     * @param angle
     * @param path
     */
    public static void rotaingImageView(int angle, String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        try {
            Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            saveBitmap(bmp, path);
        } catch (OutOfMemoryError e) {
        }
    }

    public static boolean saveBitmap(Bitmap bitmap, String path) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream(file);
            baos.writeTo(fos);
            baos.close();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static byte[] getBigBitmap(String filePath, int option) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 720, 1024);
        // Decode bitmap with inSampleSize set
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        if (bitmap != null) {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            return baos.toByteArray();
        } else {
            return null;
        }
    }


    /**
     * 添加到图库
     */
    public static void galleryAddPic(Context context, String path) {
        Intent mediaScanIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(path);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }


    public static byte[] compressImage(Bitmap image, int kb) {
        if (image == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        if (baos.toByteArray().length / 1024 > 2048) {
            baos.reset();// 重置baos即清空baos
//            LogUtil.i("！！！！！！！！！！", options);
            image.compress(Bitmap.CompressFormat.JPEG, 35, baos);
            options = 35;
        } else if (baos.toByteArray().length / 1024 > 1024) {
            image.compress(Bitmap.CompressFormat.JPEG, 25, baos);
            options = 50;
        }
        while (baos.toByteArray().length / 1024 > kb && options > 1) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
//            LogUtil.i("！！！！！！！！！！", options);
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            if (options >= 35) {
                options -= 15;// 每次都减少10
            } else if (options > 4) {
                options -= 4;
            } else {
                options = 1;
            }
        }
        // ByteArrayInputStream isBm = new
        // ByteArrayInputStream(baos.toByteArray());//
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        // Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//
        // 把ByteArrayInputStream数据生成图片
//        LogUtil.i("imag_size", baos.toByteArray().length / 1024);
        return baos.toByteArray();
    }
}

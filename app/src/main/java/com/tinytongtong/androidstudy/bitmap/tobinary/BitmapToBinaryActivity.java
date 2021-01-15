package com.tinytongtong.androidstudy.bitmap.tobinary;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.tinytongtong.androidstudy.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @Description: View截图数据转成二进制字节流
 * 结论：
 * ①bitmap占用内存的大小，跟图片宽高（像素点）有关（如果是drawable目录下资源文件的话，还跟手机的dpi等级），跟图片元素的体积关系不大。
 * ②将Bitmap数据转换成字节流有两种方式：ByteBuffer和Bitmap#compress
 * ——ByteBuffer方式只是读取bitmap的字节流，没有压缩操作。
 * ——Bitmap#compress方法获取到的字节流明显小于bitmap的字节数。
 * ——不管转换方式获取到的字节流，再次还原成bitmap后，bitmap的字节数大小跟原来一样。
 * @Author tinytongtong
 * @Date 2021/1/14 6:42 PM
 * @Version
 */
public class BitmapToBinaryActivity extends AppCompatActivity {
    private static final String TAG = BitmapToBinaryActivity.class.getSimpleName();
    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_to_binary);

        photoView = findViewById(R.id.photo_view);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maomi);
        ImageView imgSrc = findViewById(R.id.iv_src);
        imgSrc.setImageBitmap(bitmap);
        String logSrc = String.format("bitmap.getByteCount:%s, bitmap.getAllocationByteCount:%s",
                bitmap.getByteCount(), bitmap.getAllocationByteCount());
        Log.e(TAG, logSrc);// 921600
        TextView tvSrc = findViewById(R.id.tv_src);
        StringBuilder sbSrc = new StringBuilder("原图：\n");
        sbSrc.append(logSrc);
        tvSrc.setText(sbSrc);
        imgSrc.setOnClickListener(v -> showPreview(bitmap));

        byte[] bytesJPEG100 = getBitmapByte(bitmap, Bitmap.CompressFormat.JPEG, 100);
        String logJPEG1001 = String.format("bytesJPEG100.length:%s", bytesJPEG100.length);
        Log.e(TAG, logJPEG1001);// 86620
        Bitmap bitmapJPEG100 = getBitmapFromByte(bytesJPEG100);
        ImageView imgRestoreJPEG100 = findViewById(R.id.iv_restore_jpeg_100);
        imgRestoreJPEG100.setImageBitmap(bitmapJPEG100);
        String logJPEG1002 = String.format("bitmapJPEG100.getByteCount:%s, bitmapJPEG100.getAllocationByteCount:%s",
                bitmapJPEG100.getByteCount(), bitmapJPEG100.getAllocationByteCount());
        Log.e(TAG, logJPEG1002);// 921600
        TextView tvJPEG100 = findViewById(R.id.tv_restore_jpeg_100);
        StringBuilder sbJPEG100 = new StringBuilder("JPEG-100-还原后的图：\n");
        sbJPEG100.append(logJPEG1001)
                .append("\n")
                .append(logJPEG1002);
        tvJPEG100.setText(sbJPEG100);
        imgRestoreJPEG100.setOnClickListener(v -> showPreview(bitmapJPEG100));

        byte[] bytesJPEG50 = getBitmapByte(bitmap, Bitmap.CompressFormat.JPEG, 50);
        String logJPEG501 = String.format("bytesJPEG50.length:%s", bytesJPEG50.length);
        Log.e(TAG, logJPEG501);
        Bitmap bitmapJPEG50 = getBitmapFromByte(bytesJPEG50);
        ImageView imgRestoreJPEG50 = findViewById(R.id.iv_restore_jpeg_50);
        imgRestoreJPEG50.setImageBitmap(bitmapJPEG50);
        String logJPEG502 = String.format("bitmapJPEG50.getByteCount:%s, bitmapJPEG50.getAllocationByteCount:%s",
                bitmapJPEG50.getByteCount(), bitmapJPEG50.getAllocationByteCount());
        Log.e(TAG, logJPEG502);
        TextView tvJPEG50 = findViewById(R.id.tv_restore_jpeg_50);
        StringBuilder sbJPEG50 = new StringBuilder("JPEG-50-还原后的图：\n");
        sbJPEG50.append(logJPEG501)
                .append("\n")
                .append(logJPEG502);
        tvJPEG50.setText(sbJPEG50);
        imgRestoreJPEG50.setOnClickListener(v -> showPreview(bitmapJPEG50));

        Log.e(TAG, "-----------------------------------------------");
        byte[] bytesJPEGTargetSize = getBytesFromCompressedBitmap(bitmap, 32 * 1024);
        String logTargetSize1 = String.format("bytesJPEGTargetSize.length:%s", bytesJPEGTargetSize.length);
        Log.e(TAG, logTargetSize1);
        Bitmap bitmapJPEGTargetSize = getBitmapFromByte(bytesJPEGTargetSize);
        ImageView imgRestoreJPEGTargetSize = findViewById(R.id.iv_restore_jpeg_target_size);
        imgRestoreJPEGTargetSize.setImageBitmap(bitmapJPEGTargetSize);
        String logTargetSize2 = String.format("bitmapJPEGTargetSize.getByteCount:%s, bitmapJPEGTargetSize.getAllocationByteCount:%s",
                bitmapJPEGTargetSize.getByteCount(), bitmapJPEGTargetSize.getAllocationByteCount());
        Log.e(TAG, logTargetSize2);
        TextView tvTargetSize = findViewById(R.id.tv_restore_jpeg_target_size);
        StringBuilder sbTargetSize = new StringBuilder("JPEG-目标大小-还原后的图：\n");
        sbTargetSize.append(logTargetSize1)
                .append("\n")
                .append(logTargetSize2);
        tvTargetSize.setText(sbTargetSize);
        imgRestoreJPEGTargetSize.setOnClickListener(v -> showPreview(bitmapJPEGTargetSize));

        Log.e(TAG, "-----------------------------------------------");
        byte[] bytesPNG100 = getBitmapByte(bitmap, Bitmap.CompressFormat.PNG, 100);
        String logPNG1001 = String.format("bytesPNG100.length:%s", bytesPNG100.length);
        Log.e(TAG, logPNG1001);
        Bitmap bitmapPNG100 = getBitmapFromByte(bytesPNG100);
        ImageView imgRestorePNG100 = findViewById(R.id.iv_restore_png_100);
        imgRestorePNG100.setImageBitmap(bitmapPNG100);
        String logPNG1002 = String.format("bitmapPNG100.getByteCount:%s, bitmapPNG100.getAllocationByteCount:%s",
                bitmapPNG100.getByteCount(), bitmapPNG100.getAllocationByteCount());
        Log.e(TAG, logPNG1002);
        TextView tvPNG100 = findViewById(R.id.tv_restore_png_100);
        StringBuilder sbPNG100 = new StringBuilder("PNG-100-还原后的图：\n");
        sbPNG100.append(logPNG1001)
                .append("\n")
                .append(logPNG1002);
        tvPNG100.setText(sbPNG100);
        imgRestorePNG100.setOnClickListener(v -> showPreview(bitmapPNG100));

        byte[] bytesPNG50 = getBitmapByte(bitmap, Bitmap.CompressFormat.PNG, 50);
        String logPNG501 = String.format("bytesPNG50.length:%s", bytesPNG50.length);
        Log.e(TAG, logPNG501);
        Bitmap bitmapPNG50 = getBitmapFromByte(bytesPNG50);
        ImageView imgRestorePNG50 = findViewById(R.id.iv_restore_png_50);
        imgRestorePNG50.setImageBitmap(bitmapPNG50);
        String logPNG502 = String.format("bitmapPNG50.getByteCount:%s, bitmapPNG50.getAllocationByteCount:%s",
                bitmapPNG50.getByteCount(), bitmapPNG50.getAllocationByteCount());
        Log.e(TAG, logPNG502);
        TextView tvPNG50 = findViewById(R.id.tv_restore_png_50);
        StringBuilder sbPNG50 = new StringBuilder("PNG-50-还原后的图：\n");
        sbPNG50.append(logPNG501)
                .append("\n")
                .append(logPNG502);
        tvPNG50.setText(sbPNG50);
        imgRestorePNG50.setOnClickListener(v -> showPreview(bitmapPNG50));

        Log.e(TAG, "-----------------------------------------------");
        byte[] bytesByteBuffer = bmpToByteArrayByByteBuffer(bitmap);
        String logByteBuffer1 = String.format("bytesByteBuffer.length:%s", bytesByteBuffer.length);
        Log.e(TAG, logByteBuffer1);
        Bitmap bitmapRestoreByteBuffer = byteArrayToBmpByByteBuffer(bitmap, bytesByteBuffer);
        ImageView imgRestoreByteBuffer = findViewById(R.id.iv_restore_byte_buffer);
        imgRestoreByteBuffer.setImageBitmap(bitmapRestoreByteBuffer);
        String logByteBuffer2 = String.format("bitmapRestoreByteBuffer.getByteCount:%s, bitmapRestoreByteBuffer.getAllocationByteCount:%s",
                bitmapRestoreByteBuffer.getByteCount(), bitmapRestoreByteBuffer.getAllocationByteCount());
        Log.e(TAG, logByteBuffer2);
        TextView tvByteBuffer = findViewById(R.id.tv_restore_byte_buffer);
        StringBuilder sbByteBuffer = new StringBuilder("ByteBuffer-还原后的图：\n");
        sbByteBuffer.append(logByteBuffer1)
                .append("\n")
                .append(logByteBuffer2);
        tvByteBuffer.setText(sbByteBuffer);
        imgRestoreByteBuffer.setOnClickListener(v -> showPreview(bitmapRestoreByteBuffer));
    }

    private void showPreview(Bitmap bitmap) {
        photoView.setImageBitmap(bitmap);
        photoView.setVisibility(View.VISIBLE);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoView.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 压缩bitmap，同时获取字节流
     * https://www.jianshu.com/p/7927a264f5ab
     *
     * @param bitmap
     * @param format
     * @param quality
     * @return
     */
    public byte[] getBitmapByte(Bitmap bitmap, Bitmap.CompressFormat format, int quality) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //参数1转换类型，参数2压缩质量，参数3字节流资源
        bitmap.compress(format, quality, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    /**
     * 将二进制流转换成图片（Bitmap）
     * https://www.jianshu.com/p/7927a264f5ab
     *
     * @param temp
     * @return
     */
    public Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        } else {
            return null;
        }
    }

    /**
     * https://stackoverflow.com/questions/4989182/converting-java-bitmap-to-byte-array
     * 原封不动的copy字节流，不压缩
     *
     * @param bitmap
     * @return
     */
    public byte[] bmpToByteArrayByByteBuffer(Bitmap bitmap) {
        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(byteBuffer);
        return byteBuffer.array();
    }

    /**
     * https://stackoverflow.com/questions/4989182/converting-java-bitmap-to-byte-array
     *
     * @param bitmap
     * @param byteArray
     * @return
     */
    public Bitmap byteArrayToBmpByByteBuffer(Bitmap bitmap, byte[] byteArray) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap.Config configBmp = Bitmap.Config.valueOf(bitmap.getConfig().name());
        Bitmap bitmap_tmp = Bitmap.createBitmap(width, height, configBmp);
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        bitmap_tmp.copyPixelsFromBuffer(buffer);
        return bitmap_tmp;
    }

    /**
     * 将Bitmap的字节流压缩为目标大小以下
     *
     * @param src        字节流
     * @param targetSize 目标大小，单位B
     * @return 字节流
     */
    private static byte[] compressBitmapBytes2TargetSize(byte[] src, int targetSize) {
        Log.e(TAG, String.format("compressBitmapBytes2TargetSize src.length:%s, targetSize:%s", src.length, targetSize));
        Bitmap bmp = BitmapFactory.decodeByteArray(src, 0, src.length);
        byte[] result = getBytesFromCompressedBitmap(bmp, targetSize);
        if (!bmp.isRecycled()) {
            bmp.recycle();
        }
        return result;
    }

    /**
     * 将Bitmap的字节流压缩为目标大小以下
     *
     * @param bitmap     Bitmap
     * @param targetSize 目标大小，单位bit
     * @return 字节流
     */
    private static byte[] getBytesFromCompressedBitmap(Bitmap bitmap, int targetSize) {
        Log.e(TAG, String.format("getBytesFromCompressedBitmap bitmap.getByteCount():%s, targetSize:%s", bitmap.getByteCount(), targetSize));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        Log.e(TAG, String.format("getBytesFromCompressedBitmap bytes.length:%s, quality:%s", bytes.length, quality));
        while (bytes.length > targetSize && quality >= 5) {
            quality -= 5;
            if (quality < 0) {
                quality = 0;
            }
            // 重置，不然会累加
            baos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            bytes = baos.toByteArray();
            Log.e(TAG, String.format("getBytesFromCompressedBitmap bytes.length:%s, quality:%s", bytes.length, quality));
        }
        try {
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 将Bitmap的字节流压缩到目标大小以下
     *
     * @param src        Bitmap
     * @param targetSize 目标大小
     * @return Bitmap
     */
    private static Bitmap getBitmapFromCompressBitmap(Bitmap src, int targetSize) {
        if (src == null) {
            return src;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        src.compress(Bitmap.CompressFormat.PNG, quality, baos);
        byte[] bytes = baos.toByteArray();
        while (bytes.length > targetSize && quality >= 5) {
            quality -= 5;
            if (quality < 0) {
                quality = 0;
            }
            // 重置，不然会累加
            baos.reset();
            src.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            bytes = baos.toByteArray();
        }
        try {
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap dst = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return dst;

    }
}
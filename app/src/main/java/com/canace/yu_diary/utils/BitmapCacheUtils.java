package com.canace.yu_diary.utils;

import android.graphics.Bitmap;
import android.os.Handler;

import org.xutils.common.util.LogUtil;

/**
 * 图片三级缓存的工具类
 */
public class BitmapCacheUtils {
    /**
     * 网络缓存工具类
     */
    private NetCacheUtils netCacheUtils;

    /**
     * 本地缓存工具类
     */
    private LocalCacheUtils localCacheUtils;

    /**
     * 内存缓存工具类
     */
    private MemoryCacheUtils memoryCacheUtils;

    public BitmapCacheUtils(Handler handler){
        memoryCacheUtils = new MemoryCacheUtils();
        localCacheUtils = new LocalCacheUtils(memoryCacheUtils);
        netCacheUtils = new NetCacheUtils(handler,localCacheUtils,memoryCacheUtils);
    }


    public Bitmap getBitmap(String imageUrl, int position) {
        // 1.从内存中取图片
        if (memoryCacheUtils != null){
            Bitmap bitmap = memoryCacheUtils.getBitmapFromUrl(imageUrl);
            if (bitmap != null){
                LogUtil.e("内存加载图片成功==" + position);
                return  bitmap;
            }
        }
        // 2.从本地文件中取图片
        if (localCacheUtils != null){
            Bitmap bitmap = localCacheUtils.getBitmapFromUrl(imageUrl);
            if (bitmap != null){
                LogUtil.e("本地加载图片成功==" + position);
                return  bitmap;
            }
;        }

        // 3.从网络取图片
        netCacheUtils.getBitmapFomNet(imageUrl,position);
        return null;
    }
}

package com.canace.yu_diary.utils;

import android.graphics.Bitmap;

import org.xutils.cache.LruCache;

/**
 * 内存缓存工具类
 */
class MemoryCacheUtils {

    /**
     * 集合
     */
    private LruCache<String, Bitmap> lruCache;

    public MemoryCacheUtils() {
        //使用了系统分配给应用程序的八分之一内存来作为缓存大小
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 1024 / 8);
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return (value.getRowBytes() * value.getHeight()) / 1024;
            }
        };
    }

    /**
     * 根据url从内存中获取图片
     *
     * @param imageUrl
     * @return
     */
    public Bitmap getBitmapFromUrl(String imageUrl) {
        return lruCache.get(imageUrl);
    }

    /**
     * 根据url保存图片到lruCache集合中
     *
     * @param imageUrl 图片路径
     * @param bitmap   图片
     */
    public void putBitmap(String imageUrl, Bitmap bitmap) {
        lruCache.put(imageUrl, bitmap);
    }
}

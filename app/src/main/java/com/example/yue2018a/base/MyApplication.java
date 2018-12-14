package com.example.yue2018a.base;

import android.app.Application;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * date:2018/12/21
 * author:mxy(M)
 * function:
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(getCacheDir())
                .build();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();

        Fresco.initialize(this, config);
    }
}

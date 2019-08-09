package com.hxh.handwriting.thread;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by HXH at 2019/8/9
 * Handler工具
 */
public class HandlerUtil {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public static void runOnUiThread(Runnable runnable) {
        HANDLER.post(runnable);
    }

    public static void runOnUiThreadDelay(Runnable runnable, long delayMillis) {
        HANDLER.postDelayed(runnable, delayMillis);
    }

    public static void removeRunnable(Runnable runnable) {
        HANDLER.removeCallbacks(runnable);
    }
}

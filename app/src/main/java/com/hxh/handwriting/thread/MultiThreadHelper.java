package com.hxh.handwriting.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by HXH at 2019/8/9
 * 多线程帮助类<br/>
 * example code:
 * <pre>{@code
 * ProgressDialog dialog = new ProgressDialog(this);
 *     dialog.show();
 *     long start = System.currentTimeMillis();
 *     System.out.println("start:" + start);
 * MultiThreadHelper<Integer> helper = MultiThreadHelper.runInThread(() -> {
 *         System.out.println("thread1:" + Thread.currentThread().getName());
 *         List<String> list = new ArrayList<>();
 *         for (int i = 0; i < 300000; i++) {
 *             list.add(UUID.randomUUID().toString());
 *         }
 *         return list.stream().map(String::length).reduce(0, (x, y) -> x + y);
 *     });
 *     helper.apply(integer -> {
 *         System.out.println("thread2:" + Thread.currentThread().getName());
 *         System.out.println("用时:" + (System.currentTimeMillis() - start));
 *         System.out.println("integer:" + integer);
 *         dialog.dismiss();
 *     });
 *     dialog.setOnCancelListener(dialog1 -> helper.stopOff());
 *     }
 *  or
 *  {@code
 *      MultiThreadHelper.runInThread(() -> {
 *             List<String> list = new ArrayList<>();
 *             for (int i = 0; i < 100000; i++) {
 *                 list.add(UUID.randomUUID().toString());
 *             }
 *             return list.stream().map(String::length).reduce(0, (x, y) -> x + y);
 *         }).apply(integer -> {
 *             System.out.println("integer:" + integer);
 *         });
 *  }
 * </pre>
 */
public class MultiThreadHelper<T> {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    private UIListener<T> reListener;
    private boolean isAlive = true;

    private MultiThreadHelper() {
    }

    /**
     * 子线程计算数据
     *
     * @param listener {@link ThreadListener}
     * @param <T>      返回数据类型
     * @return T类型数据
     */
    public static <T> MultiThreadHelper<T> runInThread(ThreadListener<T> listener) {
        MultiThreadHelper<T> tMultiThreadHelper = new MultiThreadHelper<>();
        EXECUTOR_SERVICE.execute(() -> {
            synchronized (tMultiThreadHelper) {
                while (tMultiThreadHelper.isAlive
                        && tMultiThreadHelper.reListener == null) {
                    try {
                        tMultiThreadHelper.wait();
                        if (!tMultiThreadHelper.isAlive) {
                            return;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            T t = listener.get();
            HandlerUtil.runOnUiThread(() -> {
                synchronized (tMultiThreadHelper) {
                    if (!tMultiThreadHelper.isAlive) {
                        return;
                    }
                }
                tMultiThreadHelper.reListener.apply(t);
            });
        });
        return tMultiThreadHelper;
    }

    /**
     * 中止
     */
    public synchronized void stopOff() {
        isAlive = false;
        notifyAll();
    }

    /**
     * UI线程运行结果
     *
     * @param reListener {@link UIListener}
     */
    public void apply(UIListener<T> reListener) {
        if (!isAlive) {
            return;
        }
        synchronized (this) {
            this.reListener = reListener;
            notifyAll();
        }
    }

    /**
     * 子线程运算方法
     *
     * @param <T>
     */
    @FunctionalInterface
    public interface ThreadListener<T> {
        T get();
    }

    /**
     * UI线程处理方法
     *
     * @param <T>
     */
    @FunctionalInterface
    public interface UIListener<T> {
        void apply(T t);
    }
}

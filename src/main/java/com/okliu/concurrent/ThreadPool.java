package com.okliu.concurrent;

import java.util.LinkedList;


/**
 * @author liuhaolie
 * @Time 2020/3/17-2:34
 */

public class ThreadPool {

    /**
     * 线程池大小
     */
    private int threadPoolSize;
    private final int DEFAULT_THREAD_POOL_SIZE = 5;

    /**
     * 任务容器
     */
    private LinkedList<Runnable> tasks = new LinkedList<Runnable>();

    public ThreadPool() {
        synchronized (tasks) {
            for (int i = 0; i < DEFAULT_THREAD_POOL_SIZE; i++) {
                new TaskConsumeThread("线程 " + i).start();
            }
        }
    }

    public ThreadPool(int threadPoolSize) {
        synchronized (tasks) {
            for (int i = 0; i < threadPoolSize; i++) {
                new TaskConsumeThread("线程 " + i).start();
            }
        }
    }

    public void add(Runnable r) {
        synchronized (tasks) {
            tasks.add(r);
            // 唤醒等待的任务消费者线程
            tasks.notifyAll();
        }
    }

    private class TaskConsumeThread extends Thread {
        public TaskConsumeThread(String name) {
            super(name);
        }

        private Runnable task;

        @Override
        public void run() {
            while (true) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.removeLast();
                    // 允许添加任务的线程可以继续添加任务
                    tasks.notifyAll();

                }
                task.run();
            }
        }
    }

}

package com.ozone.mfls.activemq;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName TestMq
 * @Author Ozone
 * @Description 测试ActiviMQ
 * @Date 2019/5/24 10:14
 * @Version 1.0
 **/
public class TestMq {
    public static void main(String[] args){
        Producter producter = new Producter();
        producter.init();
        TestMq testMq = new TestMq();
        int length = 15;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
//                new ArrayBlockingQueue<Runnable>(5));
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());




        for(int i=0;i<length;i++){
            ProductorMq myTask = testMq.new ProductorMq(producter);
            singleThreadPool.execute(myTask);

        }
        singleThreadPool.shutdown();
//        /* Thread 1 */
//        new Thread(testMq.new ProductorMq(producter)).start();
//        //Thread 2
//        new Thread(testMq.new ProductorMq(producter)).start();
//        //Thread 3
//        new Thread(testMq.new ProductorMq(producter)).start();
//        //Thread 4
//        new Thread(testMq.new ProductorMq(producter)).start();
//        //Thread 5
//        new Thread(testMq.new ProductorMq(producter)).start();
    }

    private class ProductorMq implements Runnable{
        Producter producter;
        public ProductorMq(Producter producter){
            this.producter = producter;
        }

        @Override
        public void run() {
            while(true){
                try {
                    producter.sendMessage("Jaycekon-MQ");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

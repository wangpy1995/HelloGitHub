package org.wpy.leetcode.multiThread;

import org.wpy.leetcode.multiThread.abs.MultiThreadQuestion;

import java.util.concurrent.atomic.AtomicBoolean;

public class Question1115 extends MultiThreadQuestion {

    private final int n;
    //foo打印操作是否准备就绪
    private final AtomicBoolean printFooPrepared;

    /**
     *
     * @param n 需要循环打印的次数
     */
    public Question1115(int n) {
        this.n = n;
        this.printFooPrepared = new AtomicBoolean(true);
    }

    public void foo() {
        for (int i = 0; i < n; i++) {
            //等待foo打印操作准备就绪再打印foo
            while (!printFooPrepared.get()) {
                //防止死循环超时, 让出cpu时间片重新竞争
                Thread.yield();
            }
            System.out.print("foo");
            //打印完成，将准备状态置为false
            printFooPrepared.set(false);
        }
    }

    public void bar() {
        for (int i = 0; i < n; i++) {
            //foo打印操作准备就绪，则等待foo打印完成再打印bar
            while (printFooPrepared.get()) {
                Thread.yield();
            }
            System.out.print("bar ");
            //bar打印结束，准备打印foo
            printFooPrepared.set(true);
        }
    }

    @Override
    protected void runA() {
        foo();
    }

    protected void runB() {
        bar();
    }

    public static void main(String[] args) {
        new Question1115(3).startThreadAndSleep(2000);
    }
}


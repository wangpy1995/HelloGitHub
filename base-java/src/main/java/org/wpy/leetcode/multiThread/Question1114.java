package org.wpy.leetcode.multiThread;

import org.wpy.leetcode.multiThread.abs.MultiThreadQuestion;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 三个不同的线程 A、B、C 将会共用一个Foo实例。
 * <p>
 * 一个将会调用 first() 方法
 * 一个将会调用second() 方法
 * 还有一个将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Question1114 extends MultiThreadQuestion {
    private int first;
    private int second;
    private int third;
    private final Runnable[] runnables;

    private AtomicInteger count = new AtomicInteger(0);

    public Question1114(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
        runnables = new Runnable[]{
                this::first,
                this::second,
                this::third
        };
    }

    public void first() {

        // printFirst.run() outputs "first". Do not change or remove this line.
        while (count.get() != 0) {
            Thread.yield();
        }
        System.out.println("first");
        count.addAndGet(1);
    }

    public void second() {

        // printSecond.run() outputs "second". Do not change or remove this line.
        while (count.get() != 1) {
            Thread.yield();
        }
        System.out.println("second");
        count.addAndGet(1);
    }

    public void third() {
        // printThird.run() outputs "third". Do not change or remove this line.
        while (count.get() != 2) {
            Thread.yield();
        }
        System.out.println("third");
    }

    @Override
    protected void runA() {
        runnables[first - 1].run();
    }

    @Override
    protected void runB() {
        runnables[second - 1].run();
    }

    @Override
    protected void runC() {
        runnables[third - 1].run();
    }

    public static void main(String[] args) {
        new Question1114(3, 1, 2).startThreadAndSleep(2000);
    }

}
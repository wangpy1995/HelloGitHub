package org.wpy.leetcode.multiThread;

import org.wpy.leetcode.multiThread.abs.MultiThreadQuestion;

import java.util.function.IntConsumer;

/**
 * 相同的一个ZeroEvenOdd类实例将会传递给三个不同的线程：
 * <p>
 * 线程 A 将调用zero()，它只输出 0 。
 * 线程 B 将调用even()，它只输出偶数。
 * 线程 C 将调用odd()，它只输出奇数。
 * 每个线程都有一个printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列010203040506... ，其中序列的长度必须为 2n。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Question1116 extends MultiThreadQuestion {
    private int n;
    private IntConsumer zeroConsumer;
    private IntConsumer evenConsumer;
    private IntConsumer oddConsumer;
    private int numEven = 2;
    private int numOdd = 1;
    private volatile boolean runZero = true;
    private volatile boolean runEven = false;

    public Question1116(int n) {
        this.n = n;
        this.zeroConsumer = System.out::print;
        this.evenConsumer = System.out::print;
        this.oddConsumer = System.out::print;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) {
        for (int i = 0; i < n; i++) {
            while (!runZero) {
                Thread.yield();
            }
            printNumber.accept(0);
            runZero = false;
        }
    }

    public void even(IntConsumer printNumber) {
        for (int i = 0; i < (n >> 1); i++) {
            while (runZero || !runEven) {
                Thread.yield();
            }
            printNumber.accept(numEven);
            numEven += 2;
            runEven = false;
            runZero = true;
        }
    }

    public void odd(IntConsumer printNumber) {
        for (int i = 0; i < ((n + 1) >> 1); i++) {
            while (runZero || runEven) {
                Thread.yield();
            }
            printNumber.accept(numOdd);
            numOdd += 2;
            runEven = true;
            runZero = true;
        }
    }

    @Override
    protected void runA() {
        zero(zeroConsumer);
    }

    @Override
    protected void runB() {
        even(evenConsumer);
    }

    @Override
    protected void runC() {
        odd(oddConsumer);
    }

    public static void main(String[] args) {
        new Question1116(5).startThreadAndSleep(2000);
    }
}

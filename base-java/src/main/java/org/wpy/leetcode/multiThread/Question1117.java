package org.wpy.leetcode.multiThread;

import java.util.concurrent.Semaphore;

/**
 * 现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。
 * 存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
 * 氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
 * 这些线程应该三三成组突破屏障并能立即组合产生一个水分子。
 * 你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
 * 换句话说:
 * 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
 * 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
 * 书写满足这些限制条件的氢、氧线程同步代码。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/building-h2o
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question1117 {
    private Semaphore semaphoreH;
    private Semaphore semaphoreO;

    public Question1117() {
        semaphoreH = new Semaphore(2);
        semaphoreO = new Semaphore(0);
    }

    public Runnable genRunnable(char ch) {
        return () -> System.out.print(ch);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        semaphoreH.acquire();
        releaseHydrogen.run();
        semaphoreO.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        semaphoreO.acquire(2);
        releaseOxygen.run();
        semaphoreH.release(2);
    }

    public static void main(String[] args) throws InterruptedException {
        Question1117 q = new Question1117();
        String str = "OHHHHOHHHOHHHOO";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'H') {
                new Thread(() -> {
                    try {
                        q.hydrogen(q.genRunnable(ch));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            } else {
                new Thread(() -> {
                    try {
                        q.oxygen(q.genRunnable(ch));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
        Thread.sleep(3000);
    }
}

package org.wpy.leetcode.multiThread;

import org.wpy.leetcode.multiThread.abs.MultiThreadQuestion;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 5 个沉默寡言的哲学家围坐在圆桌前，每人面前一盘意面。叉子放在哲学家之间的桌面上。（5 个哲学家，5 根叉子）
 * 所有的哲学家都只会在思考和进餐两种行为间交替。哲学家只有同时拿到左边和右边的叉子才能吃到面，而同一根叉子在同一时间只能被一个哲学家使用。每个哲学家吃完面后都需要把叉子放回桌面以供其他哲学家吃面。只要条件允许，哲学家可以拿起左边或者右边的叉子，但在没有同时拿到左右叉子时不能进食。
 * 假设面的数量没有限制，哲学家也能随便吃，不需要考虑吃不吃得下。
 * 设计一个进餐规则（并行算法）使得每个哲学家都不会挨饿；也就是说，在没有人知道别人什么时候想吃东西或思考的情况下，每个哲学家都可以在吃饭和思考之间一直交替下去。
 * https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/10/23/an_illustration_of_the_dining_philosophers_problem.png
 * 哲学家从0 到 4 按 顺时针 编号。请实现函数void wantsToEat(philosopher, pickLeftFork, pickRightFork, eat, putLeftFork, putRightFork)：
 * philosopher哲学家的编号。
 * pickLeftFork和pickRightFork表示拿起左边或右边的叉子。
 * eat表示吃面。
 * putLeftFork和putRightFork表示放下左边或右边的叉子。
 * 由于哲学家不是在吃面就是在想着啥时候吃面，所以思考这个方法没有对应的回调。
 * 给你 5 个线程，每个都代表一个哲学家，请你使用类的同一个对象来模拟这个过程。在最后一次调用结束之前，可能会为同一个哲学家多次调用该函数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-dining-philosophers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question1226 extends MultiThreadQuestion {
    // 0表示叉子未使用
    private AtomicInteger forkNo = new AtomicInteger(0);
    // 叉子编号 00001 00010 00100 01000 10000
    private int[] forkMask = new int[]{1, 2, 4, 8, 16};
    // 同时竞争叉子人数必须在4个以内
    private Semaphore eatLimit = new Semaphore(4);

    public Question1226() {
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        //开始竞争forkMask中的叉子
        int leftMask = forkMask[(philosopher + 1) % 5], rightMask = forkMask[philosopher];
        // 消耗一个竞争名额，开始竞争叉子
        eatLimit.acquire();

        // 等左边叉子释放
        while (!pickFork(leftMask)) {
            Thread.yield();
        }
        // 等右边叉子释放
        while (!pickFork(rightMask)) {
            Thread.yield();
        }

        pickLeftFork.run();
        pickRightFork.run();
        // 拿到叉子开始吃面
        eat.run();
        // 放下叉子
        putLeftFork.run();
        putRightFork.run();

        // 把叉子设置为未使用状态
        while (!putFork(leftMask)){
            Thread.yield();
        }
        while (!putFork(rightMask)){
            Thread.yield();
        }
        // 竞争结束，释放名额
        eatLimit.release();
    }

    private boolean pickFork(int mask) {
        int e = forkNo.get();
        // 更新mask编号的叉子状态
        return (e & mask) <= 0 && forkNo.compareAndSet(e, e ^ mask);
    }

    private boolean putFork(int mask){
        int e = forkNo.get();
        return forkNo.compareAndSet(e,e^mask);
    }
}

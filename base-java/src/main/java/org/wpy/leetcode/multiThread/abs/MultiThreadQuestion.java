package org.wpy.leetcode.multiThread.abs;

public abstract class MultiThreadQuestion {
    private Thread threadA;
    private Thread threadB;
    private Thread threadC;
    private Thread threadD;
    private Thread threadE;

    public MultiThreadQuestion() {
        this.threadA = new Thread(this::runA);
        this.threadB = new Thread(this::runB);
        this.threadC = new Thread(this::runC);
        this.threadD = new Thread(this::runD);
        this.threadE = new Thread(this::runE);
    }

    protected void runA() {
    }

    protected void runB() {
    }

    protected void runC() {
    }

    protected void runD(){
    }

    protected void runE(){
    }

    public void startThreadAndSleep(int millis) {
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        threadE.start();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

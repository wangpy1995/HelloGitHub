package org.wpy;

public class InnerClass {

    private Long getZ() {
        return Inner.z;
    }

    private static class Inner {
        static Long z;
        static {
            System.out.println("xxxxxxxx");
            z = 10000L;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final InnerClass ic = new InnerClass();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(ic.getZ());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(ic.getZ());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        Thread.sleep(2000);
    }
}

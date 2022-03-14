package jc;

/**
 * @author pengmf
 * @since 2022/2/16
 */
public class Test1 {


    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 10; i++) {
            Common common = new Common();

            Thread x = new Thread(common, "x");
            Thread y = new Thread(common, "y");

            x.start();
            y.start();

            x.join();
            y.join();

            System.out.println(common.count);
            System.out.println(common.hashCode());


        }
    }


    static class Common implements Runnable {
        int count = 0;

        @Override
        public void run() {
            if (Thread.currentThread().getName().equals("x")) {
                for (int i = 0; i < 500000; i++) {
                    synchronized (this) {
                        count++;
                    }

                }
            } else {
                for (int i = 0; i < 500000; i++) {
                    synchronized (this) {
                        count--;
                    }
                }
            }

        }
    }

}

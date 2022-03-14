package jc;

/**
 * @author pengmf
 * @since 2022/2/16
 */
public class Test3 {

    static int count;
    static final Object lock = new Object();

    public static void main(String[] args) {


        synchronized (lock) {
            count++;
        }


    }
}

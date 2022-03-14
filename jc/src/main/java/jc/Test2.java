package jc;

/**
 * @author pengmf
 * @since 2022/2/16
 */
public class Test2 {

    public static void main(String[] args) throws Exception {

        Room room = new Room();
        Thread t1 = new Thread(room::deCreate);
        Thread t2 = new Thread(room::inCreate);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        room.printCount();


    }


    static class Room {
        private int count;


        public synchronized void deCreate() {
            for (int i = 0; i < 500000; i++) {
                count++;
            }
        }

        public synchronized void inCreate() {
            for (int i = 0; i < 500000; i++) {
                count--;
            }
        }

        public synchronized void printCount() {
            System.out.println(count);
        }

    }
}

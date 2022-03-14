package aiedu.nacos.jvm;

/**
 * @author pengmf
 * @since 2022/1/14
 */
public class Main1 {


    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        System.out.println(dg(20));
    }


    public static long dg(long num) {
        if (num == 1) {
            return 1;
        }
        return num * dg(num - 1);
    }
}

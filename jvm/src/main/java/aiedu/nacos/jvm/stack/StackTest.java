package aiedu.nacos.jvm.stack;

/**
 * @author pengmf
 * @since 2022/1/14
 */

import java.util.Properties;

/**
 * 栈内存设置
 * -Xss256k
 */
public class StackTest {

    private static long count;

    public static void main(String[] args) {

        Properties properties = System.getProperties();
        System.out.println(properties);
        try {
            dzj();
        } catch (StackOverflowError e) {
            System.out.println(count);
        }

    }

    static void dzj() {
        count++;
        dzj();
    }
}

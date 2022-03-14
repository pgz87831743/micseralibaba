package aiedu.nacos.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengmf
 * @since 2022/1/14
 */
public class HeapTest {


    public static void main(String[] args) {


        int i = 0;


        List<String> list = new ArrayList<>();
        String a = "123";
        while (true) {

            list.add(a);
        }


    }
}

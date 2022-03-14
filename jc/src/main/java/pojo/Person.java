package pojo;

import lombok.Data;

/**
 * @author pengmf
 * @since 2022/3/4
 */
@Data
public class Person {
    private String id = "俺还是分开";
    private Integer age;
    private boolean isOld;
}

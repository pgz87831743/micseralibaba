package aiedu.nacos.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author pengmf
 * @since 2022/1/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private String msg;
    private int status;
    private T data;


    public static <T> Result<T> success(T data) {
        return new Result<T>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), data);
    }

    public static <T> Result<T> success(T data, HttpStatus status) {
        return new Result<T>(status.getReasonPhrase(), status.value(), data);
    }

    public static <T> Result<T> success(T data, HttpStatus status, String msg) {
        return new Result<T>(msg, status.value(), data);
    }

    public static <T> Result<T> success() {
        return new Result<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), null);
    }


    public static <T> Result<T> fail(T data) {
        return new Result<T>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value(), data);
    }

    public static <T> Result<T> fail(T data, HttpStatus status) {
        return new Result<T>(status.getReasonPhrase(), status.value(), data);
    }

    public static <T> Result<T> fail(T data, HttpStatus status, String msg) {
        return new Result<T>(msg, status.value(), data);
    }

    public static <T> Result<T> fail() {
        return new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
    }
}

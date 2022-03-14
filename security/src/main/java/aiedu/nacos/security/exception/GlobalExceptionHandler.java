package aiedu.nacos.security.exception;

import aiedu.nacos.common.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author pengmf
 * @since 2022/1/14
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public Result<String> exception(RuntimeException e) {
        return Result.fail(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

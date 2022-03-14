package aiedu.nacos.security.util;

import aiedu.nacos.common.result.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pengmf
 * @since 2022/1/14
 */
public class ResponseUtils {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void success(HttpServletResponse response, Object data, HttpStatus status, String msg) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            OBJECT_MAPPER.writeValue(response.getOutputStream(), Result.success(data, status, msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void fail(HttpServletResponse response, HttpStatus status, String msg) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            OBJECT_MAPPER.writeValue(response.getOutputStream(), Result.success(null, status, msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

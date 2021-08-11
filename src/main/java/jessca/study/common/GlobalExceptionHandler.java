package jessca.study.common;

import io.jsonwebtoken.JwtException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(1)
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = JwtException.class)
    public Result handler (JwtException runtimeException){
        System.out.println("error");
        return Result.fail(runtimeException.getMessage());
    }
}

package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 1.自定义异常，可继承Throwable或Exception或RuntimeException。一般选择继承Exception或RuntimeException。
 * 2.如果不要求调用者一定要处理抛出的异常，就继承RuntimeException。
 * 3.如果要求调用者一定要处理抛出的异常，就继承Exception。
 * @Author chenshoukai
 * @Date 2020/07/21 8:55
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}

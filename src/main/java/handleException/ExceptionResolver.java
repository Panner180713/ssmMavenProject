package handleException;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 1.SpringMvc通过HandlerExceptionResolver处理程序的异常，包括Handler映射，数据绑定以及目标方法执行时发生的异常。
 * 2.处理异常的是使用@ExceptionHandler注解定义的方法。
 * 3.处理异常的方法的优先级问题：根据发生异常的最近的继承关系找到继承深度最浅的那个。比如发生NullPointerException，
 * 但是声明的异常有RuntimeException和Exception，则使用RuntimeException。
 * 4.若找不到Handler中的标注@ExceptionHandler的方法，会到标注@ControllerAdvice的类中寻找。
 * 5.在异常或者其父类中如果有@ResponseStatus注解，ExceptionHandlerExceptionResolver不会解析异常，
 * 会由ResponseStatusExceptionResolver解析。最后响应状态码给客户端。比如main.exception.UnauthorizedException
 * @Author chenshoukai
 * @Date 2020/07/21 8:45
 */
@ControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView handleException(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ModelAndView handleUnauthorizedException(){
        ModelAndView modelAndView = new ModelAndView("unauthorized");
        return modelAndView;
    }
}

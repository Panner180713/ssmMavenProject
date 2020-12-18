package aspect;

import annotation.MethodAnnotation;
import annotation.SecondMethodAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

/**
 * 可以在通知方法中声明一个JoinPoint类型的参数，以访问链接细节。如方法名称和参数值
 */
@Component
@Aspect
@Order(0) //指定切面优先级，值越小优先级越高
public class LogAspect {

    @Pointcut("execution(* service.impl.*.*(..))")
    private void pointCut(){}

    @Before("pointCut()")
    private void beforeInvoke(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println(methodName + " 方法的参数为：" + args);
    }

    @Around(value = "@within(org.springframework.stereotype.Controller) && @annotation(methodAnnotation) && @annotation(secondMethodAnnotation)")
    private Object aroundInvoke(ProceedingJoinPoint joinPoint, MethodAnnotation methodAnnotation, SecondMethodAnnotation secondMethodAnnotation) throws Throwable {
        System.out.println(joinPoint.getSignature().getName()+"开始执行了，参数为："+joinPoint.getArgs()+
                "，注解methodAnnotation的value值为："+methodAnnotation.value()+
                "，注解secondMethodAnnotation的value值为："+secondMethodAnnotation.value());
        //控制目标方法何时执行甚至是否执行
        Object object = joinPoint.proceed();
        System.out.println(joinPoint.getSignature().getName()+"执行完了。。。");
        //必须将执行结果返回，否则会抛出空指针异常
        return object;
    }

    /**
     * @Description: 无论连接点是正常返回还是抛出异常，后置通知都会执行
     * @Author: chenshoukai 
     * @Date: 2020/12/18 10:59
     **/
    @After(value = "@within(org.springframework.stereotype.Controller) && @annotation(methodAnnotation)")
    private void afterInvoke(JoinPoint joinPoint, MethodAnnotation methodAnnotation){
        System.out.println(joinPoint.getSignature().getName()+"执行完了,注解methodAnnotation的value为："+methodAnnotation.value());
    }

    /**
     * @Description: 如果只对某种特殊类型的异常感兴趣，可以将参数声明为这种异常的类型。然后通知就只在抛出这个类型及其子类型的异常时才被执行
     * @Author: chenshoukai
     * @Date: 2020/12/18 11:06
     **/
    @AfterThrowing(throwing = "ex",value = "pointCut()")
    private void handleException(JoinPoint joinPoint,Exception ex){
        System.out.println("出现异常:" + ex.getMessage());
    }

    /**
     * 只在连接点正常返回的时候执行
     * @within定位目标类，@annotation定位目标方法
     * @param object
     * @param methodAnnotation
     */
    @AfterReturning(returning = "object",value = "@within(org.springframework.stereotype.Controller) && @annotation(methodAnnotation)")
    private void testAfterReturning(JoinPoint joinPoint,Object object, MethodAnnotation methodAnnotation){
        System.out.println("afterReturning：" + methodAnnotation.value());
        ModelAndView modelAndView = (ModelAndView) object;
        System.out.println(modelAndView.getViewName());
    }
}

package function;

/**
 * @Author chenshoukai
 * @Date 2020/06/22 22:39
 */
@FunctionalInterface
public interface MyFunctionalInterface2<T, R> {

    /**
     * 根据不同规则计算返回值
     * @param t1
     * @param t2
     * @return
     */
    R getValue(T t1, T t2);
}

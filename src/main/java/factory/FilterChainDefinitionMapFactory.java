package factory;

import java.util.LinkedHashMap;

/**
 * @Author chenshoukai
 * @Date 2020/05/31 22:42
 */
public class FilterChainDefinitionMapFactory {

    /**
     * url模式使用ant风格，第一次匹配优先
     **/
    public LinkedHashMap<String,String> buildFilterBeanDefinitionMap(){
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("/static/**","anon");
        linkedHashMap.put("/jsp/login.jsp","anon");
        linkedHashMap.put("/jsp/animate.jsp","anon");
        linkedHashMap.put("/testShiro/shiroLogin","anon");
        linkedHashMap.put("/testShiro/logout","logout");
        linkedHashMap.put("/testShiro/jumpToListPage","anon");
        linkedHashMap.put("/**","authc");
        return linkedHashMap;
    }
}

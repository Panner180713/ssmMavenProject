package javaTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author chenshoukai
 * @Date 2020/06/24 15:16
 */
public class TestMap {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        map.put("key5","value5");

//        Set<String> strings = map.keySet();
//        for (String string : strings) {
//            System.out.println(map.get(string));
//        }

        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
//        System.out.println(map.containsValue("value6"));

        map.put("success",true);

        if((Boolean) map.get("success")){
            System.out.println("测试成功");
        }
    }
}

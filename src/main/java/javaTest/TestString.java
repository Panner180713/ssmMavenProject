package javaTest;

import javaBean.Employee;
import javaBean.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author chenshoukai
 * @Date 2020/07/02 10:43
 */
public class TestString {

    public static void stringSplit(){
        String s = "100,,101,";
        String [] array = s.split(",");
        System.out.println(array.length);
        for (String s1 : array) {
            System.out.println(s1);
        }
        System.out.println(array[2]);

        char[] charArray = {'a','b','c'};
        System.out.println(String.valueOf(charArray));
    }

    private static void setEmp(Employee emp){
        emp.setAge(12);
    }

    private static void testArrayParam(Class[] params){

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Employee employee = new Employee();
        employee.setAge(9);
        setEmp(employee);
        System.out.println(employee);

        Class employeeClass = Class.forName("javaBean.Employee");
        Constructor declaredConstructor = employeeClass.getDeclaredConstructor(int.class, String.class);
        Object tom = declaredConstructor.newInstance(100, "tom");
        Employee manager = (Employee) tom;
    }
}

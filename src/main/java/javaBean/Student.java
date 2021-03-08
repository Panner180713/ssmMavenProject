package javaBean;

import java.io.Serializable;

/**
 * 如果定义的类是带泛型的，建议在实例化时指明类的泛型。
 * 如果实例化时没有指明类的泛型，默认此泛型类型为Object类型
 * @author chenshoukai
 * @create 2019-08-06 10:35
 */
public class Student<K,V> implements Serializable {

    private K name;

    private V age;

    private K address;

    private V classId;

    private V studentId;

    public Student() {
    }

    public Student(K name, V age, K address, V classId) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.classId = classId;
    }

    public K getName() {
        return name;
    }

    public void setName(K name) {
        this.name = name;
    }

    public V getAge() {
        return age;
    }

    public void setAge(V age) {
        this.age = age;
    }

    public K getAddress() {
        return address;
    }

    public void setAddress(K address) {
        this.address = address;
    }

    public V getClassId() {
        return classId;
    }

    public void setClassId(V classId) {
        this.classId = classId;
    }

    public V getStudentId() {
        return studentId;
    }

    public void setStudentId(V studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", classId=" + classId +
                ", studentId=" + studentId +
                '}';
    }
}

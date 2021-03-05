package javaBean;

/**
 * @Author chenshoukai
 * @Date 2020/12/03 14:38
 */
public class Person implements Comparable<Person>{

    private String height;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public int compareTo(Person o) {
        return -this.height.compareTo(o.getHeight());
    }

    @Override
    public String toString() {
        return "Person{" +
                "height='" + height + '\'' +
                '}';
    }
}

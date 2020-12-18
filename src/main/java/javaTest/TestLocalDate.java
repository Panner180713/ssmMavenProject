package javaTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @Author chenshoukai
 * @Date 2020/07/29 10:35
 */
public class TestLocalDate {

    public static void main(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = LocalDate.parse("2017-09-01",fmt);
        LocalDate end = LocalDate.parse("2017-10-02",fmt);
        LocalDate today = LocalDate.now();
        String age = String.valueOf(ChronoUnit.DAYS.between(birthday, end));
        System.out.println(age);
    }
}

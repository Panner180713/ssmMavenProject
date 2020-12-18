package service;

import javaBean.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author chenshoukai
 * @create 2019-08-06 10:15
 */
@Service
public interface StudentService {

    List<Student> selectStudent(int ifAll);

    Map<String,String> findOneStudent(String name);

    Student findStudentInfoByName(String name);

    List<Student> selectStudentByName(Map<String,Object> map);

    void addStudent(Student student);

    void addStudentList(List<Student> students);

    void updateStudentInfo(Student student);
}
